package by.bsu.web.repository;


import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendListRepository extends CrudRepository<FriendList, Long> {
    FriendList findByFkUserMainAndFkUserFriend(UserData owner, UserData friend);
}
