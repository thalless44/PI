package model;

import dal.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO extends GenericDAO {

    // Método para cadastrar proprietário
    public static boolean cadastrarProprietario(String telefone, String nome, String email) {
        String sql = "INSERT INTO proprietarios (telefone, nome, email) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone);
            stmt.setString(2, nome);
            stmt.setString(3, email);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os proprietários
    public static List<Proprietario> listarTodosProprietarios() {
    List<Proprietario> lista = new ArrayList<>();
    String sql = "SELECT id_proprietario, nome, telefone, email FROM proprietarios";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Proprietario p = new Proprietario();
            p.setId(rs.getInt("id_proprietario")); // 
            p.setNome(rs.getString("nome"));
            p.setTelefone(rs.getString("telefone"));
            p.setEmail(rs.getString("email"));
            lista.add(p);
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}

    // Método para buscar um proprietário pelo ID
    public static Proprietario buscarPorId(int id) {
        Proprietario p = null;
        String sql = "SELECT * FROM proprietarios WHERE id_proprietario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Proprietario();
                    p.setId(rs.getInt("id_proprietario"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setNome(rs.getString("nome"));
                    p.setEmail(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    // Método para atualizar os dados de um proprietário
    public boolean atualizar(int id, String nome, String telefone, String email) {
        String sql = "UPDATE proprietarios SET telefone = ?, nome = ?, email = ? WHERE id_proprietario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar um proprietário
    public boolean deletar(int id) {
        String sql = "DELETE FROM proprietarios WHERE id_proprietario = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Integer buscarIdProprietarioPorNome(String nome) {
    String sql = "SELECT id_proprietario FROM proprietarios WHERE nome = ?";
    try (Connection conn = dal.ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_proprietario");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // retorna null se não encontrar
}

    // Método para atualizar um proprietário usando o objeto
    public static boolean atualizarProprietario(Proprietario p) {
        ProprietarioDAO dao = new ProprietarioDAO();
        return dao.atualizar(p.getId(), p.getTelefone(), p.getNome(), p.getEmail());
    }
}
