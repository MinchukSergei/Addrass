package by.bsu.web.service.impl;

import by.bsu.web.entity.UserColor;
import by.bsu.web.repository.UserColorRepository;
import by.bsu.web.service.UserColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserColorServiceImpl implements UserColorService {
    @Autowired
    private UserColorRepository userColorRepository;

    @Override
    public UserColor findUserColorByPkId(Byte id) {
        return userColorRepository.findUserColorByPkId(id);
    }

    @Override
    public UserColor findByName(String name) {
        return userColorRepository.findUserColorByName(name);
    }
}
