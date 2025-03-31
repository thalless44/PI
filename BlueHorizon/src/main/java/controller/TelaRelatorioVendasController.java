package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

public class TelaRelatorioVendasController {
    
     private Stage stage;

    @FXML
    private Button btnSair;

    @FXML
    private Label lblRelatorioVendas;

    @FXML
    private TableColumn<?, ?> tblCidade;

    @FXML
    private TableColumn<?, ?> tblDataCadastro;

    @FXML
    private TableColumn<?, ?> tblImovel;

    @FXML
    private TableColumn<?, ?> tblValor;

    @FXML
    void OnClickbntSair(ActionEvent event) {
       
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Tem certeza que deseja fechar a tela de relatório de vendas?");
        alert.setContentText("Todas as alterações não salvas serão perdidas.");
        alert.showAndWait();
        
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    void setStage(Stage stage) {
        
         this.stage = stage;
    }
}