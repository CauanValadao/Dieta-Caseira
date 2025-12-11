package dietacaseira.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Inclui campos da superclasse (Usuario) no equals/hashCode
@Entity 
public class MedicoVeterinario extends Usuario { 
    
    // NOTA: Não há atributos específicos aqui. Todos os campos (idUsuario, nome, email, etc.)
    // são herdados da classe pai 'Usuario'.
    
    // --- Métodos de Negócio (exatamente como solicitado) ---

    // + cadastrarPet(pet : Pet) : void
    /**
     * Registra um novo Pet no sistema, associando-o ao Dono/Tutor.
     * @param pet O objeto Pet a ser persistido.
     */
    public void cadastrarPet(Pet pet) {
        // Lógica para salvar o objeto Pet no banco de dados.
    }

    // + criarDieta(pet : Pet) : Dieta
    /**
     * Cria uma nova Dieta para o Pet especificado.
     * @param pet O Pet para o qual a dieta será criada.
     * @return O objeto Dieta recém-criado.
     */
    public Dieta criarDieta(Pet pet) {
        // Lógica para instanciar uma nova Dieta e associá-la ao Pet.
        return null; 
    }

    // + ajustarDieta(dieta : Dieta) : void
    /**
     * Modifica ou atualiza uma Dieta existente.
     * @param dieta O objeto Dieta contendo as alterações a serem salvas.
     */
    public void ajustarDieta(Dieta dieta) {
        // Lógica para atualizar a Dieta no banco de dados.
    }
}