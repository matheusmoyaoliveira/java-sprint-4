package br.com.fiap.sprint.dto.consulta;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class ConsultaRequest {

    @NotNull(message = "A data da consulta é obrigatória")
    private LocalDate dataConsulta;

    @Pattern(regexp = "^(Agendada|Realizada|Cancelada)$",
            message = "O status deve ser Agendada, Realizada ou Cancelada")
    private String status;

    @NotNull(message = "O ID do paciente é obrigatório")
    private Long idPaciente;

    @NotNull(message = "O ID do médico é obrigatório")
    private Long idMedico;

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }
}
