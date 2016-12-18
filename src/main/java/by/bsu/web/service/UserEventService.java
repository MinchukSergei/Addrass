package by.bsu.web.service;

import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserEventService {
    UserEvent findByPkId(Long id);
    void delete(Long id);
    UserEvent findByEventOwnerAndPkId(Long owner, Long pkId);
    UserEvent save(UserEvent userEvent);
    List<UserEvent> findEventsByDate(List<EventCountCriteria> params, Long ownerId, Long friendId);
    UserEvent findUserEventByIdAndFetchMembers(Long id);
}
