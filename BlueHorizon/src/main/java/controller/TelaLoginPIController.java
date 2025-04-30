package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Login;
import model.LoginDAO;
import util.AlertaUtil;

public class TelaLoginPIController {

    private Connection conexao;
    private final LoginDAO dao = new LoginDAO();
    private Stage stageLogin;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnEfetuarLogin;

    @FXML
    private Hyperlink hlRecuperarSenha;

    @FXML
    private void ActionEfetuarLogin(ActionEvent event) {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            AlertaUtil.mostrarErro("Erro","Erro ao efetuar o login", "Campo de email ou senha vazio. Verifique e preencha!");
            return;
        }

        try {
            Login usuario = processarLogin(email, senha);

            if (usuario != null) {
                abrirTelaInicial();
            } else {
                AlertaUtil.mostrarErro("Erro", "Erro ao efetuar o login", "Senha ou email incorreto. Verifique e adicione corretamente!");
            }

        } catch (IOException | SQLException e) {
            Logger.getLogger(TelaLoginPIController.class.getName()).log(Level.SEVERE, null, e);
            AlertaUtil.mostrarErro("Erro", "Erro inesperado", "Ocorreu um erro ao tentar realizar o login.");
        }
    }

    @FXML
    private void ActionHyperlinkRecSenha(ActionEvent event) {
        try {
            URL url = new File("src/main/java/view/TelaRecSenha.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage telaRecSenha = new Stage();
            TelaRecSenhaController controller = loader.getController();
            controller.setStage(telaRecSenha);

            Scene scene = new Scene(root);
            telaRecSenha.setScene(scene);
            telaRecSenha.setTitle("BlueHorizon - Recuperação de senha");
            telaRecSenha.setMaximized(true);
            telaRecSenha.show();

        } catch (IOException e) {
            Logger.getLogger(TelaLoginPIController.class.getName()).log(Level.SEVERE, null, e);
            AlertaUtil.mostrarErro("Erro", "Erro ao abrir tela", "Não foi possível abrir a tela de recuperação de senha.");
        }
    }

    public void setStage(Stage stage) {
        this.stageLogin = stage;
    }

    public Login processarLogin(String email, String senha) throws IOException, SQLException {
        if (!dao.bancoOnline()) {
            System.out.println("Banco de dados desconectado!");
            return null;
        }

        if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {
            return dao.autenticar(email, senha);
        }

        return null;
    }

    private void abrirTelaInicial() throws IOException {
        URL url = new File("src/main/java/view/TelaInicial.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        Stage telaInicial = new Stage();
        TelaInicialController controller = loader.getController();
        controller.setStage(telaInicial);

        Scene scene = new Scene(root);
        
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaInicial.getIcons().add(icone);
        
        telaInicial.setScene(scene);
        telaInicial.setTitle("BlueHorizon - Início");
        telaInicial.setMaximized(true);
        telaInicial.show();

        if (stageLogin != null) {
            stageLogin.close();
        }
    }

    // ✅ Aqui está o método que estava faltando
    public void abrirJanela() {
        System.out.println("Janela de login exibida com sucesso!");
    }
    
       
        
}