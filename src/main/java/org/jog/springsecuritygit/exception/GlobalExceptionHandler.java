package org.jog.springsecuritygit.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.jog.springsecuritygit.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> genericExceptionHandler(Exception exception, HttpServletRequest request){
        ApiError apiError = new ApiError();

        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI());
        apiError.setHttpMethod(request.getMethod());
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setMessage("Error interno, por favor intenta mas tarde");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(Exception exception, HttpServletRequest request){
        ApiError apiError = new ApiError();

        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURI());
        apiError.setHttpMethod(request.getMethod());
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setMessage("Error en la peticion");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(Exception exception, HttpServletRequest request){
        ApiError apiError = new ApiError();

        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setHttpMethod(request.getMethod());
        apiError.setTimeStamp(LocalDateTime.now());
        apiError.setMessage("No tienes permisos");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);
    }
}
