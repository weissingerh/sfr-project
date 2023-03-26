package musicapi.persistence.model;

import lombok.Getter;
import lombok.Setter;
import musicapi.persistencelayer.model.Artist;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@Document
public class Track {

    @Id
    private String id;
    @DocumentReference(lazy = true)
    private Artist artist;
    private String title;

    private int average;

    public Track(Artist artist, String title, int averageTimesListenedPerUser) {
        this.id = artist.getName().replace(" ", "") +  "_" + title.replace(" ", "");
        this.artist = artist;
//        this.artist = new Artist(artist);
        this.title = title;
        this.average = averageTimesListenedPerUser;
    }

}
