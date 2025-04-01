package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDAO extends GenericDAO {
    public static boolean cadastrarFuncionario(String nome, String cpf, Date dataNascimento, Date dataContratacao, 
                                               String endereco, String telefone, String email, String senha,String cargo, double salario) {
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
}
