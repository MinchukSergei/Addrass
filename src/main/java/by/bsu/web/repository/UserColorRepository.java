package by.bsu.web.repository;

import by.bsu.web.entity.UserColor;
import org.springframework.data.repository.CrudRepository;


public interface UserColorRepository extends CrudRepository<UserColor, Byte> {
    UserColor findUserColorByPkId(Byte id);
}
