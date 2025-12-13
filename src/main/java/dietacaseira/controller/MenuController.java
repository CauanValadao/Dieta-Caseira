package dietacaseira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

@Component
public class MenuController {

    @Autowired
    private ApplicationContext springContext;

    @FXML
    private Button btnSair; 

    // --- Método Auxiliar de Troca de Cena ---
    private void trocarCena(String fxmlFileName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            
            // 1. Garante que os Controllers sejam gerenciados pelo Spring
            loader.setControllerFactory(springContext::getBean);
            
            // 2. Carrega o root (Pode falhar se o arquivo não for encontrado!)
            Parent root = loader.load();
            
            // 3. Obtém a Stage através de um elemento FXML injetado
            Stage stage = (Stage) btnSair.getScene().getWindow();
            
            // 4. Troca e exibe
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
            
        } catch (IOException e) {
            // Este erro captura principalmente o erro de "arquivo não encontrado"
            System.err.println("----------------------------------------------------------");
            System.err.println("ERRO CRÍTICO: Falha ao carregar o FXML: " + fxmlFileName);
            System.err.println("VERIFIQUE: O nome do arquivo FXML e seu caminho em resources.");
            System.err.println("----------------------------------------------------------");
            e.printStackTrace();
        } catch (Exception ex) {
            // Este erro captura falhas após o carregamento (ex: injeção falhou no Controller de destino)
            System.err.println("ERRO GERAL NA TROCA DE CENA: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void onNovaDietaClick() {
        // Chamada de transição
        trocarCena("/nova-dieta-pet-selection-view.fxml", "Nova Dieta - Seleção do Pet");
    }

    @FXML
    public void onSairClick() {
        // Chamada de transição
        trocarCena("/login-view.fxml", "Login - Dieta Caseira");
    }

    // --- Outros Métodos ---
    @FXML
    public void onPerfilClick() {
        System.out.println("Navegar para Perfil...");
    }

    @FXML
    public void onDietasClick() {
        System.out.println("Navegar para Listagem de Dietas...");
    }

    @FXML
    public void onIngredientesClick() {
        System.out.println("Navegar para Ingredientes...");
    }
}