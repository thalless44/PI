package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TelaClientesSistemaController {

    @FXML
    private TableColumn<?, ?> TableColumnEmail;

    @FXML
    private TableColumn<?, ?> TableColumnID;

    @FXML
    private TableColumn<?, ?> TableColumnNome;

    @FXML
    private TableColumn<?, ?> TableColumnTelefone;

    @FXML
    private Button btnAlterarDados;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSair;

    @FXML
    private TableView<?> tabelaUsuarios;

    @FXML
    void OnClickSair(ActionEvent event) {

    }

    @FXML
    void OnclickAlterarDados(ActionEvent event) {

    }

    @FXML
    void OnclickExcluir(ActionEvent event) {

    }

}
