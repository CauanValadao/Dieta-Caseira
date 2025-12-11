package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity // Mapeia esta classe para a tabela 'Usuario' no banco
public class Usuario {
    
    // Atributos baseados na sua nova lista:

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario; 

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha; 

    @Column(name = "tipo_usuario", nullable = false, length = 50)
    private String tipoUsuario; // Ex: "Tutor", "Veterinario", "Administrador"

    // --- Métodos de Negócio (baseados na sua nova lista) ---

    // + login(email : String, senha : String) : boolean
    /**
     * Tenta autenticar o usuário com as credenciais fornecidas.
     * @param email O email do usuário.
     * @param senha A senha em texto puro (deve ser comparada com o hash armazenado).
     * @return true se o login for bem-sucedido, false caso contrário.
     */
    public boolean login(String email, String senha) {
        // Em uma implementação real, a senha deve ser verificada com hash.
        return this.email.equals(email) && this.senha.equals(senha);
    }

    // + editarPerfil(novoNome : String, novoEmail : String) : void
    /**
     * Atualiza o nome e email do perfil do usuário.
     * @param novoNome O novo nome a ser definido.
     * @param novoEmail O novo email a ser definido.
     */
    public void editarPerfil(String novoNome, String novoEmail) {
        this.nome = novoNome;
        this.email = novoEmail;
        // Lógica de persistência (salvar no banco) seria adicionada em uma camada de serviço.
    }
}