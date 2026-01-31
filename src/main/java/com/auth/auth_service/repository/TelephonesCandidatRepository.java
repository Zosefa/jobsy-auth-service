package com.auth.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.auth_service.entity.TelephonesCandidat;

@Repository
public interface TelephonesCandidatRepository extends JpaRepository<TelephonesCandidat, Integer> {
    TelephonesCandidat findByTelephone(String telephone);
}

