package by.bsu.web.service;


import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.UserEvent;

import java.util.List;

public interface EventMemberService {
    List<UserEvent> findCountByCriteria(List<EventCountCriteria> params, Long ownerId, Long userId);
}
