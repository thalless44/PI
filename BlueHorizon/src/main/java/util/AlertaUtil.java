package util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertaUtil {

    public static void mostrarAlerta(Alert.AlertType tipo, 
        String titulo, String headerText, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(headerText);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    // Sobrecarga para 2 parâmetros: título e mensagem (sem headerText)
    public static void mostrarAlerta(Alert.AlertType tipo, 
        String titulo, String mensagem) {
        mostrarAlerta(tipo, titulo, null, mensagem);
    }
    
    // Erro
    public static void mostrarErro(String titulo, String headerText, String mensagem) {
        mostrarAlerta(Alert.AlertType.ERROR, titulo, headerText, mensagem);
    }
    
    public static void mostrarErro(String titulo, String mensagem) {
        mostrarAlerta(Alert.AlertType.ERROR, titulo, null, mensagem);
    }
    
    // Informação
    public static void mostrarInformacao(String titulo, String headerText, String mensagem) {
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, headerText, mensagem);
    }

    public static void mostrarInformacao(String titulo, String mensagem) {
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, null, mensagem);
    }
    
    // Aviso
    public static void mostrarAviso(String titulo, String headerText, String mensagem) {
        mostrarAlerta(Alert.AlertType.WARNING, titulo, headerText, mensagem);
    }

    public static void mostrarAviso(String titulo, String mensagem) {
        mostrarAlerta(Alert.AlertType.WARNING, titulo, null, mensagem);
    }
    
    // Confirmação
    public static Optional<ButtonType> mostrarConfirmacao(String titulo, String headerText, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(headerText);
        alerta.setContentText(mensagem);
        return alerta.showAndWait();
    }
}
