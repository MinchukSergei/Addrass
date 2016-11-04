package by.bsu.web.repository;

import by.bsu.web.entity.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by USER on 04.11.2016.
 */
public interface UserDataRepository extends CrudRepository<UserData, Integer> {
    UserData findByUserLogin(String userLogin);
}
