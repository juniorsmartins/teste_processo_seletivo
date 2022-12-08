package br.com.empresax.domain.dtos.funcionario;

import br.com.empresax.domain.dtos.PolicyDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record VendedorDTORequest
    (
        @NotBlank
        @Length(max = 50)
        String nome,

        @NotNull
        @PastOrPresent
        @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate mesAnoAdmissao
    ) implements PolicyDTO<Long>
{ }
