package br.com.vagafacil.controller;

import br.com.vagafacil.models.Trabalhador;
import br.com.vagafacil.services.Operacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuscarTrabalhadorController {

    @FXML
    private TextField inputCPF;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbSalario;

    @FXML
    private Label lbArea;

    @FXML
    private Label lbLivre;

    @FXML
    private Label lbEmpresas;

    @FXML
    private Label lbDescricao;

    private Trabalhador trabalhador;

    public void initialize() {
    }

    @FXML
    public void buscarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {
        try {
            // Recupera o dado do campo
            String cpf = inputCPF.getText();

            // Validações simples
            if (cpf.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CPF não informado");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, informe o CPF.");
                alert.showAndWait();
                return;
            }

            // Passa o dado para a classe Operacoes
            trabalhador = Operacoes.buscarTrabalhador(cpf);

            // Verifica se o CPF existe
            if (trabalhador == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CPF não encontrado");
                alert.setHeaderText(null);
                alert.setContentText("Nenhum trabalhador foi encontrado com o CPF informado.");
                alert.showAndWait();
                return;
            }

            // Exibe os dados na interface
            lbArea.setText(trabalhador.getAreaAtuacao() != null ? trabalhador.getAreaAtuacao().toString() : "Não disponível");
            lbDescricao.setText(trabalhador.getDescricao() != null ? trabalhador.getDescricao() : "Não disponível");
            lbLivre.setText(trabalhador.getEstaTrabalhando() ? "Não" : "Sim");
            lbNome.setText(trabalhador.getNome() != null ? trabalhador.getNome() : "Não disponível");
            lbSalario.setText(trabalhador.getSalario() != null ? "R$ " + trabalhador.getSalario() + " por mês" : "Não informado");

            // Exibe as empresas
            if (trabalhador.getEmpresas() != null && trabalhador.getEmpresas().size() > 0) {
                lbEmpresas.setText(String.join(", ", trabalhador.getEmpresas()));
            } else {
                lbEmpresas.setText("Não disponível");
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar trabalhador: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao buscar trabalhador");
            alert.setHeaderText("Ocorreu um erro ao buscar os dados.");
            alert.setContentText("Erro: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void voltarParaTelaInicial(@SuppressWarnings("exports") ActionEvent event) {
        Stage stage = (Stage) lbNome.getScene().getWindow();
        stage.close();
    }
}
