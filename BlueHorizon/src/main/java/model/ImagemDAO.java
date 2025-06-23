package model;

import dal.ConexaoBD;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.image.Image;



public class ImagemDAO {
    
    public static int salvarImagemNoBanco(File arquivoImagem) {
    String sql = "INSERT INTO imagens_imoveis (url, dados) VALUES (?, ?)";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
         FileInputStream fis = new FileInputStream(arquivoImagem)) {

        stmt.setString(1, arquivoImagem.getName());
        stmt.setBinaryStream(2, fis, (int) arquivoImagem.length());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); 
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return -1; 
}
    
    public static Image carregarImagemDoBanco(int idImagem) {
    String sql = "SELECT dados FROM imagens_imoveis WHERE id_imagem = ?";

    try (Connection conn = ConexaoBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idImagem);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            InputStream is = rs.getBinaryStream("dados");
            return new Image(is);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}
    
    
}
