package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Proprietario;  // Importar sua classe Proprietario

public class TelaUsuarioSistemaProprietarioController {

    @FXML
    private TableColumn<Proprietario, Integer> TableColumnID;

    @FXML
    private TableColumn<Proprietario, String> TableColumnNome;

    @FXML
    private TableColumn<Proprietario, String> TableColumnEmail;

    @FXML
    private TableColumn<Proprietario, String> TableColumnTelefone;

    @FXML
    private Button btnAlterarDados;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSair;

    @FXML
    private TableView<Proprietario> tabelaUsuarios;

    @FXML
    public void initialize() {
        // Configura quais campos da classe Proprietario cada coluna exibirá
        TableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        // Aqui você pode adicionar os dados na tabela, por exemplo:
        // tabelaUsuarios.setItems(seuObservableListDeProprietarios);
    }

    @FXML
    void OnClickSair(ActionEvent event) {
        // Código para fechar a tela, por exemplo
        btnSair.getScene().getWindow().hide();
    }

    @FXML
    void OnclickAlterarDados(ActionEvent event) {
        // Código para alterar dados do proprietário selecionado
        Proprietario selecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            // implementar lógica para editar dados
            System.out.println("Alterar dados do: " + selecionado.getNome());
        } else {
            System.out.println("Nenhum proprietário selecionado.");
        }
    }

    @FXML
    void OnclickExcluir(ActionEvent event) {
        // Código para excluir proprietário selecionado
        Proprietario selecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            // implementar lógica para excluir proprietário
            System.out.println("Excluir proprietário: " + selecionado.getNome());
        } else {
            System.out.println("Nenhum proprietário selecionado.");
        }
    }

    void setStage(Stage telaPRE) {
    }
}
