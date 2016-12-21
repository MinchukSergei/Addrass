package by.bsu.web.service;

import by.bsu.web.controller.util.EventCount;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;

import java.util.List;
import java.util.Set;

public interface UserEventService {
    UserEvent findByPkId(Long id);
    void delete(Long id);
    UserEvent findByUserOwnerAndPkId(UserData owner, Long pkId);
    UserEvent save(UserEvent userEvent);
    UserEvent findUserEventByIdAndFetchMembers(Long id);

    Set<UserEvent> findSharedEventsBetweenDatesByUserId(String dateFrom, String dateTo, String format, UserData user);
    List<EventCount> findCountSharedEventsBetweenDatesByUserId(String dateFrom, String dateTo, String format, UserData user);
    Set<UserEvent> findSharedEventsWithFriend(UserData owner, UserData friend);
}
