package com.example.pets_spring_boot_mvc.Pet;

public record Pet(
        Long id,
        String petName,
        Long userId
) {
}
