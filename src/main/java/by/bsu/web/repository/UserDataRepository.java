package by.bsu.web.repository;

import by.bsu.web.entity.UserData;
import org.springframework.data.repository.CrudRepository;

public interface UserDataRepository extends CrudRepository<UserData, Integer> {
    UserData findByUserLogin(String userLogin);
}
