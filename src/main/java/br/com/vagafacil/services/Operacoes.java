package br.com.vagafacil.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import br.com.vagafacil.dao.BancoDAO;
import br.com.vagafacil.models.AreaAtuacao;
import br.com.vagafacil.models.Empresa;
import br.com.vagafacil.models.Trabalhador;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Operacoes {
    private static BancoDAO database = BancoDAO.getInstanciaBancoDAO();

    public static String cadastrarEmpresa(String nome, String cnpj, AreaAtuacao areaAtuacao, double contaBancaria, String descricao) {
        try {
            // Criação da empresa com os dados fornecidos
            Empresa empresa = new Empresa(cnpj, nome, contaBancaria, descricao, areaAtuacao, null);
    
            // Adiciona a empresa ao banco de dados
            database.adicionarEmpresa(empresa);
            return "Sucesso: Empresa cadastrada com sucesso!";
        } catch (Exception e) {
            return "Erro: Empresa não cadastrada. " + e.getMessage();
        }
    }

    public static String cadastrarTrabalhador(String nome, String cpf, AreaAtuacao areaAtuacao, double salario, String descricao) {
        try {
            Trabalhador trabalhador = new Trabalhador(nome, cpf, salario, areaAtuacao, descricao, null, false);

            database.adicionarTrabalhador(trabalhador);
            return "Sucesso: Trabalhador cadastrado com sucesso!";
        } catch (Exception e) {
            return "Erro: Trabalhador não cadastrado. " + e.getMessage();
        }
    }

    public static String contratarTrabalhador(String cnpj, String cpf) {
        @SuppressWarnings("resource")
        
        Boolean empresaEncontrada = false;
        Boolean trabalhadorEncontrado = false;

        ArrayList<Empresa> empresas = database.getArrayEmpresas();
        ArrayList<Trabalhador> trabalhadores = database.getArrayTrabalhadores();

        for(Empresa empresa : empresas) {
            if(((Empresa)empresa).getCnpj().equals(cnpj)) {
                empresaEncontrada = true;
                Empresa emp = (Empresa) empresa;

                for(Trabalhador trabalhador : trabalhadores) {
                    if(((Trabalhador)trabalhador).getCpf().equals(cpf)) {
                        trabalhadorEncontrado = true;
                        
                        if(((Trabalhador)trabalhador).getEstaTrabalhando().equals(true)) {
                            return "Erro: Não é possível contratar " + trabalhador.getNome() +
                                ", pois ele já está em um trabalho.";

                        } else {
                            if(emp.getContaBancaria() < trabalhador.getSalario()) {
                                return "Erro: Saldo insuficiente para contratar " + trabalhador.getNome() + ".";

                            } else {
                                emp.adicionarFuncionario(trabalhador);
                                trabalhador.setEstaTrabalhando(true);
                                emp.setContaBancaria(emp.getContaBancaria() - trabalhador.getSalario());
                                return "Sucesso: Trabalhador " + trabalhador.getNome() + " contratado com sucesso!";
                            }
                        }
                    }
                }
            }
        }

        if (!empresaEncontrada) {
            return "Erro: Não existe nenhuma empresa com esse CNPJ.";
        }

        if (!trabalhadorEncontrado) {
            return "Erro: Não existe nenhum trabalhador com esse CPF.";
        }

        return null;
    }

    public static String demitirTrabalhador(String cnpj, String cpf) {    
        @SuppressWarnings("resource")
       
        Boolean empresaEncontrada = false;
        Boolean trabalhadorEncontrado = false;

        ArrayList<Empresa> empresas = database.getArrayEmpresas();
        ArrayList<Trabalhador> trabalhadores = database.getArrayTrabalhadores();

        for(Empresa empresa : empresas) {
            if(((Empresa)empresa).getCnpj().equals(cnpj)) {
                empresaEncontrada = true;
                Empresa emp = (Empresa) empresa;

                for(Trabalhador trabalhador : trabalhadores) {
                    if(((Trabalhador)trabalhador).getCpf().equals(cpf)) {
                        trabalhadorEncontrado = true;
                        for(Trabalhador trb  : emp.getFuncionarios()) {
                            if (((Trabalhador)trb).getCpf().equals(cpf)) {
                                emp.demitirFuncionario(trb);
                                trb.setEstaTrabalhando(false);
                                emp.setContaBancaria(emp.getContaBancaria() + trabalhador.getSalario());
                                return "Sucesso: Trabalhador " + trabalhador.getNome() + " demitido com sucesso!";
                            } else {
                                return "Erro: O trabalhador " + trabalhador.getNome() + " não pertence a empresa.";
                            }
                        }
                    }
                }
            }
        }

        if (!empresaEncontrada) {
            return "Erro: Não existe nenhuma empresa com esse CNPJ.";
        }

        if (!trabalhadorEncontrado) {
            return "Erro: Não existe nenhum trabalhador com esse CPF.";
        }

        return null;
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

    public static void exibeErro(String titulo, String cabecalho, String mensagem) {
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        alerta.showAndWait();   
    }

    public static void exibeAlert(String titulo, String cabecalho, String mensagem) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(mensagem);
        alerta.showAndWait();   
    }
    
} 
