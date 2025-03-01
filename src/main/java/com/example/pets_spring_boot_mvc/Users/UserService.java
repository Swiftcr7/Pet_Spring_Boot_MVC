package com.example.pets_spring_boot_mvc.Users;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final AtomicLong idCount;
    private final Map<Long, User> userMap;

    public UserService() {
        this.idCount = new AtomicLong();
        this.userMap = new ConcurrentHashMap<>();
    }

    public User createUser(User user) {
        if (user.id() != null) {
            throw new IllegalArgumentException("Id for user should not be provided");
        }

        if (user.ArrayPet() != null && !user.ArrayPet().isEmpty()) {
            throw new IllegalArgumentException("User pets must be empty");
        }

        var count = idCount.incrementAndGet();
        var savingUser = new User(count, user.name(), user.age(), user.email(), new ArrayList<>());
        userMap.put(count, savingUser);
        return savingUser;
    }

    public void deleteUser(Long id) {
        userMap.remove(id);
    }

    public User getUser(Long id) {
        if (!userMap.containsKey(id)) {
            throw new NoSuchElementException("No such user with id=%s".formatted(id));
        }
        return userMap.get(id);
    }

    public List<User> getAllUsers() {
        return userMap.values().stream().toList();
    }

    public User updateUser(User user) {
        if (user.id() == null) {
            throw new IllegalArgumentException("No user id passed");
        }

        if (!userMap.containsKey(user.id())) {
            throw new NoSuchElementException("No such user with id=%s".formatted(user.id()));
        }

        userMap.put(user.id(), user);
        return user;
    }
}
