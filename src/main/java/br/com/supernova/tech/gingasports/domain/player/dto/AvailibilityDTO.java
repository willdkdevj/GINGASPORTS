package br.com.supernova.tech.gingasports.domain.player.dto;

import jakarta.validation.constraints.NotNull;

public record AvailibilityDTO(

    @NotNull
    Integer distance,

    @NotNull
    Boolean scout,

    @NotNull
    Boolean notification,

    @NotNull
    Boolean pcd
){}
