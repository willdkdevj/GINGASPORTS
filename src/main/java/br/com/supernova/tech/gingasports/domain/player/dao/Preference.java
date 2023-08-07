package br.com.supernova.tech.gingasports.domain.player.dao;

import br.com.supernova.tech.gingasports.domain.player.dto.PreferenceDTO;
import br.com.supernova.tech.gingasports.domain.enums.Modality;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Preference {

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @Enumerated(EnumType.STRING)
    private Modality modality;

    private Boolean fixedTeam;

    private String position;

    private String handedness;

    @Column(name = "wearabledevice", nullable = true)
    private Boolean wearableDevice;

    public Preference(PreferenceDTO preferenceDTO) {
        this.sport = preferenceDTO.sport();
        this.modality = preferenceDTO.modality();
        this.fixedTeam = preferenceDTO.fixedTeam();
        this.position = preferenceDTO.position();
        this.handedness = preferenceDTO.handedness();
        this.wearableDevice = preferenceDTO.wearableDevice();
    }
}
