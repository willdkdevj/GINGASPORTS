package br.com.supernova.tech.gingasports.domain.team.dto;

import br.com.supernova.tech.gingasports.domain.player.dao.Player;
import br.com.supernova.tech.gingasports.domain.player.dto.AvailibilityDTO;
import br.com.supernova.tech.gingasports.domain.player.dto.PlayerDTO;
import br.com.supernova.tech.gingasports.domain.player.dto.PreferenceDTO;
import br.com.supernova.tech.gingasports.domain.team.dao.Team;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public record ReadResponseDTO(String name, Integer ranking, BigDecimal classification, Boolean active, @JsonProperty("players") List<PlayerDTO> playerDTOList) {
    public ReadResponseDTO(Team team) {
        this(team.getName(), team.getRanking(), team.getClassification(), team.getActive(), new ArrayList<>());
        playerDTOList.addAll(verifyPlayers(team.getPlayers()));
    }

    private List<PlayerDTO> verifyPlayers(List<Player> players) {
        List<PlayerDTO> playersDTO = new ArrayList<>();
        players.stream()
                .filter(player -> player != null)
                .map(player -> {
                    PlayerDTO playerDTO = new PlayerDTO(player.getName(), player.getEmail(), player.getAlias(), player.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), player.getPhone(),
                            new AvailibilityDTO(player.getAvailibility().getDistance(), player.getAvailibility().getScout(), player.getAvailibility().getNotification(), player.getAvailibility().getPcd()),
                            new PreferenceDTO(player.getPreference().getSport(), player.getPreference().getModality(), player.getPreference().getFixedTeam(), player.getPreference().getPosition(), player.getPreference().getHandedness(), player.getPreference().getWearableDevice(), ""));

                    return playerDTO;

                }).forEachOrdered( playerDTO -> {
                    playersDTO.add(playerDTO);
                });

        return playersDTO;
    }
}
