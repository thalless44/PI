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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static model.FuncionarioDAO.deletarFuncionario;
import model.Proprietario;  // Importar sua classe Proprietario
import model.ProprietarioDAO;
import util.AlertaUtil;

public class TelaUsuarioSistemaProprietarioController {
    
    private Stage stageUsuarios;

    @FXML
    private TableColumn<Proprietario, Number> TableColumnID;

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
    private TableView<Proprietario> tabelaUsuariosProprietarios;

    @FXML
    public void initialize() {
       configurarColunasTabela();
       carregarUsuariosTabela();
    }
    
    private void configurarColunasTabela() {
        
         // Configura quais campos da classe Proprietario cada coluna exibirá      
        TableColumnID.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TableColumnTelefone.setCellValueFactory(cellData -> cellData.getValue().telefoneProperty());
        TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        TableColumnEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
      
        
         // Aqui você pode adicionar os dados na tabela, por exemplo:
        // tabelaUsuarios.setItems(seuObservableListDeProprietarios);
       
    }
    
     private void carregarUsuariosTabela() {
        List<Proprietario> lista = ProprietarioDAO.listarTodosProprietarios();
        ObservableList<Proprietario> observableList = FXCollections.observableArrayList(lista);
        tabelaUsuariosProprietarios.setItems(observableList);
    }

    @FXML
    void OnClickSair(ActionEvent event) {
        // Código para fechar a tela, por exemplo
        btnSair.getScene().getWindow().hide();
    }

    @FXML
void OnclickAlterarDados(ActionEvent event) {
    // Código para alterar dados do proprietário selecionado
    Proprietario selecionado = tabelaUsuariosProprietarios.getSelectionModel().getSelectedItem();
    if (selecionado != null) {
        try {
            URL url = new File("src/main/java/view/TelaAlterarDadosProprietarios.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            TelaAlterarDadosProprietariosController tadpr = loader.getController();
            Stage telaPRO = new Stage();

            tadpr.setStage(telaPRO);
            tadpr.setProprietario(selecionado);  // Passa o objeto selecionado da tabela

            Scene scene = new Scene(root);
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaPRO.getIcons().add(icone);

            telaPRO.setScene(scene);
            telaPRO.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Alterar dados do proprietário");
            telaPRO.setMaximized(true);
            telaPRO.show();

        } catch (Exception e) {
            e.printStackTrace();
            // Aqui você pode também mostrar um alerta visual, se quiser
        }
    } else {
        System.out.println("Nenhum proprietário selecionado.");
    }
}

    @FXML
    void OnclickExcluir(ActionEvent event) {
        // Código para excluir proprietário selecionado
        Proprietario selecionado = tabelaUsuariosProprietarios.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
       boolean confirmado = AlertaUtil.mostrarConfirmacao(
                "Confirmação de exclusão",
                "Tem certeza que deseja excluir este usuário?",
                "Esta ação não poderá ser desfeita."
        ).filter( response -> response == ButtonType.OK).isPresent();
        

        if (confirmado) {
            Proprietario proprietarioSelecionado = tabelaUsuariosProprietarios.getSelectionModel().getSelectedItem();
            if (proprietarioSelecionado != null ){
                deletarFuncionario(proprietarioSelecionado.getId()); 
                tabelaUsuariosProprietarios.getItems().remove(proprietarioSelecionado);
                AlertaUtil.mostrarInformacao("Aviso", "Proprietario excluído",
                    "O proprietário foi removido com sucesso.");
            }
            
        } else {
            event.consume();
        }
        } 
    }

    void setStage(Stage telaPRE) {
        this.stageUsuarios = telaPRE;
        
    }
    }
