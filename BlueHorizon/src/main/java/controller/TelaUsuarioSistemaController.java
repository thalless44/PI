package controller;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Funcionario;
import model.FuncionarioDAO;
import static model.FuncionarioDAO.deletarFuncionario;
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
        
        Funcionario funcionarioSelecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
    
    if (funcionarioSelecionado != null) {
        // Passar o funcionário selecionado para a tela de alteração
        TelaAlterarDadosFuncionariosController controller = new TelaAlterarDadosFuncionariosController();
        controller.setFuncionario(funcionarioSelecionado);
        
        // Abrir a tela de alteração
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/main/java/view/TelaAlterarDadosFuncionarios.fxml"));
            loader.setController(controller);
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            AlertaUtil.mostrarErro("Erro", "Não foi possível abrir a tela de alteração.");
        }
    } else {
        AlertaUtil.mostrarAviso("Nenhum funcionário selecionado", "Selecione um funcionário para alterar os dados.");
    }
    }

    @FXML
    void OnclickExcluir(ActionEvent event) {
        boolean confirmado = AlertaUtil.mostrarConfirmacao(
                "Confirmação de exclusão",
                "Tem certeza que deseja excluir este usuário? Esta ação não poderá ser desfeita."
        ).filter( response -> response == ButtonType.OK).isPresent();
        

        if (confirmado) {
            Funcionario funcionarioSelecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
            if (funcionarioSelecionado != null ){
                deletarFuncionario(funcionarioSelecionado.getId()); 
                tabelaUsuarios.getItems().remove(funcionarioSelecionado);
                AlertaUtil.mostrarInformacao("Usuário excluído",
                    "O usuário foi removido com sucesso.");
            }
            
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
