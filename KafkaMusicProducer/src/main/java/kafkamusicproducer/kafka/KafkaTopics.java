package kafkamusicproducer.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KafkaTopics {
    CHARTS_TOP_ARTISTS("topArtists"),
    TRACKS_CHER_BELIEVE("Cher_Believe"),
    TRACKS_HARRYSTYLES_ASITWAS("HarryStyles_AsItWas"),
    TRACKS_AGGREGATED("tracksAggregated"),
    TRACKS_AGGREGATED_AVERAGE("tracksAggregatedAverage"),
    LYRICS_HARRYSTYLES_ASITWAS("lysrics_HarryStyles_AsItWas");

    public final String value;


}
