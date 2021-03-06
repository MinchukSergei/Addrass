package by.bsu.web.controller;

import by.bsu.web.entity.UserData;
import by.bsu.web.service.UserDataService;
import by.bsu.web.util.Sha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private Sha256 sha256;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private SessionController sessionController;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity registerUser(@RequestBody UserData userData) {
        UserData exists = userDataService.findByUserLogin(userData.getUserLogin());
        HttpStatus status;

        if (exists != null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            userData.setUserPassword(sha256.hashString(userData.getUserPassword()));
            userDataService.save(userData);
            status = HttpStatus.OK;
        }

        return new ResponseEntity(status);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity editUser(@RequestBody UserData userData) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        if (userData.getUserPassword() != null) {
            currentUser.setUserPassword(sha256.hashString(userData.getUserPassword()));
        }
        currentUser.setUserAddressField(userData.getUserAddressField());
        currentUser.setUserEmailField(userData.getUserEmailField());
        currentUser.setUserName(userData.getUserName());
        currentUser.setUserNotesField(userData.getUserNotesField());
        currentUser.setUserOrganizationField(userData.getUserOrganizationField());
        currentUser.setUserPhoneField(userData.getUserPhoneField());
        userDataService.save(currentUser);

        status = HttpStatus.OK;

        return new ResponseEntity(status);
    }

    @RequestMapping(value = "/search/{login}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserData> getUserByLogin(@PathVariable(value = "login") String login) {
        UserData userData = userDataService.findByUserLogin(login);
        HttpStatus status;

        if (userData == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(userData, status);
    }

    @RequestMapping(value = "/{pkId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserData> getUserByPkId(@PathVariable(value = "pkId") Long pkId) {
        UserData userData = userDataService.findByPkId(pkId);
        HttpStatus status;

        if (userData == null) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(userData, status);
    }


    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteUser() {
        UserData currentUser = sessionController.getAuthorizedUser();

        userDataService.delete(currentUser);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity(status);
    }
}
