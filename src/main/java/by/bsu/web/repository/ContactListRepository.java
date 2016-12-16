package by.bsu.web.repository;


import by.bsu.web.entity.ContactList;
import by.bsu.web.entity.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactListRepository extends CrudRepository<ContactList, Long> {
    List<ContactList> findByFkUserMain(Long id);
    ContactList findByFkUserMainAndFkUserFriend(UserData owner, UserData friend);
}
