package com.auth.auth_service.controller;

import com.auth.auth_service.dto.*;
import com.auth.auth_service.entity.Utilisateur;
import com.auth.auth_service.service.interfaces.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.auth_service.service.interfaces.AuthService;

import org.springframework.http.MediaType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UtilisateurService utilisateurService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AuthResponse register(
            @RequestPart("data") String data,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) throws Exception {
        RegisterRequest request = new ObjectMapper().readValue(data, RegisterRequest.class);
        return authService.register(request, photo);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody @Valid RefreshTokenRequest request) {
        return authService.refreshToken(request.refreshToken());
    }

    @GetMapping("/me")
    public UserInfoDTO getCurrentUser() {
        return authService.getCurrentUserInfo();
    }

    @PutMapping(value= "/etat/{id}/{etat}")
    public ResponseEntity<ProfilUpdateResponse> etatCompte(
            @PathVariable int id, @PathVariable boolean etat
    ) throws Exception {
        try {
            Utilisateur utilisateur = utilisateurService.findById(id);
            Utilisateur utilisateurUpdate = utilisateurService.active(utilisateur, etat);

            if (utilisateurUpdate.isActive()) {
                return ResponseEntity.ok(new ProfilUpdateResponse(true, "Le compte a été activé avec succès !"));
            }else{
                return ResponseEntity.ok(new ProfilUpdateResponse(true, "Le compte a été archivé avec succès !"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ProfilUpdateResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/test")
    public String test() {
        return "API AUTH SERVICE is working!";
    }
}
