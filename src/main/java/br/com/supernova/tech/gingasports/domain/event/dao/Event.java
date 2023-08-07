package br.com.supernova.tech.gingasports.domain.event.dao;

import br.com.supernova.tech.gingasports.domain.Utilities;
import br.com.supernova.tech.gingasports.domain.enums.Modality;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.event.dto.EventDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.EventUpDTO;
import br.com.supernova.tech.gingasports.domain.player.dao.Player;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "events")
@Entity(name = "Event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name")
    private String eventName;

    private String address;

    private String descriptionRules;

    @Column(name = "date_game", columnDefinition = "DATE", nullable = true)
    @Future
    private LocalDate dateGame;

    private String hourGame;

    private String duration;

    private Sport sport;

    private Modality modality;

    private Integer vacancies;

    private BigDecimal monthValue;

    private BigDecimal singleValue;

    private Boolean active;

    @ManyToMany
    @JoinTable(name = "event_players",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    List<Player> players = new ArrayList<>();

    public Event(EventDTO record) {
        this.eventName = record.eventName();
        this.address = record.address();
        this.descriptionRules = record.descriptionRules();
        this.dateGame = Utilities.convertStringToDate(record.dateGame());
        this.hourGame = record.hourGame();
        this.duration = record.duration();
        this.sport = record.sport();
        this.modality = record.modality();
        this.vacancies = record.numberVacancies();
        this.monthValue = record.monthValue();
        this.singleValue = record.singleValue();
        this.active = Boolean.TRUE;
    }

    public void updateEvent(EventUpDTO record){
        this.eventName = record.name() != null ? record.name() : this.eventName;
        this.address = record.address() != null ? record.address() : this.address;
        this.descriptionRules = record.descriptionRules() != null ? record.descriptionRules() : this.descriptionRules;
        this.dateGame = record.dateGame() != null ? Utilities.convertStringToDate(record.dateGame()) : this.dateGame;
        this.hourGame = record.hourGame() != null ? record.hourGame() : this.hourGame;
        this.duration = record.duration() != null ? record.duration() : this.duration;
        this.vacancies = record.numberVacancies() != null && record.numberVacancies() > 0 ? record.numberVacancies() : this.vacancies;
        this.monthValue = record.monthValue() != null ? record.monthValue() : this.monthValue;
        this.singleValue = record.singleValue() != null ? record.singleValue() : this.singleValue;
    }
}
