package com.example.pets_spring_boot_mvc.Users;

import com.example.pets_spring_boot_mvc.Pet.Pet;

import java.util.List;

public record User(
        Long id,
        String name,
        Integer age,
        String email,
        List<Pet> ArrayPet

) {
}
