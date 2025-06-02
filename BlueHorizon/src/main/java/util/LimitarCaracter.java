package util;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;

public class LimitarCaracter {

    public enum TipoEntrada {
        NUMERODECIMAL, NOME, EMAIL, DATA, CPF, FONE;
    }

    private int qtdCaracteres;
    private TipoEntrada tpEntrada;

    public LimitarCaracter(int qtdCaracteres, TipoEntrada tpEntrada) {
        this.qtdCaracteres = qtdCaracteres;
        this.tpEntrada = tpEntrada;
    }

    public TextFormatter<String> getFormatter() {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.length() > qtdCaracteres) {
                return null;
            }

            String insertedText = change.getText();

            switch (tpEntrada) {
                case NUMERODECIMAL:
                    if (!insertedText.matches("[0-9,.]*")) return null;
                    break;

                case NOME:
                    if (!insertedText.matches("[\\p{IsLatin} ]*")) return null;
                    break;

                case EMAIL:
                    if (!insertedText.matches("[\\p{IsLatin}@.\\-_0-9]*")) return null;
                    break;

                case DATA:
                    String oldText = change.getControlText();
                    String newRawText = new StringBuilder(oldText)
                        .replace(change.getRangeStart(), change.getRangeEnd(), change.getText())
                        .toString()
                        .replaceAll("[^0-9]", "");

                    if (newRawText.length() > 8) return null;

                    StringBuilder formatted = new StringBuilder();
                    int length = newRawText.length();

                    if (length > 0) formatted.append(newRawText.substring(0, Math.min(2, length)));
                    if (length > 2) formatted.append("/").append(newRawText.substring(2, Math.min(4, length)));
                    if (length > 4) formatted.append("/").append(newRawText.substring(4, Math.min(8, length)));

                    change.setText(formatted.toString());
                    change.setRange(0, oldText.length());

                    int caretPos = formatted.length();
                    change.setCaretPosition(caretPos);
                    change.setAnchor(caretPos);
                    break;

                case CPF:
                    String oldCPF = change.getControlText();
                    String newCPFNumbers = new StringBuilder(oldCPF)
                        .replace(change.getRangeStart(), change.getRangeEnd(), change.getText())
                        .toString()
                        .replaceAll("[^0-9]", "");

                    if (newCPFNumbers.length() > 11) return null;

                    StringBuilder cpfFormatted = new StringBuilder();
                    int len = newCPFNumbers.length();

                    if (len > 0) cpfFormatted.append(newCPFNumbers.substring(0, Math.min(3, len)));
                    if (len > 3) cpfFormatted.append(".").append(newCPFNumbers.substring(3, Math.min(6, len)));
                    if (len > 6) cpfFormatted.append(".").append(newCPFNumbers.substring(6, Math.min(9, len)));
                    if (len > 9) cpfFormatted.append("-").append(newCPFNumbers.substring(9, Math.min(11, len)));

                    change.setText(cpfFormatted.toString());
                    change.setRange(0, oldCPF.length());

                    int cpfCaret = cpfFormatted.length();
                    change.setCaretPosition(cpfCaret);
                    change.setAnchor(cpfCaret);
                    break;

                case FONE: {
    String foneOldText = change.getControlText();
    String foneInsertedText = change.getText();
    int start = change.getRangeStart();
    int end = change.getRangeEnd();

    StringBuilder updatedRaw = new StringBuilder(foneOldText)
        .replace(start, end, foneInsertedText);

    // Remove todos os caracteres não numéricos
    String digits = updatedRaw.toString().replaceAll("[^0-9]", "");

    if (digits.length() > 11) return null; // Limita para 11 dígitos (2 DDD + 9 número)

    StringBuilder foneFormatted = new StringBuilder();
    int foneLen = digits.length();

    if (foneLen > 0) {
        if (foneLen <= 2) {
            foneFormatted.append(digits); // Apenas DDD
        } else {
            foneFormatted.append(digits.substring(0, 2)).append(" "); // DDD + espaço
            if (foneLen <= 7) {
                foneFormatted.append(digits.substring(2)); // Número sem hífen ainda
            } else {
                // Se tem mais que 7 dígitos (total até 11), formata como 00000-0000
                int middle = Math.min(7, foneLen); // Evita exceções
                foneFormatted.append(digits.substring(2, 7)).append("-");
                foneFormatted.append(digits.substring(7, foneLen));
            }
        }
    }

    change.setText(foneFormatted.toString());
    change.setRange(0, foneOldText.length());

    // Posiciona o cursor no final
    int caret = foneFormatted.length();
    change.setCaretPosition(caret);
    change.setAnchor(caret);
    break;
}



            }

            return change;
        });
    }
    
    public static class VerificarData {

    // Método para validar se a data é válida
    public static boolean validarData(String data) {
        if (data.length() != 10) return false;

        try {
            String day = data.substring(0, 2);
            String month = data.substring(3, 5);
            String year = data.substring(6, 10);

            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);

            // Verifica se o mês é válido (1-12)
            if (m < 1 || m > 12) {
                return false;
            }

            // Verifica se o dia é válido para o mês
            if (d < 1 || d > 31) {
                return false;
            }

            // Verificação de dias para cada mês
            if (m == 4 || m == 6 || m == 9 || m == 11) {
                if (d > 30) {
                    return false;
                }
            } else if (m == 2) {
                // Verificação para fevereiro (ano bissexto)
                if ((y % 4 == 0 && (y % 100 != 0 || y % 400 == 0)) && d > 29) {
                    return false;
                } else if (d > 28) {
                    return false;
                }
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}

    public void applyToTextInputControl(TextInputControl textInputControl) {
        textInputControl.setTextFormatter(getFormatter());
    }
}