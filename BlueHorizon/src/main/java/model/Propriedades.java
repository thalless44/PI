package model;

import java.sql.Date;
import javafx.beans.property.*;

public class Propriedades {

    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty tipoPropriedade = new SimpleStringProperty();
    private final StringProperty endereco = new SimpleStringProperty();
    private final DoubleProperty preco = new SimpleDoubleProperty();
    private final BooleanProperty disponibilidade = new SimpleBooleanProperty();
    private final ObjectProperty<Date> dataCadastro = new SimpleObjectProperty<>();
    private final StringProperty rua = new SimpleStringProperty();
    private final IntegerProperty quartos = new SimpleIntegerProperty();
    private final IntegerProperty banheiros = new SimpleIntegerProperty();
    private final IntegerProperty vagasGaragem = new SimpleIntegerProperty();
    private final BooleanProperty mobilia = new SimpleBooleanProperty();
    private final BooleanProperty jardim = new SimpleBooleanProperty();
    private final BooleanProperty sistemaSeguranca = new SimpleBooleanProperty();
    private final BooleanProperty piscina = new SimpleBooleanProperty();
    private final IntegerProperty numeroCasa = new SimpleIntegerProperty();
    private final StringProperty area = new SimpleStringProperty();
    private final IntegerProperty idCidade = new SimpleIntegerProperty();
    private final IntegerProperty idProprietario = new SimpleIntegerProperty();
    private final IntegerProperty idImagem = new SimpleIntegerProperty(); // Adicionado

    private final StringProperty nomeProprietario = new SimpleStringProperty();
    private final ObjectProperty<byte[]> imagem = new SimpleObjectProperty<>();

    private String cidade;
    private String codigoImovel;

    private Proprietario proprietario;
    private String imagemUrl;

    // Construtor com ID e idCidade
    public Propriedades(int id, String tipoPropriedade, String endereco, double preco, boolean disponibilidade, Date dataCadastro, String rua,
            int quartos, int banheiros, int vagasGaragem, boolean mobilia, boolean jardim, boolean sistemaSeguranca, boolean piscina, int numeroCasa,
            String area, int idCidade, int idProprietario, int idImagem) {

        this.id.set(id);
        this.tipoPropriedade.set(tipoPropriedade);
        this.endereco.set(endereco);
        this.preco.set(preco);
        this.disponibilidade.set(disponibilidade);
        this.dataCadastro.set(dataCadastro);
        this.rua.set(rua);
        this.quartos.set(quartos);
        this.banheiros.set(banheiros);
        this.vagasGaragem.set(vagasGaragem);
        this.mobilia.set(mobilia);
        this.jardim.set(jardim);
        this.sistemaSeguranca.set(sistemaSeguranca);
        this.piscina.set(piscina);
        this.numeroCasa.set(numeroCasa);
        this.area.set(area);
        this.idCidade.set(idCidade);
        this.idProprietario.set(idProprietario);
        this.idImagem.set(idImagem); // Adicionado
    }

    // Construtor sem ID, com idCidade
    public Propriedades(String tipoPropriedade, String endereco, double preco, boolean disponibilidade, Date dataCadastro, String rua,
            int quartos, int banheiros, int vagasGaragem, boolean mobilia, boolean jardim, boolean sistemaSeguranca, boolean piscina,
            int numeroCasa, String area) {

        this.tipoPropriedade.set(tipoPropriedade);
        this.endereco.set(endereco);
        this.preco.set(preco);
        this.disponibilidade.set(disponibilidade);
        this.dataCadastro.set(dataCadastro);
        this.rua.set(rua);
        this.quartos.set(quartos);
        this.banheiros.set(banheiros);
        this.vagasGaragem.set(vagasGaragem);
        this.mobilia.set(mobilia);
        this.jardim.set(jardim);
        this.sistemaSeguranca.set(sistemaSeguranca);
        this.piscina.set(piscina);
        this.numeroCasa.set(numeroCasa);
        this.area.set(area);
    }

    public Propriedades() {
    }

    // Propriedades JavaFX
    public IntegerProperty idProperty() { return id; }
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }

    public StringProperty tipoPropriedadeProperty() { return tipoPropriedade; }
    public String getTipoPropriedade() { return tipoPropriedade.get(); }
    public void setTipoPropriedade(String tipoPropriedade) { this.tipoPropriedade.set(tipoPropriedade); }

    public StringProperty enderecoProperty() { return endereco; }
    public String getEndereco() { return endereco.get(); }
    public void setEndereco(String endereco) { this.endereco.set(endereco); }

    public DoubleProperty precoProperty() { return preco; }
    public double getPreco() { return preco.get(); }
    public void setPreco(double preco) { this.preco.set(preco); }

    public BooleanProperty disponibilidadeProperty() { return disponibilidade; }
    public boolean isDisponibilidade() { return disponibilidade.get(); }
    public void setDisponibilidade(boolean disponibilidade) { this.disponibilidade.set(disponibilidade); }

    public ObjectProperty<Date> dataCadastroProperty() { return dataCadastro; }
    public Date getDataCadastro() { return dataCadastro.get(); }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro.set(dataCadastro); }

    public StringProperty ruaProperty() { return rua; }
    public String getRua() { return rua.get(); }
    public void setRua(String rua) { this.rua.set(rua); }

    public IntegerProperty quartosProperty() { return quartos; }
    public int getQuartos() { return quartos.get(); }
    public void setQuartos(int quartos) { this.quartos.set(quartos); }

    public IntegerProperty banheirosProperty() { return banheiros; }
    public int getBanheiros() { return banheiros.get(); }
    public void setBanheiros(int banheiros) { this.banheiros.set(banheiros); }

    public IntegerProperty vagasGaragemProperty() { return vagasGaragem; }
    public int getVagasGaragem() { return vagasGaragem.get(); }
    public void setVagasGaragem(int vagasGaragem) { this.vagasGaragem.set(vagasGaragem); }

    public BooleanProperty mobiliaProperty() { return mobilia; }
    public boolean isMobilia() { return mobilia.get(); }
    public void setMobilia(boolean mobilia) { this.mobilia.set(mobilia); }

    public BooleanProperty jardimProperty() { return jardim; }
    public boolean isJardim() { return jardim.get(); }
    public void setJardim(boolean jardim) { this.jardim.set(jardim); }

    public BooleanProperty sistemaSegurancaProperty() { return sistemaSeguranca; }
    public boolean isSistemaSeguranca() { return sistemaSeguranca.get(); }
    public void setSistemaSeguranca(boolean sistemaSeguranca) { this.sistemaSeguranca.set(sistemaSeguranca); }

    public BooleanProperty piscinaProperty() { return piscina; }
    public boolean isPiscina() { return piscina.get(); }
    public void setPiscina(boolean piscina) { this.piscina.set(piscina); }

    public IntegerProperty numeroCasaProperty() { return numeroCasa; }
    public int getNumeroCasa() { return numeroCasa.get(); }
    public void setNumeroCasa(int numeroCasa) { this.numeroCasa.set(numeroCasa); }

    public StringProperty areaProperty() { return area; }
    public String getArea() { return area.get(); }
    public void setArea(String area) { this.area.set(area); }

    public IntegerProperty idCidadeProperty() { return idCidade; }
    public int getIdCidade() { return idCidade.get(); }
    public void setIdCidade(int idCidade) { this.idCidade.set(idCidade); }

    public IntegerProperty idProprietarioProperty() { return idProprietario; }
    public int getIdProprietario() { return idProprietario.get(); }
    public void setIdProprietario(int idProprietario) { this.idProprietario.set(idProprietario); }

    public IntegerProperty idImagemProperty() { return idImagem; } // Adicionado
    public int getIdImagem() { return idImagem.get(); }            // Adicionado
    public void setIdImagem(int idImagem) { this.idImagem.set(idImagem); } // Adicionado

    public StringProperty nomeProprietarioProperty() { return nomeProprietario; }
    public String getNomeProprietario() { return nomeProprietario.get(); }
    public void setNomeProprietario(String nomeProprietario) { this.nomeProprietario.set(nomeProprietario); }

    public ObjectProperty<byte[]> imagemProperty() { return imagem; }
    public byte[] getImagem() { return imagem.get(); }
    public void setImagem(byte[] imagem) { this.imagem.set(imagem); }

    // Campos auxiliares
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCodigoImovel() { return codigoImovel; }
    public void setCodigoImovel(String codigoImovel) { this.codigoImovel = codigoImovel; }

    public Proprietario getProprietario() { return proprietario; }
    public void setProprietario(Proprietario proprietario) { this.proprietario = proprietario; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
}
