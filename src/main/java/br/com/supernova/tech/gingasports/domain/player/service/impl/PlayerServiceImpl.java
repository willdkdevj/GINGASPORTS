package br.com.supernova.tech.gingasports.domain.player.service.impl;

import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.player.dto.*;
import br.com.supernova.tech.gingasports.domain.player.service.PlayerService;
import br.com.supernova.tech.gingasports.domain.player.dao.Player;
import br.com.supernova.tech.gingasports.infra.exception.QueryException;
import br.com.supernova.tech.gingasports.infra.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Override
    public ResponseDTO save(PlayerDTO record) {
        Player player = new Player(record);
        repository.save(player);
        return new ResponseDTO(player.getId(), player.getAlias());
    }

    @Override
    public ResponseMapDTO update(PlayerUpDTO record) {
        verifyId(record.id());
        Player player = new Player();
        player.updatePlayer(record);

        return  new ResponseMapDTO(player);
    }

    @Override
    public void delete(Long id) {
        verifyId(id);
        Player player = repository.getReferenceById(id);
        player.setActive(Boolean.FALSE);
    }

    @Override
    public ReadResponseDTO read(String alias) {
        return new ReadResponseDTO(repository.findByAlias(alias));
    }

    @Override
    public Page<ResponseMapDTO> readList(Pageable page) {
        Page<ResponseMapDTO> pages = repository.findAllByActiveTrue(page).map(ResponseMapDTO::new);
        if(pages.isEmpty())
            throw new QueryException("Não foi possível obter lista de jogadores!");
        return pages;
    }

    @Override
    public List<ResponseMapDTO> readListBySport(Sport sport) {
        return repository.findByActiveTrueAndPreference_SportEquals(sport).stream().map(ResponseMapDTO::new).toList();
    }

    private void verifyId(Long id) {
        if(!repository.existsById(id)) {
            throw new QueryException("Não foi possível localizar o registro");
        }
    }
}
