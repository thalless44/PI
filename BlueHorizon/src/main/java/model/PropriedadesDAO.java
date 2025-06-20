package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropriedadesDAO extends GenericDAO{
    
      public static boolean Propriedades(String tipo_propriedade, String endereco, Double preco, boolean disponibilidade, Date data_cadastro, String rua, 
             int quartos, int banheiros, int vagasGaragem, boolean mobilia, boolean jardim, boolean sistemaSeguranca, boolean piscina, int numeroCasa, String area) {
         
        String sql = "INSERT INTO propriedades (tipo_propriedade, endereco, preco, disponibilidade, data_cadastro, rua, quartos, banheiros, vagasGaragem,"
                  + " mobilia, jardim, sistemaSeguranca, piscina, numeroCasa, area) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo_propriedade);
            stmt.setString(2, endereco);
            stmt.setDouble(3, preco);
            stmt.setBoolean(4, disponibilidade);
            stmt.setDate(5, data_cadastro);
            stmt.setString(6, rua);
            stmt.setInt(7, quartos);
            stmt.setInt(8, banheiros);
            stmt.setInt(9, vagasGaragem);
            stmt.setBoolean(10, mobilia);
            stmt.setBoolean(11, jardim);
            stmt.setBoolean(12, sistemaSeguranca);
            stmt.setBoolean(13, piscina);
            stmt.setInt(14, numeroCasa);
            stmt.setString(15, area);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
     
     
     
     //metodo de listagem
     public static List<Propriedades> listarPropriedades() {
    List<Propriedades> lista = new ArrayList<>();
    String sql = "SELECT * FROM propriedades";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Propriedades p = new Propriedades();
        
            p.setId(rs.getInt("id_propriedade"));
            p.setTipoPropriedade(rs.getString("tipo_propriedade"));
            p.setEndereco(rs.getString("endereco"));
            p.setPreco(rs.getDouble("preco"));
            p.setDisponibilidade(rs.getBoolean("disponibilidade"));
            p.setDataCadastro(rs.getDate("data_cadastro"));
            p.setRua(rs.getString("rua"));
            p.setQuartos(rs.getInt("quartos"));
            p.setBanheiros(rs.getInt("banheiros"));
            p.setVagasGaragem(rs.getInt("vagasGaragem"));
            p.setMobilia(rs.getBoolean("mobilia"));
            p.setJardim(rs.getBoolean("jardim"));
            p.setSistemaSeguranca(rs.getBoolean("sistemaSeguranca"));
            p.setPiscina(rs.getBoolean("piscina"));
            p.setNumeroCasa(rs.getInt("numeroCasa"));
            p.setArea(rs.getString("area"));
            
            lista.add(p);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
     
     //metodo para deletar
    public static boolean deletarPropriedade(int id) {
    String sql = "DELETE FROM propriedades WHERE id = ?";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int linhasAfetadas = stmt.executeUpdate();

        return linhasAfetadas > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

     public static boolean atualizarPropriedade(Propriedades p) {
    String sql = "UPDATE propriedades SET tipo_propriedade = ?, endereco = ?, preco = ?, disponibilidade = ?, data_cadastro = ?, rua = ?, quartos = ?, "
            + "banheiros  = ?, vagasGaragem = ?, mobilia = ?, jardim = ?, sistemaSeguranca = ?, piscina = ?, numeroCasa = ?, area = ? WHERE id = ?";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, p.getTipoPropriedade());
        stmt.setString(2, p.getEndereco());
        stmt.setDouble(3, p.getPreco());
        stmt.setBoolean(4, p.isDisponibilidade());
        stmt.setDate(5, p.getDataCadastro());
        stmt.setString(6, p.getRua());     
        stmt.setInt(7, p.getQuartos());
        stmt.setInt(8, p.getBanheiros());
        stmt.setInt(9, p.getVagasGaragem());
        stmt.setBoolean(10, p.isMobilia());
        stmt.setBoolean(11, p.isJardim());
        stmt.setBoolean(12, p.isSistemaSeguranca());
        stmt.setBoolean(13, p.isPiscina());
        stmt.setInt(14, p.getNumeroCasa());
        stmt.setString(15, p.getArea());
        

        int linhasAfetadas = stmt.executeUpdate();
        return linhasAfetadas > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }

     }
    
}
