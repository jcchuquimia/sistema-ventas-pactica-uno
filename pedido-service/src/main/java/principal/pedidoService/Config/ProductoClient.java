package principal.pedidoService.Config;

import principal.pedidoService.DTOs.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service")
public interface ProductoClient {
    @GetMapping("/api/v1/productos/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id);
}
