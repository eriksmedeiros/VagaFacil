package br.com.vagafacil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import br.com.vagafacil.dao.BancoDAO;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        // Carrega os dados ao iniciar a aplicação
        BancoDAO.getInstanciaBancoDAO(); // O carregamento já ocorre no construtor do singleton

        scene = new Scene(loadFXML("TelaPrincipal"), 800, 600);
        stage.setScene(scene);
        stage.setTitle("Vaga Fácil");
        stage.show();
    }

    @Override
    public void stop() {
        // Salva os dados ao encerrar a aplicação
        BancoDAO.getInstanciaBancoDAO().salvarDados();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
