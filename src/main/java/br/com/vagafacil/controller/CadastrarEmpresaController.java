package br.com.vagafacil.controller;

import br.com.vagafacil.services.Operacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import br.com.vagafacil.models.*;

public class CadastrarEmpresaController {
    
    @FXML
    private TextField txtNomeEmpresa;

    @FXML
    private ComboBox<AreaAtuacao> cbAreaAtuacao;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private TextField txtContaBancaria;

    @FXML
    private TextField txtDescricao;

    @FXML
    public void initialize() {
        // Configura o ComboBox com as áreas de atuação disponíveis
        cbAreaAtuacao.getItems().addAll(AreaAtuacao.values());
    }

    @FXML
    public void cadastrarEmpresa(@SuppressWarnings("exports") ActionEvent event) {
        // Recupera os dados dos campos
        String nome = txtNomeEmpresa.getText();
        AreaAtuacao area = cbAreaAtuacao.getValue();
        String cnpj = txtCNPJ.getText();
        String descricao = txtDescricao.getText();
        double contaBancaria = Double.parseDouble(txtContaBancaria.getText());
        String resultado = Operacoes.cadastrarEmpresa(nome, cnpj, area, contaBancaria, descricao);


        // Validações simples
        if (nome.isEmpty() || area == null || cnpj.isEmpty() || descricao.isEmpty() || txtContaBancaria.getText().isEmpty()) {
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
        Stage stage = (Stage) txtNomeEmpresa.getScene().getWindow();
        stage.close();
    }

    private void limparCampos() {
        txtNomeEmpresa.clear();
        cbAreaAtuacao.setValue(null);
        txtCNPJ.clear();
        txtContaBancaria.clear();
        txtDescricao.clear();
    }
}
