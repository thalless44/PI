package util;

import java.util.UUID;

public class RecoveryCodeGenerator {
    
    /**
     * Gera um código de recuperação único e aleatório.
     * Em uma aplicação real, este código seria armazenado no banco de dados
     * associado ao usuário e com um timestamp de expiração.
     * @return Um código de recuperação de 8 caracteres.
     */
    public static String generateUniqueRecoveryCode() {
        // Gerar um UUID e pegar os primeiros 8 caracteres em maiúsculas
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static void main(String[] args) {
        // Exemplo de como você chamaria este método:
        // String code = RecoveryCodeGenerator.generateUniqueRecoveryCode();
        // System.out.println("Código de recuperação gerado: " + code);
    }
    
}
