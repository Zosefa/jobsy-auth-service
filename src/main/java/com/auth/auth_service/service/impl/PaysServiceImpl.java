package com.auth.auth_service.service.impl;

import com.auth.auth_service.entity.Pays;
import com.auth.auth_service.repository.PaysRepository;
import com.auth.auth_service.service.interfaces.PaysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaysServiceImpl implements PaysService {
    private final PaysRepository repo;

    @Override
    public List<Pays> findAll()
    {
        return repo.findAll();
    }

    @Override
    public Optional<Pays> findById(int id)
    {
        return repo.findById(id);
    }
}
