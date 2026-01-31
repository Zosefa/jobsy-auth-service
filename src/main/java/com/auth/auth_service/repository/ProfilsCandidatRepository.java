package com.auth.auth_service.repository;

import com.auth.auth_service.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.auth_service.entity.ProfilsCandidat;

import java.util.Optional;

@Repository
public interface ProfilsCandidatRepository extends JpaRepository<ProfilsCandidat, Integer> {
    Optional<Object> findByUtilisateur(Utilisateur user);
}
