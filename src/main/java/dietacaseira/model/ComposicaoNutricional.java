package dietacaseira.model;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "composicao_nutricional")
public class ComposicaoNutricional {
    
    @EmbeddedId
    private ComposicaoNutricionalID id = new ComposicaoNutricionalID();

    // --- RELACIONAMENTO 1: INGREDIENTE ---
    @ManyToOne
    @MapsId("idIngrediente") // Vincula este objeto ao campo 'idIngrediente' da chave composta
    @JoinColumn(name = "id_ingrediente")
    @ToString.Exclude // Evita o loop voltando para Ingrediente
    private Ingrediente ingrediente;

    // --- RELACIONAMENTO 2: NUTRIENTE ---
    @ManyToOne
    @MapsId("idNutrienteReferencia") // Vincula ao campo 'idNutrienteReferencia' da chave composta
    @JoinColumn(name = "id_nutriente_referencia")
    private NutrienteReferencia nutriente;

    // --- DADO EXTRA (Quantidade) ---
    // SQL: DECIMAL(10,4)
    @Column(name = "quantidade", precision = 10, scale = 4)
    private BigDecimal quantidade;

    // Construtor utilitário para facilitar a criação
    public ComposicaoNutricional(Ingrediente ingrediente, NutrienteReferencia nutriente, BigDecimal quantidade) {
        this.ingrediente = ingrediente;
        this.nutriente = nutriente;
        this.quantidade = quantidade;
        this.id = new ComposicaoNutricionalID(ingrediente.getId(), nutriente.getId());
    }
}