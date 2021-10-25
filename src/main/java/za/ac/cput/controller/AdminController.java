package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.cput.GymManagementGUI;
import za.ac.cput.entity.Admin;
import za.ac.cput.entity.GymSession;
import za.ac.cput.entity.Member;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping({"", "/"})
    public String getAll(
            HttpServletRequest request,
            Model model
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/admin/getall")) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response

            ObjectMapper mapper = new ObjectMapper();
            Admin[] adminList = mapper.readValue(resp.body(), Admin[].class);
            model.addAttribute("adminList", adminList); // Sends data to the page
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        model.addAttribute("file", "../admin.jsp");
        return "admin/template/dashboard";
    }


    @GetMapping("/create")
    public String createGET(
            Model model
    ) {
        model.addAttribute("file", "../../newAdminForm.jsp");
        return "admin/template/dashboard";
    }

    @PostMapping("/create")
    public RedirectView createPOST(
            HttpServletRequest request,
            @ModelAttribute("admin") Member member
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/admin/create")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(member))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/admin");
    }

    @GetMapping("/{id}")
    public String view(
            HttpServletRequest request,
            Model model,
            @PathVariable int id
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/admin/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Admin admin = mapper.readValue(resp.body(), Admin.class);

            model.addAttribute("admin", admin);
            model.addAttribute("file", "../../adminList.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "admin";
    }
    @GetMapping("/update/{id}")
    public String updateGET(
            HttpServletRequest request,
            Model model,
            @PathVariable int id
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/admin/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Admin admin = mapper.readValue(resp.body(), Admin.class);

            model.addAttribute("admin", admin);
            model.addAttribute("file", "../../newAdminForm.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "admin";
    }

    @PostMapping("/update/{ID}")
    public RedirectView updatePOST(
            HttpServletRequest request,
            @ModelAttribute("admin") Admin admin,
            @PathVariable int ID
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            admin.setID(ID);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/admin/update")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(admin))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/admin");
    }

    @GetMapping("/delete/{ID}")
    public RedirectView delete(
            @PathVariable("ID") int ID,
            HttpServletRequest request
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/admin/delete/"+ID)) // url of server
                    .DELETE() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new RedirectView("/admin");
    }
}