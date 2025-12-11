package dietacaseira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data      // Lombok: Gera Getters, Setters, toString, etc.
@Entity    // JPA: Diz "Isso vira uma tabela no banco"
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id;

    private String nome;
    
    // Vamos adicionar um nutriente só para testar
    private double proteina;
}