package by.bsu.web.service.impl;

import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserIcon;
import by.bsu.web.repository.UserIconRepository;
import by.bsu.web.service.UserIconService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIconServiceImpl implements UserIconService {
    private final static int iconNameLength = 20;

    @Autowired
    private UserIconRepository userIconRepository;

    public UserIcon save(UserIcon icon) {
        return userIconRepository.save(icon);
    }

    @Override
    public UserIcon saveWithRandomName(UserIcon icon) {
        UserIcon fetchedIcon = userIconRepository.findByIconName(icon.getIconName());
        if (fetchedIcon != null) {
            if (!fetchedIcon.getIconName().equals(UserIcon.DEFAULT_ICON_NAME)) {
                icon.setPkId(fetchedIcon.getPkId());
            }
        }
        while (true) {
            String name = RandomStringUtils.randomAlphanumeric(iconNameLength);
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
    public UserIcon findDefaultIcon() {
        return userIconRepository.findByIconName(UserIcon.DEFAULT_ICON_NAME);
    }

    @Override
    public void addIconWhenRegister(UserData userData) {
        UserIcon icon = userData.getFkUserPhotoEntity();

        if (icon == null) {
            icon = findByIconName(UserIcon.DEFAULT_ICON_NAME);
        } else {
            icon = saveWithRandomName(icon);
            userData.setFkUserPhotoEntity(null);
        }

        userData.setUserPhoto(icon.getPkId());
    }

    @Override
    public boolean editIcon(UserData current, UserIcon newIcon) { //return true if we need to delete photo
        boolean deleteIcon = false;
        UserIcon prevIcon = current.getFkUserPhotoEntity();
        if (newIcon.getIconName() == null) {
            if (!prevIcon.getIconName().equals(UserIcon.DEFAULT_ICON_NAME)) {
                deleteIcon = true;
                current.setUserPhoto(findDefaultIcon().getPkId());
            }
        } else {
            if (newIcon.getIconBytes() != null) {
                newIcon = saveWithRandomName(newIcon);
                current.setUserPhoto(newIcon.getPkId());
            }
        }
        current.setFkUserPhotoEntity(null);
        return deleteIcon;
    }
}
