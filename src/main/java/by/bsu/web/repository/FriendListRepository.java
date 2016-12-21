package by.bsu.web.repository;


import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;
import org.springframework.data.repository.CrudRepository;

public interface FriendListRepository extends CrudRepository<FriendList, Long> {
    FriendList findByFkUserMainAndFkUserFriend(UserData owner, UserData friend);
}
