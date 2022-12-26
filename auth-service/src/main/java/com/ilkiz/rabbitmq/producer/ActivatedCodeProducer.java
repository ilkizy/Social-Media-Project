package com.ilkiz.rabbitmq.producer;

import com.ilkiz.rabbitmq.model.ActivatedRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivatedCodeProducer {

    private final RabbitTemplate rabbitTemplate;

    public void activatedCode(ActivatedRequestDto model){
        rabbitTemplate.convertAndSend("auth-direct-exchange", "bind-key-activated-code", model);
    }
}
