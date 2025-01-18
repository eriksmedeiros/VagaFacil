package br.com.vagafacil.controller;

import java.io.IOException;
import javafx.scene.control.Label;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TelaPrincipalController {
    @FXML
    private MenuItem btnBuscarEmpresa;

    @FXML
    private MenuItem btnBuscarTrabalhador;

    @FXML
    private MenuItem btnCadastrarEmpresa;

    @FXML
    private MenuItem btnCadastrarTrabalhador;

    @FXML
    private MenuItem btnContratarTrabalhador;

    @FXML
    private MenuItem btnDemitirTrabalhador;

    @FXML
    private MenuItem btnListarEmpresas;

    @FXML
    private MenuItem btnListarTrabalhadores;

    @FXML
    private MenuItem btnSobre;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu mnuAjuda;

    @FXML
    private Menu mnuBuscar;

    @FXML
    private Menu mnuCadastro;

    @FXML
    private Menu mnuGestao;

    @FXML
    private Menu mnuVizualizar;

    @FXML
    private Label lblData;

    @FXML
    private Label lblHora;

    @FXML
    public void initialize() {
        this.carregarBarraStatus();
    }

    @FXML
    // Método para abrir a tela de cadastro de empresa
    public void abrirTelaCadastrarEmpresa(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/cadastrarEmpresa.fxml", "Cadastrar Empresa");
    }

    @FXML
    // Método para abrir a tela de cadastro de trabalhador
    public void abrirTelaCadastrarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/cadastrarTrabalhador.fxml", "Cadastrar Trabalhador");
    }

    @FXML
    public void abrirTelaContratarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/contratarTrabalhador.fxml", "Contratar Trabalhador"); 
     }

    @FXML
    public void abrirTelaDemitirTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/demitirTrabalhador.fxml", "Demitir Trabalhador"); 
    }

    @FXML
    public void abrirTelaListarEmpresas(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/ListarEmpresas.fxml", "Listar Empresas"); 
    }

    @FXML
    public void abrirTelaListarTrabalhadores(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/ListarTrabalhadores.fxml", "Listar Trabalhadores"); 
    }

    @FXML
    public void abrirTelaBuscarEmpresa(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/BuscarEmpresa.fxml", "Buscar Empresa"); 
    }

    @FXML
    public void abrirTelaBuscarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/BuscarTrabalhador.fxml", "Buscar Trabalhador"); 
    }

    @FXML
    public void abrirTelaSobre(@SuppressWarnings("exports") ActionEvent event) {
        carregarTela("/br/com/vagafacil/TelaSobre.fxml", "Sobre");
    }

    public void carregarBarraStatus() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.lblData.setText("Data: " + LocalDate.now().format(dataFormatada));

        Timeline relogio = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter horaFormatada = DateTimeFormatter.ofPattern("HH:mm:ss");
            this.lblHora.setText("Hora: " + LocalTime.now().format(horaFormatada));
        }), new KeyFrame(Duration.seconds(1)));
        relogio.setCycleCount(Animation.INDEFINITE);
        relogio.play();
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