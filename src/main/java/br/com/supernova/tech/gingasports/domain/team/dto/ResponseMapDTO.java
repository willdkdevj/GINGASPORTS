package br.com.supernova.tech.gingasports.domain.team.dto;

import br.com.supernova.tech.gingasports.ResponseAbstract;
import br.com.supernova.tech.gingasports.domain.team.dao.Team;

import java.math.BigDecimal;

public record ResponseMapDTO(Long id, String name, Integer ranking, BigDecimal classification) {

    public ResponseMapDTO(Team team) {
        this(team.getId(), team.getName(), team.getRanking(), team.getClassification());
    }

}
