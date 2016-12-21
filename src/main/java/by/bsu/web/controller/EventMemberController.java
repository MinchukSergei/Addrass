package by.bsu.web.controller;

import by.bsu.web.entity.EventMember;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.service.EventMemberService;
import by.bsu.web.service.UserDataService;
import by.bsu.web.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/member")
public class EventMemberController {

    @Autowired
    private SessionController sessionController;

    @Autowired
    private EventMemberService eventMemberService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private UserEventService userEventService;

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserData>> getMembersFromEvent(@PathVariable("eventId") Long eventId) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchOwnEvents(currentUser.getPkId());
        Set<UserEvent> ownEvents = new HashSet<>();
        if (fetchedUser != null) {
            ownEvents = fetchedUser.getOwnEvents();
        }

        List<UserData> userDataList = new ArrayList<>();
        UserEvent userEvent = userEventService.findByPkId(eventId);
        if (ownEvents.contains(userEvent)) {
            UserEvent fetchedEvent = userEventService.findUserEventByIdAndFetchMembers(userEvent.getPkId());
            Set<EventMember> eventMembers = new HashSet<>();
            if (fetchedEvent != null) {
                eventMembers = fetchedEvent.getMembers();
            }
            for (EventMember e : eventMembers) {
                userDataList.add(e.getFkUserId());
            }
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(userDataList, status);
    }

    @RequestMapping(value = "/{eventId}/not", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserData>> getMembersNotIncludeToEvent(@PathVariable("eventId") Long eventId) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchOwnEvents(currentUser.getPkId());
        Set<UserEvent> ownEvents = new HashSet<>();
        if (fetchedUser != null) {
            ownEvents = fetchedUser.getOwnEvents();
        }

        List<UserData> userDataList = new ArrayList<>();
        UserEvent userEvent = userEventService.findByPkId(eventId);
        if (ownEvents.contains(userEvent)) {
            userDataList = eventMemberService.findFriendNotIncludeToEvent(currentUser.getPkId(), eventId);

            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(userDataList, status);
    }


    @RequestMapping(value = "/{eventId}/{friendId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addMemberToEvent(@PathVariable("eventId") Long eventId, @PathVariable("friendId") Long friendId) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchOwnEvents(currentUser.getPkId());
        Set<UserEvent> ownEvents = new HashSet<>();
        if (fetchedUser != null) {
            ownEvents = fetchedUser.getOwnEvents();
        }

        UserData friend = userDataService.findByPkId(friendId);

        UserEvent userEvent = userEventService.findByPkId(eventId);
        if (userEvent == null || !ownEvents.contains(userEvent) || friend == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            UserEvent fetchedEvent = userEventService.findUserEventByIdAndFetchMembers(userEvent.getPkId());
            Set<EventMember> members = new HashSet<>();
            if (fetchedEvent != null) {
                members = fetchedEvent.getMembers();
            }

            boolean contains = false;
            for (EventMember m : members) {
                if (m.getFkUserId().getPkId().equals(friend.getPkId())) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                EventMember eventMember = new EventMember();
                eventMember.setFkEventId(userEvent);
                eventMember.setFkUserId(friend);
                members.add(eventMember);
                userEvent.setMembers(members);
                userEventService.save(userEvent);
                status = HttpStatus.OK;
            }
        }
        return new ResponseEntity(status);
    }


    @RequestMapping(value = "/{eventId}/{friendId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteMemberToEvent(@PathVariable("eventId") Long eventId, @PathVariable("friendId") Long friendId) {
        UserData currentUser = sessionController.getAuthorizedUser();
        HttpStatus status;

        UserData fetchedUser = userDataService.findUserDateByIdAndFetchOwnEvents(currentUser.getPkId());
        Set<UserEvent> ownEvents = new HashSet<>();
        if (fetchedUser != null) {
            ownEvents = fetchedUser.getOwnEvents();
        }

        UserData friend = userDataService.findByPkId(friendId);
        UserEvent userEvent = userEventService.findByPkId(eventId);
        if (userEvent == null || !ownEvents.contains(userEvent) || friend == null) {
            status = HttpStatus.NOT_FOUND;
        } else {
            UserEvent fetchedEvent = userEventService.findUserEventByIdAndFetchMembers(userEvent.getPkId());
            Set<EventMember> members = new HashSet<>();
            if (fetchedEvent != null) {
                members = fetchedEvent.getMembers();
            }

            for (EventMember m : members) {
                if (m.getFkUserId().getPkId().equals(friend.getPkId())) {
                    members.remove(m);
                    break;
                }
            }

            userEvent.setMembers(members);
            userEventService.save(userEvent);
            status = HttpStatus.OK;
        }
        return new ResponseEntity(status);
    }

}
