package principal.InventarioService.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import principal.InventarioService.Config.RabbitMQConfig;

@Component
public class ProductoEventListener {
    private static final Logger log = LoggerFactory.getLogger(ProductoEventListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void recibirMensaje(Object mensaje) {
        log.info("inventario-service recibió mensaje: {}", mensaje);
    }
}
