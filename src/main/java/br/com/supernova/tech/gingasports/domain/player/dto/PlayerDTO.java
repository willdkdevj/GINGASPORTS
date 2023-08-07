package br.com.supernova.tech.gingasports.domain.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PlayerDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        String alias,

        @NotNull
        String birthDate,

        @NotBlank
        @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?[\\s9]?\\d{4}-?\\d{4}$")
        String phone,

        @NotNull
        @Valid
        @JsonProperty("availibility")
        AvailibilityDTO availibilityDTO,

        @NotNull
        @Valid
        @JsonProperty("preference")
        PreferenceDTO preferenceDTO
){



}
