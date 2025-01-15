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
        String resultado = Operacoes.contratarTrabalhador(cnpj, cpf);

        if (cnpj.isEmpty() || cpf.isEmpty()) {
            Operacoes.exibeErro("Erro", "Campos obrigatórios", "Por favor, preencha todos os campos.");
            return;
        }

        if (resultado != null) {
            if(resultado.startsWith("Erro")) {
                Operacoes.exibeErro("Erro", "Erro ao contratar trabalhador", resultado);
            } else if(resultado.startsWith("Sucesso")) {
                Operacoes.exibeAlert("Sucesso", "Trabalhador contratado", resultado);
            }
        }

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
