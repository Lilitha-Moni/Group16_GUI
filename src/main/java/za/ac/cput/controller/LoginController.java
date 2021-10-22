package za.ac.cput.controller;

import org.apache.http.HttpHeaders;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.cput.GymManagementGUI;
import za.ac.cput.entity.GymSession;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
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
            GymSession testSession = new GymSession("", username, password);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/login")) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, testSession.encoded()) // Sets basic auth
                    .build();

            java.net.http.HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response

            if(resp.body().equals("success"))
            {
                GymSessionRepository.getRepository().create(new GymSession(request.getRequestedSessionId(), username, password));
                return new RedirectView("dashboard");
            }

            attributes.addAttribute("user", "not-found");
            return new RedirectView("login");
        } catch (IOException | URISyntaxException | InterruptedException e) {// | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return new RedirectView("login");
    }
}
