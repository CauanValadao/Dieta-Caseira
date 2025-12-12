package dietacaseira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dietacaseira.model.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // O Spring gera o SQL: SELECT * FROM usuario WHERE email = ?
    Optional<Usuario> findByEmail(String email);

    // Útil para validar cadastro: SELECT COUNT(*) > 0 FROM usuario WHERE email = ?
    boolean existsByEmail(String email);
}