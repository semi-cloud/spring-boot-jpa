package jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item{

    private String artist;
    private String stc;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getStc() {
        return stc;
    }

    public void setStc(String stc) {
        this.stc = stc;
    }
}
