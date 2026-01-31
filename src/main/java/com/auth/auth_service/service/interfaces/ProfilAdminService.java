package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.dto.ProfilAdminDTO;
import com.auth.auth_service.dto.ProfilCandidatDTO;
import com.auth.auth_service.entity.ProfilsAdmin;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.entity.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProfilAdminService {
    ProfilsAdmin create(ProfilAdminDTO DTO, Utilisateur u, MultipartFile photo);
    ProfilsAdmin update(ProfilAdminDTO dto, ProfilsAdmin p, Optional<MultipartFile> photo);
    ProfilsAdmin findById(int id);
    List<ProfilsAdmin> findAll();
    void delete(ProfilsAdmin p);
    void updateProfil(ProfilsAdmin admin,  MultipartFile photo);
}
