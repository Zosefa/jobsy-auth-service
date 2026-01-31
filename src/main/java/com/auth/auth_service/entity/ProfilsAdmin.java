package com.auth.auth_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profils_admin")
@Getter @Setter
public class ProfilsAdmin {

    @Id
    @Column(name = "utilisateur_id")
    private Integer utilisateurId;

    private String nom;
    private String prenom;
    private String photo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "utilisateur_id")
    @JsonBackReference("utilisateur-admin")
    private Utilisateur utilisateur;

    private Boolean superAdmin = false;
}