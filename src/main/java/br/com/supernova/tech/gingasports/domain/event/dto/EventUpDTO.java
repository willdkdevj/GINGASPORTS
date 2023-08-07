package br.com.supernova.tech.gingasports.domain.event.dto;

import br.com.supernova.tech.gingasports.domain.Utilities;
import br.com.supernova.tech.gingasports.domain.event.dao.Event;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public record EventUpDTO(

        Long id,
        String name,

        String address,

        String descriptionRules,

        String dateGame,

        String hourGame,

        String duration,

        Integer numberVacancies,

        BigDecimal monthValue,

        BigDecimal singleValue

) {

    public EventUpDTO(Event event){
        this(event.getId(), event.getEventName(), event.getAddress(), event.getDescriptionRules(), event.getDateGame().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), event.getHourGame(), event.getDuration(), event.getVacancies(), event.getMonthValue(), event.getSingleValue());
    }
}
