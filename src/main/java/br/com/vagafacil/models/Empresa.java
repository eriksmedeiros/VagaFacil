package br.com.vagafacil.models;

import java.util.ArrayList;

public class Empresa {
    private String cnpj;
    private String nome;
    private Double contaBancaria;
    private String descricao;
    private AreaAtuacao areaAtuacao;
    private ArrayList<Trabalhador> funcionarios;

    public Empresa(String cnpj, String nome, Double contaBancaria, String descricao, AreaAtuacao areaAtuacao, ArrayList<Trabalhador> funcionarios) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.contaBancaria = contaBancaria;
        this.descricao = descricao;
        this.areaAtuacao = areaAtuacao;
        this.funcionarios = new ArrayList<Trabalhador>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(Double contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public AreaAtuacao getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(AreaAtuacao areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public ArrayList<Trabalhador> getFuncionarios() {
        return funcionarios;
    }

    public void adicionarFuncionario(Trabalhador funcionario) {
        funcionarios.add(funcionario);
    }

    public void demitirFuncionario(Trabalhador funcionario) {
        funcionarios.remove(funcionario);
    }

    @Override
    public String toString() {
        return  "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", contaBancaria=" + contaBancaria +
                ", descricao='" + descricao + '\'' +
                ", areaAtuacao='" + areaAtuacao + '\'' +
                ", funcionarios=" + funcionarios;
    }
    
}
