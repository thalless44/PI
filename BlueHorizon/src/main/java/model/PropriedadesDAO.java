package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PropriedadesDAO extends GenericDAO{
    
     public static boolean Propriedades(String tipo_propriedade, String endereco, Double preco, boolean disponibilidade, Date data_cadastro, String rua) {
        String sql = "INSERT INTO propriedades (tipo_propriedade, endereco, preco, disponibilidade, data_cadastro, rua) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo_propriedade);
            stmt.setString(2, endereco);
            stmt.setDouble(3, preco);
            stmt.setBoolean(4, disponibilidade);
            stmt.setDate(5, data_cadastro);
            stmt.setString(6, rua);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
