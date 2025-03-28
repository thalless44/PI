package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TelaInicialController {
    private Stage stageInicial;
    
    
    @FXML
    private MenuItem menuAcessarPerfil;
    
    
    @FXML
    private MenuItem btnUsuarioSistema;
    
    @FXML
    private Menu btnSair;

    @FXML
    private MenuItem menuCadastroCliente;

    @FXML
    private MenuItem menuCadastroFuncionario;

    @FXML
    private MenuItem menuCadastroImovol;
    
    @FXML
    private MenuItem btnFecharApp;
    
    @FXML
    private MenuItem btnRelatorioVendaDosImoveis;

    
    
    @FXML
    void OnActionFecharAplicacao(ActionEvent event) {
        
        if(SairTelaInicio()){        
            System.exit(0);       
        }else{       
            event.consume();        
        }
    }
 
    @FXML
    void onClickCliente(ActionEvent event) {
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaCadastroCliente.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene (root));
            stage.setTitle("Personalização de perfil");
            stage.setMaximized(true);

            stage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    
     @FXML
    void OnClickAbrirUsuarioSistema(ActionEvent event) {
        
         try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaUsuarioSistema.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene (root));
            stage.setTitle("UsuariosSistema");
            stage.setMaximized(true);

            stage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }

        
        

    }

    @FXML
    void onClickFuncionario(ActionEvent event) {
        
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaCadastroFuncionario.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene (root));
            stage.setTitle("Cadastro de funcionário");
            stage.setMaximized(true);

            stage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void onClickImovel(ActionEvent event) {

    }

    @FXML
    void onClickPerfil(ActionEvent event) {
        
        try{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TelaInformacoesPerfil.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            
            stage.setScene(new Scene (root));
            stage.setTitle("Personalização de perfil");
            stage.setMaximized(true);

            stage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    
     @FXML
    void OnActionBtnRelatorioVendaDosImoveis(ActionEvent event) {
        
        //Tela de relatório de vendas

    }


    private boolean SairTelaInicio() {
        
        Alert FecharTelaInicio = new Alert(Alert.AlertType.WARNING);
        FecharTelaInicio.setTitle("Aviso");
        FecharTelaInicio.setHeaderText("Tem certeza que deseja fechar a aplicação?");
        FecharTelaInicio.setContentText("Todas as alterações não salvas serão perdidas");
        return FecharTelaInicio.showAndWait().filter(response
                -> response == ButtonType.OK).isPresent();
    }
    
    }
