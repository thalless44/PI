package com.mycompany.telaspi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaInformacoesPerfilController {

    @FXML
    private Button BtnPP;

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

    @FXML
    void OnClickPersonalizarPerfil(ActionEvent event) {
        
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaPersonalizacaoPerfil.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene (root));
            stage.setTitle("Personalização de perfil");
            stage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
