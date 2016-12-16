package by.bsu.web.service.impl;

import by.bsu.web.entity.UserIcon;
import by.bsu.web.repository.UserIconRepository;
import by.bsu.web.service.UserIconService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIconServiceImpl implements UserIconService {
    private final static int ICON_NAME_LENGTH = 20;

    @Autowired
    private UserIconRepository userIconRepository;

    public UserIcon save(UserIcon icon) {
        return userIconRepository.save(icon);
    }

    @Override
    public UserIcon saveWithRandomName(UserIcon icon) {
        while (true) {
            String name = RandomStringUtils.randomAlphanumeric(ICON_NAME_LENGTH);
            UserIcon exists = userIconRepository.findByIconName(name);
            if (exists == null) {
                icon.setIconName(name);
                icon = userIconRepository.save(icon);
                break;
            }
        }
        return icon;
    }

    @Override
    public UserIcon findByIconName(String name) {
        return userIconRepository.findByIconName(name);
    }

    @Override
    public void delete(UserIcon icon) {
        userIconRepository.delete(icon);
    }

    @Override
    public UserIcon findByPkId(Long id) {
        return userIconRepository.findOne(id);
    }
}
