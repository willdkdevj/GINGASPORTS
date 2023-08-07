package br.com.supernova.tech.gingasports.domain.event.dao;

import br.com.supernova.tech.gingasports.domain.team.dao.Team;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "event_players")
@Entity(name = "EventPlayer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EventPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // sorteio aleatório
    private Boolean randomDraw;

    // permitir substituição
    private Boolean allowSubstitution;

    // Confirmação de Presença
    private Boolean confirmationPresence;

    // Valor pago (quitado)
    private Boolean paidValue;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Team player;

}
