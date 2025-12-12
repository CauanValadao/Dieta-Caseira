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

    @FXML
    public void onPerfilClick() {
        System.out.println("Navegar para Perfil...");
        // Futuro: carregar tela de perfil
    }

    @FXML
    public void onDietasClick() {
        System.out.println("Navegar para Listagem de Dietas...");
    }

    @FXML
    public void onNovaDietaClick() {
        System.out.println("Navegar para Nova Dieta...");
    }

    @FXML
    public void onIngredientesClick() {
        System.out.println("Navegar para Ingredientes...");
    }

    @FXML
    public void onSairClick() {
        // Lógica de Logout: volta para o login
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));
            loader.setControllerFactory(springContext::getBean);
            Parent root = loader.load();
            
            Stage stage = (Stage) btnSair.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login - Dieta Caseira");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}