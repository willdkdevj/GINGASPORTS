package br.com.supernova.tech.gingasports.domain.player.dto;

import jakarta.validation.constraints.NotNull;

public record PlayerUpDTO(
        @NotNull
        Long id,
        String name,
        String email,
        String alias,
        String phone) {
}
