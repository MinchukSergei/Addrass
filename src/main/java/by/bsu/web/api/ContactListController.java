package by.bsu.web.api;

import by.bsu.web.controller.SessionController;
import by.bsu.web.entity.ContactList;
import by.bsu.web.entity.UserColor;
import by.bsu.web.entity.UserData;
import by.bsu.web.repository.ContactListRepository;
import by.bsu.web.repository.UserDataRepository;
import by.bsu.web.service.UserColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactListController {
    @Autowired
    private ContactListRepository contactListRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private SessionController sessionController;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ContactList> getAllLocalContacts() {
        UserData currentUser = sessionController.getAuthorizedUser();
        return contactListRepository.findByFkUserMain(currentUser.getPkId());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> createContact(@RequestBody ContactList contactList) {
        UserData currentUser = sessionController.getAuthorizedUser();
        contactList.setFkUserMain(currentUser.getPkId());
        contactListRepository.save(contactList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<String> updateLocalContact(@RequestBody UserData userData) {
        UserData currentUser = sessionController.getAuthorizedUser();
        ContactList contactList = contactListRepository.findByFkUserMainAndFkUserFriend(currentUser.getPkId(), userData);
        if (contactList != null) {
            userDataRepository.save(userData);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteLocalContact(@RequestParam Long userId) {
        UserData currentUser = sessionController.getAuthorizedUser();
        UserData friend = new UserData();
        friend.setPkId(userId);
        ContactList contactList = contactListRepository.findByFkUserMainAndFkUserFriend(currentUser.getPkId(), friend);
        if (contactList != null) {
            userDataRepository.delete(userId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
