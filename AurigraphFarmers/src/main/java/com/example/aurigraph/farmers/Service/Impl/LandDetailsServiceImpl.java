package com.example.aurigraph.farmers.Service.Impl;

import com.example.aurigraph.farmers.DTO.CompleteLandDetailsDTO;
import com.example.aurigraph.farmers.Domain.*;
import com.example.aurigraph.farmers.Mapping.LandDetailsMapping;
import com.example.aurigraph.farmers.Repository.LandDetailsRepository;
import com.example.aurigraph.farmers.Repository.LandOwnerRepository;
import com.example.aurigraph.farmers.Security.SecurityUtils;
import com.example.aurigraph.farmers.Service.LandDetailsLandOwnersService;
import com.example.aurigraph.farmers.Service.LandDetailsService;
import com.example.aurigraph.farmers.Service.PropertyDetailsService;
import com.example.aurigraph.farmers.Service.WitnessService;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LandDetailsServiceImpl implements LandDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(LandDetailsServiceImpl.class);

    private final LandDetailsRepository landDetailsRepository;
    private final LandOwnerRepository landOwnerRepository;
    private final LandDetailsLandOwnersService landDetailsLandOwnersService;
    private final LandDetailsMapping landDetailsMapping;
    private final PropertyDetailsService propertyDetailsService;
    private final WitnessService witnessService;

    public LandDetailsServiceImpl(LandDetailsRepository landDetailsRepository,
                                  LandOwnerRepository landOwnerRepository,
                                  LandDetailsLandOwnersService landDetailsLandOwnersService,
                                  LandDetailsMapping landDetailsMapping,
                                  PropertyDetailsService propertyDetailsService,
                                  WitnessService witnessService) {
        this.landDetailsRepository = landDetailsRepository;
        this.landOwnerRepository = landOwnerRepository;
        this.landDetailsLandOwnersService = landDetailsLandOwnersService;
        this.landDetailsMapping = landDetailsMapping;
        this.propertyDetailsService = propertyDetailsService;
        this.witnessService = witnessService;
    }

    @Override
    public List<CompleteLandDetailsDTO> findAll() {
        logger.info("Fetching all land details");
        List<LandDetails> landDetails = (List<LandDetails>) landDetailsRepository.findAll();
        logger.debug("Found {} land details", landDetails.size());

        List<CompleteLandDetailsDTO> completeLandDetails = new ArrayList<>();
        for (LandDetails landDetail : landDetails) {
            logger.debug("Processing land detail with ID: {}", landDetail.getId());

            CompleteLandDetailsDTO completeLandDetailsDTO = landDetailsMapping.domainToDTO(landDetail);
            List<LandOwner> landOwners = new ArrayList<>();

            List<LandDetailsLandOwners> landDetailsLandOwners = landDetailsLandOwnersService.findByLandDetailsId(landDetail.getId());
            for (LandDetailsLandOwners landDetailsLandOwner : landDetailsLandOwners) {
                LandOwner landOwner = landOwnerRepository.findById(landDetailsLandOwner.getLandOwnerId()).orElse(null);
                if (landOwner != null) {
                    landOwners.add(landOwner);
                }
            }

            List<PropertyDetails> propertyDetails = propertyDetailsService.findByLandDetailsId(landDetail.getId());
            List<Witness> witnesses = witnessService.findByLandDetailsId(landDetail.getId());

            completeLandDetailsDTO.setLandOwners(landOwners);
            completeLandDetailsDTO.setPropertyDetails(propertyDetails);
            completeLandDetailsDTO.setWitnesses(witnesses);

            completeLandDetails.add(completeLandDetailsDTO);
        }

        logger.info("Completed fetching land details");
        return completeLandDetails;
    }

    @Override
    public CompleteLandDetailsDTO findById(Long id) {
        logger.info("Fetching land details with ID: {}", id);
        LandDetails landDetail = landDetailsRepository.findById(id).orElse(null);
        if (landDetail == null) {
            logger.warn("Land details not found for ID: {}", id);
            return null;
        }

        CompleteLandDetailsDTO completeLandDetailsDTO = landDetailsMapping.domainToDTO(landDetail);
        logger.debug("Mapping land detail with ID: {} to DTO", id);

        List<LandOwner> landOwners = new ArrayList<>();
        List<LandDetailsLandOwners> landDetailsLandOwners = landDetailsLandOwnersService.findByLandDetailsId(landDetail.getId());
        for (LandDetailsLandOwners landDetailsLandOwner : landDetailsLandOwners) {
            LandOwner landOwner = landOwnerRepository.findById(landDetailsLandOwner.getLandOwnerId()).orElse(null);
            if (landOwner != null) {
                landOwners.add(landOwner);
            }
        }
        List<PropertyDetails> propertyDetails = propertyDetailsService.findByLandDetailsId(landDetail.getId());
        List<Witness> witnesses = witnessService.findByLandDetailsId(landDetail.getId());

        completeLandDetailsDTO.setLandOwners(landOwners);
        completeLandDetailsDTO.setPropertyDetails(propertyDetails);
        completeLandDetailsDTO.setWitnesses(witnesses);
        logger.info("Completed fetching land details for ID: {}", id);
        return completeLandDetailsDTO;
    }

    @Override
    public CompleteLandDetailsDTO save(CompleteLandDetailsDTO completeLandDetailsDTO) {
        logger.info("Saving new land details");

        // Save LandDetails
        try {


        LandDetails landDetails = landDetailsMapping.dtoToDomain(completeLandDetailsDTO);
        String currentUser = SecurityUtils.getCurrentUserLogin();
        landDetails.setCreatedBy(currentUser);
        landDetails.setLastModifiedBy(currentUser);
        LandDetails savedLandDetails = landDetailsRepository.save(landDetails);
        logger.debug("Saved LandDetails with ID: {}", savedLandDetails.getId());

        // Save Land Owners
        List<LandOwner> savedLandOwners = new ArrayList<>();
        List<LandOwner> landOwners = completeLandDetailsDTO.getLandOwners();
        if (landOwners != null) {
            for (LandOwner landOwner : landOwners) {
                landOwner.setCreatedBy(currentUser);
                landOwner.setLastModifiedBy(currentUser);
                LandOwner savedLandOwner = landOwnerRepository.save(landOwner);
                savedLandOwners.add(savedLandOwner);
                logger.debug("Saved LandOwner with ID: {}", savedLandOwner.getId());

                LandDetailsLandOwners association = new LandDetailsLandOwners();
                association.setLandDetailsId(savedLandDetails.getId());
                association.setLandOwnerId(savedLandOwner.getId());
                association.setCreatedBy(currentUser);
                association.setLastModifiedBy(currentUser);
                landDetailsLandOwnersService.save(association);
            }
        }

        // Save Property Details
        List<PropertyDetails> savedPropertyDetails = new ArrayList<>();
        List<PropertyDetails> propertyDetails = completeLandDetailsDTO.getPropertyDetails();
        if (propertyDetails != null) {
            for (PropertyDetails propertyDetail : propertyDetails) {
                propertyDetail.setLandDetailsId(savedLandDetails.getId());
                propertyDetail.setCreatedBy(currentUser);
                propertyDetail.setLastModifiedBy(currentUser);
                propertyDetail = propertyDetailsService.save(propertyDetail);
                savedPropertyDetails.add(propertyDetail);
            }
        }

        // Save Witnesses
        List<Witness> savedWitnesses = new ArrayList<>();
        List<Witness> witnesses = completeLandDetailsDTO.getWitnesses();
        if (witnesses != null) {
            for (Witness witness : witnesses) {
                witness.setLandDetailsId(savedLandDetails.getId());
                witness.setCreatedBy(currentUser);
                witness.setLastModifiedBy(currentUser);
                witness = witnessService.save(witness);
                savedWitnesses.add(witness);
            }
        }

        logger.info("Successfully saved land details with ID: {}", savedLandDetails.getId());

        // Map back to DTO for response
            CompleteLandDetailsDTO responseDTO = landDetailsMapping.domainToDTO(savedLandDetails);
            responseDTO.setLandOwners(savedLandOwners);
            responseDTO.setPropertyDetails(savedPropertyDetails);
            responseDTO.setWitnesses(savedWitnesses);

            return responseDTO;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Optional<LandDetails> update(Long id, LandDetails updatedDetails) {
        logger.info("Updating land details for ID: {}", id);
        String currentUser = SecurityUtils.getCurrentUserLogin();
        return landDetailsRepository.findById(id)
                .map(existing -> {
                    if (updatedDetails.getAccountNumber() != null) {
                        existing.setAccountNumber(updatedDetails.getAccountNumber());
                    }
                    if (updatedDetails.getAccountHolder() != null) {
                        existing.setAccountHolder(updatedDetails.getAccountHolder());
                    }
                    if (updatedDetails.getDateCreated() != null) {
                        existing.setDateCreated(updatedDetails.getDateCreated());
                    }
                    if (updatedDetails.getIfscCode() != null) {
                        existing.setIfscCode(updatedDetails.getIfscCode());
                    }
                    if (updatedDetails.getSwiftCode() != null) {
                        existing.setSwiftCode(updatedDetails.getSwiftCode());
                    }
                    if (updatedDetails.getBank() != null) {
                        existing.setBank(updatedDetails.getBank());
                    }
                    if (updatedDetails.getBranch() != null) {
                        existing.setBranch(updatedDetails.getBranch());
                    }
                    if (updatedDetails.getAksmvbsMembershipNumber() != null) {
                        existing.setAksmvbsMembershipNumber(updatedDetails.getAksmvbsMembershipNumber());
                    }
                    if (updatedDetails.getGeoCoordinates() != null) {
                        existing.setGeoCoordinates(updatedDetails.getGeoCoordinates());
                    }

                    existing.setLastModifiedBy(currentUser);
                    logger.debug("Updated land details for ID: {}", id);
                    return landDetailsRepository.save(existing);
                });
    }


    @Override
    public List<CompleteLandDetailsDTO> findByUserId(Integer userId) {
        logger.info("Fetching all land details");


        List<LandDetails> landDetails =  landDetailsRepository.findLandDetailsByUserId(userId);
        logger.debug("Found {} land details", landDetails.size());

        List<CompleteLandDetailsDTO> completeLandDetails = new ArrayList<>();
        for (LandDetails landDetail : landDetails) {
            logger.debug("Processing land detail with ID: {}", landDetail.getId());

            CompleteLandDetailsDTO completeLandDetailsDTO = landDetailsMapping.domainToDTO(landDetail);
            List<LandOwner> landOwners = new ArrayList<>();

            List<LandDetailsLandOwners> landDetailsLandOwners = landDetailsLandOwnersService.findByLandDetailsId(landDetail.getId());
            for (LandDetailsLandOwners landDetailsLandOwner : landDetailsLandOwners) {
                LandOwner landOwner = landOwnerRepository.findById(landDetailsLandOwner.getLandOwnerId()).orElse(null);
                if (landOwner != null) {
                    landOwners.add(landOwner);
                }
            }

            List<PropertyDetails> propertyDetails = propertyDetailsService.findByLandDetailsId(landDetail.getId());
            List<Witness> witnesses = witnessService.findByLandDetailsId(landDetail.getId());

            completeLandDetailsDTO.setLandOwners(landOwners);
            completeLandDetailsDTO.setPropertyDetails(propertyDetails);
            completeLandDetailsDTO.setWitnesses(witnesses);

            completeLandDetails.add(completeLandDetailsDTO);
            logger.info("Completed fetching land details by User");

        }
        if(completeLandDetails.isEmpty()){
            return null;
        }
        return completeLandDetails;
    }
    @Override
    public boolean delete(Long id) {
        logger.info("Deleting land details with ID: {}", id);
        if (landDetailsRepository.existsById(id)) {
            landDetailsRepository.deleteById(id);
            logger.info("Successfully deleted land details with ID: {}", id);
            return true;
        } else {
            logger.warn("Land details not found for ID: {}", id);
            return false;
        }
    }
}
