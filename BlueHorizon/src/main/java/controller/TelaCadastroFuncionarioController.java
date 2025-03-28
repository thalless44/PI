package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroFuncionarioController {
    private Stage stage;

    @FXML
    private Button btnEfetuarCadastroFuncionario;

    @FXML
    private Button btnSair;

    @FXML
    private ComboBox<?> cmbxCargo;

    @FXML
    private TextField txtfdCPF;

    @FXML
    private TextField txtfdConfirmarSenha;

    @FXML
    private TextField txtfdDataContratacao;

    @FXML
    private TextField txtfdDataNascimento;

    @FXML
    private TextField txtfdEmail;

    @FXML
    private TextField txtfdEndereco;

    @FXML
    private TextField txtfdNome;

    @FXML
    private TextField txtfdSalario;

    @FXML
    private TextField txtfdSenha;

    @FXML
    private TextField txtfdTelefone;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void OnActionBtnEfetuarCadastroFuncionario(ActionEvent event) {

    }

    @FXML
    void OnActionBtnSair(ActionEvent event) {
        
        

    }

}
