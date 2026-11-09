
package api_productos.controller;

import api_productos.model.Producto;
import api_productos.service.ProductoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    // Inyección de dependencias por constructor
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET: listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    // GET: buscar un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable Long id) {
        return ResponseEntity.ok(
            productoService.buscarProductoPorId(id)
        );
    }

    // POST: registrar un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> registrarProducto(
            @RequestBody Producto producto) {

        Producto productoCreado =
            productoService.registrarProducto(producto);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productoCreado);
    }

    // DELETE: eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // PUT: actualizar un producto por su ID
@PutMapping("/{id}")
public ResponseEntity<Producto> actualizarProducto(
        @PathVariable Long id,
        @RequestBody Producto producto) {

    Producto productoActualizado =
        productoService.actualizarProducto(id, producto);

    return ResponseEntity.ok(productoActualizado);
    }
}