package by.bsu.web.service;


import by.bsu.web.entity.ContactList;
import by.bsu.web.entity.UserData;

public interface ContactListService {
    ContactList findByOwnerAndByFriend(UserData owner, UserData friend);
}
