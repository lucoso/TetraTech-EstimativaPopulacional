package br.com.lucaswagner.api.exceptionHandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.lucaswagner.domain.exception.EntidadeNaoEncontradaException;
import br.com.lucaswagner.domain.exception.ErroDoUsuarioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontrada(ErroDoUsuarioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		MensagemErro erro = new MensagemErro();
		erro.setStatus(status.value());
		erro.setTitulo(ex.getMessage());
		erro.setDatahora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ErroDoUsuarioException.class)
	public ResponseEntity<Object> handleNegocio(ErroDoUsuarioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		MensagemErro erro = new MensagemErro();
		erro.setStatus(status.value());
		erro.setTitulo(ex.getMessage());
		erro.setDatahora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
	}

}
