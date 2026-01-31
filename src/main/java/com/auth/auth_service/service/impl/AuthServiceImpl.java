package com.auth.auth_service.service.impl;

import com.auth.auth_service.dto.*;
import com.auth.auth_service.repository.ProfilsAdminRepository;
import com.auth.auth_service.repository.ProfilsCandidatRepository;
import com.auth.auth_service.repository.ProfilsRecruteurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.auth_service.entity.ProfilsAdmin;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.entity.ProfilsRecruteur;
import com.auth.auth_service.entity.Utilisateur;
import com.auth.auth_service.exception.BusinessException;
import com.auth.auth_service.repository.UtilisateurRepository;
import com.auth.auth_service.security.JwtService;
import com.auth.auth_service.service.interfaces.AuthService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurServiceImpl utilisateurServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final ProfilCandidatServiceImpl profilCandidatService;
    private final ProfilRecruteurServiceImpl profilRecruteurService;
    private final ProfilAdminServiceImpl profilAdminService;

    private final ProfilsCandidatRepository profilsCandidatRepository;
    private final ProfilsRecruteurRepository profilsRecruteurRepository;
    private final ProfilsAdminRepository profilsAdminRepository;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request, MultipartFile photo) {

        if (utilisateurServiceImpl.existsByEmail(request.email())) {
            throw new BusinessException("Email déjà utilisé");
        }

        UtilisateurDTO dto = new UtilisateurDTO(request.email(), request.password(), request.role());

        Utilisateur user = utilisateurServiceImpl.create(dto);

        // 2️⃣ Créer le profil selon le rôle
        switch (request.role()) {
            case CANDIDAT -> {
                if (request.profilCandidat() != null) {
                    ProfilsCandidat profil = profilCandidatService.create(request.profilCandidat(), user, photo);
                }
            }
            case RECRUTEUR -> {
                if (request.profilRecruteur() != null) {
                    ProfilsRecruteur profil = profilRecruteurService.create(request.profilRecruteur(), user, photo);
                }

            }
            case ADMIN -> {
                if (request.profilAdmin() != null) {
                    ProfilsAdmin profil = profilAdminService.create(request.profilAdmin(), user, photo);
                }
            }
        }

        // 3️⃣ Retourner JWT
        return jwtService.generateTokens(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Utilisateur user = utilisateurRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException("Identifiants invalides"));

        if (!user.isActive()) {
            log.warn("Tentative de connexion avec compte inactif: {}", request.email());
            throw new BusinessException("Votre compte n'est pas activé. Veuillez contacter l'administrateur.");
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException("Identifiants invalides");
        }

        return jwtService.generateTokens(user);
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        if (!jwtService.isRefreshValid(refreshToken)) {
            throw new BusinessException("Refresh token invalide", HttpStatus.FORBIDDEN); // 403
        }

        String email = jwtService.extractUsernameFromRefresh(refreshToken);
        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Utilisateur introuvable", HttpStatus.FORBIDDEN));

        return jwtService.generateTokens(user);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email).get();
    }

    @Override
    public Utilisateur getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessException("Utilisateur non authentifié");
        }

        Object principal = authentication.getPrincipal();
        String email;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            email = (String) principal;
        } else {
            throw new BusinessException("Type d'authentification non supporté");
        }

        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Utilisateur introuvable"));
    }

    @Override
    public UserInfoDTO getCurrentUserInfo() {
        Utilisateur user = getCurrentUser();

        Object profil = switch (user.getRole()) {
            case CANDIDAT -> profilsCandidatRepository.findByUtilisateur(user)
                    .orElse(null);
            case RECRUTEUR -> profilsRecruteurRepository.findByUtilisateur(user)
                    .orElse(null);
            case ADMIN -> profilsAdminRepository.findByUtilisateur(user)
                    .orElse(null);
            default -> null;
        };

        return new UserInfoDTO(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                profil
        );
    }
}
