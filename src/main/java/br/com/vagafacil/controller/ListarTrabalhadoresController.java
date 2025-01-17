package br.com.vagafacil.controller;

import java.util.ArrayList;

import br.com.vagafacil.models.AreaAtuacao;
import br.com.vagafacil.services.Operacoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ListarTrabalhadoresController {
    
    @FXML
    private ComboBox<AreaAtuacao> cbAreaAtuacao;

    @FXML
    private ListView<String> lvTrabalhadores; // Alterado para exibir Strings

    private ArrayList<String> trabalhadores; // Alterado para lista de Strings

    private ObservableList<String> obsTrabalhadores; // Alterado para ObservableList de Strings

    @FXML
    public void initialize() {
        // Configura o ComboBox com as áreas de atuação disponíveis
        cbAreaAtuacao.getItems().addAll(AreaAtuacao.values());
    }

    @FXML
    public void voltarParaTelaInicial(@SuppressWarnings("exports") ActionEvent event) {
        // Fecha a janela atual
        Stage stage = (Stage) lvTrabalhadores.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buscarTrabalhadores() {
        try {
            // Limpa a ListView antes de adicionar os novos itens
            lvTrabalhadores.getItems().clear();

            // Recupera os dados dos campos
            AreaAtuacao area = cbAreaAtuacao.getValue();
            if (area == null) {
                System.err.println("Erro: Nenhuma área de atuação selecionada.");
                return;
            }

            // Passa os dados para a classe Operacoes
            trabalhadores = Operacoes.buscarTrabalhadores(area);
            obsTrabalhadores = FXCollections.observableList(trabalhadores);

            // Atualiza a ListView com os nomes e CPFs
            lvTrabalhadores.setItems(obsTrabalhadores);

        } catch (Exception e) {
            System.err.println("Erro ao buscar trabalhadores: " + e.getMessage());
        }
    }
}
