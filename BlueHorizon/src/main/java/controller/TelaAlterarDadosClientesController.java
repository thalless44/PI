package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaAlterarDadosClientesController  {

    @FXML
    private Button btnAlterarDados;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblAlterarDadosClientes;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private Label txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private Label txtTelefone;

    @FXML
    void ActionAlterarDados(ActionEvent event) {
        if (!txtNome.getText().isEmpty()||!txtEmail.getText().isEmpty()||!txtTelefone.getText().isEmpty()){
            Alert alterarDados = new Alert(Alert.AlertType.WARNING);
            alterarDados.setTitle("Erro");
            alterarDados.setHeaderText("Campos obrigatórios");
            alterarDados.setContentText("Todos os campos devem ser preenchidos!");
            alterarDados.showAndWait();
        }

    }

    @FXML
    void ActionCancelar(ActionEvent event) {
        
        if(FecharTelaAlterarDadosCliente()){              
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }


    }

    private boolean FecharTelaAlterarDadosCliente() {
        
        Alert sair = new Alert(Alert.AlertType.WARNING);
        sair.setTitle("Aviso");
        sair.setHeaderText("Tem certeza que deseja fechar a tela de alterar dados de clientes?");
        sair.setContentText("Todas as alterações não salvas serão perdidas e a tela atual será fechada!");
        return sair.showAndWait().filter(response -> response == ButtonType.OK).isPresent();

    }

}