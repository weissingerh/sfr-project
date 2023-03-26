package kafkamusicproducer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmBasicTrack {

    private String name;
    private int playcount;
    private int listeners;
    private LastFmArtist artist;
}
