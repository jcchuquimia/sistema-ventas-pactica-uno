package principal.ProductoService.Config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import principal.ProductoService.Models.Producto;

@Component
@RequiredArgsConstructor
public class ProductoEventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publicarProductoCreado(Producto producto) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                producto
        );
    }
}
