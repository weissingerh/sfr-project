package kafkamusicproducer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmResponseTracksObject {
    private LastFmResponseTracks tracks;

}
