package br.com.empresax.application.excecao;

import lombok.Data;

@Data
public final class ResourceNotFoundCustomException extends RuntimeException {
    public ResourceNotFoundCustomException(String message) {
        super(message);
    }
}
