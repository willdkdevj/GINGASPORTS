package br.com.supernova.tech.gingasports.domain.player.dto;

import br.com.supernova.tech.gingasports.domain.player.dao.Player;

public record ReadResponseDTO(String name, String alias, String email, String sport) {
    public ReadResponseDTO(Player player) {
        this(player.getName(), player.getAlias(), player.getEmail(), player.getPreference().getSport().getValue());
    }
}
