package by.bsu.web.api;

import by.bsu.web.api.util.ErrorEntity;
import by.bsu.web.controller.SessionController;
import by.bsu.web.entity.ContactList;
import by.bsu.web.entity.UserData;
import by.bsu.web.repository.ContactListRepository;
import by.bsu.web.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ContactListRepository contactListRepository;

    @Autowired
    private SessionController sessionController;

    @RequestMapping(value = "/current", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    UserData getCurrentUser() {
        return sessionController.getAuthorizedUser();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> createLocalContact(@RequestBody UserData userData) {
        userDataRepository.save(userData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    UserData getUserByLogin(@PathVariable String login) {
        UserData fetchedData = userDataRepository.findByUserLogin(login);

        if (fetchedData == null) {
            throw new UserNotFoundException(login);
        }

        return fetchedData;

    }


    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "text/html")
    public String getUserByLoginWithView(Model model, @PathVariable String login) {

        try {
            UserData fetchedUser = getUserByLogin(login);
            model.addAttribute("fetchedUser", fetchedUser);
        } catch (UserNotFoundException ex) {

            model.addAttribute("error", ex.getMessage());
        }

        return "User";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void registerUser(@RequestBody UserData userData) {

        String userLogin = userData.getUserLogin();

        if (getUserByLogin(userLogin) != null) {
            throw new UserAlreadyExistsException(userLogin);
        }

        userDataRepository.save(userData);

    }


    private class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String userLogin) {
            super("User not found '" + userLogin + "'.");
        }
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public
    @ResponseBody
    ErrorEntity userNotFoundHandler(UserNotFoundException ex) {
        return new ErrorEntity(ex.getMessage());
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    private class UserAlreadyExistsException extends RuntimeException {

        public UserAlreadyExistsException(String userLogin) {
            super("User already exists '" + userLogin + "'.");
        }

    }
}
