package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data      
@NoArgsConstructor
@Entity    
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    // Macros e Energia (Dados diretos na tabela ingrediente)
    
    @Column(name = "energia_kcal", nullable = false, precision = 10, scale = 4)
    private BigDecimal energiaKcal;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal umidade;
    
    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal proteina;

    @Column(precision = 10, scale = 4)
    private BigDecimal gordura;

    @Column(precision = 10, scale = 4)
    private BigDecimal carboidrato;

    // --- RELACIONAMENTO COM MICRONUTRIENTES ---

    // Um ingrediente tem VÁRIOS registros de composição nutricional (Cálcio, Ferro, etc.)
    // 'mappedBy = "ingrediente"' refere-se ao atributo na classe ComposicaoNutricional
    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    @ToString.Exclude // Importante: Evita loop infinito ao imprimir o objeto no console
    private List<ComposicaoNutricional> micronutrientes;
}