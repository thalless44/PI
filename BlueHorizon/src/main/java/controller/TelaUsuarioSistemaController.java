package controller;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Funcionario;
import model.FuncionarioDAO;
import util.AlertaUtil;

public class TelaUsuarioSistemaController {

    private Stage stage;

    @FXML private Button btnAlterarDados;
    @FXML private Button btnExcluir;
    @FXML private Button btnSair;

    @FXML private TableView<Funcionario> tabelaUsuarios;

    @FXML private TableColumn<Funcionario, Number> TableColumnID;
    @FXML private TableColumn<Funcionario, String> TableColumnNome;
    @FXML private TableColumn<Funcionario, String> TableColumnCPF;
    @FXML private TableColumn<Funcionario, String> TableColumnTelefone;
    @FXML private TableColumn<Funcionario, String> TableColumnEndereco;
    @FXML private TableColumn<Funcionario, String> TableColumnEmail;
    @FXML private TableColumn<Funcionario, String> TableColumnSenha;
    @FXML private TableColumn<Funcionario, String> TableColumnCargo;
    @FXML private TableColumn<Funcionario, String> TableColumnSalario;
    @FXML private TableColumn<Funcionario, java.time.LocalDate> TableColumnNascimento;
    @FXML private TableColumn<Funcionario, java.time.LocalDate> TableColumnContratacao;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        configurarColunasTabela();
        carregarUsuariosTabela();
    }

    private void configurarColunasTabela() {
        
        TableColumnID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        TableColumnCPF.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        TableColumnTelefone.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
        TableColumnEndereco.setCellValueFactory(cellData -> cellData.getValue().enderecoProperty());
        TableColumnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        TableColumnSenha.setCellValueFactory(cellData -> cellData.getValue().senhaProperty());
        TableColumnCargo.setCellValueFactory(cellData -> cellData.getValue().CargoProperty());
        TableColumnSalario.setCellValueFactory(cellData -> cellData.getValue().salarioProperty());
        TableColumnNascimento.setCellValueFactory(cellData -> cellData.getValue().dataNascimentoProperty());
        TableColumnContratacao.setCellValueFactory(cellData -> cellData.getValue().dataContratacaoProperty());
    }

    private void carregarUsuariosTabela() {
        List<Funcionario> lista = FuncionarioDAO.listarTodos();
        ObservableList<Funcionario> observableList = FXCollections.observableArrayList(lista);
        tabelaUsuarios.setItems(observableList);
    }

    @FXML
    void OnClickSair(ActionEvent event) {
        if (FecharTelaUsuariosSistema()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    @FXML
    void OnclickAlterarDados(ActionEvent event) {
        AlertaUtil.mostrarInformacao("Alterar Dados",
                "Funcionalidade de alteração de dados ainda não implementada.");
    }

    @FXML
    void OnclickExcluir(ActionEvent event) {
        boolean confirmado = AlertaUtil.mostrarConfirmacao(
                "Confirmação de exclusão",
                "Tem certeza que deseja excluir este usuário? Esta ação não poderá ser desfeita."
        ).filter(response -> response == ButtonType.OK).isPresent();

        if (confirmado) {
            // Lógica de exclusão futura
            AlertaUtil.mostrarInformacao("Usuário excluído",
                    "O usuário foi removido com sucesso.");
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaUsuariosSistema() {
        return AlertaUtil.mostrarConfirmacao(
                "Confirmação de saída",
                "Tem certeza que deseja fechar a tela atual?\nTodas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}
