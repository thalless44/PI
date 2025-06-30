package controller;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;
import model.ClienteDAO;
import model.Funcionario;
import model.Propriedades;
import model.Proprietario;
import model.ProprietarioDAO;
import util.LimitarCaracter;

public class TelaPagamentoController {

    @FXML
    private Button btnCancelarpagamento;

    @FXML
    private Button btnEfetuarPagamento;

    @FXML
    private ComboBox<Cliente> cmbxClienteSelecionado;

    @FXML
    private Label labelIDImovel;

    @FXML
    private Label labelTipoPropriedade;

    @FXML
    private Label labelValor;

    @FXML
    private TextField txtFormapagamento;

    @FXML
    private TextField txtValorPagamento;

    @FXML
    private TextField txtdatapagamento;
    private Stage stage;
    
    @FXML
    void initialize(){
        new LimitarCaracter(10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtdatapagamento);
        new LimitarCaracter(10, LimitarCaracter.TipoEntrada.VALOR).applyToTextInputControl(txtValorPagamento);
        carregarProprietarios();

        
    }
    
     private void carregarProprietarios() {
        List<Cliente> clientes = ClienteDAO.listarTodos();
        cmbxClienteSelecionado.getItems().clear();
        cmbxClienteSelecionado.getItems().addAll(clientes);
    }

    @FXML
    void ActionCancelarPagamento(ActionEvent event) {

    }

    @FXML
    void ActionEfetuarPagamento(ActionEvent event) {

    }
    
    public void preencherInformacoesImovel(Propriedades propriedade){
        
        if(propriedade != null){
            
        labelIDImovel.setText(propriedade.getCodigoImovel());
       // labelValor.setText(propriedade.getPreco());
        labelTipoPropriedade.setText(propriedade.getTipoPropriedade());
       
        
        //Formatando datas para o modelo (dia/mes/ano)
        
       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DnID.setText(funcionario.getDataNascimento().format(formatter));
        DcID.setText(funcionario.getDataContratacao().format(formatter));
        
        if(funcionario.getDataNascimento() != null){
            DnID.setText(funcionario.getDataNascimento().format(formatter));
        }else{
            DnID.setText("Não informado");
        }
        
        if(funcionario.getDataContratacao() != null){
            DnID.setText(funcionario.getDataContratacao().format(formatter));
        }else{
            DnID.setText("Não informado");
        }
        */
            
        }
    }

    void setPropriedade(Propriedades propriedadeSelecionada) {
    }

    void setStage(Stage telaPG) {
        this.stage = telaPG;
    }

}
