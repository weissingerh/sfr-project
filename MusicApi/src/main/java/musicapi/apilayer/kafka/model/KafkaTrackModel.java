package musicapi.api.kafka.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import musicapi.api.kafka.model.KafkaArtistModel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaTrackModel {
    private String name;
    private KafkaArtistModel artistModel;
}
