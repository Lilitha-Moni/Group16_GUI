package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.cput.GymManagementGUI;
import za.ac.cput.entity.GymSession;
import za.ac.cput.entity.Equipment;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {
    @GetMapping({"", "/"})
    public String getAll(
            HttpServletRequest request,
            Model model
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/equipment/getall")) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response

            ObjectMapper mapper = new ObjectMapper();
            Equipment [] listEquipments = mapper.readValue(resp.body(), Equipment[].class);
            model.addAttribute("listEquipments", listEquipments); // Sends data to the page
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        model.addAttribute("file", "../equipmentlist.jsp");
        return "admin/template/dashboard";
    }


    @GetMapping("/create")
    public String createGET(
            Model model
    ) {
        model.addAttribute("file", "../newEquipmentForm.jsp");
        return "admin/template/dashboard";
    }

    @PostMapping("/create")
    public RedirectView createPOST(
            HttpServletRequest request,
            @ModelAttribute("equipment") Equipment equipment
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/equipment/create")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(equipment))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/equipment");
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/equipment/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Equipment equipment = mapper.readValue(resp.body(), Equipment.class);

            model.addAttribute("equipment", equipment);
            model.addAttribute("file", "../equipmentlist.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "equipment";
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/equipment/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Equipment equipment = mapper.readValue(resp.body(), Equipment.class);

            model.addAttribute("equipment", equipment);
            model.addAttribute("file", "../newEquipmentForm.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "equipment";
    }

    @PostMapping("/update/{id}")
    public RedirectView updatePOST(
            HttpServletRequest request,
            @ModelAttribute("equipment") Equipment equipment ,
            @PathVariable int id
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            equipment.setId(id);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/equipment/update")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(equipment))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/equipment");
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/equipment/delete/"+id)) // url of server
                    .DELETE() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new RedirectView("/equipment");
    }
}