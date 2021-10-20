package za.ac.cput.controller;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.cput.GymManagementGUI;
import za.ac.cput.entity.GymSession;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class LoginController {
    @GetMapping( "/login")
    public String login(
            HttpServletRequest request,
            Model model
    )
    {
        return "login";
    }

    @PostMapping("/login")
    public RedirectView login(
            HttpServletRequest request,
            RedirectAttributes attributes,
            String username,
            String password
    ) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(GymManagementGUI.serverAddress()+"/login");

            List<NameValuePair> pairs = new ArrayList<>();
            pairs.add(new BasicNameValuePair("username", username));
            pairs.add(new BasicNameValuePair("password", password));

            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
            HttpResponse resp = client.execute(httpPost);
            String stringResp = new BasicResponseHandler().handleResponse(resp);

            if(!stringResp.strip().isBlank())
            {
                GymSessionRepository.getRepository().create(new GymSession(request.getRequestedSessionId(), username, password));
                return new RedirectView("dashboard");
            }

            attributes.addAttribute("user", "not-found");
            return new RedirectView("login");
        } catch (IOException e) {// | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return new RedirectView("login");
    }
}
