package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal; 

@Data
@NoArgsConstructor
@Entity
@Table(name = "item_dieta")
public class ItemDieta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_dieta")
    private Long id; 

    // SQL: DECIMAL(10,4)
    @Column(nullable = false, precision = 10, scale = 4) 
    private BigDecimal quantidade;

    // --- RELACIONAMENTOS ---

    // Item aponta para UM Ingrediente.
    @ManyToOne 
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private Ingrediente ingrediente;

    // Item pertence a UMA Dieta.
    @ManyToOne 
    @JoinColumn(name = "id_dieta", nullable = false)
    @ToString.Exclude // IMPORTANTE: Corta o ciclo Dieta -> Itens -> Dieta
    private Dieta dieta;
}