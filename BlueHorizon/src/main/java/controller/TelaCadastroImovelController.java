package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    }

    @FXML
    void onClickCadastro(ActionEvent event) {

    }

    @FXML
    void onClickCancelarCadastro(ActionEvent event) {

    }

   
    
    public void setStage(Stage stage){
        this.stage = stage;
    }

}

