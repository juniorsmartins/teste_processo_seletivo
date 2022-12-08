package br.com.empresax.domain.dtos.dashboard;

import br.com.empresax.domain.dtos.PolicyDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DashboardDTOResponse
    (
        @JsonProperty("Data Pesquisada: ")
        LocalDate mesAnoPesquisado,

        @JsonProperty("Valor total pago: ")
        double valorTotalPago
    ) implements PolicyDTO<Long>
{ }
