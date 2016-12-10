package by.bsu.web.service.impl;

import by.bsu.web.controller.util.EventCount;
import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.EventMember;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.service.EventMemberService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventMemberServiceImpl implements EventMemberService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEvent> findCountByCriteria(List<EventCountCriteria> params, Long ownerId, Long friendId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createTupleQuery();

        Root em = query.from(EventMember.class);
        Join ue = em.join("fkEventId");
        query.multiselect(ue);

        Predicate predicate = builder.conjunction();

        for (EventCountCriteria param : params) {
            predicate = builder.and(predicate,
                    builder.equal(
                            builder.function(param.getType(), Integer.class, ue.get("eventDateTime")),
                            param.getValue()
                    ));
        }
        //        (
//        (em.fk_user_id = 32 OR em.fk_user_id = 2)
//        OR (ue.fk_event_owner = 2 AND em.fk_user_id = 32)
//        OR (ue.fk_event_owner = 32 AND em.fk_user_id = 2)
//        )
//        ue.fk_event_owner = 32
//
        if (friendId != null) {
            predicate = builder.and(predicate,
                    builder.or(
                            builder.or(builder.equal(em.get("fkUserId"), ownerId),
                                    builder.equal(em.get("fkUserId"), friendId)),
                            builder.or(
                                    builder.and(builder.equal(ue.get("fkEventOwner"), friendId),
                                            builder.equal(em.get("fkUserId"), ownerId)),
                                    builder.and(builder.equal(ue.get("fkEventOwner"), ownerId),
                                            builder.equal(em.get("fkUserId"), friendId))
                            )
                    )
            );
        } else {
            predicate = builder.and(predicate,
                    builder.equal(ue.get("fkEventOwner"), ownerId)
                    );
        }
//        DATE(ue.event_datetime), ue.fk_event_owner;

        query.where(predicate);
        List<Tuple> tuples = entityManager.createQuery(query).getResultList();
        List<UserEvent> eventList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            eventList.add((UserEvent) tuple.get(0));
        }
        return eventList;
    }

    public List<EventCount> findEventMonthCount(Integer month, Integer year, Long userId) {
        List<Object[]> eventMonthCountObj = null;//eventMemberRepository.findEventMonthCount(month, year, userId);
        List<EventCount> eventMonthCount = new ArrayList<>();
        for (Object[] o : eventMonthCountObj) {
            boolean isOwner = false;
            if (o[2].equals(userId)) {
                isOwner = true;
            }
            eventMonthCount.add(new EventCount((Integer) o[0], (Long) o[1], isOwner));
        }
        return eventMonthCount;
    }
}
