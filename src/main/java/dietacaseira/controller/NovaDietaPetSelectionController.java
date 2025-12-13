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
public class NovaDietaPetSelectionController {

    @Autowired
    private ApplicationContext springContext;
    
    // Referência do botão de Voltar para obter a Stage
    @FXML
    private Button btnVoltar; 

    @FXML
    public void initialize() {
        // Código de inicialização do Controller (se necessário)
    }

    @FXML
    public void onContinuarClick() {
        System.out.println("Lógica de cálculo do MER e navegação para MontagemDietaController.");
        
        // Futura lógica de transição para MontagemDietaController
    }

    @FXML
    public void onVoltarClick() {
        // Lógica para voltar ao Menu Principal
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu-view.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();
            
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Menu Principal - Dieta Caseira");
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Erro ao carregar FXML de Menu.");
            e.printStackTrace();
        }
    }
}
