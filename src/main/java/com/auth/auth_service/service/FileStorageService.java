package com.auth.auth_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageService {

    // Constantes pour les dossiers
    public static final String LOGOS_DIR = "uploads/logos";
    public static final String PHOTOS_DIR = "uploads/photos";
    public static final String CVS_DIR = "uploads/cvs";

    // Types autorisés
    public static final Set<String> IMAGE_TYPES = Set.of(
            "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    );

    public static final Set<String> PDF_TYPES = Set.of(
            "application/pdf"
    );

    /**
     * Sauvegarde un fichier dans un dossier spécifique et retourne le nom généré.
     * @param file le fichier à uploader
     * @param uploadDir le dossier où stocker le fichier
     * @param allowedTypes types MIME autorisés
     * @return nom du fichier généré
     */
    public String storeFile(MultipartFile file, String uploadDir, Set<String> allowedTypes) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Le fichier est vide.");
            }

            // Vérifier le type
            String contentType = file.getContentType();
            if (contentType == null || !allowedTypes.contains(contentType)) {
                throw new RuntimeException("Type de fichier non autorisé : " + contentType);
            }

            // Créer le dossier si nécessaire
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Générer un nom unique
            String originalFilename = file.getOriginalFilename();
            String extension = getExtension(originalFilename);
            String fileName = UUID.randomUUID() + extension;

            // Sauvegarder le fichier
            Path filePath = path.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'upload du fichier", e);
        }
    }

    // Supprime un fichier existant
    public void removeFile(String fileName, String uploadDir) {
        if (fileName != null && !fileName.isBlank()) {
            Path filePath = Paths.get(uploadDir).resolve(fileName);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                System.err.println("Impossible de supprimer le fichier : " + e.getMessage());
            }
        }
    }

    // Récupère l'extension du fichier
    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return ".dat";
        }
        return filename.substring(filename.lastIndexOf("."));
    }

    // Méthodes utilitaires pour chaque type de fichier
    public String storeLogo(MultipartFile file) {
        return storeFile(file, LOGOS_DIR, IMAGE_TYPES);
    }

    public String storePhoto(MultipartFile file) {
        return storeFile(file, PHOTOS_DIR, IMAGE_TYPES);
    }

    public String storeCV(MultipartFile file) {
        Set<String> allowed = new HashSet<>();
        allowed.addAll(IMAGE_TYPES); // si tu veux autoriser images aussi
        allowed.addAll(PDF_TYPES);
        return storeFile(file, CVS_DIR, allowed);
    }
}
