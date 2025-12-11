package dietacaseira;

import java.math.BigDecimal;

import dietacaseira.model.Ingrediente;
import dietacaseira.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class TesteBanco {
    public static void main(String[] args) {
        // 1. Tenta pegar a conexão (Se a senha estiver errada, estoura erro aqui)
        EntityManager em = JPAUtil.getEntityManager();
        Ingrediente carne = new Ingrediente();
        carne.setNome("Carne bovina");
        // 2. Cria um objeto na memória
        
        // 3. Inicia a transação com o banco
        em.getTransaction().begin();
        
        // 4. Manda salvar (O Hibernate gera o INSERT aqui)
        //em.persist(carne);
        em.persist(carne);
        // 5. Confirma a operação
        em.getTransaction().commit();
        
        // 6. Fecha a conexão
        em.close();
        JPAUtil.close();

        System.out.println("✅ SUCESSO! Ingrediente salvo e tabela criada.");
    }
}