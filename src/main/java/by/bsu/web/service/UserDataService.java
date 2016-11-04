package by.bsu.web.service;

import by.bsu.web.entity.UserData;

import java.util.List;

/**
 * Created by USER on 04.11.2016.
 */
public interface UserDataService {
    List<UserData> findAll();
    UserData findByUserLogin(String userLogin);
}
