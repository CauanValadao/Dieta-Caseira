package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal; 

// Imports dos Enums (Você precisará criar estes arquivos ou mudar para String)
import dietacaseira.enums.Especie; 
import dietacaseira.enums.Sexo;     

@Data
@NoArgsConstructor
@Entity 
public class Pet {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id_pet")
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nome;

    // No SQL é ENUM('CAO', 'GATO'). Crie o Enum Java ou mude aqui para String.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especie especie; 

    // Coluna nova que estava no SQL mas não no Java
    // No SQL é ENUM('M', 'F')
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    // No SQL é 'idade' (INT), no Java estava 'idadeAnos'
    @Column(name = "idade", nullable = false)
    private Integer idade; 

    // No SQL é DECIMAL(10,4). BigDecimal é o mais correto.
    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal peso; 

    @Column(name = "fase_vida", nullable = false, length = 45)
    private String faseVida; 

    @Column(name = "condicao_saude", length = 100) // SQL permite NULL
    private String condicaoSaude; 

    // --- RELACIONAMENTO (Onde a mágica acontece) ---
    
    // Muitos Pets possuem Um Médico.
    // Esta é a FK. O 'mappedBy' lá no Médico aponta para ESTE nome ("medico").
    @ManyToOne 
    @JoinColumn(name = "id_medico", nullable = false)
    private MedicoVeterinario medico;

    // Métodos de negócio (RER, MER) removidos. Vão para PetService ou regras de negócio.
}