package model;

import java.sql.Date;
import javafx.beans.property.*;

public class Propriedades {

    private final StringProperty tipoPropriedade = new SimpleStringProperty();
    private final StringProperty endereco = new SimpleStringProperty();
    private final DoubleProperty preco = new SimpleDoubleProperty();
    private final BooleanProperty disponibilidade = new SimpleBooleanProperty();
    private final ObjectProperty<Date> dataCadastro = new SimpleObjectProperty<>();
    private final StringProperty rua = new SimpleStringProperty();

    public Propriedades(String tipoPropriedade, String endereco, double preco, boolean disponibilidade, Date dataCadastro, String rua) {
        this.tipoPropriedade.set(tipoPropriedade);
        this.endereco.set(endereco);
        this.preco.set(preco);
        this.disponibilidade.set(disponibilidade);
        this.dataCadastro.set(dataCadastro);
        this.rua.set(rua);
    }

    public Propriedades() {
    }

    // Getters e Setters

    public StringProperty tipoPropriedadeProperty() {
        return tipoPropriedade;
    }

    public String getTipoPropriedade() {
        return tipoPropriedade.get();
    }

    public void setTipoPropriedade(String tipoPropriedade) {
        this.tipoPropriedade.set(tipoPropriedade);
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }

    public String getEndereco() {
        return endereco.get();
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public DoubleProperty precoProperty() {
        return preco;
    }

    public double getPreco() {
        return preco.get();
    }

    public void setPreco(double preco) {
        this.preco.set(preco);
    }

    public BooleanProperty disponibilidadeProperty() {
        return disponibilidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade.get();
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade.set(disponibilidade);
    }

    public ObjectProperty<Date> dataCadastroProperty() {
        return dataCadastro;
    }

    public Date getDataCadastro() {
        return dataCadastro.get();
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro.set(dataCadastro);
    }

    public StringProperty ruaProperty() {
        return rua;
    }

    public String getRua() {
        return rua.get();
    }

    public void setRua(String rua) {
        this.rua.set(rua);
    }
}
