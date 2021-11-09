package com.gabriel.lancamentos.api.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gabriel.lancamentos.api.exeception.BadRequestException;
import com.gabriel.lancamentos.api.exeception.BadRequestExceptionDetails;
import com.gabriel.lancamentos.api.exeception.ExceptionDetails;
import com.gabriel.lancamentos.api.exeception.PessoaInexistenteOuInativaException;
import com.gabriel.lancamentos.api.exeception.ValidationExceptionDetails;
import com.gabriel.lancamentos.api.handler.AllExceptionHandler.Erro;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {	 
	
	@Override
	protected ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		  return new ResponseEntity<>(
	                BadRequestExceptionDetails.builder()
	                        .timestamp(LocalDateTime.now())
	                        .status(HttpStatus.BAD_REQUEST.value())
	                        .title("Bad Request Exception, Check the Documentation")
	                        .details(ex.getMessage())
	                        .developerMessage(ex.getClass().getName())
	                        .build(), HttpStatus.BAD_REQUEST);
	}
		
	@ExceptionHandler(PessoaInexistenteOuInativaException.class)
	    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre) {
	        return new ResponseEntity<>(
	                BadRequestExceptionDetails.builder()
	                        .timestamp(LocalDateTime.now())
	                        .status(HttpStatus.BAD_REQUEST.value())
	                        .title("Bad Request Exception, Check the Documentation")
	                        .details(bre.getMessage())
	                        .developerMessage(bre.getClass().getName())
	                        .build(), HttpStatus.BAD_REQUEST);
	    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException bre) {
    	
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Pessoa inexistente  ou inativa.")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
