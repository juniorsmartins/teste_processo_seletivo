package br.com.empresax.application.excecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class TratamentoDeExcecao {

    @Autowired
    private MessageSource internationalMessage;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<SuporteParaDevolucaoDeExcecao> methodArgumentNotValidException(MethodArgumentNotValidException method) {

        List<SuporteParaDevolucaoDeExcecao> listOfHandledErrors = new ArrayList<>();
        List<FieldError> listOfFieldErrors = method.getBindingResult().getFieldErrors();
        listOfFieldErrors.forEach(theError -> {
            String message = internationalMessage.getMessage(theError, LocaleContextHolder.getLocale());
            SuporteParaDevolucaoDeExcecao exceptionHandledReturn = new SuporteParaDevolucaoDeExcecao(
                    HttpStatus.BAD_REQUEST.toString(), message, theError.getCode(), theError.getField());
            listOfHandledErrors.add(exceptionHandledReturn);
        });

        return ResponseEntity
                .badRequest()
                .body(listOfHandledErrors.get(0));
    }

    @ExceptionHandler(ResourceNotFoundCustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<SuporteParaDevolucaoDeExcecao> capturaDeResourceNotFoundCustomException(ResourceNotFoundCustomException resource) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new SuporteParaDevolucaoDeExcecao(HttpStatus.NOT_FOUND.toString(), resource.getMessage()));
    }
}
