package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class TransacoesDAO {
    
    public static boolean cadastrarTransacao(String formaPagamento, Date dataTransacao, double valor, int idCliente, int idPropriedade) {
        String sql = "INSERT INTO transacoes (formaPagamento, dataTransacao, valor, id_cliente, id_propriedade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, formaPagamento);
            stmt.setDate(2, dataTransacao);
            stmt.setDouble(3, valor);
            stmt.setInt(4, idCliente);
            stmt.setInt(5, idPropriedade);

            stmt.executeUpdate();
            System.out.println("Transação cadastrada com sucesso!");
            return true;

        } catch (Exception e) {
            System.err.println("Erro ao cadastrar transação: " + e.getMessage());
            return false;
        }
    }
    
}
