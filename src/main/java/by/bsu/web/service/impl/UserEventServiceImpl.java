package by.bsu.web.service.impl;

import by.bsu.web.entity.UserEvent;
import by.bsu.web.repository.UserEventRepository;
import by.bsu.web.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventServiceImpl implements UserEventService {
    @Autowired
    private UserEventRepository userEventRepository;

    @Override
    public UserEvent findByPkId(Long id) {
        return userEventRepository.findOne(id);
    }

//    @Override
//    public List<EventMonthCount> findMonthCountEvent(Integer month, Integer year, Long owner) {
//        List<Object[]> monthCountEventObject = userEventRepository.findMonthCountEvent(month, year, owner);
//        List<EventMonthCount> monthCountEvent = new ArrayList<>();
//        for (Object[] o : monthCountEventObject) {
//            monthCountEvent.add(new EventMonthCount((Integer) o[0], (Long) o[1]));
//        }
//        return monthCountEvent;
//    }

    @Override
    public void delete(Long id) {
        userEventRepository.delete(id);
    }

    @Override
    public UserEvent findByEventOwnerAndPkId(Long owner, Long pkId) {
        return userEventRepository.findByFkEventOwnerAndPkId(owner, pkId);
    }

    @Override
    public List<UserEvent> findByFkOwnerAndDate(Long owner, String date) {
        return userEventRepository.findByFkOwnerAndDate(owner, date);
    }

    @Override
    public UserEvent save(UserEvent userEvent) {
        return userEventRepository.save(userEvent);
    }
}

