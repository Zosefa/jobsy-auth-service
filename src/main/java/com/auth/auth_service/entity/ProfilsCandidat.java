package com.auth.auth_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "profils_candidat")
@Getter @Setter
public class ProfilsCandidat {

    @Id
    @Column(name = "utilisateur_id")
    private Integer utilisateurId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "utilisateur_id")
    @JsonBackReference("utilisateur-candidat")
    private Utilisateur utilisateur;

    private String nom;
    private String prenom;
    private String photo;

    // 🔁 localisation supprimée

    @Column(columnDefinition = "TEXT")
    private String resume;

    @Column(name = "annees_experience")
    private Integer anneesExperience = 0;

    @Column(name = "profil_completed")
    private Boolean profilCompleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pays_id")
    private Pays pays;

    @Column(length = 150)
    private String ville;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("candidat-telephones")
    private List<TelephonesCandidat> telephones;
}
