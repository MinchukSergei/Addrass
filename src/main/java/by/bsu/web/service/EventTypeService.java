package by.bsu.web.service;

import by.bsu.web.entity.EventType;

public interface EventTypeService {
    EventType findByName(String name);
}
