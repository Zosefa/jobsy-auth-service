package com.auth.auth_service.repository;

import com.auth.auth_service.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.auth_service.entity.ProfilsAdmin;

import java.util.Optional;

@Repository
public interface ProfilsAdminRepository extends JpaRepository<ProfilsAdmin, Integer> {
    Optional<Object> findByUtilisateur(Utilisateur user);
}
