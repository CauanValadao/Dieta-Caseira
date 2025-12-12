package dietacaseira.controller;

import dietacaseira.enums.TipoPerfil; // Importe seu Enum
import dietacaseira.model.Usuario;
import dietacaseira.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CadastroController {

    @Autowired
    private ApplicationContext springContext;

    @Autowired
    private UsuarioService usuarioService;

    @FXML private TextField campoNome;
    @FXML private TextField campoEmail;
    @FXML private PasswordField campoSenha;
    
    @FXML 
    private ComboBox<TipoPerfil> comboPerfil; // Note o tipo <TipoPerfil>

    @FXML private Label labelMensagem;

    @FXML
    private Label linkLogin;

    @FXML
    public void initialize() {
        // Isso carrega as opções do Enum para dentro da caixinha visualmente
        comboPerfil.getItems().setAll(TipoPerfil.values());
    }

    @FXML
    public void onBotaoCadastrarClick() {
        // Exemplo de como pegar o valor selecionado
        TipoPerfil perfilSelecionado = comboPerfil.getValue();
        String nomeDigitado = campoNome.getText();
        String emailDigitado = campoEmail.getText();
        String senhaDigitada = campoSenha.getText();

        if (perfilSelecionado == null || nomeDigitado.isEmpty() || emailDigitado.isEmpty() || senhaDigitada.isEmpty()) {
            labelMensagem.setText("Dados incompletos");
            labelMensagem.setVisible(true);
            return;
        }

        System.out.println("Cadastrando: " + campoNome.getText() + " como " + perfilSelecionado);
        
        Usuario usuario = usuarioService.cadastro(nomeDigitado, emailDigitado, senhaDigitada, perfilSelecionado);

        if(usuario != null){
            System.out.println("Usuario Cadastrado");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu-view.fxml"));
                loader.setControllerFactory(springContext::getBean);
                Parent root = loader.load();
                
                // Use algum componente da tela para pegar o Stage, ex: campoNome ou o botão
                Stage stage = (Stage) campoNome.getScene().getWindow(); 
                stage.setScene(new Scene(root));
                stage.setTitle("Menu - Dieta Caseira");
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Erro no cadastro");
    }

    @FXML
    public void onVoltarClick() {
        System.out.println("Indo para tela de login...");
        try {
            // 2. Carregador do FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login-view.fxml"));

            // 3. O PULO DO GATO: Dizemos ao FXMLLoader para usar o Spring 
            // para criar o controller. Assim o @Autowired funciona na próxima tela.
            loader.setControllerFactory(springContext::getBean);

            Parent root = loader.load();

            // 4. Pega o "Palco" (Stage) atual através de qualquer componente da tela (ex: o link)
            // Se o linkCadastro for null, verifique se colocou fx:id="linkCadastro" no FXML
            Stage stage = (Stage) linkLogin.getScene().getWindow();

            // 5. Troca a cena
            stage.setScene(new Scene(root));
            stage.setTitle("Login - Dieta Caseira");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Mostra o erro no console se o arquivo FXML não for achado
        }
    }
}