package dietacaseira.service;

import org.springframework.stereotype.Service;              // Import para @Service
import org.springframework.beans.factory.annotation.Autowired; // Import para @Autowired
import dietacaseira.repository.UsuarioRepository;
import dietacaseira.enums.TipoPerfil;
import dietacaseira.model.MedicoVeterinario;
import dietacaseira.model.Usuario;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario login(String email, String senha){
        // Busca direta. Se não achar, retorna Optional vazio.
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if(usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            if(usuario.getSenha().equals(senha)){
                return usuario; // Sucesso
            }
        }
        return null; // Falha
    }

    public Usuario cadastro(String nome, String email, String senha, TipoPerfil tipoPerfil){
        if(usuarioRepository.existsByEmail(email)){
            return null; // Já existe um usuário com esse email
        }

        Usuario novoUsuario = null;

        // 2. Instanciação baseada no tipo (Usando Polimorfismo)
        if(tipoPerfil == TipoPerfil.VET){
            novoUsuario = new MedicoVeterinario(nome, email, senha);
            // Lógica específica de vet se houver (ex: CRMV)
        } else {
            novoUsuario = new Usuario(nome, email, senha, tipoPerfil); // Tutor ou Admin
        }
        // 3. CRUCIAL: Salvar no banco de dados!
        return usuarioRepository.save(novoUsuario);
    }
}
