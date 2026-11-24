package com.jcchuquimia.pedidoService.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoEventPublisher {
    //Logging
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PedidoEventPublisher.class);
    private final RabbitTemplate rabbitTemplate;
    public void publishPedidoCreatedEvent(PedidoEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );
        logger.info("PedidoEvent publicado: {}", event);
    }
}
