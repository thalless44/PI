package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.FuncionarioDAO;
import util.AlertaUtil;

public class TelaRecSenhaController {
    private Stage stage;

    @FXML
    private Button btnRecuperarSenha;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtfdEmailRecSenha;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void ActionRecuperarSenha(ActionEvent event) throws IOException {
        
        String email = txtfdEmailRecSenha.getText().trim();
        
            if(email.isEmpty()){
                AlertaUtil.mostrarErro("Erro", "Recuperação de senha", "Por favor, informe um e-mail para recuperação.");
            return;
            }
            
            if(FuncionarioDAO.verificarEmailCadastrado(email)){
                abrirTelaNovaRecSenha(email);
            }else{
                AlertaUtil.mostrarErro("Erro", "Recuperação de senha", "O email informado não está cadastrado no sistema!");
            }
    }

    @FXML
    void ActionSair(ActionEvent event) {
        if (FecharTelaRecSenha()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaRecSenha() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação",
            "Tem certeza que deseja fechar a tela atual?",
            "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

    private void abrirTelaNovaRecSenha(String email) throws IOException {
        try {
            URL url = new File("src/main/java/view/TelaRecSenha2.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaRS2 = new Stage();
            TelaRecSenha2Controller trs2c = loader.getController(); 
            trs2c.setEmail(email);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaRS2.getIcons().add(icone);
            telaRS2.setScene(scene);
            telaRS2.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Recuperação de senha");

            telaRS2.setResizable(false); // Impede redimensionamento
            telaRS2.setWidth(700);       // Largura fixa
            telaRS2.setHeight(500);      // Altura fixa
            telaRS2.setMaximized(false);
            telaRS2.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    }
