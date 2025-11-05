package br.com.fiap.sprint.dto.paciente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PacienteRequest {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre {min} e {max} caracteres.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório.")
    @Size(max = 13, message = "O RG deve ter no máximo {max} caracteres.")
    private String rg;

    @NotNull(message = "A altura é obrigatória.")
    @DecimalMin(value = "0.5", message = "A altura deve ser maior que 0.5 metros.")
    @DecimalMax(value = "3.0", message = "A altura deve ser menor que 3 metros.")
    private Double altura;

    @NotNull(message = "O peso é obrigatório.")
    @DecimalMin(value = "1.0", message = "O peso deve ser maior que 1 kg.")
    @DecimalMax(value = "500.0", message = "O peso deve ser menor que 500 kg.")
    private Double peso;

    @Past(message = "A data de nascimento deve ser anterior à data atual.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank(message = "A escolaridade é obrigatória.")
    @Size(max = 50, message = "A escolaridade deve ter no máximo {max} caracteres.")
    private String escolaridade;

    @NotBlank(message = "O estado civil é obrigatório.")
    @Size(max = 50, message = "O estado civil deve ter no máximo {max} caracteres.")
    private String estadoCivil;

    @Size(max = 100, message = "A descrição deve ter no máximo {max} caracteres.")
    private String descricao;


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getEscolaridade() { return escolaridade; }
    public void setEscolaridade(String escolaridade) { this.escolaridade = escolaridade; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
