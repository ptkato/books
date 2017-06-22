package books.util.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityManager {
   private static EntityManagerFactory emf = null;
   
   public static EntityManager getEntityManager() {
      if (emf == null) {
         emf = Persistence.
<<<<<<< HEAD:src/java/books/util/jpa/JPAEntityManager.java
              createEntityManagerFactory("booksPU");
=======
              createEntityManagerFactory("whateverbooksPU");
>>>>>>> da4955178339fed43afa459d962f8e9acec84c9c:src/java/financas/util/jpa/JPAEntityManager.java
      }
      return emf.createEntityManager();
   }
}