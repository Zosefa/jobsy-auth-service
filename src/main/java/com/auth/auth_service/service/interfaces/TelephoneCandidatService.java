package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.dto.TelephoneDTO;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.entity.TelephonesCandidat;

public interface TelephoneCandidatService {
    TelephonesCandidat create(TelephoneDTO dto, ProfilsCandidat candidat);
    TelephonesCandidat update(TelephonesCandidat tel, TelephoneDTO dto, ProfilsCandidat candidat);
    TelephonesCandidat findByTelephone(String telephone);
}
