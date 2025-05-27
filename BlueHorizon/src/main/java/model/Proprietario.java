package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proprietario {
    
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    
    //Método construtor sem os parâmetros
    public Proprietario() {
        
    }
    
    //Método construtor com o id
    public Proprietario(int id, String telefone, String nome, String email) {
        this.id.set(id);
        this.telefone.set(telefone);
        this.nome.set(nome);
        this.email.set(telefone);
    }
    
    //Método construtor sem o id
    public Proprietario(String telefone, String nome, String email) {
        this.telefone.set(telefone);
        this.nome.set(nome);
        this.email.set(telefone);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    
    public int getId(){
        return id.get();
    }
    
    public void setId(int id){
        this.id.set(id);
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }
    
     public String getTelefone(){
        return telefone.get();
    }
    
    public void setTelefone(String telefone){
        this.telefone.set(telefone);
    }
  
    public StringProperty nomeProperty() {
        return nome;
    }
    
     public String getNome(){
        return nome.get();
    }
    
    public void setNome(String nome){
        this.nome.set(nome);
    }

    public StringProperty emailProperty() {
        return email;
    }
    
     public String getEmail(){
        return email.get();
    }
    
    public void setEmail(String email){
        this.email.set(email);
    }
   
}
