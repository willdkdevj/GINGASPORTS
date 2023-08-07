package br.com.supernova.tech.gingasports.domain.team.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TeamUpDTO(
        @NotNull
        Long id,
        String name,
        String emblem,
        Integer ranking,
        BigDecimal classification
) {}
