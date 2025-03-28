package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaInformacoesPerfilController {
    private Stage stage;

    @FXML
    private Button BtnPP;
    
    @FXML
    private Button btnSair;

    @FXML
    private Label CargoID;

    @FXML
    private Label DcID;

    @FXML
    private Label DnID;

    @FXML
    private Label EmailID;

    @FXML
    private Label EnderecoID;

    @FXML
    private Label TelefoneID;

    @FXML
    private Label cpfID;

    @FXML
    private Label nomeID;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void OnClickPersonalizarPerfil(ActionEvent event) {
        
        try {
            URL url = new File("src/main/java/view/TelaPersonalizacaoPerfil.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaPerfil = new Stage();
            TelaPersonalizacaoPerfilController ti = loader.getController(); 
            ti.setStage(telaPerfil);
            Scene scene = new Scene(root);
            telaPerfil.setScene(scene);
            telaPerfil.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar");

            telaPerfil.setMaximized(true);
            telaPerfil.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
    }
    
    @FXML
    void OnClickSairInformacoesPerfil(ActionEvent event) {
        
        if(FecharTelaInformacoesPerfil()){              
            //faz com que feche apenas a tela de rec senha, ao inves da aplicação toda
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();          
        }else{
            event.consume();
        }

    }

    private boolean FecharTelaInformacoesPerfil() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela de informações do perfil?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
    }

}
