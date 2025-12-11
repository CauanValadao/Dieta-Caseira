package dietacaseira;

import dietacaseira.model.Ingrediente;
import dietacaseira.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class TesteBanco {
    public static void main(String[] args) {
        // 1. Tenta pegar a conexão (Se a senha estiver errada, estoura erro aqui)
        EntityManager em = JPAUtil.getEntityManager();
        
        // 2. Cria um objeto na memória
        Ingrediente frango = new Ingrediente();
        frango.setNome("Peito de Frango Cozido");
        frango.setProteina(31.5);

        // 3. Inicia a transação com o banco
        em.getTransaction().begin();
        
        // 4. Manda salvar (O Hibernate gera o INSERT aqui)
        em.persist(frango);
        
        // 5. Confirma a operação
        em.getTransaction().commit();
        
        // 6. Fecha a conexão
        em.close();
        JPAUtil.close();

        System.out.println("✅ SUCESSO! Ingrediente salvo e tabela criada.");
    }
}