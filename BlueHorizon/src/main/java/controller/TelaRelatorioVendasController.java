package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaRelatorioVendasController {
    
    private Stage stage;

    @FXML
    private Button btnSair;

    @FXML
    private Label lblRelatorioVendas;

    @FXML
    private TableColumn<?, ?> tblCidade;

    @FXML
    private TableColumn<?, ?> tblDataCadastro;

    @FXML
    private TableColumn<?, ?> tblImovel;

    @FXML
    private TableColumn<?, ?> tblValor;

    @FXML
    void OnClickbntSair(ActionEvent event) {
        
        // Usando AlertaUtil para confirmar o fechamento
        if (AlertaUtil.mostrarConfirmacao(
                "Confirmação", 
                "Tem certeza que deseja fechar a tela de relatório de vendas?",
                "Todas as alterações não salvas serão perdidas."
            ).filter(response -> response == ButtonType.OK).isPresent()) {
            
            // Se o usuário confirmar, fecha a tela
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            // Caso o usuário cancele, nada acontece
            event.consume();
        }
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }
}
