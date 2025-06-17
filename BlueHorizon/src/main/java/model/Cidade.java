package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cidade {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();

    // Construtores
    public Cidade() {
    }

    public Cidade(int id, String nome) {
        this.id.set(id);
        this.nome.set(nome);
    }

    public Cidade(String nome) {
        this.nome.set(nome);
    }

    // id property
    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    // nome property
    public StringProperty nomeProperty() {
        return nome;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

   
    @Override
    public String toString() {
        return nome.get();
    }
}
