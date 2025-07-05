package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cidade;
import model.Funcionario;
import model.FuncionarioDAO;
import model.Propriedades;
import model.PropriedadesDAO;
import model.Proprietario;

public class TelaInicialController {
    private Stage stage;
    private Funcionario funcionario;
    
    
     @FXML
    private Button btnPesquisar;
     
    @FXML
    private TextField txtfdArea;

    @FXML
    private TextField txtfdBanheiros;

    @FXML
    private TextField txtfdCidade;

    @FXML
    private TextField txtfdCodImovel;

    @FXML
    private TextField txtfdJardim;

    @FXML
    private TextField txtfdMobiliada;

    @FXML
    private TextField txtfdNumeroCasa;

    @FXML
    private TextField txtfdPiscina;

    @FXML
    private TextField txtfdQuartos;

    @FXML
    private TextField txtfdRua;

    @FXML
    private TextField txtfdSistemaSeguranca;

    @FXML
    private TextField txtfdVagasGaragem;

    
    @FXML
    private MenuItem menuAcessarPerfil;
    
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox vboxConteudo;  
    
    
    @FXML
    private MenuItem btnUsuarioSistema;
    
    @FXML
    private MenuItem btnUsuarioSistema1;
    
    @FXML
    private Menu btnSair;

    @FXML
    private MenuItem menuCadastroCliente;

    @FXML
    private MenuItem menuCadastroFuncionario;

    @FXML
    private MenuItem menuCadastroImovol;
    
    @FXML
    private MenuItem menuCadastroProprietario;
    
    @FXML
    private MenuItem btnFecharApp;
    
    @FXML
    private MenuItem btnRelatorioVendaDosImoveis;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private MenuItem btnUsuarioSistemaProprietario;
    
    public void initialize() {
        carregarPropriedades();
   
}

  
    @FXML
    void OnActionFecharAplicacao(ActionEvent event) {
        
        if(SairTelaInicio()){        
            System.exit(0);       
        }else{       
            event.consume();        
        }
    }
 
    @FXML
    void onClickCliente(ActionEvent event) {
        try {
            URL url = new File("src/main/java/view/TelaCadastroCliente.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaCC = new Stage();
            TelaCadastroClienteController ti = loader.getController(); 
            ti.setStage(telaCC);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaCC.getIcons().add(icone);
            telaCC.setScene(scene);
            telaCC.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Cadastro de cliente");

            telaCC.setMaximized(true);
            telaCC.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
        
    }
    
      @FXML
    void OnClickAbrirUsuarioSistemaFuncionario(ActionEvent event) {

         try {
            
            URL url = new File("src/main/java/view/TelaUsuarioSistema.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaUS = new Stage();
            TelaUsuarioSistemaController ti = loader.getController(); 
            ti.setStage(telaUS);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaUS.getIcons().add(icone);
        
            telaUS.setScene(scene);
            telaUS.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - funcionários");

            telaUS.setMaximized(true);
            telaUS.show();
            } catch (IOException e) {
            e.printStackTrace();
            }        
    }
    
    @FXML
    void OnClickAbrirUsuarioSistemaCliente(ActionEvent event) {
        
        try {
            URL url = new File("src/main/java/view/TelaClientesSistema.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaCC = new Stage();
            TelaClientesSistemaController tcs = loader.getController(); 
            tcs.setStage(telaCC);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaCC.getIcons().add(icone);
        
            telaCC.setScene(scene);
            telaCC.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - Clientes");
            
            telaCC.setMaximized(true);
            telaCC.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
        
        

    }


    @FXML
    void onClickFuncionario(ActionEvent event) {
        
        try {
            URL url = new File("src/main/java/view/TelaCadastroFuncionario.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaCF = new Stage();
            TelaCadastroFuncionarioController ti = loader.getController(); 
            ti.setStage(telaCF);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaCF.getIcons().add(icone);
        
            telaCF.setScene(scene);
            telaCF.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Cadastro de funcionário");
            
            telaCF.setMaximized(true);
            telaCF.show();
            } catch (IOException e) {
            e.printStackTrace();
            }
    }

    @FXML
    void onClickImovel(ActionEvent event) {
          try {
            URL url = new File("src/main/java/view/TelaCadastroImovel.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Stage telaIP = new Stage();
            TelaCadastroImovelController imovelpi = loader.getController(); 
            imovelpi.setStage(telaIP);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaIP.getIcons().add(icone);
        
            telaIP.setScene(scene);
            telaIP.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar");

            telaIP.setMaximized(true);
            telaIP.show();
            } catch (IOException e) {
            e.printStackTrace();
            }

        

    }

    @FXML
    void onClickPerfil(ActionEvent event) {   
 
        try {
            
            FuncionarioDAO dao = new FuncionarioDAO();
            Funcionario funcionarioAtualizado = dao.autenticar(funcionario.getEmail(), funcionario.getSenha());
            
            URL url = new File("src/main/java/view/TelaInformacoesPerfil.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            TelaInformacoesPerfilController ti = loader.getController(); 
            
            ti.preencherInformacoesPerfil(funcionarioAtualizado);
            
            Stage telaIP = new Stage();
            ti.setStage(telaIP);
            Scene scene = new Scene(root);
            
            Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
            telaIP.getIcons().add(icone);
        
            telaIP.setScene(scene);
            telaIP.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Informações do perfil");

            telaIP.setMaximized(true);
            telaIP.show();
            } catch (IOException e) {
            e.printStackTrace();
            }

    }
    
    
     
    
     @FXML
    void OnActionBtnRelatorioVendaDosImoveis(ActionEvent event) {
        
        try {
       
        URL url = new File("src/main/java/View/TelaRelatorioVendas.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage telaRV = new Stage();
        TelaRelatorioVendasController tR = loader.getController(); 
        tR.setStage(telaRV);
        Scene scene = new Scene(root);
        
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaRV.getIcons().add(icone);
        
        telaRV.setScene(scene);
        telaRV.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Relatório de venda dos imóveis");
        telaRV.setMaximized(true);
        telaRV.show();
       
    } catch (IOException e) {
        e.printStackTrace();
    }

    }
    @FXML
    void OnClickAbrirUsuarioSistemaProprietario(ActionEvent event) {
        try {
    // Carregar o FXML da tela de Proprietário
    URL url = new File("src/main/java/view/TelaUsuarioSistemaProprietario.fxml").toURI().toURL();
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();

    // Criar a nova tela
    Stage telaPRE = new Stage();

    // Obter o controlador correto (TelaUsuarioSistemaProprietarioController)
    TelaUsuarioSistemaProprietarioController controller = loader.getController();

    // Passar a Stage para o controlador, se necessário
    controller.setStage(telaPRE);

    // Criar a cena e configurar o ícone
    Scene scene = new Scene(root);
    Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
    telaPRE.getIcons().add(icone);

    // Configurar e exibir a tela
    telaPRE.setScene(scene);
    telaPRE.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - Proprietarios");
    telaPRE.setMaximized(true);
    telaPRE.show();

} catch (IOException e) {
    e.printStackTrace();
}

        

    }
    
    @FXML
void OnClickPesquisaFiltrada(ActionEvent event) {
    String cidade = txtfdCidade.getText().trim();
    String rua = txtfdRua.getText().trim();
    String ID = txtfdCodImovel.getText().trim();
    String area = txtfdArea.getText().trim();
    String banheiros = txtfdBanheiros.getText().trim();
    String quartos = txtfdQuartos.getText().trim();
    String vagasGaragem = txtfdVagasGaragem.getText().trim();
    String jardim = txtfdJardim.getText().trim();
    String piscina = txtfdPiscina.getText().trim();
    String sistemaSeguranca = txtfdSistemaSeguranca.getText().trim();
    String mobiliada = txtfdMobiliada.getText().trim();
    String numeroCasa = txtfdNumeroCasa.getText().trim();

    List<Propriedades> imoveisFiltrados = PropriedadesDAO.listarPropriedades().stream()
        .filter(imovel -> {
            boolean matches = true;

            if (!cidade.isEmpty() && !imovel.getCidade().toLowerCase().contains(cidade.toLowerCase())) {
                matches = false;
            }
            if (!rua.isEmpty() && !imovel.getRua().toLowerCase().contains(rua.toLowerCase())) {
                matches = false;
            }
            if (!ID.isEmpty()) {
                String idImovel = String.valueOf(imovel.getId()).trim(); // <-- Correção aqui
                if (!idImovel.equalsIgnoreCase(ID)) {
                    matches = false;
                }
            }
            if (!area.isEmpty() && !String.valueOf(imovel.getArea()).contains(area)) {
                matches = false;
            }
            if (!banheiros.isEmpty() && !String.valueOf(imovel.getBanheiros()).contains(banheiros)) {
                matches = false;
            }
            if (!quartos.isEmpty() && !String.valueOf(imovel.getQuartos()).contains(quartos)) {
                matches = false;
            }
            if (!vagasGaragem.isEmpty() && !String.valueOf(imovel.getVagasGaragem()).contains(vagasGaragem)) {
                matches = false;
            }
            if (!jardim.isEmpty() && !String.valueOf(imovel.jardimProperty().getValue()).equalsIgnoreCase(jardim)) {
                matches = false;
            }
            if (!piscina.isEmpty() && !String.valueOf(imovel.piscinaProperty().getValue()).equalsIgnoreCase(piscina)) {
                matches = false;
            }
            if (!sistemaSeguranca.isEmpty() && !String.valueOf(imovel.sistemaSegurancaProperty().getValue()).equalsIgnoreCase(sistemaSeguranca)) {
                matches = false;
            }
            if (!mobiliada.isEmpty() && !String.valueOf(imovel.mobiliaProperty().getValue()).equalsIgnoreCase(mobiliada)) {
                matches = false;
            }
            if (!numeroCasa.isEmpty() && !String.valueOf(imovel.getNumeroCasa()).contains(numeroCasa)) {
                matches = false;
            }

            return matches;
        })
        .collect(Collectors.toList());

    vboxConteudo.getChildren().clear();
    if (imoveisFiltrados.isEmpty()) {
        Label vazio = new Label("Nenhuma propriedade encontrada.");
        vboxConteudo.getChildren().add(vazio);
    } else {
        for (Propriedades imovel : imoveisFiltrados) {
            vboxConteudo.getChildren().add(criarCard(imovel));
        }
    }
}



    //cadastrar Proprietario
 @FXML
void onClickProprietario(ActionEvent event) {
    try {
        // Carregar o FXML da tela de Proprietário
        URL url = new File("src/main/java/view/TelaCadastroProprietario.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        
        // Criar a nova tela
        Stage telaPRO = new Stage();

        // Obter o controlador correto (TelaProprietarioController)
        TelaCadastroProprietarioController controller = loader.getController();
        
        // Passar a Stage para o controlador, se necessário
        controller.setStage(telaPRO);

        // Criar a cena e configurar o ícone
        Scene scene = new Scene(root);
        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        telaPRO.getIcons().add(icone);

        // Configurar e exibir a tela
        telaPRO.setScene(scene);
        telaPRO.setTitle("BlueHorizon - Sistema de gerenciamento de propriedades beira-mar | Usuários do sistema - Proprietarios");
        telaPRO.setMaximized(true);
        telaPRO.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private boolean SairTelaInicio() {
        
        Alert FecharTelaInicio = new Alert(Alert.AlertType.WARNING);
        FecharTelaInicio.setTitle("Aviso");
        FecharTelaInicio.setHeaderText("Tem certeza que deseja fechar a aplicação?");
        FecharTelaInicio.setContentText("Todas as alterações não salvas serão perdidas");
        return FecharTelaInicio.showAndWait().filter(response
                -> response == ButtonType.OK).isPresent();
    }
    
    public void carregarPropriedades() {
        
        vboxConteudo.getChildren().clear();  // Limpa os cards antigos
        
        List<Propriedades> imoveis = PropriedadesDAO.listarPropriedades();

        if (imoveis.isEmpty()) {
            Label vazio = new Label("Nenhuma propriedade cadastrada.");
            vboxConteudo.getChildren().add(vazio);
            return;
        }

        for (Propriedades imovel : imoveis) {
            vboxConteudo.getChildren().add(criarCard(imovel));
        }
    }
    
    
   private VBox criarCard(Propriedades imovel) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    // Converte byte[] para imagem
    ByteArrayInputStream bis = new ByteArrayInputStream(imovel.getImagem());
    Image imagem = new Image(bis);

    // Configura o ImageView com proporção preservada
    ImageView imageView = new ImageView(imagem);
    imageView.setFitWidth(300);
    imageView.setFitHeight(200);
    imageView.setPreserveRatio(true);
    imageView.setSmooth(true);
    imageView.setCache(true);

    // Usa StackPane pra centralizar e limitar o tamanho do contêiner
    StackPane imgPane = new StackPane(imageView);
    imgPane.setPrefSize(300, 200);
    imgPane.setMaxSize(300, 200);
    imgPane.setStyle("-fx-background-color: #e0e0e0; -fx-border-radius: 8; -fx-background-radius: 8;");

    Proprietario proprietario = imovel.getProprietario();
    String nomeProprietario = proprietario != null ? proprietario.getNome() : "Não informado";
    String cidade = imovel.getCidade();

    Label lblId = new Label("ID: " + imovel.getId());
    Label lblProprietario = new Label("Proprietário: " + nomeProprietario);
    Label lblValor = new Label("Valor: " + currencyFormat.format(imovel.getPreco()));
    Label lblData = new Label("Cadastro: " + dateFormat.format(imovel.getDataCadastro()));
    Label lblCidade = new Label("Cidade: " + cidade);
    Label lblRua = new Label("Rua: " + imovel.getRua());
    Label lblTipoPropriedade = new Label("Tipo: " + imovel.getTipoPropriedade());
    Label lblArea = new Label("Área: " + imovel.getArea());
    Label lblQuartos = new Label("Quartos: " + imovel.getQuartos());
    Label lblBanheiros = new Label("Banheiros: " + imovel.getBanheiros());
    Label lblVagasGaragem = new Label("Garagem: " + imovel.getVagasGaragem());
    Label lblNumero = new Label("Nº: " + imovel.getNumeroCasa());
    Label lblMobiliada = new Label("Mobiliada: " + (imovel.isMobilia() ? "Sim" : "Não"));
    Label lblPiscina = new Label("Piscina: " + (imovel.isPiscina() ? "Sim" : "Não"));
    Label lblSS = new Label("Sistema de Segurança: " + (imovel.isSistemaSeguranca() ? "Sim" : "Não"));
    Label lblJardim = new Label("Jardim: " + (imovel.isJardim() ? "Sim" : "Não"));

    VBox vInfo = new VBox(3,
        lblId, lblProprietario, lblCidade, lblRua, lblValor, lblData,
        lblTipoPropriedade, lblArea, lblQuartos, lblBanheiros,
        lblVagasGaragem, lblNumero, lblMobiliada, lblPiscina, lblSS, lblJardim
    );
    vInfo.setPadding(new Insets(5, 0, 0, 0));

    VBox card = new VBox(10, imgPane, vInfo);
    card.setPadding(new Insets(10));
    card.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #bbb; -fx-border-radius: 8; -fx-background-radius: 8;");
    card.setCursor(Cursor.HAND);
    card.setAlignment(Pos.CENTER_LEFT);

    // Evento de clique pra abrir tela de detalhes
    card.setOnMouseClicked(event -> {
        try {
            abrirNovaTela(imovel, proprietario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    return card;
}
    
    private void abrirNovaTela(Propriedades imovel, Proprietario proprietario) throws IOException {
        URL url = new File("src/main/java/View/TelaImovelVenda.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        TelaImovelVendaController tIV = loader.getController();
        tIV.setTelaInicialController(this); // PASSANDO A REFERÊNCIA
        tIV.carregarDadosImovel(imovel, proprietario);

        Stage t = new Stage();
        tIV.setStage(t);

        Scene scene = new Scene(root);

        Image icone = new Image(getClass().getResourceAsStream("/icons/Bh.png"));
        t.getIcons().add(icone);

        t.setScene(scene);
        t.setTitle("BlueHorizon - Detalhes do Imóvel");
        t.setMaximized(true);
        t.show();
    }
    

    public void setFuncionario(Funcionario f) {
        this.funcionario = f;
    }
    
    }