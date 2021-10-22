package za.ac.cput.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import za.ac.cput.entity.GymSession;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController
{
    @RequestMapping({"/", "/home"})
    public String home(
            HttpServletRequest request,
            Model model
    )
    {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId());
        if(session != null)
            model.addAttribute("logged_in", true);
        return "home";
    }
}
