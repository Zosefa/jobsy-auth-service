package com.auth.auth_service.dto;

import org.springframework.web.multipart.MultipartFile;

public record ProfilAdminDTO(
    String nom,
    String prenom,
    Boolean superAdmin
) {}
