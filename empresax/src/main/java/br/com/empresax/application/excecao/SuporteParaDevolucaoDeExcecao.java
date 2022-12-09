package br.com.empresax.application.excecao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuporteParaDevolucaoDeExcecao {

    private String status;
    private String annotations;
    private String fieldName;
    private String message;

    public SuporteParaDevolucaoDeExcecao(String status, String mensagem) {
        this.status = status;
        message = mensagem;
    }

    public SuporteParaDevolucaoDeExcecao(String status, String message, String annotations, String fieldName) {
        this(status, message);
        this.annotations = annotations;
        this.fieldName = fieldName;
    }
}
