package br.com.supernova.tech.gingasports.domain.team.dao;

import br.com.supernova.tech.gingasports.domain.player.dao.Player;
import br.com.supernova.tech.gingasports.domain.team.dto.PlayerIDDTO;
import br.com.supernova.tech.gingasports.domain.team.dto.TeamDTO;
import br.com.supernova.tech.gingasports.domain.team.dto.TeamUpDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "teams")
@Entity(name = "Team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String emblem;

    private Integer ranking;

    private BigDecimal classification;

    private Boolean active;

    @ManyToMany
    @JoinTable(name = "team_players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player){
        this.getPlayers().add(player);
    }

    public Team(TeamDTO teamDTO){
        this.name = teamDTO.name();
        this.emblem = teamDTO.emblem();
        this.ranking = teamDTO.ranking();
        this.classification = teamDTO.classification();
        this.active = Boolean.TRUE;
    }

    public void updateTeam(TeamUpDTO teamDTO){
        this.name = teamDTO.name() != null ? teamDTO.name() : this.name;
        this.ranking = teamDTO.ranking() >= 0 ? teamDTO.ranking() : this.ranking;
        this.classification = teamDTO.classification().doubleValue() >= 0 ? teamDTO.classification() : this.classification;
    }

    public void setActive(Boolean bool){
        this.active = bool;
    }

}
