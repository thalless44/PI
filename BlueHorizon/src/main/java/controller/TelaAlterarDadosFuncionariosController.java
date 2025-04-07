package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaAlterarDadosFuncionariosController {

    @FXML
    private Button btnAlterarDadosFuncionario;

    @FXML
    private Button btnCancelarFuncionario;

    @FXML
    private ComboBox<?> cmbxAlterarCargoFuncionario;

    @FXML
    private Label lblAlterarDadosClientes;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private TextField txtfdAlterarDataFuncionario;

    @FXML
    private TextField txtfdAlterarEmailFuncionario;

    @FXML
    private TextField txtfdAlterarEnderecoFuncionario;

    @FXML
    private TextField txtfdAlterarSalarioFuncionario;

    @FXML
    private TextField txtfdAlterarTelefoneFuncionario;

    @FXML
    private TextField txtfdNomeFuncionarioAlterar;

    @FXML
    void ActionAlterarDadosFuncionario(ActionEvent event) {
        if (!txtfdAlterarDataFuncionario.getText().isEmpty()||!txtfdAlterarEnderecoFuncionario.getText().isEmpty()||!txtfdAlterarSalarioFuncionario.getText().isEmpty()||!txtfdAlterarTelefoneFuncionario.getText().isEmpty()
                ||!txtfdNomeFuncionarioAlterar.getText().isEmpty()){
            Alert alteracao = new Alert(Alert.AlertType.WARNING);
            alteracao.setTitle("Preencha um dos campos");
            alteracao.setHeaderText("Campos obrigatórios");
            alteracao.setContentText("pelo menos um dos campos deve ser preenchido!");
            alteracao.showAndWait();
        }
        

    }

    @FXML
    void ActionCancelar(ActionEvent event) {
        
         if(FecharTelaAlterarDadosFuncionario()){    
            
           
            Stage stage = (Stage) btnCancelarFuncionario.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }

    }

    private boolean FecharTelaAlterarDadosFuncionario() {
        
        return false;
        
    }

}
