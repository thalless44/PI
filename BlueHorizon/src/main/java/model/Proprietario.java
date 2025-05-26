package model;

import javafx.beans.property.*;

public class Proprietario {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    public Proprietario() {
    }

    public Proprietario(int id, String nome, String telefone, String email) {
        this.id.set(id);
        this.nome.set(nome);
        this.telefone.set(telefone);
        this.email.set(email);
    }

    public Proprietario(String nome, String telefone, String email) {
        this.nome.set(nome);
        this.telefone.set(telefone);
        this.email.set(email);
    }

    // id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // nome
    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    // telefone
    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    // email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    @Override
    public String toString() {
        return nome.get(); // Útil para ComboBox e debugging
    }
}
