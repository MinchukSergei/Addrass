package by.bsu.web.service;


import by.bsu.web.entity.UserGroup;

public interface UserGroupService {
    UserGroup findByName(String name);
}
