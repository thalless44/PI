package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImovelDAO extends GenericDAO {
    
     public static boolean InformacoesImovel(int quartos, int banheiros, int vagasGaragem, boolean mobilia, boolean jardim, boolean sistemaSeguranca, boolean piscina, int numeroCasa, String area) {
        String sql = "INSERT INTO informacoes_imoveis (quartos, banheiros, vagasGaragem, mobilia, jardim, sistemaSeguranca, piscina, numeroCasa) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quartos);
            stmt.setInt(2, banheiros);
            stmt.setInt(3, vagasGaragem);
            stmt.setBoolean(4, mobilia);
            stmt.setBoolean(5, jardim);
            stmt.setBoolean(6, sistemaSeguranca);
            stmt.setBoolean(7, piscina);
            stmt.setInt(8, numeroCasa);
            stmt.setString(9, area);

            

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
