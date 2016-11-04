package by.bsu.web.api;

import by.bsu.web.entity.UserData;
import by.bsu.web.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserDataRepository userDataRepository;

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public UserData getUserById(@PathVariable String id) {

        return userDataRepository.findByUserLogin(id);

    }

}
