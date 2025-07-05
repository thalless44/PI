package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Cidade;
import model.CidadeDAO;
import static model.CidadeDAO.buscarCidades;
import model.Propriedades;
import model.PropriedadesDAO;
import model.Proprietario;
import model.ProprietarioDAO;
import util.AlertaUtil;
import util.LimitarCaracter;

public class TelaAlterarDadosImovelController {

    private Stage stage;
    private Propriedades propriedadeSelecionada;

    @FXML
    private ImageView imageViewImovel;

    @FXML
    private Button btnAdicionarImagem;

    @FXML
    private Button btnEditarImovel;
    
    @FXML
    private ContextMenu cTextMenuCidade;

    @FXML
    private Button btnCancelarEdicaoImovel;

    @FXML
    private ComboBox<Proprietario> cmbxProprietario;

    @FXML
    private RadioButton rbDisponivel;

    @FXML
    private RadioButton rbNaoDisponivel;

    @FXML
    private RadioButton rbSimJardim;

    @FXML
    private RadioButton rbNaoJardim;

    @FXML
    private RadioButton rbSimMobiliada;

    @FXML
    private RadioButton rbNaoMobiliada;

    @FXML
    private RadioButton rbSimPiscina;

    @FXML
    private RadioButton rbNaoPiscina;

    @FXML
    private RadioButton rbSimSG;

    @FXML
    private RadioButton rbNaoSG;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtBanheiros;

    @FXML
    private TextField txtCidades;

    @FXML
    private TextField txtDatacadastro;

    @FXML
    private TextField txtNumeracaoImovel;

    @FXML
    private TextField txtQuartos;

    @FXML
    private TextField txtRua;

    @FXML
    private TextField txtTipoPropriedade;

    @FXML
    private TextField txtVagaGaragem;

    @FXML
    private TextField txtValor;

    private ToggleGroup grupoJardim;
    private ToggleGroup grupoMobiliada;
    private ToggleGroup grupoPiscina;
    private ToggleGroup grupoSG;
    private ToggleGroup grupoDisponibilidade;

    @FXML
    void initialize() {
        grupoJardim = new ToggleGroup();
        grupoMobiliada = new ToggleGroup();
        grupoPiscina = new ToggleGroup();
        grupoSG = new ToggleGroup();
        grupoDisponibilidade = new ToggleGroup();

        rbSimJardim.setToggleGroup(grupoJardim);
        rbNaoJardim.setToggleGroup(grupoJardim);

        rbSimMobiliada.setToggleGroup(grupoMobiliada);
        rbNaoMobiliada.setToggleGroup(grupoMobiliada);

        rbSimPiscina.setToggleGroup(grupoPiscina);
        rbNaoPiscina.setToggleGroup(grupoPiscina);

        rbSimSG.setToggleGroup(grupoSG);
        rbNaoSG.setToggleGroup(grupoSG);

        rbDisponivel.setToggleGroup(grupoDisponibilidade);
        rbNaoDisponivel.setToggleGroup(grupoDisponibilidade);
        
        new LimitarCaracter(10, LimitarCaracter.TipoEntrada.DATA).applyToTextInputControl(txtDatacadastro);
        new LimitarCaracter(20, LimitarCaracter.TipoEntrada.VALOR).applyToTextInputControl(txtValor);
        carregarProprietarios();
        
        txtCidades.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() >= 2) {
                List<String> sugestoes = buscarCidades(newText);
                mostrarCidade(sugestoes);
            } else {
                cTextMenuCidade.hide();
            }
        });
    }

    private void carregarProprietarios() {
        cmbxProprietario.getItems().clear();
        cmbxProprietario.getItems().addAll(ProprietarioDAO.listarTodosProprietarios());
        
        
    }

    public void carregarDadosImovel(Propriedades propriedade) {
        this.propriedadeSelecionada = propriedade;

        txtTipoPropriedade.setText(propriedade.getTipoPropriedade());
        txtRua.setText(propriedade.getRua());
        txtCidades.setText(propriedade.getCidade());
        txtValor.setText(String.format("%.2f", propriedade.getPreco()));
        txtDatacadastro.setText(propriedade.getDataCadastro() != null ? propriedade.getDataCadastro().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "");
        txtQuartos.setText(String.valueOf(propriedade.getQuartos()));
        txtBanheiros.setText(String.valueOf(propriedade.getBanheiros()));
        txtVagaGaragem.setText(String.valueOf(propriedade.getVagasGaragem()));
        txtNumeracaoImovel.setText(String.valueOf(propriedade.getNumeroCasa()));
        txtArea.setText(propriedade.getArea());
        
        if (propriedade.getImagem() != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(propriedade.getImagem());
            imageViewImovel.setImage(new Image(bis));
        }

        // Definir radio buttons conforme valores booleanos
        rbDisponivel.setSelected(propriedade.isDisponibilidade());
        rbNaoDisponivel.setSelected(!propriedade.isDisponibilidade());

        rbSimJardim.setSelected(propriedade.isJardim());
        rbNaoJardim.setSelected(!propriedade.isJardim());

        rbSimMobiliada.setSelected(propriedade.isMobilia());
        rbNaoMobiliada.setSelected(!propriedade.isMobilia());

        rbSimPiscina.setSelected(propriedade.isPiscina());
        rbNaoPiscina.setSelected(!propriedade.isPiscina());

        rbSimSG.setSelected(propriedade.isSistemaSeguranca());
        rbNaoSG.setSelected(!propriedade.isSistemaSeguranca());

        // Falta ajustar isso, pois não está recebendo os atributos de Proprietário.
        Proprietario proprietario = propriedade.getProprietario();
        if (proprietario != null) {
            cmbxProprietario.getSelectionModel().select(proprietario);
        } else {
            cmbxProprietario.getSelectionModel().clearSelection();
        }

        if (propriedade.getImagemUrl() != null && !propriedade.getImagemUrl().isEmpty()) {
            try {
                Image img = new Image(propriedade.getImagemUrl());
                imageViewImovel.setImage(img);
            } catch (Exception e) {
                // ignorar falha no carregamento da imagem
            }
        }
    }

    @FXML
    void OnClickAdicionarImagem(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString(), false);
            imageViewImovel.setImage(image);
            
            try {
            // Lê a imagem do arquivo e converte para byte[]
            byte[] imagemBytes = java.nio.file.Files.readAllBytes(file.toPath());
            propriedadeSelecionada.setImagem(imagemBytes); // SALVA OS BYTES NA PROPRIEDADE
            propriedadeSelecionada.setImagemUrl(file.toURI().toString()); // Opcional: salva o caminho da imagem
            } catch (IOException e) {
            AlertaUtil.mostrarErro("Erro", "Erro ao carregar imagem", e.getMessage());
        }

            // Você pode guardar o caminho da imagem em um atributo da propriedade (exemplo)
            // propriedadeSelecionada.setImagemUrl(file.toURI().toString());
        }
    }

    @FXML
    void onClickEditarImovel(ActionEvent event) {
        
            
            Proprietario proprietario = cmbxProprietario.getSelectionModel().getSelectedItem();
            
            
            if (propriedadeSelecionada == null) {
                AlertaUtil.mostrarErro("Erro", "Nenhum imóvel selecionado", "Não há imóvel para editar.");
                return;
            }

            
            if (proprietario == null) {
                proprietario = propriedadeSelecionada.getProprietario();
            }

            // Capturar dados
            String valorFormatado = txtValor.getText().replaceAll("[^\\d]","");
            double preco = Double.parseDouble(valorFormatado)/100.0;
            String tipoPropriedade = txtTipoPropriedade.getText();
            String cidade = txtCidades.getText();
            boolean disponibilidade = rbDisponivel.isSelected();

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataCadastroLD = LocalDate.parse(txtDatacadastro.getText(), formato);
            Date dataCadastro = Date.valueOf(dataCadastroLD);

            String rua = txtRua.getText();
            int quartos = Integer.parseInt(txtQuartos.getText());
            int banheiros = Integer.parseInt(txtBanheiros.getText());
            int vagasGaragem = Integer.parseInt(txtVagaGaragem.getText());
            boolean mobiliada = rbSimMobiliada.isSelected();
            boolean jardim = rbSimJardim.isSelected();
            boolean sistemaSeguranca = rbSimSG.isSelected();
            boolean piscina = rbSimPiscina.isSelected();
            int numeroCasa = Integer.parseInt(txtNumeracaoImovel.getText());
            String area = txtArea.getText();
            
            int idCidade = CidadeDAO.buscarIdCidadePorNome(cidade);
            if (idCidade == -1) {
            
                AlertaUtil.mostrarErro("Erro", "Cidade inválida!", "A cidade informada não foi encontrada no banco de dados");
            return;
            }
            
            
            System.out.println("ID Proprietario: " + propriedadeSelecionada.getProprietario().getId());

            // Atualizar objeto
            propriedadeSelecionada.setProprietario(proprietario);
            propriedadeSelecionada.setTipoPropriedade(tipoPropriedade);
            propriedadeSelecionada.setIdCidade(idCidade);
            propriedadeSelecionada.setPreco(preco);
            propriedadeSelecionada.setDisponibilidade(disponibilidade);
            propriedadeSelecionada.setDataCadastro(dataCadastro);
            propriedadeSelecionada.setRua(rua);
            propriedadeSelecionada.setQuartos(quartos);
            propriedadeSelecionada.setBanheiros(banheiros);
            propriedadeSelecionada.setVagasGaragem(vagasGaragem);
            propriedadeSelecionada.setMobilia(mobiliada);
            propriedadeSelecionada.setJardim(jardim);
            propriedadeSelecionada.setSistemaSeguranca(sistemaSeguranca);
            propriedadeSelecionada.setPiscina(piscina);
            propriedadeSelecionada.setNumeroCasa(numeroCasa);
            propriedadeSelecionada.setArea(area);

             //propriedadeSelecionada.setImagemUrl(...)
            
            

            boolean atualizado = PropriedadesDAO.atualizarPropriedade(propriedadeSelecionada);

            if (atualizado) {
                AlertaUtil.mostrarInformacao("Sucesso", "Alterar dados do imóvel", "Imóvel atualizado com sucesso!");
                fecharTela();
            } else {
                AlertaUtil.mostrarErro("Erro", "Falha ao atualizar o imóvel", "Tente novamente.");
            }


    }

    @FXML
    void onClickCancelarEdicaoImovel(ActionEvent event) {
        if (confirmarCancelamento()) {
            fecharTela();
        } else {
            event.consume();
        }
    }

    private boolean confirmarCancelamento() {
        return AlertaUtil.mostrarConfirmacao(
            "Confirmação", 
            "Deseja realmente cancelar a edição?",
            "As alterações não salvas serão perdidas."
        ).filter(resp -> resp == javafx.scene.control.ButtonType.OK).isPresent();
    }

    private void fecharTela() {
        if (stage == null) {
            stage = (Stage) btnCancelarEdicaoImovel.getScene().getWindow();
        }
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void mostrarCidade(List<String> sugestoes) {
        cTextMenuCidade.getItems().clear();

        for (String sugestao : sugestoes) {
            MenuItem item = new MenuItem(sugestao);
            item.setOnAction(e -> {
                txtCidades.setText(sugestao);
                cTextMenuCidade.hide();
            });
            cTextMenuCidade.getItems().add(item);
        }

        if (!sugestoes.isEmpty()) {
            cTextMenuCidade.show(txtCidades, Side.BOTTOM, 0, 0);
        } else {
            cTextMenuCidade.hide();
        }
    }

    
}

