package dietacaseira.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import dietacaseira.model.Usuario;
import dietacaseira.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@Component // Importante: Diz pro Spring que ele gerencia essa classe
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ApplicationContext springContext;
    
    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoSenha;

    @FXML
    private Label labelErro;

    @FXML private Label linkCadastro;

    @FXML
    public void onBotaoEntrarClick(){

        String emailDigitado = campoEmail.getText();
        String senhaDigitada = campoSenha.getText();

        Usuario usuario = usuarioService.login(emailDigitado, senhaDigitada);

        if(usuario != null){
            labelErro.setVisible(false);
            System.out.println("Usuario existe");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu-view.fxml"));
                loader.setControllerFactory(springContext::getBean);
                Parent root = loader.load();
                
                Stage stage = (Stage) labelErro.getScene().getWindow(); // Pega a janela atual
                stage.setScene(new Scene(root));
                stage.setTitle("Menu - Dieta Caseira");
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Usuario nao existe");
            labelErro.setVisible(true);
        }
    }

    @FXML
    public void onLinkCadastroClick() {
        System.out.println("Indo para tela de cadastro...");
        try {
            // 2. Carregador do FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cadastro-view.fxml"));

            // 3. O PULO DO GATO: Dizemos ao FXMLLoader para usar o Spring 
            // para criar o controller. Assim o @Autowired funciona na próxima tela.
            loader.setControllerFactory(springContext::getBean);

            Parent root = loader.load();

            // 4. Pega o "Palco" (Stage) atual através de qualquer componente da tela (ex: o link)
            // Se o linkCadastro for null, verifique se colocou fx:id="linkCadastro" no FXML
            Stage stage = (Stage) linkCadastro.getScene().getWindow();

            // 5. Troca a cena
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastro - Dieta Caseira");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Mostra o erro no console se o arquivo FXML não for achado
        }
    }
}