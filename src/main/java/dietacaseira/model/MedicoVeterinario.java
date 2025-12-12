package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

import dietacaseira.enums.TipoPerfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Importante para herança: considera o ID do pai no equals
@ToString(callSuper = true)          // Inclui os dados do pai (nome, email) no toString
@Entity 
// Esta anotação diz: "A chave primária desta tabela ('id_medico') 
// também é a chave estrangeira para a tabela pai (Usuario)"
@PrimaryKeyJoinColumn(name = "id_medico") 
public class MedicoVeterinario extends Usuario { 
    
    // A tabela SQL não tem colunas extras, então não temos atributos simples aqui.

    // --- Relacionamentos (Navegabilidade) ---

    // Um médico cuida de VÁRIOS pets.
    // 'mappedBy = "medico"' refere-se ao atributo 'medico' que criaremos na classe Pet.
    @OneToMany(mappedBy = "medico")
    @ToString.Exclude // Evita loop infinito no toString (Medico -> Pet -> Medico...)
    private List<Pet> petsPaciente;

    public MedicoVeterinario(String nome, String email, String senha) {
        super(nome, email, senha, TipoPerfil.VET);
    }

}