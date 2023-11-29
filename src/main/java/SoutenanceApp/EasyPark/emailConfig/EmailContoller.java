package SoutenanceApp.EasyPark.emailConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/sendEmail")
public class EmailContoller {
     @Autowired
        private EmailServiceImpl iemailService;

        // Sending a simple Email
        @PostMapping("/envoiEmail")
        public String sendMail(@RequestBody EmailDetails details) {
            return iemailService.sendSimpleMail(details);
        }
}
