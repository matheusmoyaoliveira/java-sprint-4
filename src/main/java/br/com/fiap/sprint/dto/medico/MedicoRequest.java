package br.com.fiap.sprint.dto.medico;

import jakarta.validation.constraints.*;

public class MedicoRequest {

    @NotBlank(message = "O nome do médico é obrigatório.")
    @Size(min = 2, max = 30, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String nome;

    @NotBlank(message = "O CRM é obrigatório.")
    @Pattern(regexp = "^\\d{5}-[A-Z]{2}$", message = "O CRM deve seguir o formato 12345-SP.")
    private String crm;


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCrm() { return crm; }
    public void setCrm(String crm) { this.crm = crm; }
}
