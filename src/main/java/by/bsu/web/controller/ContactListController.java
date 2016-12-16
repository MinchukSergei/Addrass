package by.bsu.web.controller;

import by.bsu.web.entity.ContactList;
import by.bsu.web.entity.UserColor;
import by.bsu.web.entity.UserData;
import by.bsu.web.service.ContactListService;
import by.bsu.web.service.UserColorService;
import by.bsu.web.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/contact")
public class ContactListController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserColorService userColorService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private ContactListService contactListService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addContact(@RequestBody ContactList contactList) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchContactList(currentUser.getPkId());
        Set<ContactList> contacts;
        if (fetchedUser == null) {
            contacts = new HashSet<>();
        } else {
            contacts = fetchedUser.getContacts();
        }

        UserColor color = null;
        if (contactList.getFkUserColor() != null) {
            color = userColorService.findByName(contactList.getFkUserColor().getName());
        }

        userDataService.save(contactList.getFkUserFriend());
        contactList.setFkUserColor(color);
        contactList.setFkUserMain(currentUser);
        contacts.add(contactList);
        currentUser.setContacts(contacts);
        userDataService.save(currentUser);
        status = HttpStatus.OK;

        return new ResponseEntity(status);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<ContactList>> getAllContacts() {
        UserData currentUser = sessionController.getAuthorizedUser();
        UserData fetchedUser = userDataService.findUserDateByIdAndFetchContactList(currentUser.getPkId());
        Set<ContactList> contacts = new HashSet<>();

        if (fetchedUser != null) {
            contacts = fetchedUser.getContacts();
        }

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity editContact(@RequestBody ContactList contactList) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchContactList(currentUser.getPkId());
        Set<ContactList> contacts = new HashSet<>();
        if (fetchedUser != null) {
            contacts = fetchedUser.getContacts();
        }

        UserData editableUser = userDataService.findByPkId(contactList.getFkUserFriend().getPkId());

        UserColor color = null;
        if (contactList.getFkUserColor() != null) {
            color = userColorService.findByName(contactList.getFkUserColor().getName());
        }

        if (editableUser != null) {
            ContactList oldContactList = contactListService.findByOwnerAndByFriend(currentUser, editableUser);

            if (contacts.contains(oldContactList)) {
                contacts.remove(oldContactList);

                userDataService.save(contactList.getFkUserFriend());
                oldContactList.setFkUserColor(color);
                oldContactList.setFkUserFriend(contactList.getFkUserFriend());
                contacts.add(oldContactList);
                currentUser.setContacts(contacts);
                userDataService.save(currentUser);
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.FORBIDDEN;
            }
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity(status);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteContactById(@PathVariable Long id) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchContactList(currentUser.getPkId());
        Set<ContactList> contacts = new HashSet<>();

        if (fetchedUser != null) {
            contacts = fetchedUser.getContacts();
        }

        UserData deletedUser = userDataService.findByPkId(id);
        if (deletedUser != null) {
            ContactList oldContactList = contactListService.findByOwnerAndByFriend(currentUser, deletedUser);
            if (contacts.contains(oldContactList)) {
                userDataService.delete(deletedUser);
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.FORBIDDEN;
            }
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity(status);
    }
}
