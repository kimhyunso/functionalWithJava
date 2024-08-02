package org.example.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record User(UUID id, String group, LocalDateTime lastLogin, List logEntries) {
}
