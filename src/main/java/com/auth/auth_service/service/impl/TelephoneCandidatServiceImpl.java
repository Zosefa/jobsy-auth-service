package com.auth.auth_service.service.impl;

import com.auth.auth_service.dto.TelephoneDTO;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.entity.TelephonesCandidat;
import com.auth.auth_service.repository.TelephonesCandidatRepository;
import com.auth.auth_service.service.interfaces.TelephoneCandidatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelephoneCandidatServiceImpl implements TelephoneCandidatService {

    private final TelephonesCandidatRepository repo;

    public TelephonesCandidat create(TelephoneDTO dto, ProfilsCandidat candidat) {
        TelephonesCandidat telephone = new TelephonesCandidat();

        telephone.setTelephone(dto.telephone());
        telephone.setIsPhonePrincipal(dto.isPhonePrincipal());
        telephone.setCandidat(candidat);

        return telephone;
    }

    public TelephonesCandidat update(TelephonesCandidat tel, TelephoneDTO dto, ProfilsCandidat candidat){
        return tel;
    }

    public TelephonesCandidat findByTelephone(String telephone)
    {
        return repo.findByTelephone(telephone);
    }

}
