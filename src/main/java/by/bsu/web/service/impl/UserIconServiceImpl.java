package by.bsu.web.service.impl;

import by.bsu.web.entity.UserIcon;
import by.bsu.web.repository.UserIconRepository;
import by.bsu.web.service.UserIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIconServiceImpl implements UserIconService {

    @Autowired
    private UserIconRepository userIconRepository;

    public UserIcon save(UserIcon icon) {
        return userIconRepository.save(icon);
    }
}
