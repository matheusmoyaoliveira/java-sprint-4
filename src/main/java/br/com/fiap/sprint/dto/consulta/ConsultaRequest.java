package br.com.fiap.sprint.dto.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ConsultaRequest {

    @NotNull(message = "A data e hora da consulta são obrigatórias.")
    @Future(message = "A data da consulta deve ser futura.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    @NotBlank(message = "A modalidade é obrigatória.")
    @Size(max = 20, message = "A modalidade deve ter no máximo {max} caracteres.")
    private String modalidade;

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }
}
