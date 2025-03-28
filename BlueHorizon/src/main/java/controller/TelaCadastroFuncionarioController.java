package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        
        if(FecharTelaCadastroFuncionario()){              
            //faz com que feche apenas a tela de rec senha, ao inves da aplicação toda
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }
        
        

    }

    private boolean FecharTelaCadastroFuncionario() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela de cadastro de funcionarios?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

}
