package com.auth.auth_service.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.auth.auth_service.dto.ProfilCandidatDTO;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.entity.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilCandidatService {
    ProfilsCandidat create(ProfilCandidatDTO dro, Utilisateur u, MultipartFile photo);
    ProfilsCandidat update(ProfilCandidatDTO dto, ProfilsCandidat p, Optional<MultipartFile> photo);
    ProfilsCandidat findById(int id);
    List<ProfilsCandidat> findAll();
    void delete(ProfilsCandidat p);
    void updateProfil(ProfilsCandidat candidat,  MultipartFile photo);
}
