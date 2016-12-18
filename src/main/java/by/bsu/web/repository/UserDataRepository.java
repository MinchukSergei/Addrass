package by.bsu.web.repository;

import by.bsu.web.entity.UserData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDataRepository extends CrudRepository<UserData, Long> {
    UserData findByUserLogin(String userLogin);

    @Query("SELECT ud FROM UserData ud JOIN FETCH ud.contacts WHERE ud.pkId = (:id)")
    UserData findUserDateByIdAndFetchContactList(@Param(value = "id") Long id);

    @Query("SELECT ud FROM UserData ud JOIN FETCH ud.friends WHERE ud.pkId = (:id)")
    UserData findUserDateByIdAndFetchFriendList(@Param(value = "id") Long id);

    @Query("SELECT ud FROM UserData ud JOIN FETCH ud.ownEvents WHERE ud.pkId = (:id)")
    UserData findUserDateByIdAndFetchOwnEvents(@Param(value = "id") Long id);
}
