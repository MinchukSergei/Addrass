package by.bsu.web.repository;

import by.bsu.web.entity.EventMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.PersistenceContext;
import java.util.List;


public interface EventMemberRepository extends CrudRepository<EventMember, Long> {

    @Query("SELECT DAY(ue.eventDateTime), COUNT(ue.pkId), ue.fkEventOwner " +
    " FROM EventMember em LEFT JOIN em.fkEventId ue" +
            " ON ue.pkId = em.fkEventId " +
            " WHERE ue.fkEventOwner = ?1 OR em.fkUserId = ?2 " +
            " AND DAY(ue.eventDateTime) = ?4 " +
            " AND MONTH(ue.eventDateTime) = ?3 " +
            " AND YEAR(ue.eventDateTime) = ?2 " +
            " GROUP BY DATE(ue.eventDateTime), ue.fkEventOwner")
    List<Object[]> findEventCount(Long userId, Integer year, Integer month, Integer day);


}
