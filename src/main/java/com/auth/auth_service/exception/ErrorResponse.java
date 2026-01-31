package com.auth.auth_service.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String detail,
        int status,
        LocalDateTime timestamp
) {}
