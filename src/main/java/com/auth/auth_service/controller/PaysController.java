package com.auth.auth_service.controller;

import com.auth.auth_service.entity.Pays;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.auth_service.service.interfaces.PaysService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pays")
@RequiredArgsConstructor
public class PaysController {
    private final PaysService paysService;

    @GetMapping
    public ResponseEntity<List<Pays>> findAll() {
        return ResponseEntity.ok(paysService.findAll());
    }
}
