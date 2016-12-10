package by.bsu.web.service.impl;


import by.bsu.web.entity.UserGroup;
import by.bsu.web.repository.UserGroupRepository;
import by.bsu.web.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGroupServiceImpl implements UserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public UserGroup findByName(String name) {
        return userGroupRepository.findByName(name);
    }
}
