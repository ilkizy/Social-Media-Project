package com.ilkiz.rabbitmq.producer;

import com.ilkiz.rabbitmq.model.UpdateUsernameEmailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserProducer {

    private String userDirectExchange = "user-direct-exchange";

    private String userUpdatebindingKey = "user-update-binding-key";

    private final RabbitTemplate rabbitTemplate;


    public void sendUpdateUser(UpdateUsernameEmailModel model) {

        rabbitTemplate.convertAndSend(userDirectExchange, userUpdatebindingKey, model);

    }
}
