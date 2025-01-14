package br.com.vagafacil.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FuncionalidadesController {
    
    public void abrirTelaContratarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
       carregarTela("/br/com/vagafacil/contratarTrabalhador.fxml", "Contratar Trabalhador"); 
    }

    public void abrirTelaDemitirTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/demitirTrabalhador.fxml", "Demitir Trabalhador"); 
    }

    public void abrirTelaListarEmpresas(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/caminho/listarEmpresas", "Listar Empresas"); 
    }

    public void abrirTelaListarTrabalhadores(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/caminho/listarTrabalhadores", "Listar Trabalhadores"); 
    }

    public void abrirTelaBuscarEmpresa(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/caminho/buscarEmpresa", "Buscar Empresa"); 
    }
    
    public void abrirTelaBuscarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/caminho/buscarTrabalhador", "Buscar Trabalhador"); 
    }

    private void carregarTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao carregar a tela: " + caminhoFXML);
        }
    }
}
