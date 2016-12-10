package by.bsu.web.controller;

import by.bsu.web.controller.util.EventCount;
import by.bsu.web.controller.util.EventCountCriteria;
import by.bsu.web.entity.UserData;
import by.bsu.web.entity.UserEvent;
import by.bsu.web.service.EventMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/event/member")
public class EventMemberController {

    @Autowired
    private SessionController sessionController;

    @Autowired
    private EventMemberService eventMemberService;

    @RequestMapping(value = "/month", method = RequestMethod.GET)
    @ResponseBody
    public List<UserEvent> getEventCount(@RequestParam("date") String date, @RequestParam(value = "friendId", required = false) Long friendId) {
        UserData current = sessionController.getAuthorizedUser();

        String[] filterObj = date.split("-");
        String[] paramTypes = {"year", "month", "day"};
        List<EventCountCriteria> criterias = new ArrayList<>();

        for (int i = 0; i < filterObj.length; i++) {
            criterias.add(new EventCountCriteria(paramTypes[i], Integer.valueOf(filterObj[i])));
        }

        return eventMemberService.findCountByCriteria(criterias, current.getPkId(), friendId);
    }
}
