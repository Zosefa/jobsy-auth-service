package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.entity.Entreprise;

import java.util.List;
import java.util.Optional;

public interface EntrepriseService {
    List<Entreprise> findAll();
    Optional<Entreprise> findById(int id);
}