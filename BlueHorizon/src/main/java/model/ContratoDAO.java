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
    public static boolean cadastrarContrato(Contrato contrato) {
        String sql = "INSERT INTO contratos (data_cadastro, data_contrato, id_cliente, id_imovel, valor_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, contrato.getDataCadastro());
            stmt.setDate(2, contrato.getDataContrato());
            stmt.setInt(3, contrato.getIdCliente());
            stmt.setInt(4, contrato.getIdImovel());
            stmt.setDouble(5, contrato.getValorTotal());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os contratos (READ)
    public static List<Contrato> listarTodos() {
        List<Contrato> lista = new ArrayList<>();
        String sql = "SELECT * FROM contratos";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contrato contrato = new Contrato(
                    rs.getInt("id_contrato"),
                    rs.getDate("data_cadastro"),
                    rs.getDate("data_contrato"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_imovel"),
                    rs.getDouble("valor_total")
                );
                lista.add(contrato);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para atualizar contrato (UPDATE)
    public static boolean atualizarContrato(Contrato contrato) {
        String sql = "UPDATE contratos SET data_cadastro = ?, data_contrato = ?, id_cliente = ?, id_imovel = ?, valor_total = ? WHERE id_contrato = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, contrato.getDataCadastro());
            stmt.setDate(2, contrato.getDataContrato());
            stmt.setInt(3, contrato.getIdCliente());
            stmt.setInt(4, contrato.getIdImovel());
            stmt.setDouble(5, contrato.getValorTotal());
            stmt.setInt(6, contrato.getIdContrato());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar contrato (DELETE)
    public static boolean deletarContrato(int idContrato) {
        String sql = "DELETE FROM contratos WHERE id_contrato = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idContrato);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar contrato por ID
    public static Contrato buscarPorId(int idContrato) {
        String sql = "SELECT * FROM contratos WHERE id_contrato = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idContrato);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Contrato(
                    rs.getInt("id_contrato"),
                    rs.getDate("data_cadastro"),
                    rs.getDate("data_contrato"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_imovel"),
                    rs.getDouble("valor_total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
