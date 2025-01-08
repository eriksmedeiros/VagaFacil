package br.com.vagafacil.models;

import java.util.ArrayList;

public class Trabalhador {
    private String nome;
    private String cpf;
    private Double salario;
    private String areaAtuacao;
    private String descricao;
    private ArrayList<String> empresas;
    private Boolean estaTrabalhando;
    
    public Trabalhador(String nome, String cpf, Double salario, String areaAtuacao, String descricao, ArrayList<String> empresas, Boolean estaTrabalhando) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.areaAtuacao = areaAtuacao;
        this.descricao = descricao;
        this.empresas = empresas;
        this.estaTrabalhando = estaTrabalhando;
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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<String> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ArrayList<String> empresas) {
        this.empresas = empresas;
    }

    public Boolean getEstaTrabalhando() {
        return estaTrabalhando;
    }

    public void setEstaTrabalhando(Boolean estaTrabalhando) {
        this.estaTrabalhando = estaTrabalhando;
    }

    @Override
    public String toString() {
        return
        "cpf='" + cpf + '\'' + 
        ", nome='" + nome + '\'' + 
        ", salario=" + salario + 
        ", descricao='" + descricao + '\'' + 
        ", areaAtuacao='" + areaAtuacao + '\'' + 
        ", empresas=" + empresas + 
        ", estaTrabalhando=" + estaTrabalhando;
    }	
}
