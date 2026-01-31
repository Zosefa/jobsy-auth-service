package com.auth.auth_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pays")
@Getter @Setter
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nom;

    @Column(name = "code_iso2", nullable = false, unique = true, length = 2)
    private String codeIso2;

    @Column(name = "code_iso3", unique = true, length = 3)
    private String codeIso3;
}
