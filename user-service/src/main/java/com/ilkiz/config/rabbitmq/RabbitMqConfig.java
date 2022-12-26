package com.ilkiz.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    private String userDirectExchange = "user-direct-exchange";

    private String userUpdatebindingKey = "user-update-binding-key";

    private String userUpdateQueue = "user-update-queue";

    @Bean
    DirectExchange userExchange(){
        return new DirectExchange(userDirectExchange);
    }

    @Bean
    Queue userQueue(){
        return new Queue(userUpdateQueue);
    }

    @Bean
    public Binding bindingUser(final  Queue userQueue, final DirectExchange userExchange){
        return BindingBuilder.bind(userQueue).to(userExchange).with(userUpdatebindingKey);
    }
}
