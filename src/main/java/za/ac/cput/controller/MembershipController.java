package za.ac.cput.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.cput.GymManagementGUI;
import za.ac.cput.entity.GymSession;
import za.ac.cput.entity.Member;
import za.ac.cput.entity.Membership;
import za.ac.cput.entity.MembershipBase;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/membership")
public class MembershipController {

    @GetMapping({"", "/"})
    public String getAll(
            HttpServletRequest request,
            Model model
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/membership/getall")) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response

            ObjectMapper mapper = new ObjectMapper();
            Membership[] allMemberships = mapper.readValue(resp.body(), Membership[].class); // Converts JSON string into Membership array
            model.addAttribute("allMemberships", allMemberships); // Sends data to the page
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        model.addAttribute("file", "../membership/menu.jsp");
        return "admin/template/dashboard";
    }

    @GetMapping("/create")
    public String createGET(
            Model model
    ) {
        model.addAttribute("subheadding", "Create new Medical Staff member");
        model.addAttribute("file", "../membership/form.jsp");
        return "admin/template/dashboard";
    }

    @PostMapping("/create")
    public RedirectView createPOST(
            HttpServletRequest request,
            @ModelAttribute("membership") Membership membership
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/membership/create")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(membership))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/membership");
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/membership/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Membership membership = mapper.readValue(resp.body(), Membership.class);

            model.addAttribute("membership", membership);
            model.addAttribute("file", "../membership/details.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "membership";
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/membership/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Membership membership = mapper.readValue(resp.body(), Membership.class);

            model.addAttribute("subheadding", "Create new Medical Staff member");
            model.addAttribute("membership", membership);
            model.addAttribute("file", "../membership/form.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "membership";
    }

    @PostMapping("/update/{id}")
    public RedirectView updatePOST(
            HttpServletRequest request,
            @PathVariable int id
    ) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest getMember = HttpRequest.newBuilder()
                    .uri(
                            new URI(
                                    GymManagementGUI.serverAddress()+
                                            "/member/read/"+request.getParameter("member")))
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> member = client.send(getMember, HttpResponse.BodyHandlers.ofString());

            Membership membership = new Membership.Builder(id)
                    .addMember(mapper.readValue(member.body(), Member.class))
                    .setExpiryDate(format.parse(request.getParameter("expireDate")))
                    .setTotalFees(Double.parseDouble(request.getParameter("totalFees")))
                    .setType(request.getParameter("type"))
                    .build();

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/membership/update")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(membership))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException | ParseException e) {
            e.printStackTrace();
        }
        return new RedirectView("/membership");
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(
            @PathVariable("id") int id,
            HttpServletRequest request
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/membership/delete/"+id)) // url of server
                    .DELETE() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new RedirectView("/membership");
    }
}
