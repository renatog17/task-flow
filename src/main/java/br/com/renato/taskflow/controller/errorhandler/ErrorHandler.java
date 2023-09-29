package br.com.renato.taskflow.controller.errorhandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> tratarErroValidation(MethodArgumentNotValidException exception) {
		var erros = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}
	
	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<?> tratarErroFormatoInvalidoData(DateTimeParseException exception){
		ErrorResponse error = new ErrorResponse("Data em formato inválido. Formato esperado dd/MM/aaaa", HttpStatus.BAD_REQUEST, "400");
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> tratarErroChaveEstrangeiraNaoEncontrada(SQLIntegrityConstraintViolationException exception){
		ErrorResponse error = new ErrorResponse("Referência para lista ou quadro não encontrado", HttpStatus.BAD_REQUEST, "400");
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> tratarErroTipoInvalido(HttpMessageNotReadableException exception){
		ErrorResponse error = new ErrorResponse("Um tipo não esperado foi atribuido", HttpStatus.BAD_REQUEST, "400");
		return ResponseEntity.badRequest().body(error);
	}
}
