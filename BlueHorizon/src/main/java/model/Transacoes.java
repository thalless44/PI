package model;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transacoes {
     
    private final IntegerProperty idTransacao = new SimpleIntegerProperty();
    private final StringProperty formaPagamento = new SimpleStringProperty();
    private final ObjectProperty <LocalDate> dataTransacao = new SimpleObjectProperty<>();
    private final DoubleProperty valor = new SimpleDoubleProperty();
    public Transacoes() {
    }
    
    public Transacoes(int idTransacao, String formaPagamento, LocalDate dataTransacao, double valor) {
        this.idTransacao.set(idTransacao);
        this.formaPagamento.set(formaPagamento);
        this.dataTransacao.set(dataTransacao);
        this.valor.set(valor);
    }
    
     public Transacoes(String formaPagamento, LocalDate dataTransacao, double valor) {
        this.formaPagamento.set(formaPagamento);
        this.dataTransacao.set(dataTransacao);
        this.valor.set(valor);
    }
     
     //getters & setters
     
    public int getIdTransacao() {
    return idTransacao.get();
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao.set(idTransacao);
    }

    public IntegerProperty idTransacaoProperty() {
        return idTransacao;
    }

    public String getFormaPagamento() {
        return formaPagamento.get();
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento.set(formaPagamento);
    }

    public StringProperty formaPagamentoProperty() {
        return formaPagamento;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao.get();
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao.set(dataTransacao);
    }

    public ObjectProperty<LocalDate> dataTransacaoProperty() {
        return dataTransacao;
    }

    public double getValor() {
        return valor.get();
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public DoubleProperty valorProperty() {
        return valor;
    }
  
}
