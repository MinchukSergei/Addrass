package by.bsu.web.controller;

import by.bsu.web.controller.util.DateValidator;
import by.bsu.web.controller.util.EventCount;
import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.EventType;
import by.bsu.web.entity.FriendList;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.service.EventTypeService;
import by.bsu.web.service.UserDataService;
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
    private static final String DATE_FORMAT = "%d-%m-%Y";

    @Autowired
    private UserEventService userEventService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private EventTypeService eventTypeService;

    @Autowired
    private DateValidator dateValidator;

    @Autowired
    private UserDataService userDataService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addEvent(@RequestBody UserEvent userEvent) {
        UserData current = sessionController.getAuthorizedUser();

        EventType eventType = eventTypeService.findByName(userEvent.getEventType().getName());
        if (eventType == null) {
            eventType = eventTypeService.findByName(EventType.DEFAULT_TYPE);
        }
        userEvent.setEventType(eventType);
        userEvent.setUserOwner(current);

        userEventService.save(userEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> editEvent(@RequestBody UserEvent userEvent) {
        UserData current = sessionController.getAuthorizedUser();
        UserEvent exists = userEventService.findByUserOwnerAndPkId(current, userEvent.getPkId());
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

    @RequestMapping(value = "/{from}/{to}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<UserEvent>> getEventsBetweenDates(@PathVariable("from") String from,
                                                                @PathVariable("to") String to) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;
        Set<UserEvent> sharedEvents = new HashSet<>();

        if (!dateValidator.dateIsValid(from) || !dateValidator.dateIsValid(to)) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            sharedEvents = userEventService.findSharedEventsBetweenDatesByUserId(from, to, DATE_FORMAT, currentUser);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(sharedEvents, status);
    }

    @RequestMapping(value = "/count/{from}/{to}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<EventCount>> getEventCountBetweenDates(@PathVariable("from") String from,
                                                                      @PathVariable("to") String to) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;
        List<EventCount> countSharedEvents = new ArrayList<>();

        if (!dateValidator.dateIsValid(from) || !dateValidator.dateIsValid(to)) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            countSharedEvents = userEventService.findCountSharedEventsBetweenDatesByUserId(from, to, DATE_FORMAT, currentUser);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(countSharedEvents, status);
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<UserEvent>> getEventsSharedWithUser(@PathVariable("login") String login) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;
        Set<UserEvent> events = new HashSet<>();

        UserData friend = userDataService.findByUserLogin(login);
        if (friend == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            UserData fetchedUser = userDataService.findUserDateByIdAndFetchFriendList(currentUser.getPkId());
            Set<FriendList> friends = new HashSet<>();
            if (fetchedUser != null) {
                friends = fetchedUser.getFriends();
            }

            boolean contains = false;
            for (FriendList f : friends) {
                if (f.getFkUserFriend().getPkId().equals(friend.getPkId())) {
                    contains = true;
                    break;
                }
            }

            if (contains) {
                events = userEventService.findSharedEventsWithFriend(currentUser, friend);
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.FORBIDDEN;
            }
        }
        return new ResponseEntity<>(events, status);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Set<UserEvent>> getOwnEvents() {
        UserData currentUser = sessionController.getAuthorizedUser();

        UserData fetchedWithOwnEventsUser = userDataService.findUserDateByIdAndFetchOwnEvents(currentUser.getPkId());
        Set<UserEvent> ownEvents = new HashSet<>();
        if (fetchedWithOwnEventsUser != null) {
            ownEvents = fetchedWithOwnEventsUser.getOwnEvents();
        }

        return new ResponseEntity<>(ownEvents, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        UserData current = sessionController.getAuthorizedUser();
        UserEvent userEvent = userEventService.findByUserOwnerAndPkId(current, id);
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
