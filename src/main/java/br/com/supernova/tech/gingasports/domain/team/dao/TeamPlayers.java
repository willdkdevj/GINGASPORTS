package br.com.supernova.tech.gingasports.domain.team.dao;

import br.com.supernova.tech.gingasports.domain.player.dao.Player;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "team_players")
@Entity(name = "TeamPlayers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TeamPlayers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
