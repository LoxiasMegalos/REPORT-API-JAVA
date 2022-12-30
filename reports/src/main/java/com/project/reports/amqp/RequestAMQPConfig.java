package com.project.reports.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestAMQPConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


    @Bean
    public Queue newOrderQueue() {
        return QueueBuilder.nonDurable("orders.new-requests").build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("orders.ex").build();
    }

    @Bean
    public Binding bindingOrderReport(FanoutExchange fanoutExchange){
        return BindingBuilder.bind(newOrderQueue()).to(fanoutExchange);
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory conn){
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

}
