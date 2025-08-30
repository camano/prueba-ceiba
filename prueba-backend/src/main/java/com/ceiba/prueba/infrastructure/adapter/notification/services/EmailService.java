package com.ceiba.prueba.infrastructure.adapter.notification.services;

import com.ceiba.prueba.domain.notification.gateway.INotificationPort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailService implements INotificationPort {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enqueue(Long userId, Long subscriptionId, String channel, Map<String, Object> payload) {
        if (!"EMAIL".equalsIgnoreCase(channel)) {
            return; // Solo manejamos emails aquí
        }

        String fundName = (String) payload.get("fundName");
        String amount = (String) payload.get("amount");
        String type = (String) payload.get("type");

        // Armar el correo
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("destinatario@correo.com"); // Aquí debes resolver el email real del usuario
        message.setSubject("Notificación de Suscripción - " + type);
        message.setText(
                "Hola,\n\n" +
                        "Tu operación ha sido registrada:\n" +
                        "- Tipo: " + type + "\n" +
                        "- Fondo: " + fundName + "\n" +
                        "- Monto: " + amount + "\n\n" +
                        "Gracias por usar nuestros servicios."
        );

        mailSender.send(message);
    }
}
