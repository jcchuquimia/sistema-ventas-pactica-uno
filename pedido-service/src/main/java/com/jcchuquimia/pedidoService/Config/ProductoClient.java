package com.jcchuquimia.pedidoService.Config;

import com.jcchuquimia.pedidoService.DTOs.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service")
public class ProductoClient {
    @GetMapping("/api/v1/productos/{id}")
    Producto obtenerProductoPorId(@PathVariable Long id);
}
