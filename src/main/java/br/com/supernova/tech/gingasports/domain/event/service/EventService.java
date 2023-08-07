package br.com.supernova.tech.gingasports.domain.event.service;

import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.event.dto.EventDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.EventUpDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.ReadResponseDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.ResponseMapDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    ResponseDTO save(EventDTO record);

    ResponseMapDTO update(EventUpDTO record);

    void delete(Long id);

    ReadResponseDTO read(String alias);

    Page<ResponseMapDTO> readList(Pageable record);

    List<ResponseMapDTO> readListBySport(Sport sport);
}
