package by.bsu.web.controller;

import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserIcon;
import by.bsu.web.repository.UserDataRepository;
import by.bsu.web.repository.UserIconRepository;
import by.bsu.web.service.UserDataService;
import by.bsu.web.service.UserIconService;
import by.bsu.web.util.Sha256;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private Sha256 sha256;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserIconService userIconService;

    @Autowired
    private SessionController sessionController;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody UserData userData) {
        UserData exists = userDataService.findByUserLogin(userData.getUserLogin());

        HttpStatus result;
        if (exists == null) {
            userIconService.addIconWhenRegister(userData);
            userData.setUserPassword(sha256.hashString(userData.getUserPassword()));
            userDataService.save(userData);
            result = HttpStatus.CREATED;
        } else {
            result = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(result);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<String> editUser(@RequestBody UserData userData) {
        UserData current = sessionController.getAuthorizedUser();
        HttpStatus result;
        if (Objects.equals(current.getPkId(), userData.getPkId())) {
            UserIcon newIcon = userData.getFkUserPhotoEntity();
            boolean deleteIcon = userIconService.editIcon(current, newIcon);

            if (userData.getUserPassword() != null) {
                current.setUserPassword(sha256.hashString(userData.getUserPassword()));
            }

            userDataService.save(current);
            if (deleteIcon) { //delete previous photo if need
                userIconService.delete(current.getFkUserPhotoEntity());
            }
            result = HttpStatus.OK;
        } else {
            result = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>(result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteUser() {
        UserData current = sessionController.getAuthorizedUser();
        if (current.getFkUserPhotoEntity().getIconName().equals(UserIcon.DEFAULT_ICON_NAME)) {
            current.setFkUserPhotoEntity(null);
        }
        userDataService.delete(current);
        HttpStatus result = HttpStatus.OK;
        return new ResponseEntity<>(result);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public UserData getCurrentUser() {
        return sessionController.getAuthorizedUser();
    }

//    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "text/html")
//    public String getUserByLoginWithView(Model model, @PathVariable String login) {
//        try {
//            UserData fetchedUser = getUserByLogin(login);
//            model.addAttribute("fetchedUser", fetchedUser);
//        } catch (UserNotFoundException ex) {
//
//            model.addAttribute("error", ex.getMessage());
//        }
//        return "User";
//    }


//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public void registerUser(@RequestBody UserData userData) {
//        String userLogin = userData.getUserLogin();
//        if (getUserByLogin(userLogin) != null) {
//            throw new UserAlreadyExistsException(userLogin);
//        }
//        userDataRepository.save(userData);
//    }

//    private class UserNotFoundException extends RuntimeException {
//        public UserNotFoundException(String userLogin) {
//            super("User not found '" + userLogin + "'.");
//        }
//    }
//
//
//    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ErrorEntity userNotFoundHandler(UserNotFoundException ex) {
//        return new ErrorEntity(ex.getMessage());
//    }
//
//
//    @ResponseStatus(HttpStatus.CONFLICT)
//    private class UserAlreadyExistsException extends RuntimeException {
//        public UserAlreadyExistsException(String userLogin) {
//            super("User already exists '" + userLogin + "'.");
//        }
//    }
}
