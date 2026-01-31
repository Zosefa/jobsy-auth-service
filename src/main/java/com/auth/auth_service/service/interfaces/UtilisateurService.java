package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.entity.Utilisateur;
import com.auth.auth_service.dto.UtilisateurDTO;

public interface UtilisateurService {
    boolean existsByEmail(String email);
    Utilisateur findById(int id);
    Utilisateur create(UtilisateurDTO dto);
    Utilisateur update(Utilisateur u, UtilisateurDTO dto);
    Utilisateur active(Utilisateur u, boolean etat);
    void delete(Utilisateur u);
}
