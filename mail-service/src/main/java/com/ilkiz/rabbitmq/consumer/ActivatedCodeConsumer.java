package com.ilkiz.rabbitmq.consumer;

import com.ilkiz.rabbitmq.model.ActivatedRequestDto;
import com.ilkiz.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivatedCodeConsumer {

    private final EmailSenderService senderService;

    @RabbitListener(queues = "queue-auth-activated-code")
    public void activatedCode(ActivatedRequestDto dto){
        log.info("Activate: {}", dto.toString());
        senderService.sendActivateCode(dto);
    }

}
