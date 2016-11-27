package by.bsu.web.repository;

import by.bsu.web.entity.UserIcon;
import org.springframework.data.repository.CrudRepository;


public interface UserIconRepository extends CrudRepository<UserIcon, Long> {
    UserIcon findByIconName(String name);
}
