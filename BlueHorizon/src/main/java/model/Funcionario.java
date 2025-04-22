package model;

import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario {
    
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty salario = new SimpleStringProperty();
    private final StringProperty nome = new SimpleStringProperty();
    private final StringProperty telefone = new SimpleStringProperty();
    private final StringProperty endereco = new SimpleStringProperty();
    private final StringProperty cpf = new SimpleStringProperty();
    private final StringProperty cargo = new SimpleStringProperty();
    private final ObjectProperty <LocalDate> dataContratacao = new SimpleObjectProperty<>();
    private final ObjectProperty <LocalDate> dataNascimento = new SimpleObjectProperty<>();
    private final StringProperty senha = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    //Método construtor sem os parâmetros
    public Funcionario(int id, String salario, String nome, String telefone, String endereco, String cpf, String cargo, LocalDate dataContratacao, LocalDate dataNascimento, String senha, String email) {
        
        this.id.set(id);
        this.salario.set(salario);
        this.nome.set(nome);
        this.telefone.set(telefone);
        this.endereco.set(endereco);
        this.cpf.set(cpf);
        this.cargo.set(cargo);
        this.dataContratacao.set(dataContratacao);
        this.dataNascimento.set(dataNascimento);
        this.senha.set(senha);
        this.email.set(email);
        
    }
    
     public Funcionario(String salario, String nome, String telefone, String endereco, String cpf, String cargo, LocalDate dataContratacao, LocalDate dataNascimento, String senha, String email) {
        
        this.salario.set(salario);
        this.nome.set(nome);
        this.telefone.set(telefone);
        this.endereco.set(endereco);
        this.cpf.set(cpf);
        this.cargo.set(cargo);
        this.dataContratacao.set(dataContratacao);
        this.dataNascimento.set(dataNascimento);
        this.senha.set(senha);
        this.email.set(email);
        
    }

    public Funcionario() {
    }
    
     
            public IntegerProperty idProperty() {
               return id;
           }

           public int getId() {
               return id.get();
           }

           public void setId(int id) {
               this.id.set(id);
           }
           
           public StringProperty salarioProperty(){
               return salario;
           }
           
           public String getSalario(){
               return salario.get();
           }
           
           public void setSalario(String salario){
               this.salario.set(salario);
           }
           
           public StringProperty nomeProperty(){
               return nome;
           }
           
           public String getNome(){
               return nome.get();
           }
           
           public void setNome(String nome){
               this.nome.set(nome);
           }
           
           public StringProperty telefoneProperty(){
               return telefone;
           }
           
           public String getTelefone(){
               return telefone.get();
           }
           
           public void setTelefone(String telefone){
               this.telefone.set(telefone);
           }
           
           public StringProperty enderecoProperty(){
               return endereco;
           }
           
           public String getEndereco(){
               return endereco.get();
           }
           
           public void setEndereco(String endereco){
               this.endereco.set(endereco);
           }
           
           public StringProperty cpfProperty(){
               return cpf;
           }
           
           public String getCpf(){
               return cpf.get();
           }
           
           public void setCpf(String cpf){
               this.cpf.set(cpf);
           }
           
           public StringProperty CargoProperty(){
               return cargo;
           }
           
           public String getCargo(){
               return cargo.get();
           }
           
           public void setCargo(String cargo){
               this.cargo.set(cargo);
           }
           
           public ObjectProperty<LocalDate> dataContratacaoProperty() {
           return dataContratacao;
           }

            public LocalDate getDataContratacao() {
                return dataContratacao.get();
            }

            public void setDataContratacao(LocalDate dataContratacao) {
                this.dataContratacao.set(dataContratacao);
            }

            public ObjectProperty<LocalDate> dataNascimentoProperty() {
                return dataNascimento;
            }

            public LocalDate getDataNascimento() {
                return dataNascimento.get();
            }

            public void setDataNascimento(LocalDate dataNascimento) {
                this.dataNascimento.set(dataNascimento);
            }

            public StringProperty senhaProperty() {
                return senha;
            }

            public String getSenha() {
                return senha.get();
            }

            public void setSenha(String senha) {
                this.senha.set(senha);
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

