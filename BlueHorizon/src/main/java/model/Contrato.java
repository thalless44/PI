package model;

import javafx.beans.property.*;
import java.sql.Date;

public class Contrato {

    private final IntegerProperty idContrato = new SimpleIntegerProperty();
    private final ObjectProperty<Date> dataCadastro = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> dataContrato = new SimpleObjectProperty<>();
    private final IntegerProperty idCliente = new SimpleIntegerProperty();
    private final IntegerProperty idImovel = new SimpleIntegerProperty();
    private final DoubleProperty valorTotal = new SimpleDoubleProperty();

    public Contrato() {}

    public Contrato(int idContrato, Date dataCadastro, Date dataContrato, int idCliente, int idImovel, double valorTotal) {
        setIdContrato(idContrato);
        setDataCadastro(dataCadastro);
        setDataContrato(dataContrato);
        setIdCliente(idCliente);
        setIdImovel(idImovel);
        setValorTotal(valorTotal);
    }

    public IntegerProperty idContratoProperty() {
        return idContrato;
    }

    public int getIdContrato() {
        return idContrato.get();
    }

    public void setIdContrato(int idContrato) {
        this.idContrato.set(idContrato);
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

    public ObjectProperty<Date> dataContratoProperty() {
        return dataContrato;
    }

    public Date getDataContrato() {
        return dataContrato.get();
    }

    public void setDataContrato(Date dataContrato) {
        this.dataContrato.set(dataContrato);
    }

    public IntegerProperty idClienteProperty() {
        return idCliente;
    }

    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int idCliente) {
        this.idCliente.set(idCliente);
    }

    public IntegerProperty idImovelProperty() {
        return idImovel;
    }

    public int getIdImovel() {
        return idImovel.get();
    }

    public void setIdImovel(int idImovel) {
        this.idImovel.set(idImovel);
    }

    public DoubleProperty valorTotalProperty() {
        return valorTotal;
    }

    public double getValorTotal() {
        return valorTotal.get();
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal.set(valorTotal);
    }
}
