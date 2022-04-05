package com.devsuperior.bds04.resources;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class EventResource {

    @Autowired
    EventService service;

    // PAGED
    @GetMapping("/paged")
    public ResponseEntity<Page<EventDTO>> findAllPaged(Pageable pageable){
        Page<EventDTO> getPage = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(getPage);
    }

    // LIST
    @GetMapping
    public ResponseEntity<List<EventDTO>> findAllList(){
        List<EventDTO> getList = service.findAllList();
        return ResponseEntity.ok().body(getList);
    }



}
