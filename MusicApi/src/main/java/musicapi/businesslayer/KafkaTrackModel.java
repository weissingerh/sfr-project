package musicapi.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaTrackModel {
    private String name;
    private KafkaArtistModel artistModel;
}
