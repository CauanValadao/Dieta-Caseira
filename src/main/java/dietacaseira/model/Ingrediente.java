package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import java.math.*;

@Data      // Lombok: Gera Getters, Setters, toString, etc.
@Entity    // JPA: Diz "Isso vira uma tabela no banco"
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    @Column(name = "id_ingrediente")
    private Long id;

    private String nome;

    @Column(name = "energia_kcal")
    private BigDecimal energiaKcal;

    private BigDecimal umidade;
    
    private BigDecimal proteina;

    private BigDecimal gordura;

    private BigDecimal carboidrato;
}