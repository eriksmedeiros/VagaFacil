package br.com.vagafacil.services;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.vagafacil.dao.BancoDAO;
import br.com.vagafacil.models.AreaAtuacao;
import br.com.vagafacil.models.Empresa;
import br.com.vagafacil.models.Trabalhador;

public class Operacoes {
    private static BancoDAO database = BancoDAO.getInstanciaBancoDAO();

    public static void cadastrarEmpresa(String nome, String cnpj, AreaAtuacao areaAtuacao, double contaBancaria, String descricao) {
        try {
            // Criação da empresa com os dados fornecidos
            Empresa empresa = new Empresa(cnpj, nome, contaBancaria, descricao, areaAtuacao, null);
    
            // Adiciona a empresa ao banco de dados
            database.adicionarEmpresa(empresa);
            System.out.println("Empresa cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar empresa: " + e.getMessage());
        }
    }

    public static void cadastrarTrabalhador() {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Digite o nome do trabalhador: ");
            String nome = scan.nextLine();

            System.out.println("Digite o CPF do trabalhador: ");
            String cpf = scan.nextLine();

            System.out.println("Digite o salário do trabalhador:");
            double salario = scan.nextDouble();
            scan.nextLine();

            AreaAtuacao areaAtuacao = null;
            while (areaAtuacao == null) {
                System.out.println("Escolha uma das Áreas de Atuação:");
                AreaAtuacao.exibirOpções();
                try {
                    areaAtuacao = AreaAtuacao.valueOf(scan.nextLine().toUpperCase());
                } catch (Exception e) {
                    System.out.println("Opção inválida, tente novamente.");
                }
            }

            System.out.println("Adicione uma Descrição:");
            String descricao = scan.nextLine();

            Trabalhador trabalhador = new Trabalhador(nome, cpf, salario, areaAtuacao, descricao, null, false);
            database.adicionarTrabalhador(trabalhador);
            System.out.println("Trabalhador cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar trabalhador: " + e.getMessage());
        }
    }

    public static void contratarTrabalhador() {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        
        String cnpj;
        String cpf;
        Boolean empresaEncontrada = false;
        Boolean trabalhadorEncontrado = false;

        System.out.println("Digite o CNPJ da empresa");
        cnpj = scan.nextLine();

        ArrayList<Empresa> empresas = database.getArrayEmpresas();
        ArrayList<Trabalhador> trabalhadores = database.getArrayTrabalhadores();

        for(Empresa empresa : empresas) {
            if(((Empresa)empresa).getCnpj().equals(cnpj)) {
                empresaEncontrada = true;
                Empresa emp = (Empresa) empresa;

                System.out.println("Digite o CPF do trabalhado que você deseja contratar");
                cpf = scan.nextLine();

                for(Trabalhador trabalhador : trabalhadores) {
                    if(((Trabalhador)trabalhador).getCpf().equals(cpf)) {
                        trabalhadorEncontrado = true;
                        if(((Trabalhador)trabalhador).getEstaTrabalhando().equals(true)) {
                            System.out.println("Não é possível contratar " +trabalhador.getNome() +", pois ele(a) já está em um trabalho");
                            break;
                        } else {
                            if(emp.getContaBancaria() < trabalhador.getSalario()) {
                                System.out.println("A empresa não tem saldo suficiente para pagar esse trabalhador.");
                            } else {
                                emp.adicionarFuncionario(trabalhador);
                                trabalhador.setEstaTrabalhando(true);
                                emp.setContaBancaria(emp.getContaBancaria() - trabalhador.getSalario());
                            }
                        }
                    }
                }
            }
        }

        if (!empresaEncontrada) {
            System.out.println("Não existe nenhuma empresa com esse CNPJ");
        }

        if (!trabalhadorEncontrado) {
            System.out.println("Não existe nenhum trabalhador com esse CPF");
        }
    }

    public static void demitirTrabalhador() {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        
        String cnpj;
        String cpf;
        Boolean empresaEncontrada = false;
        Boolean trabalhadorEncontrado = false;

        System.out.println("Digite o CNPJ da empresa");
        cnpj = scan.nextLine();

        ArrayList<Empresa> empresas = database.getArrayEmpresas();
        ArrayList<Trabalhador> trabalhadores = database.getArrayTrabalhadores();

        for(Empresa empresa : empresas) {
            if(((Empresa)empresa).getCnpj().equals(cnpj)) {
                empresaEncontrada = true;
                Empresa emp = (Empresa) empresa;

                System.out.println("Digite o CPF do trabalhado que você deseja contratar");
                cpf = scan.nextLine();

                for(Trabalhador trabalhador : trabalhadores) {
                    if(((Trabalhador)trabalhador).getCpf().equals(cpf)) {
                        trabalhadorEncontrado = true;
                        for(Trabalhador trb  : emp.getFuncionarios()) {
                            if (((Trabalhador)trb).getCpf().equals(cpf)) {
                                emp.demitirFuncionario(trb);
                                trb.setEstaTrabalhando(false);
                                emp.setContaBancaria(emp.getContaBancaria() + trabalhador.getSalario());
                            } else {
                                System.out.println("Esse funcionário não pertence à empresa");
                            }
                        }
                    }
                }
            }
        }

        if (!empresaEncontrada) {
            System.out.println("Não existe nenhuma empresa com esse CNPJ");
        }

        if (!trabalhadorEncontrado) {
            System.out.println("Não existe nenhum trabalhador com esse CPF");
        }
    }

    public static ArrayList<Empresa> buscarEmpresas(AreaAtuacao area) {
        return database.getArrayEmpresas().stream()
                       .filter(empresa -> empresa.getAreaAtuacao() == area)
                       .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Trabalhador> buscarTrabalhadores(AreaAtuacao area) {
        return database.getArrayTrabalhadores().stream()
                       .filter(trabalhador -> trabalhador.getAreaAtuacao() == area && !trabalhador.getEstaTrabalhando())
                       .collect(Collectors.toCollection(ArrayList::new));
    }

    public static Trabalhador buscarTrabalhador(String cpf) {
        if (cpf == null) {
            return null;
        }
        return database.getArrayTrabalhadores().stream()
                       .filter(trabalhador -> cpf.equals(trabalhador.getCpf()))
                       .findFirst()
                       .orElse(null);
    }
    
    public static Empresa buscarEmpresa(String cnpj) {
        if (cnpj == null) {
            return null;
        }
        return database.getArrayEmpresas().stream()
                       .filter(empresa -> cnpj.equals(empresa.getCnpj()))
                       .findFirst()
                       .orElse(null);
    }
    
} 
