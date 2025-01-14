package br.com.vagafacil.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    // Método para abrir a tela de cadastro de empresa
    public void abrirTelaCadastrarEmpresa(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/CadastrarEmpresa.fxml", "Cadastrar Empresa");
    }

    // Método para abrir a tela de cadastro de trabalhador
    public void abrirTelaCadastrarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/CadastrarTrabalhador.fxml", "Cadastrar Trabalhador");
    }

    // Método para abrir a tela de funcionalidades
    public void abrirTelaFuncionalidades(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/funcionalidades.fxml", "Funcionalidades");
    }

    // Método genérico para carregar telas
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

