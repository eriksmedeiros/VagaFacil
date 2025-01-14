package br.com.vagafacil.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.com.vagafacil.models.Empresa;
import br.com.vagafacil.models.Trabalhador;

public class BancoDAO {
    
    //atributos
    private ArrayList<Empresa> empresas;
    private ArrayList<Trabalhador> trabalhadores;

    //instancia privada
    private static BancoDAO instanciaBancoDAO;

    //construtor privador
    private BancoDAO() {
        empresas = new ArrayList<>();
        trabalhadores = new ArrayList<>();
    }

    //métodos
    public static BancoDAO getInstanciaBancoDAO() {
        if (instanciaBancoDAO == null) {
            instanciaBancoDAO = new BancoDAO();
        }

        return instanciaBancoDAO;
    }

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

    public void salvarDados() {
        try {
            File dataFolder = new File("data");
        
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }
        
            File file = new File(dataFolder, "dados.bin");
        
            try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(trabalhadores);
                oos.writeObject(empresas); 
                System.out.println("Dados salvos com sucesso");
                }
        } catch (Exception e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void carregarDados() {
        try {
            File dataFolder = new File("data");
            File file = new File(dataFolder, "dados.bin");
            
            if (!file.exists()) {
                return;
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
