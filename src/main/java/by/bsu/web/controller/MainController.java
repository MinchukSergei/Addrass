package by.bsu.web.controller;

import by.bsu.web.entity.UserData;
import by.bsu.web.service.UserDataService;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 04.11.2016.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView getMain() {
        ModelAndView modelAndView = new ModelAndView("Strings");

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring-hibernate-config.xml"); //move from src.main.java to src.main.resources
        ctx.refresh();

        UserDataService service = ctx.getBean("jpaUserDataService", UserDataService.class);
        List<UserData> strings = service.findAll();

        modelAndView.addObject("strings", strings);
        return modelAndView;
    }
}
