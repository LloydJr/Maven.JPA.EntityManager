import entities.Cd;
//import jakarta.persistence.*;
//import jakarta.persistence.*;

//import javax.persistence.*;
import javax.persistence.*;
import java.util.List;

public class TestSystem {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("jpaentity");

    public static void main(String[] args) {
        addCd(20, "Be mine", "Long song", 2022, "John Doe", 0.99);
        addCd(10, "Rejected", "Heart Break", 2020, "John Doe", 0.99);

        getCd(20);
        getAllCd();
        updateTitle(20, "Now Mine");
        deleteCd(10);

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addCd(Integer id, String title, String description, Integer year, String artist, Double price) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Cd cd = new Cd();
            cd.setId(Math.toIntExact(id));
            cd.setTitle(title);
            cd.setDescription(description);
            cd.setYear(Math.toIntExact(year));
            cd.setArtist(artist);
            cd.setPrice(Double.valueOf(price));
            entityManager.persist(cd);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public static void getCd(Integer id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT * FROM cd WHERE cd.id = :cdId";

        TypedQuery<Cd> typedQuery = entityManager.createQuery(query, Cd.class);
        typedQuery.setParameter("id", id);
        Cd cd = null;
        try{
            cd = typedQuery.getSingleResult();
            System.out.println(cd.getTitle() + " " + cd.getDescription() + " "
                    + cd.getYear() + " " + cd.getArtist() + " " + cd.getPrice() + " ");
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }
    public static void getAllCd() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT * FROM cd WHERE cd.id IS NOT NULL";
        TypedQuery<Cd> typedQuery = entityManager.createQuery(query, Cd.class);
        List<Cd> cdList;
        try {
            cdList = typedQuery.getResultList();
            cdList.forEach(cd-> System.out.println(cd.getTitle() + cd.getDescription()
                    + cd.getYear() + cd.getArtist() + cd.getPrice()));
        }
        catch (NoResultException e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
        }
    }
    public static void updateTitle(Integer id, String title) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        Cd cd = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            cd = entityManager.find(Cd.class, id);
            cd.setTitle(title);

            entityManager.persist(cd);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
    public static void deleteCd(Integer id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;
        Cd cd = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            cd = entityManager.find(Cd.class, id);
            entityManager.remove(cd);
            entityManager.persist(cd);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}