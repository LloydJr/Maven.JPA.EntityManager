package services;

import entities.Artist;
import entities.ArtistID;
import entities.Cd;

import javax.persistence.*;
import java.util.List;

public class ArtistIdService {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("jpaentity");

    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction et = null;

    public void createArtistId(Integer cdId, Integer artId){
        ArtistID artistID = new ArtistID();
        try {
            et = em.getTransaction();
            et.begin();
            artistID.setCdId(cdId);
            artistID.setArtistId(artId);
            em.persist(artistID);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void getArtistId(Integer artId) {
        String query = "SELECT x FROM  x WHERE x.id = :id";

        TypedQuery<ArtistID> typedQuery = em.createQuery(query, ArtistID.class);
        typedQuery.setParameter("artId", artId);
        ArtistID artistID = null;
        try {
            artistID = typedQuery.getSingleResult();
            System.out.println(artistID.getCdId() + artistID.getArtistId());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void findAllArtistId() {
        String query = "SELECT x FROM Artist_Id x WHERE x.artist_id IS NOT NULL";
        TypedQuery<ArtistID> typedQuery = em.createQuery(query, ArtistID.class);
        List<ArtistID> artistIDList;
        try {
            artistIDList = typedQuery.getResultList();
            artistIDList.forEach(artistID
                    -> System.out.println(artistID.getCdId() + artistID.getArtistId()));
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void updateArtistId(Integer cdId, Integer artId) {
        ArtistID artistID = null;
        try {
            et = em.getTransaction();
            et.begin();
            artistID = em.find(ArtistID.class, cdId);
            artistID.setArtistId(artId);

            em.persist(artistID);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public void deleteArtistId(Integer artId) {
        ArtistID artistID = null;
        try {
            et = em.getTransaction();
            et.begin();
            artistID = em.find(ArtistID.class, artId);
            em.remove(artistID);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
