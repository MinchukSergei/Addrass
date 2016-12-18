package by.bsu.web.service;


import by.bsu.web.entity.UserData;

import java.util.List;

public interface EventMemberService {
    List<UserData> findFriendNotIncludeToEvent(Long ownerId, Long eventId);
    void delete(Long pkId);
}
