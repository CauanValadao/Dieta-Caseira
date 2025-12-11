package dietacaseira.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ComposicaoNutricional {
    
    @EmbeddedId
    private ComposicaoNutricionalID id = new ComposicaoNutricionalID();

    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "id_ingrediente")
    private Ingrediente ingrediente;

    @ManyToOne
    @MapsId("nutrienteReferenciaId") // Conecta com o ID da chave acima
    @JoinColumn(name = "id_nutriente_referencia")
    private NutrienteReferencia nutriente;

    @Column(name = "quantidade", precision = 10, scale = 2, nullable = false)
    private BigDecimal quantidade;

    public ComposicaoNutricional(Ingrediente ingrediente, NutrienteReferencia nutriente, BigDecimal quantidade) {
        this.ingrediente = ingrediente;
        this.nutriente = nutriente;
        this.quantidade = quantidade;
        this.id = new ComposicaoNutricionalID(ingrediente.getId(), nutriente.getId());
    }
}
