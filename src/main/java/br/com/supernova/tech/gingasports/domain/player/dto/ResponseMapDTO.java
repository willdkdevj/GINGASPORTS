package br.com.supernova.tech.gingasports.domain.player.dto;

import br.com.supernova.tech.gingasports.domain.player.dao.Player;

public record ResponseMapDTO(Long id, String name, String alias, String email, SportDTO sport) {

    public ResponseMapDTO(Player player) {
        this(player.getId(), player.getName(), player.getAlias(), player.getEmail(), new SportDTO(player.getPreference()));
    }
}
