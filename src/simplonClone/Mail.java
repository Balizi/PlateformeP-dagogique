
package simplonClone;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static void send(String firstname,String lastname,String brief) {

        // Recipient's email ID needs to be mentioned.
        String to = "albertmohamed17@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "soumayaamghardev@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mrbalizi52@gmail.com", "dksoixjsqfyobrxw");
            }

        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Simplonline : Nouveau Brief");

            // Now set the actual message
            message.setText( "Bonjour "+firstname+" " +lastname+
                    "\n" +
                    "Votre formateur vous a assigné un le nouveau brief "+  brief +"  dans le cadre d’un projet de groupe !\n" +
                    "\n" +
                    "Rendez-vous sur la plateforme pour le consulter avec le reste de votre groupe. Définissez et créez les tâches au sein de ce projet dont vous allez vous occuper individuellement.\n" +
                    "\n" +
                    "Une fois que vous aurez chacun.e soumis vos rendus, votre formateur pourra consulter vos tâches, l'ensemble du (ou des) rendu(s) et vous évaluer.\n" +
                    "\n" +
                    "En cas de questions, contactez votre formateur \n" +
                    "\n" +
                    "A bientôt,\n" +
                    "L’équipe Simplonline.");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}