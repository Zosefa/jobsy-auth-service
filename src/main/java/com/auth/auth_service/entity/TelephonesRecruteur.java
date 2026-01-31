package com.auth.auth_service.entity;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "telephones_recruteur",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"recruteur_id"}, name = "unique_phone_principal_recruteur")})
@Getter @Setter
public class TelephonesRecruteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recruteur_id")
    @JsonBackReference("recruteur-telephones")
    private ProfilsRecruteur recruteur;

    private String telephone;

    @Column(name = "is_phone_principal")
    private Boolean isPhonePrincipal = false;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();
}