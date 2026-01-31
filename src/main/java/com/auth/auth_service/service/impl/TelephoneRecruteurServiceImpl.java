package com.auth.auth_service.service.impl;

import com.auth.auth_service.dto.TelephoneDTO;
import com.auth.auth_service.entity.ProfilsRecruteur;
import com.auth.auth_service.entity.TelephonesRecruteur;
import com.auth.auth_service.repository.TelephonesRecruteurRepository;
import com.auth.auth_service.service.interfaces.TelephoneRecruteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelephoneRecruteurServiceImpl implements TelephoneRecruteurService {
    private final TelephonesRecruteurRepository repo;

    public TelephonesRecruteur create(TelephoneDTO dto, ProfilsRecruteur recruteur) {
        TelephonesRecruteur telephone = new TelephonesRecruteur();

        telephone.setTelephone(dto.telephone());
        telephone.setIsPhonePrincipal(dto.isPhonePrincipal());
        telephone.setRecruteur(recruteur);

        return telephone;
    }

}
