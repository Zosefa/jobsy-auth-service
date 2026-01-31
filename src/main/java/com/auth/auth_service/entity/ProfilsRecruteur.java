package com.auth.auth_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "profils_recruteur")
@Getter @Setter
public class ProfilsRecruteur {

    @Id
    @Column(name = "utilisateur_id")
    private Integer utilisateurId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "utilisateur_id")
    @JsonBackReference("utilisateur-recruteur")
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_id", nullable = false)
    private Entreprise entreprise;

    private String photo;

    private String fonction;

    private Boolean verifie = false;

    @OneToMany(mappedBy = "recruteur", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("recruteur-telephones")
    private List<TelephonesRecruteur> telephones;
}