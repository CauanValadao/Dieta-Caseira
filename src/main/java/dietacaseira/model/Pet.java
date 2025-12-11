package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Transient; // Usado para campos que não vão para o banco de dados
import lombok.Data;
import java.lang.Math; // Necessário para o Math.pow no cálculo do RER

/**
 * Entidade que representa um Pet, mapeada para uma tabela no banco de dados.
 */
@Data // Lombok: Gera automaticamente Getters, Setters, toString, equals, hashCode
@Entity // JPA: Marca a classe como uma entidade de persistência
public class Pet {

    // --- Atributos de Persistência (Mapeamento JPA) ---

    @Id // Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    @Column(name = "id_pet")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String especie; // Ex: Cão, Gato

    @Column(nullable = false)
    private Integer idadeAnos; // Preferível usar a unidade no nome

    // Usamos double para o peso para maior precisão em cálculos como o RER
    @Column(nullable = false)
    private Double pesoKg; // Em Kilogramas

    @Column(name = "fase_vida", nullable = false, length = 50)
    private String faseVida; // Ex: Filhote, Adulto, Idoso

    @Column(name = "condicao_saude", nullable = false, length = 100)
    private String condicaoSaude; // Ex: Saudável, Castrado, Gestante, Obeso

    // --- Métodos de Negócio (Lógica) ---

    /**
     * Atualiza o perfil do Pet com novas informações.
     * Observação: Com Lombok (@Data), os Setters são gerados automaticamente,
     * mas manter este método pode ser útil para encapsular a lógica de atualização.
     */
    @Transient // Indica que este método não é um campo persistido, mas sim uma lógica de negócio.
    public void atualizarPerfil(String nome, Integer idadeAnos, Double pesoKg, String faseVida, String condicaoSaude) {
        this.nome = nome;
        this.idadeAnos = idadeAnos;
        this.pesoKg = pesoKg;
        this.faseVida = faseVida;
        this.condicaoSaude = condicaoSaude;
    }

    /**
     * Calcula o RER (Requisito Energético de Repouso) em kcal/dia.
     * Fórmula padrão (para cães e gatos > 2kg): RER = 70 * (Peso_em_kg)^(0.75)
     *
     * @return O valor calculado do RER em kcal/dia (arredondado para inteiro).
     */
    @Transient
    public int calcularRER() {
        if (this.pesoKg == null || this.pesoKg <= 0) {
             return 0; // Evita erro se o peso não estiver definido
        }
        // RER = 70 * Peso^0.75
        double rer = 70 * Math.pow(this.pesoKg, 0.75);
        return (int) Math.round(rer);
    }

    /**
     * Calcula o MER (Requisito Energético de Manutenção) em kcal/dia,
     * utilizando o RER e um fator de energia metabólica (FEM).
     *
     * @return O valor calculado do MER em kcal/dia (arredondado para inteiro).
     */
    @Transient
    public int calcularMER() {
        double fatorMer;
        int rer = calcularRER();

        if (rer == 0) return 0; // Não calcula se o RER for zero

        // --- Lógica de Fatores (FEM) Simplificada ---
        // ESTA LÓGICA DEVE SER REVISADA E VALIDADA POR UM VETERINÁRIO NUTRICIONISTA.
        String faseVidaLower = faseVida != null ? faseVida.toLowerCase() : "";
        String condicaoSaudeLower = condicaoSaude != null ? condicaoSaude.toLowerCase() : "";

        if (faseVidaLower.contains("filhote")) {
            fatorMer = 2.0; // Filhotes (geralmente 2.0 a 3.0 dependendo da idade e espécie)
        } else if (condicaoSaudeLower.contains("gestante")) {
            fatorMer = 1.7; // Gestação
        } else if (condicaoSaudeLower.contains("lactante")) {
            fatorMer = 3.0; // Lactação (varia muito)
        } else if (condicaoSaudeLower.contains("castrado")) {
            fatorMer = 1.2; // Adulto Castrado
        } else if (faseVidaLower.contains("adulto") && condicaoSaudeLower.contains("saudavel")) {
            fatorMer = 1.6; // Adulto não castrado
        } else if (faseVidaLower.contains("idoso")) {
            fatorMer = 1.4; // Idoso
        } else {
            fatorMer = 1.5; // Fator padrão/manutenção
        }
        // ---------------------------------------------

        // MER = RER * Fator_MER
        double mer = rer * fatorMer;
        return (int) Math.round(mer);
    }
}