package br.com.vagafacil.controller;

import br.com.vagafacil.models.AreaAtuacao;
import br.com.vagafacil.services.Operacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarTrabalhadorController {
    
    @FXML
    private TextField txtNomeTrabalhador;

    @FXML
    private TextField txtCpf;

    @FXML
    private ComboBox<AreaAtuacao> cbAreaAtuacao;

    @FXML
    private TextField txtSalario;

    @FXML
    private TextField txtDescricao;

    @FXML
    public void initialize() {
        cbAreaAtuacao.getItems().addAll(AreaAtuacao.values());
    }

    @FXML
    public void cadastrarTrabalhador(@SuppressWarnings("exports") ActionEvent event) {

        String nome = txtNomeTrabalhador.getText();
        AreaAtuacao area = cbAreaAtuacao.getValue();
        String cpf = txtCpf.getText();
        String descricao = txtDescricao.getText();
        double salario = Double.parseDouble(txtSalario.getText());
        String resultado = Operacoes.cadastrarTrabalhador(nome, cpf, area, salario, descricao);


        if (nome.isEmpty() || area == null || cpf.isEmpty() || descricao.isEmpty() || txtSalario.getText().isEmpty()) {
            Operacoes.exibeErro("Erro", "Campos obrigatórios", "Por favor, preencha todos os campos.");
            return;
        }

        if (resultado != null) {
            if(resultado.startsWith("Erro")) {
                Operacoes.exibeErro("Erro", "Cadastro não realizado", resultado);
            } else if(resultado.startsWith("Sucesso")) {
                Operacoes.exibeAlert("Sucesso", "Cadastro realizado", resultado);
            }
        }

        limparCampos();
    }

    @FXML
    public void voltarParaTelaInicial(@SuppressWarnings("exports") ActionEvent event) {
        // Fecha a janela atual
        Stage stage = (Stage) txtNomeTrabalhador.getScene().getWindow();
        stage.close();
    }

    private void limparCampos() {
        txtNomeTrabalhador.clear();
        cbAreaAtuacao.setValue(null);
        txtCpf.clear();
        txtSalario.clear();
        txtDescricao.clear();
    }

}
