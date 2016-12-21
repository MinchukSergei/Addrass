package by.bsu.web.repository;


import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserEventRepository extends CrudRepository<UserEvent, Long> {
    UserEvent findByUserOwnerAndPkId(UserData owner, Long pkId);

    @Query("SELECT ue FROM UserEvent ue JOIN FETCH ue.members WHERE ue.pkId = (:id)")
    UserEvent findUserEventByIdAndFetchMembers(@Param(value = "id") Long id);

    @Query("SELECT ue FROM UserEvent ue LEFT JOIN ue.members em " +
            "WHERE (ue.userOwner = :user OR em.fkUserId = :user) " +
            "AND ue.eventDateTime BETWEEN STR_TO_DATE(:dateFrom, :format) " +
            "AND ADDDATE(STR_TO_DATE(:dateTo, :format), 1)")
    Set<UserEvent> findSharedEventsBetweenDatesByUserId(@Param("dateFrom") String dateFrom,
                                                        @Param("dateTo") String dateTo,
                                                        @Param("format") String dateFormat,
                                                        @Param("user") UserData user);

    @Query("SELECT DATE_FORMAT(ue.eventDateTime, :format), COUNT(ue.pkId), ue.userOwner " +
            "FROM UserEvent ue LEFT JOIN ue.members em " +
            "WHERE (ue.userOwner = :user OR em.fkUserId = :user) " +
            "AND ue.eventDateTime BETWEEN STR_TO_DATE(:dateFrom, :format) " +
            "AND ADDDATE(STR_TO_DATE(:dateTo, :format), 1)" +
            "GROUP BY DATE_FORMAT(ue.eventDateTime, :format), ue.userOwner")
    Set<Object[]> findCountSharedEventsBetweenDatesByUserId(@Param("dateFrom") String dateFrom,
                                                            @Param("dateTo") String dateTo,
                                                            @Param("format") String dateFormat,
                                                            @Param("user") UserData user);


    @Query("SELECT ue FROM UserEvent ue LEFT JOIN ue.members em " +
            "WHERE (ue.userOwner IN (:owner, :friend) AND em.fkUserId IN (:owner, :friend) " +
            "OR (em.fkUserId = :owner AND em.fkEventId IN " +
            "(SELECT em2.fkEventId FROM EventMember em2 WHERE em2.fkUserId = :friend)))")
    Set<UserEvent> findSharedEventsWithFriend(@Param("owner") UserData owner, @Param("friend") UserData friend);

}
