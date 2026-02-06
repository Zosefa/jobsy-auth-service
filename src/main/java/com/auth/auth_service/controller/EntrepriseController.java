package com.auth.auth_service.controller;

import com.auth.auth_service.entity.Entreprise;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.auth_service.service.interfaces.EntrepriseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/entreprise")
@RequiredArgsConstructor
public class EntrepriseController {
    private final EntrepriseService entrepriseService;

    @GetMapping
    public ResponseEntity<List<Entreprise>> findAll() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }
}
