package controller;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Contrato;
import model.ContratoDAO; 

public class TelaRelatorioVendasController {

    @FXML
    private Button btnSair;

    @FXML
    private TableView<Contrato> tblContratos;

    @FXML
    private TableColumn<Contrato, Integer> colIdContrato;

    @FXML
    private TableColumn<Contrato, String> colDataContrato;

    @FXML
    private TableColumn<Contrato, Double> colValorCompra;

    @FXML
    private TableColumn<Contrato, Integer> colIdPropriedade;

    @FXML
    private TableColumn<Contrato, Integer> colIdCliente;
    
    private Stage stage;
    
    private final NumberFormat moedaBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


    @FXML
    public void initialize() {
        colIdContrato.setCellValueFactory(cellData -> cellData.getValue().idContratoProperty().asObject()); 
        colDataContrato.setCellValueFactory(cellData -> {
            if (cellData.getValue().getDataContrato() != null) {
                String formattedDate = cellData.getValue().getDataContrato()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return new SimpleStringProperty(formattedDate);
            } else {
                return new SimpleStringProperty("");
            }
        });

       colValorCompra.setCellValueFactory(cellData -> cellData.getValue().valorCompraProperty().asObject());

        colValorCompra.setCellFactory(column -> new TableCell<Contrato, Double>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                if (empty || valor == null) {
                    setText(null);
                } else {
                    setText(moedaBR.format(valor));
                }
            }
        });
        colIdPropriedade.setCellValueFactory(cellData -> cellData.getValue().idImovelProperty().asObject());
        colIdCliente.setCellValueFactory(cellData -> cellData.getValue().idClienteProperty().asObject());

        carregarContratos();
    }

    private void carregarContratos() {
        ObservableList<Contrato> listaContratos = FXCollections.observableArrayList(ContratoDAO.listarContratosCompletos());
        tblContratos.setItems(listaContratos);
    }

    @FXML
    void OnClickbntSair(ActionEvent event) {
        if (stage != null) {
            stage.close();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
