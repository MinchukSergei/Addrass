package by.bsu.web.service.impl;

import by.bsu.web.entity.UserData;
import by.bsu.web.repository.UserDataRepository;
import by.bsu.web.service.UserDataService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserDataServiceImpl implements UserDataService {
    @PersistenceContext
    private EntityManager em;


    @Autowired
    private UserDataRepository userDataRepository;

    public List<UserData> findAll() {
        return Lists.newArrayList(userDataRepository.findAll());
    }

    public UserData findByUserLogin(String userLogin) {
        return userDataRepository.findByUserLogin(userLogin);
    }

    @Override
    public UserData save(UserData userData) {
        return userDataRepository.save(userData);
    }

    @Override
    public void delete(UserData userData) {
        userDataRepository.delete(userData);
    }
}
