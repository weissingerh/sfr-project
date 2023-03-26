package musicapi.persistence.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Track {

    @Id
    private String trackId;
    private String artist;
    private String title;
    private int average;

    public Track(String artist, String title, int averageTimesListenedPerUser) {
        this.trackId = artist.replace(" ", "") +  "_" + title.replace(" ", "");
        this.artist = artist;
        this.title = title;
        this.average = averageTimesListenedPerUser;
    }
}
