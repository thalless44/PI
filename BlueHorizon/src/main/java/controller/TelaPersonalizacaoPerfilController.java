package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class TelaPersonalizacaoPerfilController {
    private Stage stage; 

    @FXML
    private Button BtnPP; 
    
    @FXML
    private Button BtnPP1;

    @FXML
    private ComboBox<String> Cargocb; // Definido como ComboBox<String>

    @FXML
    private TextField Cpftxt;

    @FXML
    private TextField DatadeContratacaotxt;

    @FXML
    private TextField DatadeNascimentotxt;

    @FXML
    private TextField Emailtxt;

    @FXML
    private TextField Enderecotxt;

    @FXML
    private TextField Nometxt;

    @FXML
    private TextField TelefoneTxt;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void OnClickAlterarPerfil(ActionEvent event) {
        // Lógica para alterar o perfil
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Alterações fei com sucesso");
        
    }

   
    // Método para inicializar o ComboBox com as opções
    @FXML
    public void initialize() {
        // Adicionando as opções "Gerente" e "Corretor" no ComboBox
        Cargocb.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
    }

    private boolean FecharTelaPersonalizacaoPerfil() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Confirmação");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela atual?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
        
    }
    
    @FXML
    void OnClickSair(ActionEvent event) {
        try {
            URL url = new File("src/main/java/view/TelaTelaInicial.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaInicial = new Stage();
            TelaInicialController ti = loader.getController(); 
            ti.setStage(telaInicial);
            Scene scene = new Scene(root);
            telaInicial.setScene(scene);
            telaInicial.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar");

            telaInicial.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
        
        
    
    }}
   
