package br.com.supernova.tech.gingasports.domain.player.dto;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
        @NotNull
        String login,
        @NotNull
        String pass) {
}
