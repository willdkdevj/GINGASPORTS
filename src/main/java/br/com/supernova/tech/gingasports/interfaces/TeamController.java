package br.com.supernova.tech.gingasports.interfaces;

import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.team.dto.*;
import br.com.supernova.tech.gingasports.domain.team.dto.TeamDTO;
import br.com.supernova.tech.gingasports.domain.team.dto.TeamUpDTO;
import br.com.supernova.tech.gingasports.domain.team.service.TeamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1/team")
@SecurityRequirement(name = "bearer-key")
public class TeamController {

    @Autowired
    private TeamService service;

    @PostMapping("/save")
    @Transactional
    public ResponseEntity savePlayer(@RequestBody @Valid TeamDTO teamDTO, UriComponentsBuilder uriBuilder) {
        ResponseDTO responseDTO = service.save(teamDTO);
        return ResponseEntity.created(uriBuilder.path("/team/{id}").buildAndExpand(responseDTO.id()).toUri()).body(responseDTO);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ResponseMapDTO> updatePlayer(@RequestBody @Valid TeamUpDTO upRecord) {
        ResponseMapDTO update = service.update(upRecord);

        return ResponseEntity.ok(update);
    }

    @GetMapping("/search/{alias}")
    public ResponseEntity<ReadResponseDTO> readPlayer(@PathVariable String alias) {
        ReadResponseDTO read = service.read(alias);

        return ResponseEntity.ok(read);
    }

    @DeleteMapping("/inactive/{id}")
    @Transactional
    public ResponseEntity<ResponseDTO> deletePlayer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public Page<ResponseMapDTO> queriesTeam(@PageableDefault(size = 5, sort = {"name"}) Pageable pageable) {
        return service.readList(pageable);
    }
}
