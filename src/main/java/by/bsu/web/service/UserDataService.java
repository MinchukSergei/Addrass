package by.bsu.web.service;

import by.bsu.web.entity.UserData;

import java.util.List;

public interface UserDataService {
    List<UserData> findAll();
    UserData findByUserLogin(String userLogin);
}
