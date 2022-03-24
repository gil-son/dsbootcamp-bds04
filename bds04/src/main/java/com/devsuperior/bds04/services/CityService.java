package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    @Transactional(readOnly = true)
    public Page<CityDTO> findAllPaged(Pageable pageable){ // PageRequest pageRequest
        Page<City> page = repository.findAll(pageable);
        return page.map(x -> new CityDTO(x));
    }
}
