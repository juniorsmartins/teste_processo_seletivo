package br.com.empresax.domain.dtos.dashboard;

import br.com.empresax.domain.dtos.PolicyDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record DashboardDTORequest
    (
        @NotNull
        @PastOrPresent
        @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonSerialize(using = LocalDateSerializer.class)
        LocalDate mesAnoPesquisado
    ) implements PolicyDTO<Long>
{ }
