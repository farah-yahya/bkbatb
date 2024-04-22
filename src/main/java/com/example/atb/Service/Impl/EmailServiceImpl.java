package com.example.atb.Service.Impl;

import com.example.atb.Entities.User;
import com.example.atb.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendEmailWithTemplate(User user) {
        // Créer une instance de MimeMessage à l'aide de la fonction JavaMailSender
        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    "UTF-8");

            // Définir l'adresse électronique du destinataire
            helper.setTo(user.getEmail());

            // Définir l'objet de l'e-mail
            helper.setSubject("ATB Subscription");

            // Charger le modèle de courrier électronique à partir d'un fichier ou d'une
            // chaîne de caractères
            String emailTemplate = "<!DOCTYPE html>\n" +
                    "<html lang=\"fr\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            background-color: #f4f4f4;\n" +
                    "        }\n" +
                    "\n" +
                    "        .container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            padding: 20px;\n" +
                    "            background-color: #ffffff;\n" +
                    "            border-radius: 5px;\n" +
                    "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "\n" +
                    "        .logo {\n" +
                    "            text-align: center;\n" +
                    "            margin-bottom: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .logo img {\n" +
                    "            max-width: 200px;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1 {\n" +
                    "            color: #333;\n" +
                    "        }\n" +
                    "\n" +
                    "        p {\n" +
                    "            color: #555;\n" +
                    "        }\n" +
                    "\n" +
                    "        .btn {\n" +
                    "            display: inline-block;\n" +
                    "            padding: 10px 20px;\n" +
                    "            background-color: #007BFF;\n" +
                    "            color: #fff;\n" +
                    "            text-decoration: none;\n" +
                    "            border-radius: 3px;\n" +
                    "            margin-top: 10px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .btn:hover {\n" +
                    "            background-color: #0056b3;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"logo\">\n" +
                    "            <img src=\"{{imageSource}}\" alt=\"ATB Logo\">\n" +
                    "        </div>\n" +
                    "        <h1>Bienvenue chez ATB (Arab Tunisian Bank), {{recipientName}}</h1>\n" +
                    "        <p>Vous avez souscrit à nos services bancaires.</p>\n" +
                    "        <p>Merci de nous informer en cas de problème.</p>\n" +
                    "        <p>Votre nom d'utilisateur : {{username}}</p>\n" +
                    "        <p>Votre adresse e-mail : {{email}}</p>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";

            // Remplacer les espaces réservés par des valeurs réelles
            emailTemplate = emailTemplate.replace("{{imageSource}}", "classpath:/images/atbLogo.png");
            emailTemplate = emailTemplate.replace("{{recipientName}}", user.getLastName() + " " + user.getFirstName());
            emailTemplate = emailTemplate.replace("{{username}}", user.getLastName() + " " + user.getFirstName());
            emailTemplate = emailTemplate.replace("{{email}}", user.getEmail());

            // Définir le contenu de l'e-mail en HTML
            helper.setText(emailTemplate, true);

            // Envoyer l'email
            emailSender.send(message);
        } catch (MessagingException e) {
            // Handle messaging exceptions
            throw new RuntimeException(e);
        }
    }
}
