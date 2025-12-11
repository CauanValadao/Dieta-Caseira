package dietacaseira.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ComposicaoNutricionalID implements Serializable{
    
    private Long idIngrediente;
    private Long idReceita;
}
