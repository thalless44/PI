package controller;

import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
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
    
    private final NumberFormat moedaBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
    TableColumnCargo.setCellValueFactory(cellData -> cellData.getValue().CargoProperty());
    TableColumnSalario.setCellValueFactory(cellData -> cellData.getValue().salarioProperty());
    TableColumnSalario.setCellFactory(column -> new TableCell<Funcionario, String>() {
        @Override
        protected void updateItem(String salario, boolean empty) {
            super.updateItem(salario, empty);
            if (empty || salario == null) {
                setText(null);
            } else {
                try {
                    double valor = Double.parseDouble(salario);
                    setText(moedaBR.format(valor));
                } catch (NumberFormatException e) {
                    setText(salario); 
                }
            }
        }
    });

    // Datas: mostra no padrão dd/MM/yyyy
    TableColumnNascimento.setCellValueFactory(cellData -> cellData.getValue().dataNascimentoProperty());
    TableColumnNascimento.setCellFactory(column -> new TableCell<Funcionario, java.time.LocalDate>() {
        @Override
        protected void updateItem(java.time.LocalDate data, boolean empty) {
            super.updateItem(data, empty);
            setText(empty || data == null ? "" : data.format(formatoData));
        }
    });

    TableColumnContratacao.setCellValueFactory(cellData -> cellData.getValue().dataContratacaoProperty());
    TableColumnContratacao.setCellFactory(column -> new TableCell<Funcionario, java.time.LocalDate>() {
        @Override
        protected void updateItem(java.time.LocalDate data, boolean empty) {
            super.updateItem(data, empty);
            setText(empty || data == null ? "" : data.format(formatoData));
        }
    });
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

    if (funcionarioSelecionado == null) {
        System.out.println("Nenhum funcionário selecionado.");
        return;
    }

    try {
        URL url = new File("src/main/java/view/TelaAlterarDadosFuncionarios.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        TelaAlterarDadosFuncionariosController tadfc = loader.getController();
        Stage telaADF = new Stage();

        tadfc.setStage(telaADF);
        tadfc.setFuncionario(funcionarioSelecionado);  

        Scene scene = new Scene(root);
        
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaADF.getIcons().add(icone);
        
        telaADF.setScene(scene);
        telaADF.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Alterar dados do funcionário");
        telaADF.setMaximized(true);
        telaADF.show();

           
          } catch (Exception e) {
        e.printStackTrace();
    }
}



    @FXML
    void OnclickExcluir(ActionEvent event) {
        boolean confirmado = AlertaUtil.mostrarConfirmacao(
                "Confirmação de exclusão",
                "Tem certeza que deseja excluir este usuário?",
                "Esta ação não poderá ser desfeita."
        ).filter( response -> response == ButtonType.OK).isPresent();
        

        if (confirmado) {
            Funcionario funcionarioSelecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
            if (funcionarioSelecionado != null ){
                deletarFuncionario(funcionarioSelecionado.getId()); 
                tabelaUsuarios.getItems().remove(funcionarioSelecionado);
                AlertaUtil.mostrarInformacao("Aviso", "Usuário excluído",
                    "O usuário foi removido com sucesso.");
            }
            
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaUsuariosSistema() {
        return AlertaUtil.mostrarConfirmacao(
                "Confirmação de saída",
                "Tem certeza que deseja fechar a tela atual?",
                "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

}