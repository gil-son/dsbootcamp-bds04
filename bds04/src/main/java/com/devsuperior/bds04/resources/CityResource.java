package com.devsuperior.bds04.resources;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;
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
public class CityResource {

    @Autowired
    CityService service;


    // LIST
    @GetMapping
    public ResponseEntity<List<CityDTO>> findAllList(){
        List<CityDTO> getList = service.findAllList();
        return ResponseEntity.ok().body(getList);
    }


    // PAGED
    @GetMapping("/paged")
    public ResponseEntity<Page<CityDTO>> findAllPaged(Pageable pageable){
        Page<CityDTO> getPage = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(getPage);
    }






}
