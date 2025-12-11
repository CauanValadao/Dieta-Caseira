package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;

import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal; // Usaremos BigDecimal para quantidade para maior precisão

@Data
@NoArgsConstructor
@Entity // Torna esta classe uma entidade JPA, mapeada para uma tabela
public class ItemDieta {
    
    // O diagrama não especifica um ID, mas é necessário para entidades JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    // Atributos baseados no diagrama:

    // quantidade: int
    // Usaremos BigDecimal para precisão em quantidades fracionadas
    @Column(nullable = false, precision = 10, scale = 2) 
    private BigDecimal quantidade;

    // Relacionamento com Ingrediente (Muitos para Um)
    // ingrediente: Ingrediente
    @ManyToOne 
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private Ingrediente ingrediente;

    // Relacionamento com Dieta (Muitos para Um)
    // dieta: Dieta
    @ManyToOne 
    @JoinColumn(name = "id_dieta", nullable = false)
    private Dieta dieta;

    // --- Métodos de Negócio (baseados no diagrama) ---
    
    // + calcularNutrientes(): Map<String, int>
    // Retorna os valores de nutrientes do ingrediente multiplicados pela quantidade 
    public Map<String, Integer> calcularNutrientes() {
        // Lógica: 
        // 1. Acessar os dados nutricionais do 'ingrediente'.
        // 2. Multiplicar cada nutriente pela 'quantidade' deste ItemDieta.
        return null;
    }

    // + getCalorias(): int
    public int getCalorias() {
        // Lógica: 
        // 1. Acessar o valor calórico do 'ingrediente' (energia_kcal).
        // 2. Multiplicar esse valor pela 'quantidade' e retornar.
        return 0;
    }
}