package model;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;

public class LimitarCaracter {

    public enum TipoEntrada {
        NUMEROINTEIRO, NUMERODECIMAL, NOME, EMAIL, DATA;
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

            // Limita a quantidade de caracteres
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
                    if (!insertedText.matches("[0-9/]*")) return null;
                    break;
            }

            return change;
        });
    }

    public void applyToTextInputControl(TextInputControl textInputControl) {
        textInputControl.setTextFormatter(getFormatter());
    }
}
