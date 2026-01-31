package com.auth.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.auth_service.entity.TelephonesRecruteur;

@Repository
public interface TelephonesRecruteurRepository extends JpaRepository<TelephonesRecruteur, Integer> {}

