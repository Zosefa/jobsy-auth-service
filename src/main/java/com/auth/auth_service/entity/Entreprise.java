package com.auth.auth_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "entreprises")
@Getter @Setter
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    private String logo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pays_id")
    private Pays pays;

    @Column(length = 150)
    private String ville;

    @Column(name = "siege_social", columnDefinition = "TEXT")
    private String siegeSocial;

    private Boolean verifie = false;

    @OneToMany(mappedBy = "entreprise")
    private List<ProfilsRecruteur> recruteurs;
}