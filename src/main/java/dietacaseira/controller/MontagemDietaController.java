package dietacaseira.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; // NOVO: Necessário para carregar o FXML
import javafx.scene.Parent; // NOVO: Necessário para o root do FXML
import javafx.scene.Scene; // NOVO: Necessário para a nova cena
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext; // NOVO: Necessário para carregar controllers
import javafx.beans.property.SimpleStringProperty;
import org.springframework.stereotype.Component;
import javafx.stage.Stage; // NOVO: Necessário para obter a janela atual

import dietacaseira.model.Dieta;
import dietacaseira.model.Ingrediente;
import dietacaseira.model.ItemDieta;
import dietacaseira.model.Pet;
import dietacaseira.service.DietaService;
import dietacaseira.service.IngredienteService;
// import dietacaseira.util.StageManager; // REMOVIDO: StageManager descartado

import java.io.IOException; // NOVO: Para tratar exceção de carregamento FXML
import java.math.BigDecimal;


@Component
public class MontagemDietaController {

    @Autowired private DietaService dietaService;
    @Autowired private IngredienteService ingredienteService;
    // REMOVIDO: @Autowired private StageManager stageManager; 
    @Autowired private ApplicationContext springContext; // ADICIONADO: Contexto para carregar a próxima tela
    
    // --- Elementos FXML ---
    @FXML private Label lblPetInfo;
    @FXML private TextField txtNomeIngrediente;
    @FXML private Label lblIngredienteSelecionado;
    @FXML private TextField txtQuantidade;
    @FXML private TableView<ItemDieta> tblItensDieta;
    @FXML private TableColumn<ItemDieta, String> colNome;
    @FXML private TableColumn<ItemDieta, BigDecimal> colQuantidade;
    @FXML private TableColumn<ItemDieta, String> colKcal; // Mostrará o cálculo
    @FXML private TableColumn<ItemDieta, Button> colAcao; // Botão de Remover
    @FXML private Label lblTotalKcal;
    @FXML private Label lblStatus;
    @FXML private Button btnSalvarDieta;

    // ADICIONADO: Elemento para obter a Stage no onVoltarClick
    @FXML private Button btnVoltarAoMenu; // Precisamos disso no FXML!

    // --- Dados da Sessão ---
    private Pet petAtual;
    private double merRequerido;
    private Ingrediente ingredienteSelecionado;
    private ObservableList<ItemDieta> itensDieta = FXCollections.observableArrayList();

    // --- Método de Inicialização (Chamado pela tela anterior) ---
    public void initData(Pet pet, double mer) {
        this.petAtual = pet;
        this.merRequerido = mer;
        
        lblPetInfo.setText(
            String.format("Pet: %s | MER Requerido: %.2f Kcal/dia", 
                          pet.getNome() != null ? pet.getNome() : "[Novo Pet]", 
                          merRequerido)
        );
        
        // Inicializa a Tabela
        configurarTabela();
        tblItensDieta.setItems(itensDieta);
        calcularTotais();
    }
    
    @FXML
    public void initialize() {
        // ... (seu código de inicialização) ...
    }

    private void configurarTabela() {
        // ... (seu código de configuração de tabela) ...
        colNome.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIngrediente().getNome())
        );
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        
        // Coluna Kcal: Calcula a caloria do item para exibição
        colKcal.setCellValueFactory(cellData -> {
            ItemDieta item = cellData.getValue();
            double kcalItem = (item.getQuantidade().doubleValue() / 100) * item.getIngrediente().getEnergiaKcal().doubleValue();
            return new SimpleStringProperty(String.format("%.2f Kcal", kcalItem));
        });
        
        // Coluna Ação: Botão para remover o item
        colAcao.setCellFactory(param -> new TableCell<ItemDieta, Button>() {
            final Button btn = new Button("Remover");
            {
                btn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
                btn.setOnAction(event -> {
                    itensDieta.remove(getTableRow().getItem());
                    calcularTotais();
                });
            }
            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });
    }

    @FXML
    private void onBuscarIngredienteClick() {
        // ... (seu código de busca) ...
    }

    @FXML
    private void onAdicionarItemClick() {
        // ... (seu código de adição) ...
    }

    private void calcularTotais() {
        // ... (seu código de cálculo) ...
    }

    @FXML
    private void onValidarNutrientesClick() {
        // ... (seu código de validação) ...
    }
    
    @FXML
    private void onSalvarDietaClick() {
        // ... (seu código de salvar dieta) ...
        
        try {
            // ... (Lógica de salvar) ...
            
            // Retorna ao menu principal
            onVoltarClick(); // Chama o método de transição de tela
            
        } catch (Exception e) {
            // ... (Tratamento de erro) ...
        }
    }

    @FXML
    private void onVoltarClick() {
        // 🎯 LÓGICA DE TRANSIÇÃO: Retorna para o menu principal sem StageManager
        try {
            // FXML Menu
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu-view.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();
            
            // Obtém a Stage (janela) atual usando o botão de voltar injetado
            Stage stage = (Stage) btnVoltarAoMenu.getScene().getWindow();
            
            // Troca de Cena
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal - Dieta Caseira");
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Erro ao carregar FXML de Menu: /menu-view.fxml");
            e.printStackTrace();
        }
    }
}