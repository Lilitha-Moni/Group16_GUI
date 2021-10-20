package za.ac.cput.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import za.ac.cput.GymManagementGUI;
import za.ac.cput.entity.GymSession;
import za.ac.cput.entity.MedicalStaff;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

@Controller
@RequestMapping("/medicalstaff")
public class MedicalStaffController {

    @GetMapping("/getall")
    public String getAll(
            HttpServletRequest request,
            Model model
    ) {
        GymSession session = GymSessionRepository.getRepository().read(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/medicalstaff/getall")) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response

            ObjectMapper mapper = new ObjectMapper();
            MedicalStaff[] allMedicalStaff = mapper.readValue(resp.body(), MedicalStaff[].class); // Converts JSON string into MedicalStaff array
            model.addAttribute("allMedicalStaff", allMedicalStaff); // Sends data to the page
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return "medicalstaff/getall";
    }
}
