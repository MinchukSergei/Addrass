package by.bsu.web.service;


import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;

public interface FriendListService {
    FriendList findByPkId(Long id);
    FriendList save(FriendList friendList);
    FriendList findByFkUserMainAndFkUserFriendEntity(UserData owner, UserData friend);
    void delete(Long id);
}
