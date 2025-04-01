package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO extends GenericDAO{
    
    public Boolean bancoOnline() {
		Connection con = conectarDAO();
		if (con != null) {
			try {
				conectarDAO().close();
			} catch (SQLException e) {
			}
			return true;
		} else
			return false;
	}
    //	 Método para autenticar usuários
	public Login autenticar(String login, String senha) throws SQLException {
		String sql = "SELECT * FROM funcionarios WHERE email=? AND senha=?";
		Login usuario = null;
		Connection con = conectarDAO();
		if (con != null) {
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
                        
               while (rs.next()) {
                   String email = rs.getString("email");
                   String senhabd = rs.getString("senha");
                   
				usuario = new Login(email,senhabd);
				
			}

			rs.close();
			stmt.close();
			conectarDAO().close();
			return usuario;
		} else {
			return null;
			
		}
    
    
}
}
