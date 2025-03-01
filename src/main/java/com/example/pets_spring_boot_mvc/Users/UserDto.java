package com.example.pets_spring_boot_mvc.Users;

import com.example.pets_spring_boot_mvc.Pet.PetDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDto(
        Long id,
        @NotBlank(message = "The name can't be empty")
        String name,
        @Email(message = "Incorrect email")
        String email,
        @Min(value = 0, message = "The age must be greater than 0")
        Integer age,
        List<PetDto> ArrayPets

) {
}
