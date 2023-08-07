package br.com.supernova.tech.gingasports.domain.player.service;

import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.player.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {

    ResponseDTO save(PlayerDTO record);

    ResponseMapDTO update(PlayerUpDTO record);

    void delete(Long id);

    ReadResponseDTO read(String alias);

    Page<ResponseMapDTO> readList(Pageable record);

    List<ResponseMapDTO> readListBySport(Sport sport);
}
