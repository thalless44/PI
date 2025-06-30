package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {

    public static List<String> buscarCidades(String prefixo) {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nome FROM cidades WHERE nome LIKE ? ORDER BY nome LIMIT 10";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prefixo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public static Integer buscarIdCidadePorNome(String nomeCidade) {
    String sql = "SELECT id FROM cidade WHERE nome = ?";
    try (Connection conn = dal.ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nomeCidade);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // retorna null se não encontrar
}
}
