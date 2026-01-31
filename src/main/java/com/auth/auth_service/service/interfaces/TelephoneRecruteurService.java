package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.dto.TelephoneDTO;
import com.auth.auth_service.entity.ProfilsRecruteur;
import com.auth.auth_service.entity.TelephonesRecruteur;

public interface TelephoneRecruteurService {
    TelephonesRecruteur create(TelephoneDTO dto, ProfilsRecruteur recruteur);
}
