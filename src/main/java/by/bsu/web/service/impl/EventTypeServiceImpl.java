package by.bsu.web.service.impl;

import by.bsu.web.entity.EventType;
import by.bsu.web.repository.EventTypeRepository;
import by.bsu.web.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeServiceImpl implements EventTypeService {
    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public EventType findByName(String name) {
        return eventTypeRepository.findByName(name);
    }
}
