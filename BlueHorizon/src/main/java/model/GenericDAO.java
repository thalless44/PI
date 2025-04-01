package model;

import dal.ConexaoBD;
import java.sql.Connection;

//Classe abstrata não pode ser instanciada, é um modelo para as classes que irão herdar seu comportamento
//Esta classe facilita para que não precisemos em todas as Classes DAO executar todo este código, 
//iremos apenas passar os parâmetros
public abstract class GenericDAO {
	private Connection conexao;

	// Protected pois pertencem a esta classe, somente podem ser usadas por classes
	// que herdam desta
	protected GenericDAO() {
		this.conexao = ConexaoBD.conectar();
	}

	// Método que retorna a conexaao
	protected Connection conectarDAO() {
		this.conexao = ConexaoBD.conectar();
		return conexao;
	}
        
}
