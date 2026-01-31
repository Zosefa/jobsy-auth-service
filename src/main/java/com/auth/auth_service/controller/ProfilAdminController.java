package com.auth.auth_service.controller;

import com.auth.auth_service.dto.ProfilAdminDTO;
import com.auth.auth_service.dto.ProfilUpdateResponse;
import com.auth.auth_service.entity.ProfilsAdmin;
import com.auth.auth_service.service.interfaces.ProfilAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profil-admin")
@RequiredArgsConstructor
public class ProfilAdminController {
    private ProfilAdminService profilAdminService;

    @PutMapping("/{id}")
    public ResponseEntity<ProfilUpdateResponse> update(
            @PathVariable int id,
            @RequestBody @Valid ProfilAdminDTO request
    ) {
        try {
            ProfilsAdmin profilsRecruteur = profilAdminService.findById(id);
            profilAdminService.update(request, profilsRecruteur, null);

            return ResponseEntity.ok(new ProfilUpdateResponse(true, "Profil mise a jour avec succès !"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ProfilUpdateResponse(false, e.getMessage()));
        }
    }

    @PutMapping(value= "/update-photo/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfilUpdateResponse> updatePhoto(
            @PathVariable int id,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) throws Exception {
        try {
            ProfilsAdmin profilsRecruteur = profilAdminService.findById(id);
            profilAdminService.updateProfil(profilsRecruteur, photo);

            return ResponseEntity.ok(new ProfilUpdateResponse(true, "Photo mise a jour avec succès !"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ProfilUpdateResponse(false, e.getMessage()));
        }
    }
}
