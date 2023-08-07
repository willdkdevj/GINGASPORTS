package br.com.supernova.tech.gingasports.domain.team.service.Impl;

import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.player.dao.Player;
import br.com.supernova.tech.gingasports.domain.team.dto.*;
import br.com.supernova.tech.gingasports.domain.team.service.TeamService;
import br.com.supernova.tech.gingasports.domain.team.dao.Team;
import br.com.supernova.tech.gingasports.infra.exception.QueryException;
import br.com.supernova.tech.gingasports.infra.repository.PlayerRepository;
import br.com.supernova.tech.gingasports.infra.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public ResponseDTO save(TeamDTO record) {
        Team team = new Team(record);
        team.getPlayers().addAll(verifyPlayers(team, record.players()));
        
        teamRepository.save(team);
        
        return new ResponseDTO(team.getId(), team.getName());
    }

    @Override
    public ResponseMapDTO update(TeamUpDTO record) {
        verifyId(record.id());
        Team team = new Team();
        team.updateTeam(record);
        return  new ResponseMapDTO(team);
    }

    @Override
    public void delete(Long id) {
        verifyId(id);
        Team team = teamRepository.getReferenceById(id);
        team.setActive(Boolean.FALSE);
    }

    @Override
    public ReadResponseDTO read(String name) {
        return new ReadResponseDTO(teamRepository.findByName(name));
    }

    @Override
    public Page<ResponseMapDTO> readList(Pageable record) {
        Page<ResponseMapDTO> pages = teamRepository.findAllByActiveTrue(record).map(ResponseMapDTO::new);
        if(pages.isEmpty())
            throw new QueryException("Não foi possível obter lista de times!");
        return pages;
    }

    private void verifyId(Long id) {
        if(!teamRepository.existsById(id)) {
            throw new QueryException("Não foi possível localizar o registro");
        }
    }
    
    
    private List<Player> verifyPlayers(Team team, List<PlayerIDDTO> idPlayers){
        List<Player> players = team.getPlayers();
        List<PlayerIDDTO> verifiedId = idPlayers.stream().filter(id -> playerRepository.existsById(Long.valueOf(id.player()))).toList();

        verifiedId.forEach(id -> {
            players.add(playerRepository.getReferenceById(Long.valueOf(id.player())));
        });

        return players;
    }
}
