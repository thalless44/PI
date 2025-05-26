package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertaUtil;
import model.ProprietarioDAO; // Supondo que você tenha um DAO específico para Proprietario.
import util.LimitarCaracter;

public class TelaCadastroProprietarioController {

    @FXML
    private Button btnCancelarcadastro;

    @FXML
    private Button btnEfetuarCadastro;

    @FXML
    private TextField txtEmailProprietario;

    @FXML
    private TextField txtNomeProprietario;

    @FXML
    private TextField txtTelefoneProprietario;

    private Stage stage;

    // Método para definir a Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Inicialização dos campos (se necessário, adicione alguma lógica aqui)
    @FXML
    public void initialize() {
        
        new LimitarCaracter(50, LimitarCaracter.TipoEntrada.NOME).applyToTextInputControl(txtNomeProprietario);
        new LimitarCaracter(100, LimitarCaracter.TipoEntrada.EMAIL).applyToTextInputControl(txtEmailProprietario);
        new LimitarCaracter(12, LimitarCaracter.TipoEntrada.FONE).applyToTextInputControl(txtTelefoneProprietario);
    }

    // Ação de Cancelar Cadastro
    @FXML
    void ActionCancelarCadastro(ActionEvent event) {
        if (CancelarCadastroProprietario()) {
            fecharTela();
        } else {
            event.consume();
        }
    }

    // Ação para efetuar o cadastro
    @FXML
    void ActionEfetuarCadastro(ActionEvent event) {
        cadastrarProprietario();
    }

    // Método para confirmar se o usuário deseja cancelar o cadastro
    private boolean CancelarCadastroProprietario() {
        return AlertaUtil.mostrarConfirmacao(
            "Aviso",
            "Tem certeza que deseja fechar a tela de cadastro de proprietário?",
            "Todas as alterações não salvas serão perdidas e a tela atual será fechada!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

    // Método para cadastrar o proprietário
    private void cadastrarProprietario() {
        String nome = txtNomeProprietario.getText().trim();
        String email = txtEmailProprietario.getText().trim();
        String telefone = txtTelefoneProprietario.getText().trim();

        // Validações dos campos
        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            AlertaUtil.mostrarErro("Erro", "Campos obrigatórios",
                    "Todos os campos devem ser preenchidos!");
        } else {
            // Chamando o DAO para cadastrar o proprietário
            boolean sucesso = ProprietarioDAO.cadastrarProprietario(nome, email, telefone);

            if (sucesso) {
                AlertaUtil.mostrarInformacao("Cadastro de Proprietário", "Cadastro realizado",
                        "O proprietário foi cadastrado com sucesso.");
                fecharTela();
            } else {
                AlertaUtil.mostrarErro("Erro", "Erro ao cadastrar",
                        "Ocorreu um erro ao tentar cadastrar o proprietário.");
            }
        }
    }

    // Método para fechar a tela
    private void fecharTela() {
        if (stage != null) {
            stage.close();
        }
    }
}
