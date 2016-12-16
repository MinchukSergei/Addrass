package by.bsu.web.service;

import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;

import java.util.List;
import java.util.Set;

public interface UserDataService {
    List<UserData> findAll();
    UserData findByUserLogin(String userLogin);
    UserData save(UserData userData);
    void delete(UserData userData);
    UserData findByPkId(Long id);

    UserData findUserDateByIdAndFetchContactList(Long id);
    UserData findUserDateByIdAndFetchFriendList(Long id);
}
