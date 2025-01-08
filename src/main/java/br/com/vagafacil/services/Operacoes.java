package br.com.vagafacil.services;

import java.util.Scanner;

import br.com.vagafacil.dao.BancoDAO;
import br.com.vagafacil.models.Empresa;
import br.com.vagafacil.models.Trabalhador;

public class Operacoes {
    private static BancoDAO database = BancoDAO.getInstanciaBancoDAO();

    public static void cadastrarEmpresa() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Digite o nome da empresa: ");
            String nome = scan.nextLine();

            System.out.println("Digite o CNPJ da empresa: ");
            String cnpj = scan.nextLine();

            System.out.println("Digite o valor inicial da Conta Bancária:");
            double contaBancaria = scan.nextDouble();
            scan.nextLine();

            System.out.println("Adicione uma descrição para a empresa:");
            String descricao = scan.nextLine();

            System.out.println("Digite a área de atuação da empresa:");
            String areaAtuacao = scan.nextLine();

            Empresa empresa = new Empresa(cnpj, nome, contaBancaria, descricao, areaAtuacao, null);
            database.adicionarEmpresa(empresa);
            System.out.println("Empresa cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar empresa: " + e.getMessage());
        }
    }

    public static void cadastrarTrabalhador() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Digite o nome do trabalhador: ");
            String nome = scan.nextLine();

            System.out.println("Digite o CPF do trabalhador: ");
            String cpf = scan.nextLine();

            System.out.println("Digite o salário do trabalhador:");
            double salario = scan.nextDouble();
            scan.nextLine();

            System.out.println("Adicione uma Área de Atuação:");
            String areaAtuacao = scan.nextLine();

            System.out.println("Adicione uma Descrição:");
            String descricao = scan.nextLine();

            Trabalhador trabalhador = new Trabalhador(nome, cpf, salario, areaAtuacao, descricao, null, false);
            database.adicionarTrabalhador(trabalhador);
            System.out.println("Trabalhador cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar trabalhador: " + e.getMessage());
        }
    }
} 
