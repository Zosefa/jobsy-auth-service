package com.auth.auth_service.service.impl;

import com.auth.auth_service.dto.ProfilCandidatDTO;
import com.auth.auth_service.dto.TelephoneDTO;
import com.auth.auth_service.entity.Pays;
import com.auth.auth_service.entity.ProfilsCandidat;
import com.auth.auth_service.entity.TelephonesCandidat;
import com.auth.auth_service.entity.Utilisateur;
import com.auth.auth_service.repository.PaysRepository;
import com.auth.auth_service.repository.ProfilsCandidatRepository;
import com.auth.auth_service.service.FileStorageService;
import com.auth.auth_service.service.interfaces.ProfilCandidatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfilCandidatServiceImpl implements ProfilCandidatService {

    private final ProfilsCandidatRepository profilCandidatRepository;
    private final PaysRepository paysRepository; // 👈 nouveau
    private final TelephoneCandidatServiceImpl telephoneService;
    private final FileStorageService fileStorageService;

    @Override
    public ProfilsCandidat create(ProfilCandidatDTO dto, Utilisateur u, MultipartFile photo){
        ProfilsCandidat profilsCandidat = new ProfilsCandidat();
        profilsCandidat.setUtilisateur(u);

        profilsCandidat.setNom(dto.nom());
        profilsCandidat.setPrenom(dto.prenom());

        MultipartFile newPhoto = photo;
        if (newPhoto != null && !newPhoto.isEmpty()) {
            String newFileName = fileStorageService.storeFile(
                    newPhoto,
                    FileStorageService.PHOTOS_DIR,
                    FileStorageService.IMAGE_TYPES
            );
            profilsCandidat.setPhoto(newFileName);
        }

        // ✅ Pays / ville / adresse
        if (dto.paysId() != null) {
            Pays pays = paysRepository.findById(dto.paysId())
                    .orElseThrow(() -> new RuntimeException("Pays id=" + dto.paysId() + " introuvable"));
            profilsCandidat.setPays(pays);
        } else {
            profilsCandidat.setPays(null);
        }
        profilsCandidat.setVille(dto.ville());
        profilsCandidat.setAdresse(dto.adresse());

        profilsCandidat.setAnneesExperience(dto.anneesExperience());
        profilsCandidat.setResume(dto.resume());
        profilsCandidat.setProfilCompleted(dto.profilCompleted());

        List<TelephonesCandidat> telephones =
                (dto.telephones() == null ? List.<TelephoneDTO>of() : dto.telephones())
                        .stream()
                        .map(t -> telephoneService.create(t, profilsCandidat))
                        .toList();

        profilsCandidat.setTelephones(telephones);

        profilCandidatRepository.save(profilsCandidat);
        return profilsCandidat;
    }

    @Override
    public ProfilsCandidat update(ProfilCandidatDTO dto, ProfilsCandidat profilsCandidat, Optional<MultipartFile> photo){

        profilsCandidat.setNom(dto.nom());
        profilsCandidat.setPrenom(dto.prenom());

        // ✅ Pays / ville / adresse
        if (dto.paysId() != null) {
            Pays pays = paysRepository.findById(dto.paysId())
                    .orElseThrow(() -> new RuntimeException("Pays id=" + dto.paysId() + " introuvable"));
            profilsCandidat.setPays(pays);
        } else {
            profilsCandidat.setPays(null);
        }
        profilsCandidat.setVille(dto.ville());
        profilsCandidat.setAdresse(dto.adresse());

        profilsCandidat.setAnneesExperience(dto.anneesExperience());
        profilsCandidat.setResume(dto.resume());
        profilsCandidat.setProfilCompleted(dto.profilCompleted());

        // Photo (inchangé)
        if (photo != null && photo.isPresent()) {
            MultipartFile newPhoto = photo.get();
            if (newPhoto != null && !newPhoto.isEmpty()) {
                String oldFileName = profilsCandidat.getPhoto();
                if (oldFileName != null && !oldFileName.isBlank()) {
                    fileStorageService.removeFile(oldFileName, FileStorageService.PHOTOS_DIR);
                }
                String newFileName = fileStorageService.storeFile(
                        newPhoto,
                        FileStorageService.PHOTOS_DIR,
                        FileStorageService.IMAGE_TYPES
                );
                profilsCandidat.setPhoto(newFileName);
            }
        }

        // Téléphones (inchangé, juste sécuriser dto.telephones null)
        List<TelephonesCandidat> current = profilsCandidat.getTelephones();
        List<TelephoneDTO> incoming = dto.telephones() == null ? List.of() : dto.telephones();

        Map<Integer, TelephonesCandidat> currentById = current.stream()
                .filter(t -> t.getId() != null)
                .collect(Collectors.toMap(TelephonesCandidat::getId, Function.identity()));

        Set<Integer> incomingIds = incoming.stream()
                .map(TelephoneDTO::id)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        current.removeIf(t -> t.getId() != null && !incomingIds.contains(t.getId()));

        for (TelephoneDTO telDto : incoming) {
            if (telDto.id() == null) {
                TelephonesCandidat newTel = new TelephonesCandidat();
                newTel.setCandidat(profilsCandidat);
                newTel.setTelephone(telDto.telephone());
                newTel.setIsPhonePrincipal(Boolean.TRUE.equals(telDto.isPhonePrincipal()));
                current.add(newTel);
                continue;
            }

            TelephonesCandidat existing = currentById.get(telDto.id());
            if (existing == null) {
                throw new RuntimeException("Téléphone id=" + telDto.id() + " introuvable pour ce candidat");
            }
            existing.setTelephone(telDto.telephone());
            existing.setIsPhonePrincipal(Boolean.TRUE.equals(telDto.isPhonePrincipal()));
        }

        profilCandidatRepository.save(profilsCandidat);
        return profilsCandidat;
    }

    @Override
    public ProfilsCandidat findById(int id){
        return profilCandidatRepository.findById(id).get();
    }

    @Override
    public List<ProfilsCandidat> findAll(){
        return profilCandidatRepository.findAll();
    }

    @Override
    public void delete(ProfilsCandidat p){
        profilCandidatRepository.delete(p);
    }

    @Override
    public void updateProfil(ProfilsCandidat candidat, MultipartFile photo) {
        MultipartFile newPhoto = photo;
        if (newPhoto != null && !newPhoto.isEmpty()) {
            String oldFileName = candidat.getPhoto();
            if (oldFileName != null && !oldFileName.isBlank()) {
                fileStorageService.removeFile(oldFileName, FileStorageService.PHOTOS_DIR);
            }
            String newFileName = fileStorageService.storeFile(
                    newPhoto,
                    FileStorageService.PHOTOS_DIR,
                    FileStorageService.IMAGE_TYPES
            );
            candidat.setPhoto(newFileName);
            profilCandidatRepository.save(candidat);
        }
    }
}
