package br.com.vagafacil.controller;

import br.com.vagafacil.services.Operacoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

import br.com.vagafacil.models.*;

public class ListarEmpresasController {

    @FXML
    private ComboBox<AreaAtuacao> cbAreaAtuacao;

    @FXML
    private ListView<Empresa> lvEmpresas;

    private ArrayList<Empresa> empresas;

    private ObservableList<Empresa> obsEmpresas;

    @FXML
    public void initialize() {
        // Configura o ComboBox com as áreas de atuação disponíveis
        cbAreaAtuacao.getItems().addAll(AreaAtuacao.values());
    }

    @FXML
    public void voltarParaTelaInicial(@SuppressWarnings("exports") ActionEvent event) {
        // Fecha a janela atual
        Stage stage = (Stage) lvEmpresas.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buscarEmpresas() {
        try {
            // Limpa a ListView antes de adicionar os novos itens
            lvEmpresas.getItems().clear();

            // Recupera os dados dos campos
            AreaAtuacao area = cbAreaAtuacao.getValue();
            if (area == null) {
                System.err.println("Erro: Nenhuma área de atuação selecionada.");
                return;
            }

            // Passa os dados para a classe Operacoes
            empresas = Operacoes.buscarEmpresas(area);
            obsEmpresas = FXCollections.observableList(empresas);

            lvEmpresas.setItems(obsEmpresas);

        } catch (Exception e) {
            System.err.println("Erro ao buscar empresas: " + e.getMessage());
        }
    }
}
