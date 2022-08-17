package services;

import entities.Artist;
import entities.ArtistID;

import javax.persistence.*;
import java.util.List;

public class ArtistService {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("jpaentity");

    EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction et = null;

    public void createArtist(Integer id, String firstName, String lastName, String instrument) {
        Artist artist = new Artist();
        try {
            et = em.getTransaction();
            et.begin();
            artist.setId(id);
            artist.setFirstName(firstName);
            artist.setLastName(lastName);
            artist.setInstrument(instrument);
            em.persist(artist);
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

    public void getArtist(Integer id) {
        String query = "SELECT x FROM  x WHERE x.id = :id";

        TypedQuery<Artist> typedQuery = em.createQuery(query, Artist.class);
        typedQuery.setParameter("artId", id);
        Artist artist = null;
        try {
            artist = typedQuery.getSingleResult();
            System.out.println(artist.getFirstName() + " " + artist.getLastName() + " " + artist.getInstrument() + " ");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void findAllArtist() {
        String query = "SELECT x FROM Artist_Id x WHERE x.artist_id IS NOT NULL";
        TypedQuery<Artist> typedQuery = em.createQuery(query, Artist.class);
        List<Artist> artistList;
        try {
            artistList = typedQuery.getResultList();
            artistList.forEach(artist
                    -> System.out.println(artist.getFirstName() + " " + artist.getLastName()
                    + " " + artist.getInstrument() + " "));
        } catch (NoResultException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateArtist(Integer id, String firstName, String lastName, String instrument) {
        Artist artist = null;
        try {
            et = em.getTransaction();
            et.begin();

            artist = em.find(Artist.class, id);
            artist.setFirstName(firstName);
            artist.setLastName(lastName);
            artist.setInstrument(instrument);

            em.persist(artist);
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

    public void deleteArtist(Integer id) {
        Artist artist = null;
        try {
            et = em.getTransaction();
            et.begin();
            artist = em.find(Artist.class, id);
            em.remove(artist);
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
