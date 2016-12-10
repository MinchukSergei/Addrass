package by.bsu.web.controller;

import by.bsu.web.entity.EventType;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.service.EventTypeService;
import by.bsu.web.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        EventType eventType = eventTypeService.findByName(userEvent.getFkEventTypeEntity().getName());
        if (eventType == null) {
            eventType = eventTypeService.findByName(EventType.DEFAULT_TYPE);
        }
        userEvent.setFkEventTypeEntity(eventType);
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
            EventType eventType = eventTypeService.findByName(userEvent.getFkEventTypeEntity().getName());
            if (eventType != null) {
                exists.setFkEventTypeEntity(eventType);
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

    @RequestMapping(value = "/day", method = RequestMethod.GET)
    @ResponseBody
    public List<UserEvent> getEvent(@RequestParam("date") String date) {
        UserData current = sessionController.getAuthorizedUser();
        return userEventService.findByFkOwnerAndDate(current.getPkId(), date);
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

//    @RequestMapping(value = "/get/month", method = RequestMethod.GET)
//    @ResponseBody
//    public List<EventMonthCount> getMonthEventsCount(@RequestParam("date") String date) {
//        Calendar calendar = Calendar.getInstance();
//        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            calendar.setTime(sdf.parse(date));
//        } catch (ParseException ignored) {}
//
//        Long owner = 2L;
//        Integer month = calendar.get(Calendar.MONTH) + 1;
//        Integer year = calendar.get(Calendar.YEAR);
//        return userEventService.findMonthCountEvent(month, year, owner);
//    }

}
