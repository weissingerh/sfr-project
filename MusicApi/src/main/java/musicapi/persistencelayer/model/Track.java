package musicapi.persistencelayer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@Document
@NoArgsConstructor
public class Track {

    @Id
    private String id;
    @DocumentReference(lazy = true)
    private Artist artist;
    private String title;

    private int average;

    @DocumentReference(lazy = true, lookup = "{ 'track' : ?#{#self._id} }")
    @ReadOnlyProperty
    private TopTrack topTrack;

    public Track(Artist artist, String title, int averageTimesListenedPerUser) {
        this.id = artist.getName().replace(" ", "") +  "_" + title.replace(" ", "");
        this.artist = artist;
//        this.artist = new Artist(artist);
        this.title = title;
        this.average = averageTimesListenedPerUser;
    }

}
