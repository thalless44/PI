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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Funcionario;
import model.FuncionarioDAO;

public class TelaInicialController {
    private Stage stage;
    private Funcionario funcionario;

    
    @FXML
    private MenuItem menuAcessarPerfil;
    
    
    @FXML
    private MenuItem btnUsuarioSistema;
    
    @FXML
    private MenuItem btnUsuarioSistema1;
    
    @FXML
    private Menu btnSair;

    @FXML
    private MenuItem menuCadastroCliente;

    @FXML
    private MenuItem menuCadastroFuncionario;

    @FXML
    private MenuItem menuCadastroImovol;
    
    @FXML
    private MenuItem menuCadastroProprietario;
    
    @FXML
    private MenuItem btnFecharApp;
    
    @FXML
    private MenuItem btnRelatorioVendaDosImoveis;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private MenuItem btnUsuarioSistemaProprietario;

  
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
        try {
            URL url = new File("src/main/java/view/TelaCadastroCliente.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaCC = new Stage();
            TelaCadastroClienteController ti = loader.getController(); 
            ti.setStage(telaCC);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaCC.getIcons().add(icone);
            telaCC.setScene(scene);
            telaCC.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Cadastro de cliente");

            telaCC.setMaximized(true);
            telaCC.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
        
    }
    
      @FXML
    void OnClickAbrirUsuarioSistemaFuncionario(ActionEvent event) {

         try {
            
            URL url = new File("src/main/java/view/TelaUsuarioSistema.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaUS = new Stage();
            TelaUsuarioSistemaController ti = loader.getController(); 
            ti.setStage(telaUS);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaUS.getIcons().add(icone);
        
            telaUS.setScene(scene);
            telaUS.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - funcionários");

            telaUS.setMaximized(true);
            telaUS.show();
            } catch (IOException e) {
            e.printStackTrace();
            }        
    }
    
    @FXML
    void OnClickAbrirUsuarioSistemaCliente(ActionEvent event) {
        
        try {
            URL url = new File("src/main/java/view/TelaClientesSistema.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaCC = new Stage();
            TelaClientesSistemaController tcs = loader.getController(); 
            tcs.setStage(telaCC);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaCC.getIcons().add(icone);
        
            telaCC.setScene(scene);
            telaCC.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - Clientes");
            
            telaCC.setMaximized(true);
            telaCC.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
        
        

    }


    @FXML
    void onClickFuncionario(ActionEvent event) {
        
        try {
            URL url = new File("src/main/java/view/TelaCadastroFuncionario.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaCF = new Stage();
            TelaCadastroFuncionarioController ti = loader.getController(); 
            ti.setStage(telaCF);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaCF.getIcons().add(icone);
        
            telaCF.setScene(scene);
            telaCF.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Cadastro de funcionário");
            
            telaCF.setMaximized(true);
            telaCF.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
    }

    @FXML
    void onClickImovel(ActionEvent event) {
          try {
            URL url = new File("src/main/java/view/TelaCadastroImovel.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaIP = new Stage();
            TelaCadastroImovelController imovelpi = loader.getController(); 
            imovelpi.setStage(telaIP);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaIP.getIcons().add(icone);
        
            telaIP.setScene(scene);
            telaIP.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar");

            telaIP.setMaximized(true);
            telaIP.show();
            } catch (IOException e) {
            e.printStackTrace();
            }

        

    }

    @FXML
    void onClickPerfil(ActionEvent event) {   
 
        try {
            
            FuncionarioDAO dao = new FuncionarioDAO();
            Funcionario funcionarioAtualizado = dao.autenticar(funcionario.getEmail(), funcionario.getSenha());
            
            URL url = new File("src/main/java/view/TelaInformacoesPerfil.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            TelaInformacoesPerfilController ti = loader.getController(); 
            
            ti.preencherInformacoesPerfil(funcionarioAtualizado);
            
            Stage telaIP = new Stage();
            ti.setStage(telaIP);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaIP.getIcons().add(icone);
        
            telaIP.setScene(scene);
            telaIP.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Informações do perfil");

            telaIP.setMaximized(true);
            telaIP.show();
            } catch (IOException e) {
            e.printStackTrace();
            }

    }
    
     @FXML
    void OnActionBtnRelatorioVendaDosImoveis(ActionEvent event) {
        
        try {
       
        URL url = new File("src/main/java/View/TelaRelatorioVendas.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaRV = new Stage();
        TelaRelatorioVendasController tR = loader.getController(); 
        tR.setStage(telaRV);
        Scene scene = new Scene(root);
        
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaRV.getIcons().add(icone);
        
        telaRV.setScene(scene);
        telaRV.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Relatório de venda dos imóveis");
        telaRV.setMaximized(true);
        telaRV.show();
       
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
    @FXML
    void OnClickAbrirUsuarioSistemaProprietario(ActionEvent event) {
        try {
    // Carregar o FXML da tela de Proprietário
    URL url = new File("src/main/java/view/TelaUsuarioSistemaProprietario.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();

    // Criar a nova tela
    Stage telaPRE = new Stage();

    // Obter o controlador correto (TelaUsuarioSistemaProprietarioController)
    TelaUsuarioSistemaProprietarioController controller = loader.getController();

    // Passar a Stage para o controlador, se necessário
    controller.setStage(telaPRE);

    // Criar a cena e configurar o ícone
    Scene scene = new Scene(root);
    Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
    telaPRE.getIcons().add(icone);

    // Configurar e exibir a tela
    telaPRE.setScene(scene);
    telaPRE.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - Proprietarios");
    telaPRE.setMaximized(true);
    telaPRE.show();

} catch (IOException e) {
    e.printStackTrace();
}

        

    }
    //cadastrar Proprietario
 @FXML
void onClickProprietario(ActionEvent event) {
    try {
        // Carregar o FXML da tela de Proprietário
        URL url = new File("src/main/java/view/TelaCadastroProprietario.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        
        // Criar a nova tela
        Stage telaPRO = new Stage();

        // Obter o controlador correto (TelaProprietarioController)
        TelaCadastroProprietarioController controller = loader.getController();
        
        // Passar a Stage para o controlador, se necessário
        controller.setStage(telaPRO);

        // Criar a cena e configurar o ícone
        Scene scene = new Scene(root);
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaPRO.getIcons().add(icone);

        // Configurar e exibir a tela
        telaPRO.setScene(scene);
        telaPRO.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - Proprietarios");
        telaPRO.setMaximized(true);
        telaPRO.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private boolean SairTelaInicio() {
        
        Alert FecharTelaInicio = new Alert(Alert.AlertType.WARNING);
        FecharTelaInicio.setTitle("Aviso");
        FecharTelaInicio.setHeaderText("Tem certeza que deseja fechar a aplicação?");
        FecharTelaInicio.setContentText("Todas as alterações não salvas serão perdidas");
        return FecharTelaInicio.showAndWait().filter(response
                -> response == ButtonType.OK).isPresent();
    }

    public void setFuncionario(Funcionario f) {
        this.funcionario = f;
    }
    
    }