package controller;

import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Proprietario;
import model.ProprietarioDAO;
import util.AlertaUtil;

public class TelaAlterarDadosProprietariosController {

    @FXML
    private Button btnAlterarDados;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblAlterarDadosClientes;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    private Proprietario proprietario;

    private Stage stage;

    @FXML
    void ActionAlterarDados(ActionEvent event) throws SQLException {
        if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtTelefone.getText().isEmpty()) {
            AlertaUtil.mostrarAviso("Aviso", "Campos obrigatórios", 
                "Todos os campos devem ser preenchidos!");
        } else {
            proprietario.setNome(txtNome.getText());
            proprietario.setTelefone(txtTelefone.getText());
            proprietario.setEmail(txtEmail.getText());

            Optional<ButtonType> resultado = AlertaUtil.mostrarConfirmacao(
                "Confirmação", "Alterar Dados", "Deseja realmente alterar os dados do proprietário?"
            );

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                boolean atualizado = ProprietarioDAO.atualizarProprietario(proprietario);
                if (atualizado) {
                    AlertaUtil.mostrarInformacao("Sucesso", "Alteração realizada", "Os dados foram atualizados com sucesso.");
                    stage.close();
                } else {
                    AlertaUtil.mostrarErro("Erro", "Falha na alteração", "Não foi possível atualizar os dados.");
                }
            }
        }
    }

    @FXML
    void ActionCancelar(ActionEvent event) {
        if (FecharTelaAlterarDadosProprietario()) {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();          
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaAlterarDadosProprietario() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação", 
            "Tem certeza que deseja fechar a tela de alterar dados do proprietário?",
            "Todas as alterações não salvas serão perdidas e a tela atual será fechada!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

    public void setStage(Stage telaADP) {
        this.stage = telaADP;
    }

    public void setProprietario(Proprietario proprietarioSelecionado) {
        this.proprietario = proprietarioSelecionado;

        // Preencher os campos com os dados do proprietário
        txtNome.setText(proprietario.getNome());
        txtEmail.setText(proprietario.getEmail());
        txtTelefone.setText(proprietario.getTelefone());
    }
}
