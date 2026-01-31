package com.auth.auth_service.service.impl;

import com.auth.auth_service.dto.ProfilAdminDTO;
import com.auth.auth_service.dto.ProfilCandidatDTO;
import com.auth.auth_service.dto.TelephoneDTO;
import com.auth.auth_service.entity.ProfilsAdmin;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.entity.TelephonesCandidat;
import com.auth.auth_service.entity.Utilisateur;
import com.auth.auth_service.repository.ProfilsAdminRepository;
import com.auth.auth_service.service.FileStorageService;
import com.auth.auth_service.service.interfaces.ProfilAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfilAdminServiceImpl implements ProfilAdminService {
    private final ProfilsAdminRepository repo;
    private final FileStorageService fileStorageService;

    @Override
    public ProfilsAdmin create(ProfilAdminDTO dto, Utilisateur u, MultipartFile photo)
    {
        ProfilsAdmin profil = new ProfilsAdmin();
        profil.setUtilisateur(u);
        profil.setSuperAdmin(dto.superAdmin());
        profil.setNom(dto.nom());
        profil.setPrenom(dto.prenom());

        MultipartFile newPhoto = photo;
        if (newPhoto != null && !newPhoto.isEmpty()) {
            String newFileName = fileStorageService.storeFile(newPhoto, FileStorageService.PHOTOS_DIR, FileStorageService.IMAGE_TYPES);
            profil.setPhoto(newFileName);
        }

        repo.save(profil);

        return profil;
    }

    @Override
    public ProfilsAdmin update(ProfilAdminDTO dto, ProfilsAdmin admin, Optional<MultipartFile> photo){
        admin.setNom(dto.nom());
        admin.setPrenom(dto.prenom());

        if (photo != null && photo.isPresent()) {
            MultipartFile newPhoto = photo.get();
            if (newPhoto != null && !newPhoto.isEmpty()) {
                String oldFileName = admin.getPhoto();
                if (oldFileName != null && !oldFileName.isBlank()) {
                    fileStorageService.removeFile(oldFileName, FileStorageService.PHOTOS_DIR);
                }
                String newFileName = fileStorageService.storeFile(
                        newPhoto,
                        FileStorageService.PHOTOS_DIR,
                        FileStorageService.IMAGE_TYPES
                );
                admin.setPhoto(newFileName);
            }
        }

        repo.save(admin);

        return admin;
    };

    @Override
    public ProfilsAdmin findById(int id){
        return repo.findById(id).get();
    };

    @Override
    public List<ProfilsAdmin> findAll(){
        return repo.findAll();
    };

    @Override
    public void delete(ProfilsAdmin p){
        repo.delete(p);
    };

    @Override
    public void updateProfil(ProfilsAdmin admin,  MultipartFile photo)
    {
        MultipartFile newPhoto = photo;
        if (newPhoto != null && !newPhoto.isEmpty()) {
            String oldFileName = admin.getPhoto();
            if (oldFileName != null && !oldFileName.isBlank()) {
                fileStorageService.removeFile(oldFileName, FileStorageService.PHOTOS_DIR);
            }
            String newFileName = fileStorageService.storeFile(
                    newPhoto,
                    FileStorageService.PHOTOS_DIR,
                    FileStorageService.IMAGE_TYPES
            );
            admin.setPhoto(newFileName);
            repo.save(admin);
        }
    }
}
