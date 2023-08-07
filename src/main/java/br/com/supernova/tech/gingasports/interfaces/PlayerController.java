package br.com.supernova.tech.gingasports.interfaces;

import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.player.dto.*;
import br.com.supernova.tech.gingasports.domain.player.service.PlayerService;
import br.com.supernova.tech.gingasports.interfaces.util.ControllerUtil;
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
@RequestMapping("v1/player")
@SecurityRequirement(name = "bearer-key")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @PostMapping("/save")
    @Transactional
    public ResponseEntity savePlayer(@RequestBody @Valid PlayerDTO playerDTO, UriComponentsBuilder uriBuilder) {
        ResponseDTO responseDTO = service.save(playerDTO);
        return ResponseEntity.created(uriBuilder.path("/player/{id}").buildAndExpand(responseDTO.id()).toUri()).body(responseDTO);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ResponseMapDTO> updatePlayer(@RequestBody @Valid PlayerUpDTO upRecord) {
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
    public Page<ResponseMapDTO> queriesPlayer(@PageableDefault(size = 5, sort = {"alias", "name"}) Pageable pageable) {
        return service.readList(pageable);
    }

     @GetMapping("/list/{sport}")
    public Page<ResponseMapDTO> queriesPlayer(@PageableDefault(size = 10, sort = {"sport", "alias"}) Pageable pageable, @PathVariable Sport sport) {
         return ControllerUtil.convertListByPage(pageable, service.readListBySport(sport));
    }
}
