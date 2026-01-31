package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.entity.Pays;

import java.util.List;
import java.util.Optional;

public interface PaysService {
    List<Pays> findAll();
    Optional<Pays> findById(int id);
}
