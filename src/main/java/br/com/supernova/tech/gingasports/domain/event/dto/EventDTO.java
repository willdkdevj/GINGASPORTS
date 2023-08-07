package br.com.supernova.tech.gingasports.domain.event.dto;

import br.com.supernova.tech.gingasports.domain.enums.Modality;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.team.dto.PlayerIDDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record EventDTO (
    @NotBlank
    String eventName,

    @NotBlank
    String address,

    String descriptionRules,

    @NotBlank
    String dateGame,

    @NotBlank
    String hourGame,

    @NotBlank
    String duration,

    @NotNull
    Sport sport,

    @NotNull
    Modality modality,

    @NotNull
    Integer numberVacancies,

    BigDecimal monthValue,

    BigDecimal singleValue,

    List<PlayerIDDTO> players

){}
