package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.AlertaUtil;
import dal.ConexaoBD;

public class TelaContratoController {

    @FXML
    private Button btnSair;

    @FXML
    private Label lblContrato, lblDataCadastro, lblDataContrato, lblEmailCliente, lblIdContrato,
                  lblIdImovel, lblNomeCliente, lblTelefoneCliente, lblValorImovel, lblValorTotal;

    private int idContrato;  // guarda o id do contrato a ser carregado

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
        carregarDadosContrato();
    }

    private void carregarDadosContrato() {
        String sql = "SELECT c.id_contrato, c.data_cadastro, c.data_contrato, c.valor_total, " +
                     "cli.nome AS nome_cliente, cli.email AS email_cliente, cli.telefone AS telefone_cliente, " +
                     "c.id_imovel, p.preco AS valor_imovel " +
                     "FROM contratos c " +  
                     "JOIN clientes cli ON c.id_cliente = cli.id_cliente " +
                     "JOIN propriedades p ON c.id_imovel = p.id_propriedade " +
                     "WHERE c.id_contrato = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idContrato);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    lblIdContrato.setText(String.valueOf(rs.getInt("id_contrato")));

                    Date dataCadastro = rs.getDate("data_cadastro");
                    lblDataCadastro.setText(dataCadastro != null ? dataCadastro.toLocalDate().format(formatter) : "");

                    Date dataContrato = rs.getDate("data_contrato");
                    lblDataContrato.setText(dataContrato != null ? dataContrato.toLocalDate().format(formatter) : "");

                    lblNomeCliente.setText(rs.getString("nome_cliente"));
                    lblEmailCliente.setText(rs.getString("email_cliente"));
                    lblTelefoneCliente.setText(rs.getString("telefone_cliente"));

                    lblIdImovel.setText(String.valueOf(rs.getInt("id_imovel")));

                    lblValorImovel.setText(String.format("R$ %.2f", rs.getDouble("valor_imovel")));
                    lblValorTotal.setText(String.format("R$ %.2f", rs.getDouble("valor_total")));

                    lblContrato.setText("Contrato #" + rs.getInt("id_contrato"));
                } else {
                    AlertaUtil.mostrarErro("Erro", "Contrato não encontrado", "Nenhum contrato com esse ID foi encontrado.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            AlertaUtil.mostrarErro("Erro", "Ocorreu um erro inesperado.", "Verifique os dados e tente novamente.");
        }
    }

    @FXML
    void onActionSair(ActionEvent event) {
        if (FecharTelaContrato()) {
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.close();
        } else {
            event.consume();
        }
    }

    private boolean FecharTelaContrato() {
        return AlertaUtil.mostrarConfirmacao(
            "Aviso",
            "Tem certeza que deseja fechar a tela de contrato?",
            "Todas as alterações não salvas serão perdidas!"
        ).filter(response -> response == ButtonType.OK).isPresent();
    }
}
