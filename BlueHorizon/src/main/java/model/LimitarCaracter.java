package model;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputControl;

public class LimitarCaracter {

    public enum TipoEntrada {
        NUMEROINTEIRO, NUMERODECIMAL, NOME, EMAIL, DATA;
    };

    private int qtdCaracteres;
    private TipoEntrada tpEntrada;

    public LimitarCaracter(int qtdCaracteres, TipoEntrada tpEntrada) {
        this.qtdCaracteres = qtdCaracteres;
        this.tpEntrada = tpEntrada;
    }

    public TextFormatter<String> getFormatter() {
        // Define um filtro de caracteres com base no tipo de entrada
        TextFormatter.Change change = new TextFormatter.Change() {
            @Override
            public void commit() {
                String newText = getText();
                // Limita a quantidade de caracteres
                if (newText.length() > qtdCaracteres) {
                    newText = newText.substring(0, qtdCaracteres);
                }
                setText(newText);
            }
        };

        TextFormatter<String> formatter;
        formatter = new TextFormatter<>(change);
        switch (tpEntrada) {
            case NUMEROINTEIRO:
                formatter.setFilter(c -> c.getText().matches("[^0-9]") ? null : c);
                break;
            case NUMERODECIMAL:
                formatter.setFilter(c -> c.getText().matches("[^0-9,.]") ? null : c);
                break;
            case NOME:
                formatter.setFilter(c -> c.getText().matches("[^\\p{IsLatin} ]") ? null : c);
                break;
            case EMAIL:
                formatter.setFilter(c -> c.getText().matches("[^\\p{IsLatin}@.\\-_][^0-9]") ? null : c);
                break;
            case DATA:
                formatter.setFilter(c -> c.getText().matches("[^0-9/]") ? null : c);
                break;
        }
        return formatter;
    }

    public void applyToTextInputControl(TextInputControl textInputControl) {
        // Aplica o TextFormatter ao TextInputControl
        textInputControl.setTextFormatter(getFormatter());
    }
}
