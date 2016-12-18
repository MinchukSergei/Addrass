package by.bsu.web.service.impl;

import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.EventMember;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.repository.UserEventRepository;
import by.bsu.web.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserEventServiceImpl implements UserEventService {
    @Autowired
    private UserEventRepository userEventRepository;

    @Override
    public UserEvent findByPkId(Long id) {
        return userEventRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        userEventRepository.delete(id);
    }

    @Override
    public UserEvent findByEventOwnerAndPkId(Long owner, Long pkId) {
        return userEventRepository.findByFkEventOwnerAndPkId(owner, pkId);
    }

    @Override
    public UserEvent findUserEventByIdAndFetchMembers(Long id) {
        return userEventRepository.findUserEventByIdAndFetchMembers(id);
    }

    @Override
    public UserEvent save(UserEvent userEvent) {
        return userEventRepository.save(userEvent);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEvent> findEventsByDate(List<EventCountCriteria> params, Long ownerId, Long friendId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createTupleQuery();

        Root<UserEvent> ue = query.from(UserEvent.class);
        Join<UserEvent, EventMember> em = ue.join("members", JoinType.LEFT);
        query.multiselect(ue).distinct(true);

        Predicate predicate = builder.conjunction();

        for (EventCountCriteria param : params) {
            predicate = builder.and(predicate,
                    builder.equal(
                            builder.function(param.getType(), Integer.class, ue.get("eventDateTime")),
                            param.getValue()
                    ));
        }

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
//            em.fk_user_id = 32 OR ue.fk_event_owner = 32
            predicate = builder.and(predicate,
                    builder.or(
                            builder.equal(ue.get("fkEventOwner"), ownerId),
                            builder.equal(em.get("fkUserId"), ownerId)
                    )
            );
        }

        query.where(predicate);
        List<Tuple> tuples = entityManager.createQuery(query).getResultList();
        List<UserEvent> eventList = new ArrayList<>();
        for (Tuple tuple : tuples) {
            eventList.add((UserEvent) tuple.get(0));
        }
        return eventList;
    }
}

