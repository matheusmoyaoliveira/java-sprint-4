package br.com.fiap.sprint.domain;

import java.time.LocalDate;

public class Paciente {

    private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private Double altura;
    private Double peso;
    private LocalDate dataNascimento;
    private String escolaridade;
    private String estadoCivil;
    private String descricao;

    public Paciente() {
    }

    public Paciente(Long id, String nome, String cpf, String rg, Double altura, Double peso, LocalDate dataNascimento, String escolaridade, String estadoCivil, String descricao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.altura = altura;
        this.peso = peso;
        this.dataNascimento = dataNascimento;
        this.escolaridade = escolaridade;
        this.estadoCivil = estadoCivil;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
