package com.example.Crud.API.repository;

import com.example.Crud.API.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Studentrepo extends JpaRepository<Students, Integer> {
}
