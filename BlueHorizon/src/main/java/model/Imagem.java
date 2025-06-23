package model;

import javafx.beans.property.*;

public class Imagem {

    private final IntegerProperty idImagem = new SimpleIntegerProperty();
    private final StringProperty url = new SimpleStringProperty();
    private final ObjectProperty<byte[]> dados = new SimpleObjectProperty<>();

    // Construtor completo
    public Imagem(int idImagem, String url, byte[] dados) {
        this.idImagem.set(idImagem);
        this.url.set(url);
        this.dados.set(dados);
    }

    // Construtor sem ID (para inserção)
    public Imagem(String url, byte[] dados) {
        this.url.set(url);
        this.dados.set(dados);
    }

    // Construtor vazio
    public Imagem() {
    }

    // idImagem
    public int getIdImagem() {
        return idImagem.get();
    }

    public void setIdImagem(int idImagem) {
        this.idImagem.set(idImagem);
    }

    public IntegerProperty idImagemProperty() {
        return idImagem;
    }

    // url
    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public StringProperty urlProperty() {
        return url;
    }

    // dados
    public byte[] getDados() {
        return dados.get();
    }

    public void setDados(byte[] dados) {
        this.dados.set(dados);
    }

    public ObjectProperty<byte[]> dadosProperty() {
        return dados;
    }
}
