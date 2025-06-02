package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proprietario {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    // Construtor com parâmetros
    public Proprietario(int id, String nome, String telefone, String email) {
        this.id.set(id);
        this.nome.set(nome);
        this.telefone.set(telefone);
        this.email.set(email);
    }

    // Construtor sem ID
    public Proprietario(String nome, String telefone, String email) {
        this.nome.set(nome);
        this.telefone.set(telefone);
        this.email.set(email);
    }

    // Construtor vazio
    public Proprietario() {
    }

    // Getters e Setters para as propriedades
    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
