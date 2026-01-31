package com.auth.auth_service.dto;

import com.auth.auth_service.entity.Utilisateur;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInfoDTO(
        Integer id,
        String email,
        Utilisateur.UserRole role,
        @JsonProperty("isActive")
        boolean isActive,
        Object profil
) {}
