package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para cadastrar cliente (CREATE)
    public static boolean cadastrarCliente(String nome, String email, String telefone) {
        String sql = "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);       // Define o nome
            stmt.setString(2, email);      // Define o email
            stmt.setString(3, telefone);   // Define o telefone

            int linhasAfetadas = stmt.executeUpdate();  // Executa o INSERT
            return linhasAfetadas > 0;  // Retorna true se a inserção foi bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();  // Exibe o erro no console
            return false;         // Retorna false em caso de erro
        }
    }

    // Método para deletar cliente (DELETE)
    public static boolean deletarCliente(int id_cliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_cliente);  // Define o ID do cliente que será deletado
            int linhasAfetadas = stmt.executeUpdate();  // Executa o DELETE

            return linhasAfetadas > 0;  // Retorna true se algo foi deletado
        } catch (SQLException e) {
            e.printStackTrace();  // Exibe o erro no console
            return false;         // Retorna false em caso de erro
        }
    }

    // Método para listar todos os clientes (READ)
    public static List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Percorre o resultado e adiciona os clientes na lista
            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("telefone")
                );
                lista.add(c);  // Adiciona o cliente na lista
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Exibe o erro no console
        }

        return lista;  // Retorna a lista de clientes
    }

    // Método para atualizar cliente (UPDATE)
    public static void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, email = ?, telefone = ? WHERE id_cliente = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());       // Atualiza o nome
            stmt.setString(2, cliente.getEmail());      // Atualiza o email
            stmt.setString(3, cliente.getTelefone());   // Atualiza o telefone
            stmt.setInt(4, cliente.getId());            // Define o ID do cliente a ser atualizado

            stmt.executeUpdate();  // Executa o UPDATE

        } catch (SQLException e) {
            e.printStackTrace();  // Exibe o erro no console
            throw new RuntimeException("Erro ao atualizar cliente.", e);  // Lança exceção
        }
    }
}
