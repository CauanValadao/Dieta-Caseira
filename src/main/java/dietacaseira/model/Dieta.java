package dietacaseira.model;

import java.util.Date;
import java.util.Map;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ElementCollection; // Para o Map

@Data
@NoArgsConstructor
@Entity
public class Dieta {

    // Atributos baseados no diagrama:
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDieta; // O diagrama sugere int, mas Long é mais comum para IDs JPA

    private String tipo;

    @Temporal(TemporalType.DATE)
    private Date dataCriacao; // Renomeado de 'dataCriacao' para melhor clareza

    private int totalCalorico;

    // Map: mapeamento de elementos para quantidades
    @ElementCollection
    private Map<String, Integer> analiseNutricional;

    // Relacionamento com ItemIngrediente (Um para Muitos)
    @OneToMany(mappedBy = "dieta")
    private List<ItemDieta> itensIngredientes;

    // --- Métodos de Negócio (baseados no diagrama) ---

    // + addIngrediente(item: ItemIngrediente): boolean
    public boolean addIngrediente(ItemDieta item) {
        // Lógica para adicionar o ItemIngrediente à lista 'itensIngredientes'
        // e possivelmente atualizar 'totalCalorico' e 'analiseNutricional'.
        // Deve retornar true se a adição foi bem-sucedida.
        return false; 
    }

    // + removeIngrediente(item: ItemIngrediente): boolean
    public boolean removeIngrediente(ItemDieta item) {
        // Lógica para remover o ItemIngrediente da lista.
        return false;
    }

    // + calculaNutrientes(): Map<String, Integer>
    public Map<String, Integer> calculaNutrientes() {
        // Lógica para calcular o total de nutrientes de todos os ItemIngrediente
        return null;
    }

    // + comparaTabela(): Map<String, Integer>
    public Map<String, Integer> comparaTabela() {
        // Lógica para comparar nutrientes da dieta com alguma tabela de referência
        return null;
    }

    // + geraRelatorio(): Arquivo
    public Object geraRelatorio() { // Usando Object como placeholder para Arquivo
        // Lógica para gerar um arquivo de relatório
        return null;
    }

    // + editarIngrediente(item: ItemIngrediente): boolean
    public boolean editarIngrediente(ItemDieta item) {
        // Lógica para encontrar e atualizar um ItemIngrediente existente
        return false;
    }
}