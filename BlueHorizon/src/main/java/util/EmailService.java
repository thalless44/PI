package util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Authenticator; // Importe este Authenticator!
import jakarta.mail.PasswordAuthentication; // E este PasswordAuthentication!
import java.util.Properties;

public class EmailService {

    // Configure com as suas credenciais e informações do servidor SMTP
    private static final String USERNAME = "email"; // Seu e-mail remetente
    private static final String PASSWORD = "senha";   // Sua senha do e-mail
    private static final String HOST = "smtp.gmail.com";     // Ex: smtp.gmail.com, smtp.outlook.com
    private static final String PORT = "587"; // Porta padrão para TLS; use "465" para SSL/TLS explícito

    /**
     * Envia um e-mail de recuperação de senha com um código específico.
     * @param recipientEmail O endereço de e-mail do destinatário.
     * @param recoveryCode O código de recuperação a ser enviado.
     * @return true se o e-mail foi enviado com sucesso, false caso contrário.
     */
    public static boolean sendRecoveryEmail(String recipientEmail, String recoveryCode) {
        // Configura as propriedades para a sessão de e-mail
        Properties prop = new Properties();
        prop.put("mail.smtp.host", HOST);
        prop.put("mail.smtp.port", PORT);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // Habilita TLS

        // Cria uma nova sessão com autenticador
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            // Cria uma nova mensagem de e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME)); // Define o remetente
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail) // Define o destinatário
            );
            message.setSubject("Código de Recuperação de Senha"); // Assunto do e-mail
            
            // Corpo do e-mail
            String emailContent = "Olá,\n\n" +
                                  "Seu código de recuperação de senha é: " + recoveryCode +
                                  "\n\nUse este código para redefinir sua senha. Ele é válido por um tempo limitado." +
                                  "\n\nAtenciosamente,\nSua Equipe de Suporte";
            message.setText(emailContent);

            // Envia a mensagem
            Transport.send(message);
            System.out.println("E-mail de recuperação enviado com sucesso para: " + recipientEmail);
            return true;
        } catch (MessagingException e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        // Exemplo de como você chamaria este método:
         boolean sent = EmailService.sendRecoveryEmail("destino@example.com", "XYZ789");
         if (sent) {
           System.out.println("E-mail de teste enviado com sucesso!");
         } else {
            System.out.println("Falha no envio do e-mail de teste.");
         }
    }
}