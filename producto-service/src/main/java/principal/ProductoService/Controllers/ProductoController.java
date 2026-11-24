package principal.ProductoService.Controllers;


import principal.ProductoService.Models.Producto;
import principal.ProductoService.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.GuardarProducto(producto);
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoService.findById(id);
    }

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.findAll();
    }
}
