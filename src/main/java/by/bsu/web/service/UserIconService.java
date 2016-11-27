package by.bsu.web.service;

import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserIcon;

/**
 * Created by USER on 08.11.2016.
 */
public interface UserIconService {
    UserIcon saveWithRandomName(UserIcon icon);
    UserIcon findByIconName(String name);
    void delete(UserIcon icon);
    UserIcon findDefaultIcon();

    void addIconWhenRegister(UserData userData);
    boolean editIcon(UserData current, UserIcon newIcon);
}
