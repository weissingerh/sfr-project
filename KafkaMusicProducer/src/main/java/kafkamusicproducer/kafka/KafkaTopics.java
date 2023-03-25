package kafkamusicproducer.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KafkaTopics {
    CHARTS_TOP_ARTISTS("topArtists"),
    TRACKS_SOURCE_TOPIC("tracks"),
    TRACKS_AGGREGATED("tracksAggregated"),
    TRACKS_AGGREGATED_AVERAGE("tracksAggregatedAverage"),
    LYRICS_HARRYSTYLES_ASITWAS("lyrics_HarryStyles_AsItWas");

    public final String value;


}
