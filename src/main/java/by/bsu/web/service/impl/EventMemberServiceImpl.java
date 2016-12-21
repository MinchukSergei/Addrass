package by.bsu.web.service.impl;

import by.bsu.web.entity.EventMember;
import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;
import by.bsu.web.repository.EventMemberRepository;
import by.bsu.web.service.EventMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventMemberServiceImpl implements EventMemberService {
    @Autowired
    private EventMemberRepository eventMemberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Long pkId) {
        eventMemberRepository.delete(pkId);
    }

    @Override
    public List<UserData> findFriendNotIncludeToEvent(Long ownerId, Long eventId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createTupleQuery();

        Root<EventMember> em = query.from(EventMember.class);
        query.multiselect(em.get("fkUserId"));

        query.where(builder.equal(em.get("fkEventId"), eventId));
        List<Tuple> eventMembers = entityManager.createQuery(query).getResultList();
        List<UserData> includedFriendsInEvent = new ArrayList<>();
        for (Tuple t : eventMembers) {
            includedFriendsInEvent.add((UserData) t.get(0));
        }

        CriteriaQuery<Tuple> mainQuery = builder.createTupleQuery();
        Root<FriendList> fl = mainQuery.from(FriendList.class);
        mainQuery.multiselect(fl.get("fkUserFriend"));

        mainQuery.where(builder.and(
                builder.equal(fl.get("fkUserMain"), ownerId),
                builder.not(fl.get("fkUserFriend").in(includedFriendsInEvent))
        ));

        List<Tuple> notInEventMembers = entityManager.createQuery(mainQuery).getResultList();
        List<UserData> friendList = new ArrayList<>();

        for (Tuple t : notInEventMembers) {
            friendList.add((UserData)t.get(0));
        }
        return friendList;
    }

}
