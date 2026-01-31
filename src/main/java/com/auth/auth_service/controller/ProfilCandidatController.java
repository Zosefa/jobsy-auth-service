package com.auth.auth_service.controller;

import com.auth.auth_service.dto.ProfilCandidatDTO;
import com.auth.auth_service.dto.ProfilUpdateResponse;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.service.interfaces.ProfilCandidatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profil-candidat")
@RequiredArgsConstructor
public class ProfilCandidatController {
    private final ProfilCandidatService profilCandidatService;

    @PutMapping("/{id}")
    public ResponseEntity<ProfilUpdateResponse> update(
            @PathVariable int id,
            @RequestBody @Valid ProfilCandidatDTO request
    ) {
        try {
            ProfilsCandidat profilsCandidat = profilCandidatService.findById(id);
            profilCandidatService.update(request, profilsCandidat, null);

            return ResponseEntity.ok(new ProfilUpdateResponse(true, "Profil mise a jour effectuée avec succès !"));
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
            ProfilsCandidat profilsCandidat = profilCandidatService.findById(id);
            profilCandidatService.updateProfil(profilsCandidat, photo);

            return ResponseEntity.ok(new ProfilUpdateResponse(true, "Photo de profil mise a jour avec succès !"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ProfilUpdateResponse(false, e.getMessage()));
        }
    }
}
