package dietacaseira.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    // O nome aqui deve ser IDÊNTICO ao que está no seu persistence.xml <persistence-unit name="...">
    private static final EntityManagerFactory FACTORY = 
        Persistence.createEntityManagerFactory("dieta-caseira-pu");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
    
    public static void close() {
        FACTORY.close();
    }
}