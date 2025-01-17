package br.com.vagafacil.controller;

import br.com.vagafacil.Interfaces.TelaSegundaria;
import br.com.vagafacil.models.AreaAtuacao;
import br.com.vagafacil.services.Operacoes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarTrabalhadorController implements TelaSegundaria{
    
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
        try {

            String nome = txtNomeTrabalhador.getText();
            AreaAtuacao area = cbAreaAtuacao.getValue();
            String cpf = txtCpf.getText();
            String descricao = txtDescricao.getText();
            double salario = Double.parseDouble(txtSalario.getText());

            if (nome.isEmpty() || area == null || cpf.isEmpty() || descricao.isEmpty() || txtSalario.getText().isEmpty()) {
                System.out.println("Por favor, preencha todos os campos");
                return;
            }

            Operacoes.cadastrarTrabalhador(nome, cpf, area, salario, descricao);

            limparCampos();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar trabalhador: " +e.getMessage());
        }
    }

    private void limparCampos() {
        txtNomeTrabalhador.clear();
        cbAreaAtuacao.setValue(null);
        txtCpf.clear();
        txtSalario.clear();
        txtDescricao.clear();
    }

    @FXML
    @Override
    public void voltarTelaInicial() {
        // Fecha a janela atual
        Stage stage = (Stage) txtNomeTrabalhador.getScene().getWindow();
        stage.close();
    }

}
