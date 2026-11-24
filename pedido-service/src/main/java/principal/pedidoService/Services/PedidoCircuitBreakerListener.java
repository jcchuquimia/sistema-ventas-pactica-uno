package principal.pedidoService.Services;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PedidoCircuitBreakerListener {
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final PedidoReocveryService pedidoReocveryService;
    public PedidoCircuitBreakerListener(CircuitBreakerRegistry circuitBreakerRegistry, PedidoReocveryService pedidoReocveryService) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.pedidoReocveryService = pedidoReocveryService;
    }
    @PostConstruct
    public void registerCircuitBreakerListener(){
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("productoServiceCB");
        circuitBreaker.getEventPublisher().onStateTransition(this::manjarTransicion);
    }
    private void manjarTransicion(CircuitBreakerOnStateTransitionEvent event){
        if (event.getStateTransition() == CircuitBreaker.StateTransition.HALF_OPEN_TO_CLOSED) {
            pedidoReocveryService.recoverPedidosPendientes();
        }
    }
}
