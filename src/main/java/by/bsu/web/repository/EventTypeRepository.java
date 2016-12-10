package by.bsu.web.repository;

import by.bsu.web.entity.EventType;
import org.springframework.data.repository.CrudRepository;


public interface EventTypeRepository extends CrudRepository<EventType, Byte> {
    EventType findByName(String name);
}
