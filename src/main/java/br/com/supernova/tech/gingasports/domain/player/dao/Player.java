package br.com.supernova.tech.gingasports.domain.player.dao;

import br.com.supernova.tech.gingasports.domain.Utilities;
import br.com.supernova.tech.gingasports.domain.event.dao.Event;
import br.com.supernova.tech.gingasports.domain.player.dto.PlayerDTO;
import br.com.supernova.tech.gingasports.domain.player.dto.PlayerUpDTO;
import br.com.supernova.tech.gingasports.domain.team.dao.Team;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Table(name = "players")
@Entity(name = "Player")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Player {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String email;

    private String alias;

    @Column(name = "birthdate", columnDefinition = "DATE", nullable = true)
    private LocalDate birthDate;

    private String phone;

    @Embedded
    private Availibility availibility;

    @Embedded
    private Preference preference;

    private Boolean active;

    @ManyToMany(mappedBy = "players")
    private List<Team> teams;

    @ManyToMany(mappedBy = "players")
    private List<Event> events;

    public Player(PlayerDTO record){
        this.name = record.name();
        this.email = record.email();
        this.alias = record.alias();
        this.birthDate = Utilities.convertStringToDate(record.birthDate());
        this.phone = record.phone();
        this.availibility = new Availibility(record.availibilityDTO());
        this.preference = new Preference(record.preferenceDTO());
        this.active = Boolean.TRUE;
    }

    public void updatePlayer(PlayerUpDTO upRecord){
        this.name = upRecord.name().isEmpty() || upRecord.name() != null ? upRecord.name() : this.name;
        this.email = upRecord.email().isEmpty() || upRecord.email() != null ? upRecord.email() : this.email;
        this.alias = upRecord.alias().isEmpty() || upRecord.alias() != null ? upRecord.alias() : this.alias;
        this.phone = upRecord.phone().isEmpty() || upRecord.phone() != null ? upRecord.phone() : this.phone;
    }

    public void setActive(Boolean bool){
        this.active = bool;
    }

}
