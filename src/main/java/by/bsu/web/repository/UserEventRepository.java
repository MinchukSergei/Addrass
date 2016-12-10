package by.bsu.web.repository;


import by.bsu.web.entity.UserEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserEventRepository extends CrudRepository<UserEvent, Long> {

    @Query(value = "SELECT DAYOFMONTH(u.eventDateTime), COUNT(u.pkId) FROM UserEvent u" +
            " WHERE MONTH(u.eventDateTime) = ? AND YEAR(u.eventDateTime) = ? AND u.fkEventOwner = ? GROUP BY (DATE (u.eventDateTime))")
    List<Object[]> findMonthCountEvent(Integer month, Integer year, Long owner);


    UserEvent findByFkEventOwnerAndPkId(Long owner, Long pkId);

    @Query(value = "SELECT u FROM UserEvent u WHERE u.fkEventOwner   = ? " +
            " AND DATE(u.eventDateTime) = STR_TO_DATE(?, '%e/%c/%Y')")
    List<UserEvent> findByFkOwnerAndDate(Long id , String date);


}
