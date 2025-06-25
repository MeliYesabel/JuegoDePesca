package com.tallerwebi.dominio.servicio;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Aplicando JavaMail
@Service
public class ServicioMail {
    private String remitente;
    private String clave;


    //Aplicamos en el constructor para leer email del remitente y su clave por .properties


    public ServicioMail() {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("email.properties")){
            Properties properties = new Properties();
            properties.load(input);
            remitente = properties.getProperty("email.usuario");
            clave = properties.getProperty("email.clave");

        } catch (IOException exception) {
            throw new RuntimeException("Error al cargar la configuracion de las variables de email", exception);
        }
    }

    public void enviarEmail(String destinatario, String asunto, String cuerpo) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try{
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport.send(message);

        }catch (MessagingException exception){
            throw new RuntimeException();
        }

    }
}
