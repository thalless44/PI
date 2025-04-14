package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import util.AlertaUtil;

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
        // Lógica para alterar o perfil (aqui você pode colocar a implementação)
        
        // Usando AlertaUtil para mostrar um alerta de sucesso
        AlertaUtil.mostrarInformacao(
            "Alterações feitas com sucesso!", 
            "As alterações no seu perfil foram salvas com sucesso."
        );
    }

    // Método para inicializar o ComboBox com as opções
    @FXML
    public void initialize() {
        // Adicionando as opções "Gerente" e "Corretor" no ComboBox
        Cargocb.setItems(FXCollections.observableArrayList("Gerente", "Corretor"));
    }

    @FXML
    void OnClickSair(ActionEvent event) {
        if (FecharTelaPersonalizacaoPerfil()) {
            Stage stage = (Stage) BtnPP1.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaPersonalizacaoPerfil() {
        // Usando AlertaUtil para confirmar se o usuário realmente quer fechar a tela
        return AlertaUtil.mostrarConfirmacao(
            "Aviso", 
            "Tem certeza que deseja fechar a tela de personalização de perfil?\nTodas as alterações não salvas serão perdidas e a tela atual será fechada!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}



   
