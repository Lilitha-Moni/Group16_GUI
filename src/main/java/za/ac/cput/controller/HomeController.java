package za.ac.cput.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController
{
    @RequestMapping({"/", "/home"})
    public String home(
            HttpServletRequest request
    )
    {
        System.out.println(request.getRequestedSessionId());
        return "home";
    }
}
