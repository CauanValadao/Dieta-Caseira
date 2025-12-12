package dietacaseira.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // Diz que esta classe será embutida dentro de outra como ID
public class ComposicaoNutricionalID implements Serializable {
    
    // Os nomes aqui devem bater com os nomes dos atributos na classe principal
    // que usarem @MapsId, ou simplesmente serem os tipos Long correspondentes.
    
    private Long idIngrediente;
    private Long idNutrienteReferencia; 
}