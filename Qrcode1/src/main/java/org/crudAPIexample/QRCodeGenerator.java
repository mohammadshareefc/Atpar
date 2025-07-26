package org.crudAPIexample;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    /**
     * Generates a QR code for a given text (link) and saves it as a PNG image.
     *
     * @param text The text (e.g., URL) to encode in the QR code.
     * @param width The width of the QR code image in pixels.
     * @param height The height of the QR code image in pixels.
     * @param filePath The full path where the QR code image will be saved (e.g., "C:/qrcodes/my_qr_code.png").
     * @throws WriterException If there is an error during QR code encoding.
     * @throws IOException If there is an error writing the image file.
     */
    public static void generateQRCode(String text, int width, int height, String filePath)
            throws WriterException, IOException {

        // Configure QR code hints (e.g., error correction level)
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // L = Low, M = Medium, Q = Quartile, H = High
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8"); // Ensure proper character encoding

        // Create a QR code writer instance
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // Encode the text into a BitMatrix (the QR code's raw data)
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        // Define the file path for the output image
        Path path = FileSystems.getDefault().getPath(filePath);

        // Write the BitMatrix to a PNG image file
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        System.out.println("QR Code generated successfully at: " + filePath);
    }

    public static void main(String[] args) {
        String linkToEncode = "https://play.google.com/store/apps/details?id=com.spandana.development&pcampaignid=web_share"; // The link you want to encode
        int qrCodeWidth = 300; // Width of the QR code image
        int qrCodeHeight = 300; // Height of the QR code image
        String outputFilePath = "Spandanahospitaldmm.png"; // Name of the output file

        try {
            generateQRCode(linkToEncode, qrCodeWidth, qrCodeHeight, outputFilePath);
        } catch (WriterException e) {
            System.err.println("Error encoding QR code: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing QR code image: " + e.getMessage());
        }
    }
}
