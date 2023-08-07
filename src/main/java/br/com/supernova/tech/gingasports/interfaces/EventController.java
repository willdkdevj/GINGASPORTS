package br.com.supernova.tech.gingasports.interfaces;

import br.com.supernova.tech.gingasports.domain.enums.Sport;
import br.com.supernova.tech.gingasports.domain.event.dto.EventDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.EventUpDTO;
import br.com.supernova.tech.gingasports.domain.event.service.EventService;
import br.com.supernova.tech.gingasports.domain.event.dto.ReadResponseDTO;
import br.com.supernova.tech.gingasports.domain.common.ResponseDTO;
import br.com.supernova.tech.gingasports.domain.event.dto.ResponseMapDTO;
import br.com.supernova.tech.gingasports.interfaces.util.ControllerUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("v1/event")
@SecurityRequirement(name = "bearer-key")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/save")
    @Transactional
    public ResponseEntity saveEvent(@RequestBody @Valid EventDTO eventDTO, UriComponentsBuilder uriBuilder) {
        ResponseDTO responseDTO = service.save(eventDTO);
        return ResponseEntity.created(uriBuilder.path("/event/{id}").buildAndExpand(responseDTO.id()).toUri()).body(responseDTO);
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<ResponseMapDTO> updatePlayer(@RequestBody @Valid EventUpDTO upRecord) {
        return ResponseEntity.ok(service.update(upRecord));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ReadResponseDTO> readPlayer(@PathVariable String name) {
        return ResponseEntity.ok(service.read(name));
    }

    @DeleteMapping("/inactive/{id}")
    @Transactional
    public ResponseEntity<ResponseDTO> deletePlayer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public Page<ResponseMapDTO> queriesPlayer(@PageableDefault(size = 5, sort = {"sport", "modality", "eventName"}) Pageable pageable) {
        return service.readList(pageable);
    }

    @GetMapping("/list/{sport}")
    public Page<ResponseMapDTO> queriesPlayer(@PageableDefault(size = 10, sort = {"sport", "modality"}) Pageable pageable, @PathVariable Sport sport) {
        List<ResponseMapDTO> responseMapDTOS = service.readListBySport(sport);
        return convertListByPage(pageable, responseMapDTOS);
    }

   private Page<ResponseMapDTO> convertListByPage(Pageable pageable, List<ResponseMapDTO> listResponse){
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), listResponse.size());
        final Page<ResponseMapDTO> page = new PageImpl<>(listResponse.subList(start, end), pageable, listResponse.size());

        return page;
    }
}
