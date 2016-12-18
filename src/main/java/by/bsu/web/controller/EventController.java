package by.bsu.web.controller;

import by.bsu.web.controller.util.EventCount;
import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.EventType;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.service.EventTypeService;
import by.bsu.web.service.UserEventService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private UserEventService userEventService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private EventTypeService eventTypeService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addEvent(@RequestBody UserEvent userEvent) {
        UserData current = sessionController.getAuthorizedUser();

        EventType eventType = eventTypeService.findByName(userEvent.getEventType().getName());
        if (eventType == null) {
            eventType = eventTypeService.findByName(EventType.DEFAULT_TYPE);
        }
        userEvent.setEventType(eventType);
        userEvent.setFkEventOwner(current.getPkId());

        userEventService.save(userEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> editEvent(@RequestBody UserEvent userEvent) {
        UserData current = sessionController.getAuthorizedUser();
        UserEvent exists = userEventService.findByEventOwnerAndPkId(current.getPkId(), userEvent.getPkId());
        HttpStatus result;

        if (exists != null) {
            EventType eventType = eventTypeService.findByName(userEvent.getEventType().getName());
            if (eventType != null) {
                exists.setEventType(eventType);
            }
            exists.setName(userEvent.getName());
            exists.setEventCoordinateX(userEvent.getEventCoordinateX());
            exists.setEventCoordinateY(userEvent.getEventCoordinateY());
            exists.setEventDateTime(userEvent.getEventDateTime());

            userEventService.save(exists);
            result = HttpStatus.OK;
        } else {
            result = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(result);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserEvent> getEventsByDate(@RequestParam("date") String date,
                                           @RequestParam(value = "id", required = false) Long friendId) {
        UserData current = sessionController.getAuthorizedUser();

        String[] filterObj = date.split("-");
        String[] paramTypes = {"year", "month", "day"};
        List<EventCountCriteria> criterias = new ArrayList<>();

        for (int i = 0; i < filterObj.length; i++) {
            criterias.add(new EventCountCriteria(paramTypes[i], Integer.valueOf(filterObj[i])));
        }

        return userEventService.findEventsByDate(criterias, current.getPkId(), friendId);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public List<EventCount> getEventsCountByDate(@RequestParam("date") String date,
                                                 @RequestParam(value = "id", required = false) Long friendId) {
        UserData current = sessionController.getAuthorizedUser();

        String[] filterObj = date.split("-");
        String[] paramTypes = {"year", "month", "day"};
        List<EventCountCriteria> criterias = new ArrayList<>();

        for (int i = 0; i < filterObj.length; i++) {
            criterias.add(new EventCountCriteria(paramTypes[i], Integer.valueOf(filterObj[i])));
        }
        List<UserEvent> eventList = userEventService.findEventsByDate(criterias, current.getPkId(), friendId);
        List<EventCount> eventCountList = new ArrayList<>();
        Map<Integer, Integer> ownEventCount = new HashMap<>();
        Map<Integer, Integer> memberEventCount = new HashMap<>();

        for (UserEvent e : eventList) {
            int day = e.getEventDateTime().get(Calendar.DAY_OF_MONTH);
            if (e.getFkEventOwner().equals(current.getPkId())) {
                ownEventCount.put(day, ownEventCount.get(day) == null ? 1 : ownEventCount.get(day) + 1);
            } else {
                memberEventCount.put(day, memberEventCount.get(day) == null ? 1 : memberEventCount.get(day) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> p : ownEventCount.entrySet()) {
            eventCountList.add(new EventCount(p.getKey(), p.getValue(), true));
        }

        for (Map.Entry<Integer, Integer> p : memberEventCount.entrySet()) {
            eventCountList.add(new EventCount(p.getKey(), p.getValue(), false));
        }

        return eventCountList;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEvent(@RequestParam Long id) {
        UserData current = sessionController.getAuthorizedUser();
        UserEvent userEvent = userEventService.findByEventOwnerAndPkId(current.getPkId(), id);
        HttpStatus result;
        if (userEvent != null) {
            userEventService.delete(id);
            result = HttpStatus.OK;
        } else {
            result = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(result);
    }
}
