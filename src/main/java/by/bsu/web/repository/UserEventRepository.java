package by.bsu.web.repository;


import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserEventRepository extends CrudRepository<UserEvent, Long> {
    UserEvent findByFkEventOwnerAndPkId(Long owner, Long pkId);

    @Query("SELECT ue FROM UserEvent ue JOIN FETCH ue.members WHERE ue.pkId = (:id)")
    UserEvent findUserEventByIdAndFetchMembers(@Param(value = "id") Long id);

}
