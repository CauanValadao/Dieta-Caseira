package dietacaseira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dietacaseira.model.NutrienteReferencia;

@Repository
public interface NutrienteReferenciaRepository extends JpaRepository<NutrienteReferencia, Long> {
}