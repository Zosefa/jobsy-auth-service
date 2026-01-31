package com.auth.auth_service.service.impl;

import com.auth.auth_service.dto.EntrepriseDTO;
import com.auth.auth_service.dto.ProfilRecruteurDTO;
import com.auth.auth_service.dto.TelephoneDTO;
import com.auth.auth_service.entity.*;
import com.auth.auth_service.repository.EntrepriseRepository;
import com.auth.auth_service.repository.PaysRepository;
import com.auth.auth_service.repository.ProfilsRecruteurRepository;
import com.auth.auth_service.service.FileStorageService;
import com.auth.auth_service.service.interfaces.ProfilRecruteurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfilRecruteurServiceImpl implements ProfilRecruteurService {

    private final ProfilsRecruteurRepository repo;
    private final EntrepriseRepository entrepriseRepository;
    private final PaysRepository paysRepository;

    private final TelephoneRecruteurServiceImpl telephoneService;
    private final FileStorageService fileStorageService;

    @Override
    public ProfilsRecruteur create(ProfilRecruteurDTO dto, Utilisateur u, MultipartFile photo) {

        Entreprise entreprise = resolveEntreprise(dto);

        ProfilsRecruteur profil = new ProfilsRecruteur();
        profil.setUtilisateur(u);
        profil.setEntreprise(entreprise);

        // ✅ photo recruteur
        if (photo != null && !photo.isEmpty()) {
            String newFileName = fileStorageService.storeFile(
                    photo, FileStorageService.PHOTOS_DIR, FileStorageService.IMAGE_TYPES
            );
            profil.setPhoto(newFileName);
        }

        profil.setFonction(dto.fonction());
        profil.setVerifie(dto.verifie());

        List<TelephonesRecruteur> telephones =
                (dto.telephones() == null ? List.<TelephoneDTO>of() : dto.telephones())
                        .stream()
                        .map(t -> telephoneService.create(t, profil))
                        .toList();

        profil.setTelephones(telephones);

        repo.save(profil);
        return profil;
    }

    @Override
    public ProfilsRecruteur update(ProfilRecruteurDTO dto, ProfilsRecruteur profil, Optional<MultipartFile> photo){

        // entreprise: optionnel en update, mais possible
        if (dto.entrepriseId() != null || dto.entreprise() != null) {
            profil.setEntreprise(resolveEntreprise(dto));
        }

        profil.setFonction(dto.fonction());
        profil.setVerifie(dto.verifie());

        // ✅ update photo recruteur
        if (photo != null && photo.isPresent()) {
            MultipartFile newPhoto = photo.get();
            if (newPhoto != null && !newPhoto.isEmpty()) {
                String oldFileName = profil.getPhoto();
                if (oldFileName != null && !oldFileName.isBlank()) {
                    fileStorageService.removeFile(oldFileName, FileStorageService.PHOTOS_DIR);
                }
                String newFileName = fileStorageService.storeFile(
                        newPhoto, FileStorageService.PHOTOS_DIR, FileStorageService.IMAGE_TYPES
                );
                profil.setPhoto(newFileName);
            }
        }

        // Téléphones (même logique que toi)
        List<TelephonesRecruteur> current = profil.getTelephones();
        List<TelephoneDTO> incoming = dto.telephones() == null ? List.of() : dto.telephones();

        Map<Integer, TelephonesRecruteur> currentById = current.stream()
                .filter(t -> t.getId() != null)
                .collect(Collectors.toMap(TelephonesRecruteur::getId, Function.identity()));

        Set<Integer> incomingIds = incoming.stream()
                .map(TelephoneDTO::id)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        current.removeIf(t -> t.getId() != null && !incomingIds.contains(t.getId()));

        for (TelephoneDTO telDto : incoming) {
            if (telDto.id() == null) {
                TelephonesRecruteur newTel = new TelephonesRecruteur();
                newTel.setRecruteur(profil);
                newTel.setTelephone(telDto.telephone());
                newTel.setIsPhonePrincipal(Boolean.TRUE.equals(telDto.isPhonePrincipal()));
                current.add(newTel);
                continue;
            }

            TelephonesRecruteur existing = currentById.get(telDto.id());
            if (existing == null) {
                throw new RuntimeException("Téléphone id=" + telDto.id() + " introuvable pour ce RECRUTEUR");
            }
            existing.setTelephone(telDto.telephone());
            existing.setIsPhonePrincipal(Boolean.TRUE.equals(telDto.isPhonePrincipal()));
        }

        repo.save(profil);
        return profil;
    }

    @Override
    public void updateProfil(ProfilsRecruteur recruteur, MultipartFile photo) {
        if (photo != null && !photo.isEmpty()) {
            String oldFileName = recruteur.getPhoto();
            if (oldFileName != null && !oldFileName.isBlank()) {
                fileStorageService.removeFile(oldFileName, FileStorageService.PHOTOS_DIR);
            }
            String newFileName = fileStorageService.storeFile(
                    photo, FileStorageService.PHOTOS_DIR, FileStorageService.IMAGE_TYPES
            );
            recruteur.setPhoto(newFileName);
            repo.save(recruteur);
        }
    }

    private Entreprise resolveEntreprise(ProfilRecruteurDTO dto) {
        // Option 1: rejoindre une entreprise existante
        if (dto.entrepriseId() != null) {
            return entrepriseRepository.findById(dto.entrepriseId())
                    .orElseThrow(() -> new RuntimeException("Entreprise id=" + dto.entrepriseId() + " introuvable"));
        }

        // Option 2: créer une entreprise
        EntrepriseDTO e = dto.entreprise();
        if (e == null) {
            throw new RuntimeException("Il faut fournir entrepriseId ou entreprise");
        }

        Entreprise entreprise = new Entreprise();
        entreprise.setNom(e.nom());
        entreprise.setVille(e.ville());
        entreprise.setSiegeSocial(e.siegeSocial());
        entreprise.setVerifie(false);

        if (e.paysId() != null) {
            Pays pays = paysRepository.findById(e.paysId())
                    .orElseThrow(() -> new RuntimeException("Pays id=" + e.paysId() + " introuvable"));
            entreprise.setPays(pays);
        }

        return entrepriseRepository.save(entreprise);
    }

    @Override public ProfilsRecruteur findById(int id){ return repo.findById(id).get(); }
    @Override public List<ProfilsRecruteur> findAll(){ return repo.findAll(); }
    @Override public void delete(ProfilsRecruteur p){ repo.delete(p); }
}
