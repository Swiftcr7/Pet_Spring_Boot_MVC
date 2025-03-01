package com.example.pets_spring_boot_mvc.Pet;

import com.example.pets_spring_boot_mvc.Users.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PetService {
    private final AtomicLong petId;
    private final UserService userService;

    public PetService(UserService userService) {
        this.userService = userService;
        petId = new AtomicLong();
    }

    public Pet createPet(Pet pet) {
        if (pet.id() != null) {
            throw new IllegalArgumentException("Id for pet should not be provided");
        }
        var petForUser = new Pet(petId.incrementAndGet(), pet.petName(), pet.userId());
        userService.getUser(petForUser.userId()).ArrayPet().add(petForUser);
        return petForUser;
    }

    private Optional<Pet> findPet(Long id) {
        return userService.getAllUsers().stream()
                .flatMap(user -> user.ArrayPet().stream())
                .filter(pet -> pet.id().equals(id))
                .findAny();
    }

    public Pet getPet(Long id) {
        return findPet(id).orElseThrow(() -> new NoSuchElementException("No such pet with id=%s".formatted(id)));
    }

    public void deletePet(Long id) {
        var pet = findPet(id).orElseThrow(() -> new NoSuchElementException("No such pet with id=%s".formatted(id)));
        var user = userService.getUser(pet.userId());
        user.ArrayPet().remove(pet);
    }

    public Pet updatePet(Pet pet) {
        if (pet.id() == null) {
            throw new IllegalArgumentException("No pet id passed");
        }
        var foundingPet = findPet(pet.id()).orElseThrow(() -> new NoSuchElementException("No such pet with id=%s".formatted(pet.id())));
        var updatingPet = new Pet(foundingPet.id(), pet.petName(), foundingPet.userId());
        var user = userService.getUser(pet.userId());
        user.ArrayPet().remove(foundingPet);
        user.ArrayPet().add(updatingPet);
        return updatingPet;
    }
}
