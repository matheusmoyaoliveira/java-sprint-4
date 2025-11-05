package br.com.fiap.sprint.dto.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ConsultaResponse {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    private String modalidade;

    public ConsultaResponse() {}

    public ConsultaResponse(Long id, LocalDateTime dataHora, String modalidade) {
        this.id = id;
        this.dataHora = dataHora;
        this.modalidade = modalidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }
}
