package org.iahsnil.gateway.admin.events;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iahsnil.gateway.admin.config.BusConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@AllArgsConstructor
public class EventSender {

    private RabbitTemplate rabbitTemplate;

    private MessageConverter messageConverter;

    @PostConstruct
    public void init() {
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void send(String routingKey, Object object) {
        log.info("routingKey:{}=>message:{}", routingKey, object);
        rabbitTemplate.convertAndSend(BusConfig.EXCHANGE_NAME, routingKey, object);
    }
}
