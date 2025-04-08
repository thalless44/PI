package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProprietarioDAO extends GenericDAO{
    
     public static boolean Proprietarios(String telefone, String nome, String email) {
        String sql = "INSERT INTO proprietarios(telefone, nome, email) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone);
            stmt.setString(2, nome);
            stmt.setString(3, email);
           
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
