package by.bsu.web.controller;

import by.bsu.web.entity.ContactList;
import by.bsu.web.entity.UserColor;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserIcon;
import by.bsu.web.repository.ContactListRepository;
import by.bsu.web.repository.UserDataRepository;
import by.bsu.web.service.UserColorService;
import by.bsu.web.service.UserIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactListController {
    @Autowired
    private UserColorService userColorService;

    @Autowired
    private ContactListRepository contactListRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserIconService userIconService;

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
        userIconService.addIconWhenRegister(contactList.getFkUserFriend());
        UserColor color = userColorService.findByName(contactList.getFkUserColorEntity().getName());
        contactList.setFkUserColor(color.getPkId());
        contactList.setFkUserColorEntity(null);
        contactList.setFkUserMain(currentUser.getPkId());
        contactListRepository.save(contactList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<String> updateLocalContact(@RequestBody ContactList newContactList) {
        UserData currentUser = sessionController.getAuthorizedUser();
        UserData userData = newContactList.getFkUserFriend();
        ContactList contactList = contactListRepository.findByFkUserMainAndFkUserFriend(currentUser.getPkId(), userData);
        if (contactList != null) {
            UserData current = contactList.getFkUserFriend();
            UserIcon newIcon = userData.getFkUserPhotoEntity();
            boolean deleteIcon = userIconService.editIcon(current, newIcon);

            userDataRepository.save(current);
            contactList.setFkUserFriend(current);

            UserColor color = userColorService.findByName(newContactList.getFkUserColorEntity().getName());
            contactList.setFkUserColor(color.getPkId());
            contactList.setFkUserColorEntity(null);
            contactListRepository.save(contactList);
            if (deleteIcon) {
                userIconService.delete(current.getFkUserPhotoEntity());
            }
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
            UserData current = contactList.getFkUserFriend();
            if (current.getFkUserPhotoEntity().getIconName().equals(UserIcon.DEFAULT_ICON_NAME)) {
                current.setFkUserPhotoEntity(null);
            }
            userDataRepository.delete(userId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
