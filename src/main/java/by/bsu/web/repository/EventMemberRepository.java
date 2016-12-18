package by.bsu.web.repository;

import by.bsu.web.entity.EventMember;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.PersistenceContext;
import java.util.List;


public interface EventMemberRepository extends CrudRepository<EventMember, Long> {


}
