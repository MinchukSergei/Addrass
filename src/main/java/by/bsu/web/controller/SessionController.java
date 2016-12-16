package by.bsu.web.controller;


import by.bsu.web.entity.UserData;
import by.bsu.web.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionController {
    @Autowired
    private UserDataService userDataService;

    public UserData getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserData userData = null;
        User user;
        if (authentication.getPrincipal() instanceof User) {
            user = (User) authentication.getPrincipal();
            userData = userDataService.findByUserLogin(user.getUsername());
        }
        return userData;
    }
}
