package org.example.record;

import java.time.LocalDateTime;
import java.util.Objects;

public record User(String username, boolean active, LocalDateTime lastLogin){
    public User{
        Objects.requireNonNull(username);
        Objects.requireNonNull(lastLogin);
        username = username.toLowerCase();
    }

}
