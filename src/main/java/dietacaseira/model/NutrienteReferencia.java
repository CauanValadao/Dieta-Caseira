package dietacaseira.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import dietacaseira.enums.TipoNutriente;

@Data
@Entity
public class NutrienteReferencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nutriente_referencia")
    private Long id;

    private String nome;

    @Column(name = "unidade_padrao")
    private String unidadeMedida;

    //Saber se é um mineral ou uma vitamina
    @Enumerated(EnumType.STRING)
    private TipoNutriente tipo;
}
