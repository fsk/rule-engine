package org.codexist.ruleengine.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendDomainAlertEmail(String to, String domain, int totalRequestCount) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //helper.setTo("furkan.kulaksiz@codex.ist");
        helper.setTo("furkansahinkulaksiz@gmail.com");
        helper.setSubject("High Request Count Domain Alert");

        String htmlContent = """
                                <html>
                                <body>
                                <h2>Alert: High Request Count Detected</h2>
                                <p>The following domain has exceeded the average request count:</p>
                                <ul>
                                <li><strong>Domain Name:</strong> %s </li>
                                <li><strong>Total Requests:</strong> %s </li>
                                </ul>
                                </body>
                                </html>
                """.formatted(domain, totalRequestCount);

        helper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
}

