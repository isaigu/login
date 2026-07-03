package util;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
public class CorreoUtil {

    // === CONFIGURA ESTO ===
    private static final String REMITENTE = "gutirrresisai@gmail.com";
    private static final String CLAVE     = "ayhw tvof hwtf gcfs"; // App Password de Gmail
    private static final String BASE_URL  = "http://localhost:8080/practica_con_bd";
    // ======================

    public static void enviarVerificacion(String destino, String token) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(REMITENTE, CLAVE);
            }
        });

        String link = BASE_URL + "/VerificarServlet?token=" + token;

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(REMITENTE));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(destino));
        msg.setSubject("Verifica tu cuenta");
        msg.setContent(
            "<h3>Bienvenido</h3>"
            + "<p>Gracias por registrarte. Confirma tu cuenta dando clic en el siguiente enlace:</p>"
            + "<p><a href='" + link + "'>Verificar mi cuenta</a></p>"
            + "<p>Si no creaste esta cuenta, ignora este correo.</p>",
            "text/html; charset=utf-8"
        );

        Transport.send(msg);
    }
}