package controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.AlertaUtil;

public class TelaEditarImovelVendaController {
    
    @FXML
    private ImageView ImageViewImovel;

    @FXML
    private Button btnAdicionarImagem;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnSair;

    @FXML
    private RadioButton rbDisponivel;

    @FXML
    private RadioButton rbIndisponivel;

    @FXML
    private RadioButton rbNaoJardim;

    @FXML
    private RadioButton rbNaoMobiliada;

    @FXML
    private RadioButton rbNaoPiscina;

    @FXML
    private RadioButton rbNaoSS;

    @FXML
    private RadioButton rbSimJardim;

    @FXML
    private RadioButton rbSimMobiliada;

    @FXML
    private RadioButton rbSimPiscina;

    @FXML
    private RadioButton rbSimSS;

    @FXML
    private TextField txtfdArea;

    @FXML
    private TextField txtfdBanheiros;

    @FXML
    private TextField txtfdCidade;

    @FXML
    private TextField txtfdDataCadastro;

    @FXML
    private TextField txtfdEmailProprietario;

    @FXML
    private TextField txtfdNomeProprietario;

    @FXML
    private TextField txtfdNumeracaoImovel;

    @FXML
    private TextField txtfdQuartos;

    @FXML
    private TextField txtfdRua;

    @FXML
    private TextField txtfdTelefoneProprietario;

    @FXML
    private TextField txtfdVagasGaragem;

    @FXML
    private TextField txtfdValorImovel;

    
    @FXML
    void ActionbtnAlterarInformacoesImovel(ActionEvent event) {

    }

    @FXML
    void ActionbtnSair(ActionEvent event) {
        
        if(fecharTelaEditarImovelVenda()){
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();          
        } else {
            event.consume();
        }
    }

    @FXML
    void OnActionbtnAdicionarImagem(ActionEvent event) {
        
        // Colocar pra rodar quando a tela inicial estiver funcionando
        
        /*    
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString(), false);
            ImageViewImovel.setImage(image);
        }
        */
    }

    private boolean fecharTelaEditarImovelVenda() {
        
         // Chamando o método para confirmação de fechamento
        return AlertaUtil.mostrarConfirmacao(
            "Aviso", 
            "Tem certeza que deseja fechar a tela de alterar informações do imóvel?",
            "Todas as alterações não salvas serão perdidas e a tela atual será fechada!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }

}

