package by.bsu.web.service.impl;

import by.bsu.web.entity.ContactList;
import by.bsu.web.entity.UserData;
import by.bsu.web.repository.ContactListRepository;
import by.bsu.web.service.ContactListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactListServiceImpl implements ContactListService {
    @Autowired
    private ContactListRepository contactListRepository;

    @Override
    public ContactList findByOwnerAndByFriend(UserData owner, UserData friend) {
        return contactListRepository.findByFkUserMainAndFkUserFriend(owner, friend);
    }
}
