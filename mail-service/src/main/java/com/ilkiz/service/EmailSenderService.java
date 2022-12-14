package com.ilkiz.service;

import com.ilkiz.rabbitmq.model.ActivatedRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendActivateCode(ActivatedRequestDto dto){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ilkizyoruk@gmail.com");
        mailMessage.setTo(dto.getEmail());
        mailMessage.setSubject("Aktivasyon kodunuz");
        mailMessage.setText(dto.getActivatedCode());
        javaMailSender.send(mailMessage);
    }
}
