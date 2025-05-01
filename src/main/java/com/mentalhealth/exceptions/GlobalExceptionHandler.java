package com.mentalhealth.exceptions;

import com.mentalhealth.constants.Constants;
import com.mentalhealth.dto.response.ErrorResponseDTO;
import com.mentalhealth.dto.response.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                .toList();

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiUri(((ServletWebRequest) request).getRequest().getRequestURI())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(String.join(", ", errorList))
                .timestamp(java.time.LocalDateTime.now())
                .build();

        ResponseDTO<ErrorResponseDTO> response = ResponseDTO.<ErrorResponseDTO>builder()
                .statusCode(Constants.BAD_REQUEST_CODE)
                .statusMessage(Constants.BAD_REQUEST_MESSAGE)
                .data(errorResponseDTO)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    protected ResponseEntity<ResponseDTO<ErrorResponseDTO>> handleDuplicateResourceException(DuplicateResourceException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiUri(((ServletWebRequest) request).getRequest().getRequestURI())
                .httpStatus(HttpStatus.CONFLICT)
                .errorMessage(ex.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        ResponseDTO<ErrorResponseDTO> response = ResponseDTO.<ErrorResponseDTO>builder()
                .statusCode(Constants.CONFLICT_CODE)
                .statusMessage("Duplicate Resource Exception")
                .data(errorResponseDTO)
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ResponseDTO<ErrorResponseDTO>> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiUri(((ServletWebRequest) request).getRequest().getRequestURI())
                .httpStatus(HttpStatus.NOT_FOUND)
                .errorMessage(ex.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        ResponseDTO<ErrorResponseDTO> response = ResponseDTO.<ErrorResponseDTO>builder()
                .statusCode(Constants.NOT_FOUND_CODE)
                .statusMessage(Constants.NOT_FOUND_MESSAGE)
                .data(errorResponseDTO)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(JwtTokenException.class)
    protected ResponseEntity<ResponseDTO<ErrorResponseDTO>> handleJwtTokenException(JwtTokenException ex, WebRequest request) {

        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiUri(((ServletWebRequest) request).getRequest().getRequestURI())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .errorMessage(ex.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        ResponseDTO<ErrorResponseDTO> response = ResponseDTO.<ErrorResponseDTO>builder()
                .statusCode(Constants.UNAUTHORIZED_CODE)
                .statusMessage(Constants.UNAUTHORIZED_MESSAGE)
                .data(errorResponseDTO)
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseDTO<ErrorResponseDTO>> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .apiUri(((ServletWebRequest) request).getRequest().getRequestURI())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorMessage("An unexpected error occurred: " + ex.getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();

        ResponseDTO<ErrorResponseDTO> response = ResponseDTO.<ErrorResponseDTO>builder()
                .statusCode(Constants.INTERNAL_SERVER_ERROR_CODE)
                .statusMessage(Constants.INTERNAL_SERVER_ERROR_MESSAGE)
                .data(errorResponseDTO)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}