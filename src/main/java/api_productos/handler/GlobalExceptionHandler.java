
package api_productos.handler;

import api_productos.exception.ProductoInvalidoException;
import api_productos.exception.ProductoNoEncontradoException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manejar productos que no existen: HTTP 404
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarProductoNoEncontrado(
            ProductoNoEncontradoException ex) {

        Map<String, Object> respuesta = Map.of(
            "mensaje", ex.getMessage(),
            "estado", HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    // Manejar datos inválidos: HTTP 400
    @ExceptionHandler(ProductoInvalidoException.class)
    public ResponseEntity<Map<String, Object>> manejarProductoInvalido(
            ProductoInvalidoException ex) {

        Map<String, Object> respuesta = Map.of(
            "mensaje", ex.getMessage(),
            "estado", HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
    }
}
