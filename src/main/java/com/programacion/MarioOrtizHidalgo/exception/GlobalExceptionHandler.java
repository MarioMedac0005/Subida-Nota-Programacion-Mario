package com.programacion.MarioOrtizHidalgo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para los controladores REST.
 * Captura y gestiona excepciones específicas lanzadas durante el procesamiento de las solicitudes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción {@link ProductNotFoundException} cuando un producto no es encontrado.
     *
     * @param productNotFoundException la excepción lanzada cuando no se encuentra el producto.
     * @param request el objeto {@link HttpServletRequest} que contiene información sobre la solicitud HTTP.
     * @return una respuesta HTTP 404 con un cuerpo detallado en formato {@link ErrorResponse}.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(
            ProductNotFoundException productNotFoundException,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError("Not Found");
        errorResponse.setMessage(productNotFoundException.getMessage());
        errorResponse.setPath(request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
