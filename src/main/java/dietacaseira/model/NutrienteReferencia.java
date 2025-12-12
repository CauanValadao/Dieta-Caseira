package dietacaseira.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// Crie este Enum no pacote enums depois: TipoNutriente { MINERAL, VITAMINA, AMINOACIDO }
import dietacaseira.enums.TipoNutriente; 

@Data
@NoArgsConstructor
@Entity
@Table(name = "nutriente_referencia") // Boa prática explicitar o nome
public class NutrienteReferencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nutriente_referencia")
    private Long id;

    @Column(nullable = false, length = 45)
    private String nome;

    @Column(name = "unidade_padrao", nullable = false, length = 45)
    private String unidadePadrao; // ex: "mg", "g", "UI"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNutriente tipo;
}