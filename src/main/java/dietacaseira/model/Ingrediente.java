package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
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

    @Transient // Indica que este método não é um campo persistido no banco de dados
    public boolean validarDados() {
        // Validação de nulidade para campos críticos
        if (nome == null || nome.trim().isEmpty()) {
            return false;
        }
        if (proteina == null || gordura == null || carboidrato == null) {
            return false;
        }
        // Você pode adicionar mais lógica aqui (ex: verificar se os valores não são negativos)
        if (proteina.compareTo(BigDecimal.ZERO) < 0 || gordura.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        return true;
    }

    /**
     * Atualiza as informações nutricionais principais do ingrediente.
     *
     * @param proteinas Nova quantidade de proteínas (em BigDecimal).
     * @param gorduras Nova quantidade de gorduras (em BigDecimal).
     * @param vitaminas Novo mapa de vitaminas.
     * @param minerais Novo mapa de minerais.
     */
    @Transient
    public void atualizarInformacoes(BigDecimal proteinas, BigDecimal gorduras) {
        this.proteina = proteinas;
        this.gordura = gorduras;
        // Na prática, é bom recalcular a energiaKcal após a atualização de macro-nutrientes.
        // O cálculo da energiaKcal (fórmula de Atwater) deve ser implementado aqui se necessário.
    }
}