package br.com.supernova.tech.gingasports.domain.team.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
public record TeamDTO(
        @NotBlank
        String name,

        @NotBlank
        String emblem,

        @NotNull
        Integer ranking,

        @NotNull
        BigDecimal classification,

        List<PlayerIDDTO> players
) {
}
