package com.auth.auth_service.service.impl;

import com.auth.auth_service.service.interfaces.UtilisateurService;
import org.springframework.stereotype.Service;

import com.auth.auth_service.repository.UtilisateurRepository;
import com.auth.auth_service.entity.Utilisateur;
import com.auth.auth_service.dto.UtilisateurDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur create(UtilisateurDTO dto){
        Utilisateur u = new Utilisateur();
        u.setEmail(dto.email());
        u.setPassword(passwordEncoder.encode(dto.password()));
        u.setRole(dto.role());

        utilisateurRepository.save(u);
        return u;
    }

    @Override
    public Utilisateur update(Utilisateur u, UtilisateurDTO dto){
        u.setEmail(dto.email());
        u.setPassword(passwordEncoder.encode(dto.password()));
        u.setRole(dto.role());

        utilisateurRepository.save(u);
        return u;
    }

    @Override
    public boolean existsByEmail(String email){
        return utilisateurRepository.existsByEmail(email);
    }

    @Override
    public Utilisateur findById(int id){
        return utilisateurRepository.findById(id).get();
    }

    @Override
    public Utilisateur active(Utilisateur u, boolean active){
        u.setActive(active);
        utilisateurRepository.save(u);
        return u;
    }

    @Override
    public void delete(Utilisateur u){
        utilisateurRepository.delete(u);
    }
}
