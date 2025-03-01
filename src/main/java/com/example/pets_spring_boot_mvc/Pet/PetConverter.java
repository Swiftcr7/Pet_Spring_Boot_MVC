package com.example.pets_spring_boot_mvc.Pet;

import org.springframework.stereotype.Component;

@Component
public class PetConverter {
    public Pet dtoToPet(PetDto petDto) {
        return new Pet(
                petDto.id(),
                petDto.petName(),
                petDto.userId());
    }

    public PetDto petToDto(Pet pet) {
        return new PetDto(
                pet.id(),
                pet.petName(),
                pet.userId()
        );
    }
}
