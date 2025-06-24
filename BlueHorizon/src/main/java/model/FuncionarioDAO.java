package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO extends GenericDAO{

    // Método para cadastrar funcionário (já existente)
    public static boolean cadastrarFuncionario(String nome, String cpf, Date dataNascimento, Date dataContratacao, 
                                               String endereco, String telefone, String email, String senha, 
                                               String cargo, double salario) {
        String sql = "INSERT INTO funcionarios (nome, cpf, dataNascimento, dataContratacao, endereco, telefone, email, senha, cargo, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setDate(3, dataNascimento);
            stmt.setDate(4, dataContratacao);
            stmt.setString(5, endereco);
            stmt.setString(6, telefone);
            stmt.setString(7, email);
            stmt.setString(8, senha);
            stmt.setString(9, cargo);
            stmt.setDouble(10, salario);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar funcionário
    public static boolean deletarFuncionario(int id_funcionario) {
          String sql = "DELETE FROM funcionarios WHERE id_funcionario = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_funcionario);  // Atribui o ID que queremos deletar
            int linhasAfetadas = stmt.executeUpdate();  // Executa o DELETE

            return linhasAfetadas > 0;  // Se o valor for maior que 0, quer dizer que algo foi deletado
        } catch (SQLException e) {
            e.printStackTrace();  // Se houver erro, exibe no console
            return false;  // Retorna false caso aconteça algum erro
        }
    }
    // Novo método para listar os funcionários
     public static List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                    rs.getInt("id_funcionario"),
                    String.valueOf(rs.getDouble("Salario")),
                    rs.getString("nome"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getString("cpf"),
                    rs.getString("cargo"),
                    rs.getDate("dataContratacao").toLocalDate(),
                    rs.getDate("dataNascimento").toLocalDate(),
                    rs.getString("Senha"),
                    rs.getString("email")
                );
                lista.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public static void atualizarFuncionario(Funcionario funcionario) throws SQLException {
        
        String sql = "UPDATE funcionarios SET Salario=?, nome=?, telefone=?, endereco=?, cpf=?, cargo=?, dataContratacao=?, dataNascimento=?, Senha=?, email=? WHERE id_funcionario=?"; 
                
        try(Connection conn = ConexaoBD.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, funcionario.getSalario());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getEndereco());
            stmt.setString(5, funcionario.getCpf());
            stmt.setString(6, funcionario.getCargo());
            stmt.setDate(7, Date.valueOf(funcionario.getDataContratacao()));
            stmt.setDate(8, Date.valueOf(funcionario.getDataNascimento()));
            stmt.setString(9, funcionario.getSenha());
            stmt.setString(10, funcionario.getEmail());
            stmt.setInt(11, funcionario.getId());
       
            
            stmt.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();
            
            throw new RuntimeException("Erro ao atualizar funcionário.", e);
        }
        
        
    }

    public boolean bancoOnline() {
        
        Connection conn = conectarDAO();
        if(conn != null){
            try{
                conectarDAO().close();
            }catch(SQLException e){
                
            }
            return true;
        }else{
            return false;
        }
    }

    public Funcionario autenticar(String email, String senha) {
       String sql = "SELECT * FROM funcionarios WHERE email=? AND senha=?";
    Funcionario usuario = null;

    try (Connection con = conectarDAO();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setString(1, email);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Crie o objeto com todos os dados necessários, se desejar
            usuario = new Funcionario(
                rs.getInt("id_funcionario"),
                String.valueOf(rs.getDouble("salario")),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("endereco"),
                rs.getString("cpf"),
                rs.getString("cargo"),
                rs.getDate("dataContratacao").toLocalDate(),
                rs.getDate("dataNascimento").toLocalDate(),
                rs.getString("senha"),
                rs.getString("email")
            );
        }

        rs.close();
    } catch (SQLException e) {
        e.printStackTrace(); // Isso ajuda a entender o que deu errado
    }
    return usuario; // Vai retornar null se não encontrar
}
  public static boolean verificarDadosRecSenha(String email, String cpf, java.sql.Date dataNascimento) {
    String sql = "SELECT * FROM funcionarios " +
                 "WHERE email=? AND cpf=? AND dataNascimento=?";
    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        stmt.setString(2, cpf);
        stmt.setDate(3, dataNascimento);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public static boolean atualizarSenha(String email, String novaSenha) {
    String sql = "UPDATE funcionarios SET senha=? WHERE email=?";
    
    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, novaSenha);
        stmt.setString(2, email);
        
        int linhasAfetadas = stmt.executeUpdate(); // Atualiza a senha no banco
        return linhasAfetadas > 0; // Se atualizou com sucesso, retorna true
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}

