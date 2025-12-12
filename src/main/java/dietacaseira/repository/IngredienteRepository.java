package dietacaseira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dietacaseira.model.Ingrediente;
import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

    // Busca ingredientes que contenham o texto (para o autocomplete da dieta)
    List<Ingrediente> findByNomeContainingIgnoreCase(String nome);
}