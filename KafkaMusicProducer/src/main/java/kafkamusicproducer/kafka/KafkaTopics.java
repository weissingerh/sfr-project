package kafkamusicproducer.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum KafkaTopics {
    CHARTS_TOP_ARTISTS("topArtists"),
    CHARTS_TOP_TRACKS("topTracks"),
    TRACKS_SOURCE_TOPIC("tracks"),
    TRACKS_AGGREGATED("tracksAggregated"),
    TRACKS_AGGREGATED_AVERAGE("tracksAggregatedAverage"),
    LYRICS("lyrics");

    public final String value;


}
