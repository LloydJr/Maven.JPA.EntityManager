package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Artist")
public class Artist implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "firstName", unique = true)
    private String firstName;
    @Column(name = "lastName", unique = true)
    private String lastName;
    @Column(name = "instrument", unique = true)
    private String instrument;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
