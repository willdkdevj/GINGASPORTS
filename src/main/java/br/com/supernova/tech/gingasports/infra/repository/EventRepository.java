package br.com.supernova.tech.gingasports.infra.repository;

import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.event.dao.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByEventName(String name);

    Event findByEventNameEqualsAndActiveTrue(String eventName);

    Page<Event> findAllByActiveTrue(Pageable page);

    List<Event> findAllBySportEqualsAndActiveTrue(Sport sport);


}
