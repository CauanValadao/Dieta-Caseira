package dietacaseira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dietacaseira.model.Pet;
import dietacaseira.model.MedicoVeterinario;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    // Busca todos os pets vinculados a um médico específico (para preencher a tabela da tela principal)
    List<Pet> findByMedico(MedicoVeterinario medico);

    // Barra de busca: Busca pets pelo nome (ignorando maiúscula/minúscula)
    // Ex: Se digitar "rex", acha "Rex", "REX", "T-Rex"
    List<Pet> findByNomeContainingIgnoreCase(String nome);
}