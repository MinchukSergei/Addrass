package by.bsu.web.controller;

import by.bsu.web.entity.UserData;
import by.bsu.web.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserDataService userDataService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView getMain() {
        ModelAndView modelAndView = new ModelAndView("Strings");

        List<UserData> strings = userDataService.findAll();

        modelAndView.addObject("strings", strings);
        return modelAndView;
    }
}
