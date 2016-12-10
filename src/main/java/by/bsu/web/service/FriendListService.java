package by.bsu.web.service;


import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserGroup;

import java.util.List;

public interface FriendListService {
    List<FriendList> findByFkUserMainNotBlackList(Long id);
    List<FriendList> findByFkUserMainNotBlackListAndGroup(Long id, UserGroup group);
    List<FriendList> findByFkUserMainBlackList(Long id);
    List<FriendList> findByFkUserMain(Long id);
    FriendList save(FriendList friendList);
    FriendList findByFkUserMainAndFkUserFriendEntity(Long main, UserData friend);
    void delete(Long id);
}
