package com.example.pets_spring_boot_mvc.Users;

import com.example.pets_spring_boot_mvc.Pet.PetConverter;
import com.example.pets_spring_boot_mvc.Pet.PetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final PetConverter petConverter;
    private final UserConverter userConverter;

    public UserController(
            UserService userService,
            PetConverter petConverter,
            UserConverter userConverter
    ) {
        this.userService = userService;
        this.petConverter = petConverter;
        this.userConverter = userConverter;

    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Validated UserDto userDto) {
        var savedUser = userService.createUser(userConverter.DtoToUser(userDto));
        return ResponseEntity.ok(userConverter.userToDto(savedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") Long id) {
        var gettingUser = userService.getUser(id);
        return ResponseEntity.ok(userConverter.userToDto(gettingUser));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") Long id,
            @RequestBody @Validated UserDto userDto
    ) {
        User user = new User(
                id,
                userDto.name(),
                userDto.age(),
                userDto.email(),
                userDto.ArrayPets().stream().map(petConverter::dtoToPet).toList()
        );
        var updattingUser = userService.updateUser(user);
        return ResponseEntity.ok(userConverter.userToDto(updattingUser));
    }
}
