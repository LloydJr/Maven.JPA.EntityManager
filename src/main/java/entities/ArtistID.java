package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Artist_Id")
public class ArtistID implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @Column(name = "cd_id", nullable = false)
    private Integer cdId;
    @Column(name = "artist_id", nullable = false)
    private Integer artistId;

    public Integer getCdId() {
        return cdId;
    }

    public void setCdId(Integer cdId) {
        this.cdId = cdId;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }
}
