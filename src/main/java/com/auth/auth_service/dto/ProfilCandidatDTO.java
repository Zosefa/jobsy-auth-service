package com.auth.auth_service.dto;

import java.util.List;

public record ProfilCandidatDTO(
        String nom,
        String prenom,

        Integer paysId,
        String ville,
        String adresse,

        String resume,
        Integer anneesExperience,
        Boolean profilCompleted,
        List<TelephoneDTO> telephones
) {}
