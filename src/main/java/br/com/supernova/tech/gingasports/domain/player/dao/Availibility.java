package br.com.supernova.tech.gingasports.domain.player.dao;

import br.com.supernova.tech.gingasports.domain.player.dto.AvailibilityDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Availibility {
    private Integer distance;

    private Boolean scout;

    private Boolean notification;

    private Boolean pcd;

    public Availibility(AvailibilityDTO availibilityDTO) {
        this.distance = availibilityDTO.distance();
        this.scout = availibilityDTO.scout();
        this.notification = availibilityDTO.notification();
        this.pcd = availibilityDTO.pcd();
    }
}
