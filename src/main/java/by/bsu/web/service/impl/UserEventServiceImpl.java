package by.bsu.web.service.impl;

import by.bsu.web.controller.util.EventCount;
import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.EventMember;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.repository.UserEventRepository;
import by.bsu.web.service.UserEventService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class UserEventServiceImpl implements UserEventService {
    @Autowired
    private UserEventRepository userEventRepository;

    @Override
    public UserEvent findByPkId(Long id) {
        return userEventRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        userEventRepository.delete(id);
    }

    @Override
    public UserEvent findByUserOwnerAndPkId(UserData owner, Long pkId) {
        return userEventRepository.findByUserOwnerAndPkId(owner, pkId);
    }

    @Override
    public UserEvent findUserEventByIdAndFetchMembers(Long id) {
        return userEventRepository.findUserEventByIdAndFetchMembers(id);
    }

    @Override
    public Set<UserEvent> findSharedEventsBetweenDatesByUserId(String dateFrom, String dateTo, String format, UserData user) {
        return userEventRepository.findSharedEventsBetweenDatesByUserId(dateFrom, dateTo, format, user);
    }

    @Override
    public List<EventCount> findCountSharedEventsBetweenDatesByUserId(String dateFrom, String dateTo, String format, UserData user) {
        Set<Object[]> countSharedEvents = userEventRepository.findCountSharedEventsBetweenDatesByUserId(dateFrom, dateTo, format, user);
        List<EventCount> eventCounts = new ArrayList<>();

        Map<String, Long> eventOwnCountMap = new HashMap<>();
        Map<String, Long> eventSharedCountMap = new HashMap<>();

        for (Object[] oo : countSharedEvents) {
            String date = (String) oo[0];
            Long count = (Long) oo[1];
            UserData owner = (UserData) oo[2];

            if (owner.getPkId().equals(user.getPkId())) {
                eventOwnCountMap.put(date,
                        eventOwnCountMap.get(date) == null ? 1 :
                                eventOwnCountMap.get(date) + count);
            } else {
                eventSharedCountMap.put(date,
                        eventSharedCountMap.get(date) == null ? 1 :
                                eventSharedCountMap.get(date) + count);
            }
        }

        for (Map.Entry<String, Long> entry : eventOwnCountMap.entrySet()) {
            EventCount eventCount = new EventCount();
            eventCount.setDate(entry.getKey());
            eventCount.setEventCount(entry.getValue());
            eventCount.setOwner(true);
            eventCounts.add(eventCount);
        }

        for (Map.Entry<String, Long> entry : eventSharedCountMap.entrySet()) {
            EventCount eventCount = new EventCount();
            eventCount.setDate(entry.getKey());
            eventCount.setEventCount(entry.getValue());
            eventCount.setOwner(false);
            eventCounts.add(eventCount);
        }
        return eventCounts;
    }

    @Override
    public Set<UserEvent> findSharedEventsWithFriend(UserData owner, UserData friend) {
        return userEventRepository.findSharedEventsWithFriend(owner, friend);
    }

    @Override
    public UserEvent save(UserEvent userEvent) {
        return userEventRepository.save(userEvent);
    }



}

