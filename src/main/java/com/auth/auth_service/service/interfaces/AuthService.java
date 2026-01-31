package com.auth.auth_service.service.interfaces;

import com.auth.auth_service.dto.AuthResponse;
import com.auth.auth_service.dto.LoginRequest;
import com.auth.auth_service.dto.RegisterRequest;
import com.auth.auth_service.dto.UserInfoDTO;
import com.auth.auth_service.entity.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {
    AuthResponse register(RegisterRequest request, MultipartFile photo);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(String refreshToken);
    Utilisateur findByEmail(String email);
    Utilisateur getCurrentUser();
    UserInfoDTO getCurrentUserInfo();

}