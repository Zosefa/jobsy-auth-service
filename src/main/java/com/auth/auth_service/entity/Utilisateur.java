package com.auth.auth_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "utilisateurs")
@Getter @Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(name = "is_active")
    private boolean active = true;

    @OneToOne(mappedBy = "utilisateur")
    @JsonManagedReference("utilisateur-candidat")
    private ProfilsCandidat profilCandidat;

    @OneToOne(mappedBy = "utilisateur")
    @JsonManagedReference("utilisateur-recruteur")
    private ProfilsRecruteur profilRecruteur;

    @OneToOne(mappedBy = "utilisateur")
    @JsonManagedReference("utilisateur-admin")
    private ProfilsAdmin profilAdmin;

    public enum UserRole {
        CANDIDAT,
        RECRUTEUR,
        ADMIN
    }
}