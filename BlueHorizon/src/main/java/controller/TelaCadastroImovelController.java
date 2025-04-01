package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaCadastroImovelController {

    private Stage stage;
    
    @FXML
    private Button btnAdicionarImagem;

    @FXML
    private Button btnCadastro;

    @FXML
    private Button btnCancelarCadastro;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtBanheiros;

    @FXML
    private TextField txtCidades;

    @FXML
    private TextField txtDatacadastro;

    @FXML
    private TextField txtDisponibilidade;

    @FXML
    private TextField txtEmailProprietario;

    @FXML
    private TextField txtIDimovel;

    @FXML
    private TextField txtJardim;

    @FXML
    private TextField txtMobiliada;

    @FXML
    private TextField txtNumeracaoImovel;

    @FXML
    private TextField txtPiscina;

    @FXML
    private TextField txtProprietario;

    @FXML
    private TextField txtQuartos;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtSistemadeSeguranca;

    @FXML
    private TextField txtTelefoneProprietario;

    @FXML
    private TextField txtVagaGaragem;

    @FXML
    private TextField txtValor;

    @FXML
    void OnClickAdicionarImagem(ActionEvent event) {
        
        //Método para adicionar as imagens dos imóveis
        
        //Código aqui...

    }

    @FXML
    void onClickCadastro(ActionEvent event) {
        
        //Método para efetuar o cadastro do imóvel
        
        //Código aqui...

    }

    @FXML
    void onClickCancelarCadastro(ActionEvent event) {
        
        if(CancelarCadastroImovel()){
            
            Stage stage = (Stage) btnCancelarCadastro.getScene().getWindow();
            stage.close(); 
            
        }else{        
            event.consume();
        }

    }

   
    
    public void setStage(Stage stage){
        this.stage = stage;
    }

    private boolean CancelarCadastroImovel() {
        
        Alert confirmar = new Alert(Alert.AlertType.WARNING);
        confirmar.setTitle("Aviso");
        confirmar.setHeaderText("Tem certeza que deseja fechar a tela de cadastro de imóvel?");
        confirmar.setContentText("Todas as alterações não salvas serão perdidas e a tela atual será fechada!");
        return confirmar.showAndWait().filter(response -> response == ButtonType.OK).isPresent();
        
    }

}

