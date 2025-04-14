package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaInicialController {

    private Stage stage;

    @FXML
    private MenuItem menuAcessarPerfil;

    @FXML
    private MenuItem btnUsuarioSistema;

    @FXML
    private Menu btnSair;

    @FXML
    private MenuItem menuCadastroCliente;

    @FXML
    private MenuItem menuCadastroFuncionario;

    @FXML
    private MenuItem menuCadastroImovol;

    @FXML
    private MenuItem btnFecharApp;

    @FXML
    private MenuItem btnRelatorioVendaDosImoveis;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void OnActionFecharAplicacao(ActionEvent event) {
        if (SairTelaInicio()) {
            System.exit(0);
        } else {
            event.consume();
        }
    }

    @FXML
    void onClickCliente(ActionEvent event) {
        abrirTela("TelaCadastroCliente.fxml", "Cadastro de cliente");
    }

    @FXML
    void OnClickAbrirUsuarioSistema(ActionEvent event) {
        abrirTela("TelaUsuarioSistema.fxml", "Usuários do sistema");
    }

    @FXML
    void onClickFuncionario(ActionEvent event) {
        abrirTela("TelaCadastroFuncionario.fxml", "Cadastro de funcionário");
    }

    @FXML
    void onClickImovel(ActionEvent event) {
        abrirTela("TelaCadastroImovel.fxml", "Cadastro de imóvel");
    }

    @FXML
    void onClickPerfil(ActionEvent event) {
        abrirTela("TelaInformacoesPerfil.fxml", "Informações do perfil");
    }

    @FXML
    void OnActionBtnRelatorioVendaDosImoveis(ActionEvent event) {
        abrirTela("TelaRelatorioVendas.fxml", "Relatório de venda dos imóveis");
    }

    private boolean SairTelaInicio() {
        return AlertaUtil.mostrarConfirmacao(
            "Fechar Aplicação",
            "Tem certeza que deseja fechar a aplicação?\nTodas as alterações não salvas serão perdidas."
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

    private void abrirTela(String caminhoFXML, String titulo) {
        try {
            URL url = new File("src/main/java/view/" + caminhoFXML).toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage novaTela = new Stage();
            Scene scene = new Scene(root);
            novaTela.setScene(scene);
            novaTela.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | " + titulo);
            novaTela.setMaximized(true);
            novaTela.show();

            // Se o controller tiver o método setStage, setamos aqui (opcional)
            Object controller = loader.getController();
            if (controller instanceof TelaCadastroClienteController) {
                ((TelaCadastroClienteController) controller).setStage(novaTela);
            } else if (controller instanceof TelaCadastroFuncionarioController) {
                ((TelaCadastroFuncionarioController) controller).setStage(novaTela);
            } else if (controller instanceof TelaCadastroImovelController) {
                ((TelaCadastroImovelController) controller).setStage(novaTela);
            } else if (controller instanceof TelaUsuarioSistemaController) {
                ((TelaUsuarioSistemaController) controller).setStage(novaTela);
            } else if (controller instanceof TelaInformacoesPerfilController) {
                ((TelaInformacoesPerfilController) controller).setStage(novaTela);
            } else if (controller instanceof TelaRelatorioVendasController) {
                ((TelaRelatorioVendasController) controller).setStage(novaTela);
            }

        } catch (IOException e) {
            e.printStackTrace();
            AlertaUtil.mostrarErro("Erro ao abrir tela", "Não foi possível carregar a tela: " + titulo);
        }
    }
}