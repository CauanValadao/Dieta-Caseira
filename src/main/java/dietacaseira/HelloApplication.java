package dietacaseira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication // ESSENCIAL: Liga o Spring Boot
public class HelloApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        // Inicia o Spring Boot antes do JavaFX subir a janela
        springContext = SpringApplication.run(HelloApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login-view.fxml"));        
        // MÁGICA: Faz o JavaFX usar o Spring para criar os controllers
        // Isso permite usar @Autowired dentro dos Controllers do JavaFX!
        fxmlLoader.setControllerFactory(springContext::getBean);
        
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Dieta Caseira");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Fecha o Spring quando a janela fechar
        springContext.close();
    }

    public static void main(String[] args) {
        launch();
    }
}