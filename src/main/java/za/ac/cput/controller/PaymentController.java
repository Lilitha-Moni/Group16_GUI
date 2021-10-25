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
import za.ac.cput.entity.Payment;
import za.ac.cput.repository.GymSessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @GetMapping({"", "/"})
    public String getAll(
            HttpServletRequest request,
            Model model
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/payment/getall")) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response

            ObjectMapper mapper = new ObjectMapper();
            Payment[] paymentList = mapper.readValue(resp.body(), Payment[].class);
            model.addAttribute("paymentList ", paymentList ); // Sends data to the page
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        model.addAttribute("file", "../payment.jsp");
        return "admin/template/dashboard";
    }


    @GetMapping("/create")
    public String createGET(
            Model model
    ) {
        model.addAttribute("file", "../../newPaymentForm.jsp");
        return "admin/template/dashboard";
    }

    @PostMapping("/create")
    public RedirectView createPOST(
            HttpServletRequest request,
            @ModelAttribute("payment") Payment payment
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/payment/create")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payment))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/payment");
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/payment/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Payment payment = mapper.readValue(resp.body(), Payment.class);

            model.addAttribute("payment", payment);
            model.addAttribute("file", "../../paymentList.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "payment";
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/payment/read/"+id)) // url of server
                    .GET() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Payment payment = mapper.readValue(resp.body(), Payment.class);

            model.addAttribute("payment", payment);
            model.addAttribute("file", "../../newPaymentForm.jsp");
            return "admin/template/dashboard";

        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return "payment";
    }

    @PostMapping("/update/{ID}")
    public RedirectView updatePOST(
            HttpServletRequest request,
            @ModelAttribute("payment") Payment payment,
            @PathVariable int ID
    ) {
        GymSession session = GymSessionRepository.getSession(request.getRequestedSessionId()); // Get session (Username and password)
        try {
            ObjectMapper mapper = new ObjectMapper();
            payment.getPaymentID(ID);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(new URI(GymManagementGUI.serverAddress()+"/payment/update")) // url of server
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(payment))) // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/payment");
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
                    .uri(new URI(GymManagementGUI.serverAddress()+"/payment/delete/"+ID)) // url of server
                    .DELETE() // request type
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .header(HttpHeaders.AUTHORIZATION, session.encoded()) // Sets basic auth
                    .build();

            client.send(httpRequest, HttpResponse.BodyHandlers.ofString()); // response
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        return new RedirectView("/payment");
    }
}
