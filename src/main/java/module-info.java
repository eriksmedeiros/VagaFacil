module br.com.vagafacil {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens br.com.vagafacil to javafx.fxml;
    exports br.com.vagafacil;

    opens br.com.vagafacil.controller to javafx.fxml;
    exports br.com.vagafacil.controller;
}
