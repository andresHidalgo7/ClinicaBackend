package com.uniquindio.edu.clinicaX.servicios.implementacion;

import com.uniquindio.edu.clinicaX.dto.EmailDTO;
import com.uniquindio.edu.clinicaX.servicios.interfaces.EmailServicio;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServicioImpl implements EmailServicio {

    private final JavaMailSender javaMailSender;
    @Override
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        helper.setSubject(emailDTO.asunto()); helper.setText(emailDTO.cuerpo(), true);
        helper.setTo(emailDTO.destino()); helper.setFrom("no_reply@dominio.com");
        javaMailSender.send(mensaje);
    }
}
