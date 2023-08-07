package br.com.supernova.tech.gingasports.domain.team.service;

import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.team.dto.*;
import br.com.supernova.tech.gingasports.domain.team.dto.TeamDTO;
import br.com.supernova.tech.gingasports.domain.team.dto.TeamUpDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {

    ResponseDTO save(TeamDTO record);

    ResponseMapDTO update(TeamUpDTO record);

    void delete(Long id);

    ReadResponseDTO read(String alias);

    Page<ResponseMapDTO> readList(Pageable record);

}
