package kafkamusicproducer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmResponseTracks {

//    private LastFmArtist artist;
    private List<LastFmBasicTrack> track;

}
