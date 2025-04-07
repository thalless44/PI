package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaContratoController {

    @FXML
    private Button btnSair;

    @FXML
    private Label lblContrato;

    @FXML
    private Label lblDataCadastro;

    @FXML
    private Label lblDataContrato;

    @FXML
    private Label lblEmailCliente;

    @FXML
    private Label lblIdContrato;

    @FXML
    private Label lblIdImovel;

    @FXML
    private Label lblNomeCliente;

    @FXML
    private Label lblTelefoneCliente;

    @FXML
    private Label lblValorImovel;

    @FXML
    private Label lblValorTotal;

    @FXML
    void ActionSair(ActionEvent event) {
        if(FecharTelaContrato()){              
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }
    }

    private boolean FecharTelaContrato() {
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Aviso");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela de contrato?");
      
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }
}