package br.dev.danielsebastian.coffeemaker.bo;

import br.dev.danielsebastian.coffeemaker.exception.EmailException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailBo {

    private static final String EMAIL_FROM = System.getenv("EMAIL");
    private static final String APP_PASSWORD = System.getenv("PASSWORD");

    public void sendEmail(
            String recipient,
            String about,
            String message) throws EmailException {

        // Dados de configuração do email
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Autenticação na conta de email do remetente
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
            }
        });

        // Enviar a mensagem
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(EMAIL_FROM));
            msg.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipient));
            msg.setSubject(about);
            msg.setText(message);
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new EmailException("Erro ao enviar e-mail: " + e.getMessage());
        }

    }
}
