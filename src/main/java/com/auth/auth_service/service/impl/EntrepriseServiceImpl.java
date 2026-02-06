package com.auth.auth_service.service.impl;

import com.auth.auth_service.entity.Entreprise;
import com.auth.auth_service.repository.EntrepriseRepository;
import com.auth.auth_service.service.interfaces.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntrepriseServiceImpl implements EntrepriseService {
    private final EntrepriseRepository repo;

    @Override
    public List<Entreprise> findAll()
    {
        return repo.findAll();
    }

    @Override
    public Optional<Entreprise> findById(int id)
    {
        return repo.findById(id);
    }
}
