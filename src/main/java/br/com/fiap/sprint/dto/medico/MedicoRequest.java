package br.com.fiap.sprint.dto.medico;

import jakarta.validation.constraints.*;

public class MedicoRequest {

    @NotBlank(message = "O nome do médico é obrigatório.")
    @Size(min = 2, max = 30, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String nome;

    @NotBlank(message = "O CRM é obrigatório.")
    @Pattern(
            regexp = "^CRM-\\d{5}$",
            message = "O CRM deve seguir o formato CRM-12345 (três letras, hífen e cinco números)."
    )
    private String crm;


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
}
