package br.com.renato.taskflow.controller.errorhandler;

import org.springframework.http.HttpStatusCode;

public record ErrorResponse(String mensagem, HttpStatusCode statusCode, String code) {

     public String getMensagem() {
         return mensagem;
     }
}
