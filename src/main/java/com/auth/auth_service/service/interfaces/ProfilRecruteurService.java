package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.dto.ProfilRecruteurDTO;
import com.auth.auth_service.entity.ProfilsRecruteur;
import com.auth.auth_service.entity.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProfilRecruteurService {
    ProfilsRecruteur create(ProfilRecruteurDTO dto, Utilisateur u, MultipartFile photo);
    ProfilsRecruteur update(ProfilRecruteurDTO dto, ProfilsRecruteur p, Optional<MultipartFile> photo);
    ProfilsRecruteur findById(int id);
    List<ProfilsRecruteur> findAll();
    void delete(ProfilsRecruteur p);
    void updateProfil(ProfilsRecruteur recruteur, MultipartFile photo);
}
