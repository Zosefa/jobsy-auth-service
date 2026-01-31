package com.auth.auth_service.dto;

import com.auth.auth_service.entity.Utilisateur.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @Email String email,
        @NotBlank String password,
        UserRole role,
        ProfilCandidatDTO profilCandidat,
        ProfilRecruteurDTO profilRecruteur,
        ProfilAdminDTO profilAdmin
) {}