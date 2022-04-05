package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class EventService {

    @Autowired
    private EventRepository repository;

    // LIST
    public List<EventDTO> findAllList(){
        List<Event> EventList = repository.findAll();
        //for(Event c : EventList){dto.add(new EventDTO(c));}
        return EventList.stream().map( x -> new EventDTO(x)).collect(Collectors.toList());
    }

    // PAGED
    @Transactional(readOnly = true)
    public Page<EventDTO> findAllPaged(Pageable pageable){ // PageRequest pageRequest
        Page<Event> page = repository.findAll(pageable);
        return page.map(x -> new EventDTO(x));
    }
}
