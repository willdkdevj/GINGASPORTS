package br.com.supernova.tech.gingasports.domain.player.dto;

import br.com.supernova.tech.gingasports.domain.enums.Modality;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PreferenceDTO(
        @NotNull
        Sport sport,

        @NotNull
        Modality modality,

        @NotNull
        Boolean fixedTeam,

        @NotBlank
        String position,

        @NotBlank
        String handedness,

        @NotNull
        Boolean wearableDevice,

        String devices
){}
