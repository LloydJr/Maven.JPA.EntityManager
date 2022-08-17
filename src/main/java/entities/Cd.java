package entities;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;

import java.io.Serializable;

//import jakarta.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cd")
public class Cd implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cdId", nullable = false)
    private Integer id;
    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "description", unique = true)
    private String description;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "artist", unique = true)
    private String artist;
    @Column(name = "price", unique = true)
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
