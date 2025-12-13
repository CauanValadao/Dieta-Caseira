package dietacaseira.model;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dieta")
    private Integer id; 

    @Column(nullable = false, length = 45)
    private String tipo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    // SQL: DECIMAL(10,4)
    @Column(name = "total_calorico", precision = 10, scale = 4)
    private BigDecimal totalCalorico;

    // --- RELACIONAMENTOS ---

    // A Dieta pertence a UM Pet.
    @ManyToOne
    @JoinColumn(name = "id_pet", nullable = false)
    private Pet pet;

    // A Dieta possui VÁRIOS itens (ingredientes + quantidades).
    // mappedBy aponta para o atributo 'dieta' dentro de ItemDieta.
    @OneToMany(mappedBy = "dieta", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // Evita loop infinito
    private List<ItemDieta> itens;
}