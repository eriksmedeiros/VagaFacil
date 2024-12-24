module br.com.vagafacil {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.com.vagafacil to javafx.fxml;
    exports br.com.vagafacil;

    opens br.com.vagafacil.controller to javafx.fxml;
    exports br.com.vagafacil.controller;
}
