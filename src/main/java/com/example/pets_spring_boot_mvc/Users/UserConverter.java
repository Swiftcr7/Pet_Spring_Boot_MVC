package com.example.pets_spring_boot_mvc.Users;

import com.example.pets_spring_boot_mvc.Pet.PetConverter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private final PetConverter petConverter;

    public UserConverter(PetConverter petConverter) {
        this.petConverter = petConverter;
    }

    public UserDto userToDto(User user) {
        return new UserDto(
                user.id(),
                user.name(),
                user.email(),
                user.age(),
                user.ArrayPet().stream().map(petConverter::petToDto).toList()
        );
    }

    public User DtoToUser(UserDto userDto) {
        return new User(
                userDto.id(),
                userDto.name(),
                userDto.age(),
                userDto.email(),
                userDto.ArrayPets().stream().map(petConverter::dtoToPet).toList()
        );
    }
}
