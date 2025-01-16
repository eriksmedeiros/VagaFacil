package br.com.vagafacil.dao;

import java.io.*;
import java.util.ArrayList;

import br.com.vagafacil.models.Empresa;
import br.com.vagafacil.models.Trabalhador;

public class BancoDAO {

    // Atributos
    private ArrayList<Empresa> empresas;
    private ArrayList<Trabalhador> trabalhadores;

    // Instância privada
    private static BancoDAO instanciaBancoDAO;

    // Construtor privado
    private BancoDAO() {
        empresas = new ArrayList<>();
        trabalhadores = new ArrayList<>();
        carregarDados(); // Carrega os dados ao inicializar a instância
    }

    // Singleton para obter a instância do BancoDAO
    public static BancoDAO getInstanciaBancoDAO() {
        if (instanciaBancoDAO == null) {
            instanciaBancoDAO = new BancoDAO();
        }
        return instanciaBancoDAO;
    }

    // Métodos para acessar e modificar listas
    public ArrayList<Empresa> getArrayEmpresas() {
        return empresas;
    }

    public ArrayList<Trabalhador> getArrayTrabalhadores() {
        return trabalhadores;
    }

    public void adicionarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }

    public void adicionarTrabalhador(Trabalhador trabalhador) {
        trabalhadores.add(trabalhador);
    }

    public void removerEmpresa(Empresa empresa) {
        empresas.remove(empresa);
    }

    public void removerTrabalhador(Trabalhador trabalhador) {
        trabalhadores.remove(trabalhador);
    }

    // Salva os dados em um arquivo binário
    public void salvarDados() {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Cria o diretório se não existir
            }

            File file = new File(dataFolder, "dados.bin");

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(trabalhadores);
                oos.writeObject(empresas);
                System.out.println("Dados salvos com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // Carrega os dados de um arquivo binário
    @SuppressWarnings("unchecked")
    public void carregarDados() {
        try {
            File dataFolder = new File("data");
            File file = new File(dataFolder, "dados.bin");

            if (!file.exists()) {
                return; // Não há dados para carregar
            }

            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                trabalhadores = (ArrayList<Trabalhador>) ois.readObject();
                empresas = (ArrayList<Empresa>) ois.readObject();
                System.out.println("Dados carregados com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}
