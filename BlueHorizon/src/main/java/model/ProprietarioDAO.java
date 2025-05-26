package model;

import dal.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO extends GenericDAO {

    public static boolean Proprietarios(String telefoneProprietario, String nomeProprietario, String emailProprietario) {
       
        return false;
       
    }

    public static boolean cadastrarProprietario(String nome, String email, String telefone) {
        return false;
    }

    // CREATE
    public boolean inserir(String telefone, String nome, String email) {
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

    // READ - Listar todos
    public List<Proprietario> listarTodos() {
        List<Proprietario> proprietarios = new ArrayList<>();
        String sql = "SELECT * FROM proprietarios";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proprietario p = new Proprietario();
                p.setId(rs.getInt("id"));
                p.setTelefone(rs.getString("telefone"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                proprietarios.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proprietarios;
    }

    // READ - Buscar por ID
    public Proprietario buscarPorId(int id) {
        Proprietario p = null;
        String sql = "SELECT * FROM proprietarios WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new Proprietario();
                    p.setId(rs.getInt("id"));
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

    // UPDATE
    public boolean atualizar(int id, String telefone, String nome, String email) {
        String sql = "UPDATE proprietarios SET telefone = ?, nome = ?, email = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setInt(4, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deletar(int id) {
        String sql = "DELETE FROM proprietarios WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
