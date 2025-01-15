package br.com.vagafacil.controller;

import br.com.vagafacil.models.Empresa;
import br.com.vagafacil.services.Operacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BuscarEmpresaController {

    @FXML
    private TextField inputCNPJ;

    @FXML
    private Label lbNome;

    @FXML
    private Label lbArea;

    @FXML
    private Label lbDescricao;

    private Empresa empresa;

    public void initialize() {
    }

    @FXML
    public void buscarEmpresa(@SuppressWarnings("exports") ActionEvent event) {
        try {
            // Recupera o dado do campo
            String cnpj = inputCNPJ.getText();

            // Validações simples
            if (cnpj.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("CNPJ não informado");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, informe o CNPJ.");
                alert.showAndWait();
                return;
            }

            // Passa o dado para a classe Operacoes
            empresa = Operacoes.buscarEmpresa(cnpj);

            // Verifica se o CNPJ existe
            if (empresa == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("CNPJ não encontrado");
                alert.setHeaderText(null);
                alert.setContentText("Nenhuma empresa foi encontrada com o CNPJ informado.");
                alert.showAndWait();
                return;
            }

            // Exibe os dados na interface
            lbArea.setText(empresa.getAreaAtuacao() != null ? empresa.getAreaAtuacao().toString() : "Não disponível");
            lbDescricao.setText(empresa.getDescricao() != null ? empresa.getDescricao() : "Não disponível");
            lbNome.setText(empresa.getNome() != null ? empresa.getNome() : "Não disponível");

        } catch (Exception e) {
            System.err.println("Erro ao buscar empresa: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Sistema");
            alert.setHeaderText("Ocorreu um erro ao buscar a empresa");
            alert.showAndWait();
        }
    }

    @FXML
    public void voltarParaTelaInicial(@SuppressWarnings("exports") ActionEvent event) {
        Stage stage = (Stage) lbNome.getScene().getWindow();
        if (stage != null) {
            stage.close();
        }
    }
}
