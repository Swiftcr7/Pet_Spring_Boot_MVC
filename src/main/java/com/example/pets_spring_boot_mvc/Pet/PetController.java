package com.example.pets_spring_boot_mvc.Pet;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pets")
@RestController
public class PetController {
    private final PetService petService;
    private final PetConverter petConverter;

    public PetController(PetService petService, PetConverter petConverter) {
        this.petConverter = petConverter;
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody @Validated PetDto petDto) {
        Pet savingPet = petService.createPet(petConverter.dtoToPet(petDto));
        return ResponseEntity.ok(petConverter.petToDto(savingPet));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable("petId") Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{petId}")
    public ResponseEntity<PetDto> UpdatePet(
            @PathVariable("petId") Long id,
            @RequestBody @Validated PetDto petDto
    ) {
        var updatingPet = new Pet(id, petDto.petName(), petDto.userId());
        var petAfterUpdating = petService.updatePet(updatingPet);
        return ResponseEntity.ok(petConverter.petToDto(petAfterUpdating));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDto> getPet(@PathVariable("petId") Long id) {
        var gettingPet = petService.getPet(id);
        return ResponseEntity.ok(petConverter.petToDto(gettingPet));
    }
}
