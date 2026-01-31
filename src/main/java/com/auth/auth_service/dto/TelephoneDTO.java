package com.auth.auth_service.dto;

public record TelephoneDTO(
        Integer id,
        String telephone,
        Boolean isPhonePrincipal
) {}
