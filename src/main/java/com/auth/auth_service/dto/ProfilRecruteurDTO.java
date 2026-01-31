package com.auth.auth_service.dto;

import java.util.List;

public record ProfilRecruteurDTO(
        Integer entrepriseId,              // rejoindre entreprise existante (option 1)
        EntrepriseDTO entreprise,          // créer une entreprise (option 2)

        String fonction,
        Boolean verifie,
        List<TelephoneDTO> telephones
) {}
