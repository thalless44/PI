package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

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
import util.LimitarCaracter;

public class TelaRecSenhaController {
    private Stage stage;

    @FXML
    private Button btnRecuperarSenha;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtfdCPF;

    @FXML
    private TextField txtfdDataNascimento;

    @FXML
    private TextField txtfdEmailRecSenha;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    void initialize(){
       new LimitarCaracter(14, LimitarCaracter.TipoEntrada.CPF).applyToTextInputControl(txtfdCPF);
       new LimitarCaracter(10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtfdDataNascimento);
    }

       @FXML
    void ActionRecuperarSenha(ActionEvent event) throws IOException {
        String email = txtfdEmailRecSenha.getText().trim();
        String cpf = txtfdCPF.getText().trim();
        String dataStr = txtfdDataNascimento.getText().trim();

        if (email.isEmpty() || cpf.isEmpty() || dataStr.isEmpty()) {
            AlertaUtil.mostrarErro("Erro", "Recuperação de senha",
                                  "Todos os campos são obrigatórios.");
            return;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                                  .withResolverStyle(ResolverStyle.STRICT);
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dataStr, fmt);
        } catch (DateTimeParseException e) {
            AlertaUtil.mostrarErro("Erro", "Data inválida",
                "Formato deve ser dd/MM/yyyy e data válida.");
            return;
        }

        // converte para java.sql.Date
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        // valida no banco
        if (FuncionarioDAO.verificarDadosRecSenha(email, cpf, sqlDate)) {
            abrirTelaNovaRecSenha(email);
        } else {
            AlertaUtil.mostrarErro("Erro", "Recuperação de senha",
                "Os dados informados não conferem com nenhum cadastro.");
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
        telaRS2.setTitle("BlueHorizon - Recuperação de senha");
        telaRS2.setResizable(false);
        telaRS2.setWidth(700);
        telaRS2.setHeight(500);
        telaRS2.setMaximized(false);
        telaRS2.show();
    }
}
