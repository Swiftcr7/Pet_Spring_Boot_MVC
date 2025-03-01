package com.example.pets_spring_boot_mvc.ErrorHandler;

import java.time.LocalDateTime;

public record ServerMessageError(
        String message,
        String detailedMessage,
        LocalDateTime errorTime
) {
}
