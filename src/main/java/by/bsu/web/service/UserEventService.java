package by.bsu.web.service;

import by.bsu.web.entity.UserEvent;

import java.util.List;

public interface UserEventService {
    UserEvent findByPkId(Long id);
//    List<EventMonthCount> findMonthCountEvent(Integer month, Integer year, Long owner);
    void delete(Long id);
    UserEvent findByEventOwnerAndPkId(Long owner, Long pkId);
    List<UserEvent> findByFkOwnerAndDate(Long owner, String date);
    UserEvent save(UserEvent userEvent);
}
