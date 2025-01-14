package br.com.vagafacil.controller;

import br.com.vagafacil.services.Operacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContratarTrabalhadorController {
    @FXML
    private Button btnContratar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private TextField txtCPF;

    @FXML
    void contratarTrabalhador(ActionEvent event) {
        String cnpj = txtCNPJ.getText();
        String cpf = txtCPF.getText();

        if (cnpj.isEmpty() || cpf.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        Operacoes.contratarTrabalhador(cnpj, cpf);

        limparCampos();
    }

    @FXML
    void voltarTelaInicial(ActionEvent event) {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

    private void limparCampos() {
        txtCNPJ.clear();
        txtCPF.clear();
    }
}
