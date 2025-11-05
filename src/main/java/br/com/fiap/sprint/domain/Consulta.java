package br.com.fiap.sprint.domain;

import java.time.LocalDateTime;

public class Consulta {

    private Long id;
    private LocalDateTime dataHora;
    private String modalidade;
    private Long idPaciente;
    private Long idMedico;

    public Consulta() {
    }

    public Consulta(Long id, LocalDateTime dataHora, String modalidade, Long idPaciente, Long idMedico) {
        this.id = id;
        this.dataHora = dataHora;
        this.modalidade = modalidade;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
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
