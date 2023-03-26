package kafkamusicproducer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmResponseChartsArtists {
    private List<LastFmArtist> artists;
}
