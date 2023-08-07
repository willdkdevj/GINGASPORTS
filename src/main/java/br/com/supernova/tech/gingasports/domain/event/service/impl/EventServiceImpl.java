package br.com.supernova.tech.gingasports.domain.event.service.impl;

import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.event.dao.Event;
import br.com.supernova.tech.gingasports.domain.event.dto.EventDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.EventUpDTO;
import br.com.supernova.tech.gingasports.domain.event.service.EventService;
import br.com.supernova.tech.gingasports.domain.event.dto.ReadResponseDTO;
import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.ResponseMapDTO;
import br.com.supernova.tech.gingasports.infra.exception.QueryException;
import br.com.supernova.tech.gingasports.infra.repository.EventRepository;
import br.com.supernova.tech.gingasports.infra.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private PlayerRepository playerRepository;

    public EventServiceImpl(@Autowired EventRepository eventRepository, @Autowired PlayerRepository playerRepository){
        this.eventRepository = eventRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public ResponseDTO save(EventDTO record) {
        Event event = new Event(record);
        eventRepository.save(event);
        return new ResponseDTO(event.getId(), event.getEventName());
    }

    @Override
    public ResponseMapDTO update(EventUpDTO record) {
        verifyId(record.id());
        Event event = new Event();
        event.updateEvent(record);

        return new ResponseMapDTO(event);
    }

    @Override
    public void delete(Long id) {
        verifyId(id);
        Event event = eventRepository.getReferenceById(id);
        event.setActive(Boolean.FALSE);
    }

    @Override
    public ReadResponseDTO read(String name) {
        return new ReadResponseDTO(eventRepository.findByEventNameEqualsAndActiveTrue(name));
    }

    @Override
    public Page<ResponseMapDTO> readList(Pageable page) {
        Page<ResponseMapDTO> pages = eventRepository.findAllByActiveTrue(page).map(ResponseMapDTO::new);
        if(pages.isEmpty())
            throw new QueryException("Não foi possível obter lista de eventos!");
        return pages;
    }

    @Override
    public List<ResponseMapDTO> readListBySport(Sport sport) {
        List<Event> eventList = eventRepository.findAllBySportEqualsAndActiveTrue(sport);
        return convertResponseDTO(eventList);
    }

    private void verifyId(Long id) {
        if(!eventRepository.existsById(id)) {
            throw new QueryException("Não foi possível localizar o registro");
        }
    }

    private List<ResponseMapDTO> convertResponseDTO(List<Event> events){
        List<ResponseMapDTO> responses = new ArrayList<>();
        events.stream().filter(event -> event != null).map(event -> {
            ResponseMapDTO responseMap = new ResponseMapDTO(event.getId(),
                    event.getEventName(),
                    event.getAddress(),
                    event.getDescriptionRules(),
                    event.getDateGame().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    event.getHourGame(),
                    event.getDuration(),
                    event.getVacancies(),
                    event.getMonthValue(),
                    event.getSingleValue());

            return responseMap;
        }).forEachOrdered(responseMapDTO -> {
            responses.add(responseMapDTO);
        });

        return responses;
    }
}
