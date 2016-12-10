package by.bsu.web.repository;

import by.bsu.web.entity.UserGroup;
import org.springframework.data.repository.CrudRepository;


public interface UserGroupRepository extends CrudRepository<UserGroup, Byte> {
    UserGroup findByName(String name);
}
