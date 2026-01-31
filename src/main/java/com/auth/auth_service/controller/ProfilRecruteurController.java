package com.auth.auth_service.controller;

import com.auth.auth_service.dto.ProfilRecruteurDTO;
import com.auth.auth_service.dto.ProfilUpdateResponse;
import com.auth.auth_service.entity.ProfilsRecruteur;
import com.auth.auth_service.service.interfaces.ProfilRecruteurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profil-recruteur")
@RequiredArgsConstructor
public class ProfilRecruteurController {
    private final ProfilRecruteurService profilRecruteurService;

    @PutMapping("/{id}")
    public ResponseEntity<ProfilUpdateResponse> update(
            @PathVariable int id,
            @RequestBody @Valid ProfilRecruteurDTO request
    ) {
        try {
            ProfilsRecruteur profilsRecruteur = profilRecruteurService.findById(id);
            profilRecruteurService.update(request, profilsRecruteur, null);

            return ResponseEntity.ok(new ProfilUpdateResponse(true, "Profil mise à jour effectuée avec succès !"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ProfilUpdateResponse(false, e.getMessage()));
        }
    }

    @PutMapping(value= "/update-photo/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfilUpdateResponse> updatePhoto(
            @PathVariable int id,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) {
        try {
            ProfilsRecruteur profilsRecruteur = profilRecruteurService.findById(id);
            profilRecruteurService.updateProfil(profilsRecruteur, photo);

            return ResponseEntity.ok(new ProfilUpdateResponse(true, "Photo mise à jour avec succès !"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ProfilUpdateResponse(false, e.getMessage()));
        }
    }
}
