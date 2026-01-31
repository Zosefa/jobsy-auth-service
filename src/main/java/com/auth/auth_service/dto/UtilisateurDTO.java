package com.auth.auth_service.dto;

import com.auth.auth_service.entity.Utilisateur.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UtilisateurDTO(
        @Email String email,
        @NotBlank String password,
        UserRole role
) {}