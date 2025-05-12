package controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import static model.ClienteDAO.deletarCliente;
import model.Funcionario;
import util.AlertaUtil;

public class TelaClientesSistemaController {

    private Stage stage;
    private Funcionario funcionario;

    @FXML private Button btnAlterarDados;
    @FXML private Button btnExcluir;
    @FXML private Button btnSair;

    @FXML private TableView<Cliente> tabelaUsuarios;

    @FXML private TableColumn<Cliente, Number> TableColumnID;
    @FXML private TableColumn<Cliente, String> TableColumnNome;
    @FXML private TableColumn<Cliente, String> TableColumnTelefone;
    @FXML private TableColumn<Cliente, String> TableColumnEmail;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        configurarColunasTabela();
        carregarClientesTabela();
    }

    private void configurarColunasTabela() {
        TableColumnID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        TableColumnTelefone.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
        TableColumnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }

    private void carregarClientesTabela() {
        List<Cliente> lista = ClienteDAO.listarTodos();
        ObservableList<Cliente> observableList = FXCollections.observableArrayList(lista);
        tabelaUsuarios.setItems(observableList);
    }

    @FXML
    void OnClickSair(ActionEvent event) {
        if (FecharTelaClientesSistema()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    @FXML
    void OnclickAlterarDados(ActionEvent event) {
        Cliente clienteSelecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();

        if (clienteSelecionado == null) {
            System.out.println("Nenhum cliente selecionado.");
            return;
        }

        try {
            URL url = new File("src/main/java/view/TelaAlterarDadosClientes.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            TelaAlterarDadosClientesController tadcc = loader.getController();
            Stage telaADC = new Stage();

            tadcc.setStage(telaADC);
            tadcc.setCliente(clienteSelecionado);

            Scene scene = new Scene(root);

            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaADC.getIcons().add(icone);

            telaADC.setScene(scene);
            telaADC.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Alterar dados do cliente");
            telaADC.setMaximized(true);
            telaADC.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnclickExcluir(ActionEvent event) {
        boolean confirmado = AlertaUtil.mostrarConfirmacao(
            "Confirmação de exclusão",
            "Tem certeza que deseja excluir este cliente?",
            "Esta ação não poderá ser desfeita."
        ).filter(response -> response == ButtonType.OK).isPresent();

        if (confirmado) {
            Cliente clienteSelecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
            if (clienteSelecionado != null) {
                deletarCliente(clienteSelecionado.getId());
                tabelaUsuarios.getItems().remove(clienteSelecionado);
                AlertaUtil.mostrarInformacao("Aviso", "Cliente excluído",
                    "O cliente foi removido com sucesso.");
            }
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaClientesSistema() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação de saída",
            "Tem certeza que deseja fechar a tela atual?",
            "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}