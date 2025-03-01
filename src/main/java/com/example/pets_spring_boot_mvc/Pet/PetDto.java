package com.example.pets_spring_boot_mvc.Pet;

public record PetDto(
        Long id,
        String petName,
        Long userId
) {
}
