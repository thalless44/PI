package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDAO {

    // Método para cadastrar contrato (CREATE)
   public static int cadastrarContrato(Contrato contrato, Date dataContrato, Double valorCompra, int idPropriedade, int idCliente) {
    String sql = "INSERT INTO contratos (dataContrato, valorCompra, id_propriedade, id_cliente) VALUES (?, ?, ?, ?)";
    int idGerado = -1;

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        stmt.setDate(1, dataContrato);
        stmt.setDouble(2, valorCompra);
        stmt.setInt(3, idPropriedade);
        stmt.setInt(4, idCliente);
       
        

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
                contrato.setIdContrato(idGerado); // já seta o ID no objeto
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return idGerado;
}
   
    public static List<Contrato> listarContratosCompletos() {
    List<Contrato> contratos = new ArrayList<>();

    String sql = "SELECT c.id_contrato, c.dataContrato, c.valorCompra, c.id_propriedade, c.id_cliente FROM contratos c";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Contrato contrato = new Contrato();
            contrato.setIdContrato(rs.getInt("id_contrato"));
            contrato.setDataContrato(rs.getDate("dataContrato").toLocalDate());
            contrato.setValorCompra(rs.getDouble("valorCompra"));
            contrato.setIdImovel(rs.getInt("id_propriedade"));
            contrato.setIdCliente(rs.getInt("id_cliente"));

            contratos.add(contrato);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return contratos;
}

    
}
