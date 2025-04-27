package model;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;

public class LimitarCaracter {

    public enum TipoEntrada {
        NUMEROINTEIRO, NUMERODECIMAL, NOME, EMAIL, DATA, CPF, FONE;
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
                case NUMEROINTEIRO:
                    if (!insertedText.matches("[0-9]*")) return null;
                    break;

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

                case FONE:
                    String oldTel = change.getControlText();
                    String newTelNumbers = new StringBuilder(oldTel)
                        .replace(change.getRangeStart(), change.getRangeEnd(), change.getText())
                        .toString()
                        .replaceAll("[^0-9]", "");

                    if (newTelNumbers.length() > 11) return null;

                    StringBuilder telFormatted = new StringBuilder();
                    int telLen = newTelNumbers.length();

                    if (telLen > 0) telFormatted.append("(").append(newTelNumbers.substring(0, Math.min(2, telLen)));
                    if (telLen >= 2) telFormatted.append(")");

                    if (telLen > 2 && telLen <= 7) {
                        telFormatted.append(" ").append(newTelNumbers.substring(2, telLen));
                    } else if (telLen > 7) {
                        telFormatted.append(" ").append(newTelNumbers.substring(2, 7));
                        telFormatted.append("-").append(newTelNumbers.substring(7, telLen));
                    }

                    change.setText(telFormatted.toString());
                    change.setRange(0, oldTel.length());

                    int telCaret = telFormatted.length();
                    change.setCaretPosition(telCaret);
                    change.setAnchor(telCaret);
                    break;
            }

            return change;
        });
    }

    public void applyToTextInputControl(TextInputControl textInputControl) {
        textInputControl.setTextFormatter(getFormatter());
    }
}
