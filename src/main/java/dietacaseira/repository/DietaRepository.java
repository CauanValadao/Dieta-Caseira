package dietacaseira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dietacaseira.model.Dieta;
import dietacaseira.model.Pet;
import java.util.List;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Integer> {

    // Busca todas as dietas de um Pet específico, ordenadas da mais recente para a mais antiga
    List<Dieta> findByPetOrderByDataCriacaoDesc(Pet pet);

    List<Dieta> findByPet(Pet pet);
}