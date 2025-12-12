package dietacaseira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dietacaseira.model.MedicoVeterinario;

@Repository
public interface MedicoVeterinarioRepository extends JpaRepository<MedicoVeterinario, Integer> {
    // Graças à herança JOINED, o Spring sabe fazer o JOIN correto
    // entre a tabela usuario e medico_veterinario automaticamente.
}