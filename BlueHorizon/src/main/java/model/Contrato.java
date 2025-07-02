package model;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Contrato {

    private final IntegerProperty idContrato = new SimpleIntegerProperty();
    private final ObjectProperty<LocalDate> dataContrato = new SimpleObjectProperty<>();
    private final DoubleProperty valorCompra = new SimpleDoubleProperty();
    private final IntegerProperty idImovel = new SimpleIntegerProperty();  
    private final IntegerProperty idCliente = new SimpleIntegerProperty(); 
    
    private Propriedades propriedade;
    private Cliente cliente;

    public Contrato() {}

    public Contrato(int idContrato, LocalDate dataContrato, double valorCompra, int idImovel, int idCliente) {
        this.idContrato.set(idContrato);
        this.dataContrato.set(dataContrato);
        this.valorCompra.set(valorCompra);
        this.idImovel.set(idImovel);
        this.idCliente.set(idCliente);
    }

    // Getters e setters existentes
    public int getIdContrato() {
        return idContrato.get();
    }

    public void setIdContrato(int idContrato) {
        this.idContrato.set(idContrato);
    }

    public IntegerProperty idContratoProperty() {
        return idContrato;
    }

    public LocalDate getDataContrato() {
        return dataContrato.get();
    }

    public void setDataContrato(LocalDate dataContrato) {
        this.dataContrato.set(dataContrato);
    }

    public ObjectProperty<LocalDate> dataContratoProperty() {
        return dataContrato;
    }

    public double getValorCompra() {
        return valorCompra.get();
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra.set(valorCompra);
    }

    public DoubleProperty valorCompraProperty() {
        return valorCompra;
    }

    public int getIdImovel() {
        return idImovel.get();
    }

    public void setIdImovel(int idImovel) {
        this.idImovel.set(idImovel);
    }

    public IntegerProperty idImovelProperty() {
        return idImovel;
    }

    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int idCliente) {
        this.idCliente.set(idCliente);
    }

    public IntegerProperty idClienteProperty() {
        return idCliente;
    }

    // Getters e setters para os objetos relacionados
    public Propriedades getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedades propriedade) {
        this.propriedade = propriedade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}