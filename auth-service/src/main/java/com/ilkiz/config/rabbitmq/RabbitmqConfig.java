package com.ilkiz.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String authDirectExchange = "auth-direct-exchange";
    private String bindingActivatedCode = "bind-key-activated-code";
    private String queueActivatedCode = "queue-auth-activated-code";

    @Bean
    DirectExchange authExchange(){
        return new DirectExchange(authDirectExchange);
    }
    @Bean
    Queue activatedCodeQueue(){
        return new Queue(queueActivatedCode);
    }
    @Bean
    public Binding bindingCreateUser(final Queue activatedCodeQueue, final DirectExchange authExchange){

        return BindingBuilder.bind(activatedCodeQueue).to(authExchange).with(bindingActivatedCode);
    }
}
