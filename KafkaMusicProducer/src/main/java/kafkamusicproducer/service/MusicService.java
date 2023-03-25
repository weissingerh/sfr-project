package kafkamusicproducer.service;

import at.technikum.Track;
import kafkamusicproducer.kafka.KafkaTopics;
import kafkamusicproducer.model.AggregatedAverageListeners;
import kafkamusicproducer.model.AverageListenersTrack;
import kafkamusicproducer.serdes.MusicAggregatedAverageSerde;
import kafkamusicproducer.kafka.ConfigHandler;
import kafkamusicproducer.serdes.TrackAverageSerde;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final ConfigHandler configHandler = new ConfigHandler();

    public void aggregateMusicStreams(String sourceTopic) {

        final Topology topology;
        final StreamsBuilder streamsBuilder;
        final KafkaStreams kafkaStreams;
        final Properties properties;

        properties = configHandler.loadJsonConfig();
        streamsBuilder = new StreamsBuilder();

        streamsBuilder.<String, Track>stream(sourceTopic)
                .groupBy((key, value) -> value.getArtist())
                .aggregate(this::getAverageTopSongs, (key, value, aggregator) -> {
                    aggregator.setTitle(value.getTitle());
                    aggregator.setArtist(value.getArtist());
                    aggregator.getAverageListeners().add(value.getListeners());
                    aggregator.getAveragePlaycount().add(value.getPlaycount());
                    setNewAverage(aggregator);
                    return aggregator;
                }, Materialized.<String, AggregatedAverageListeners, KeyValueStore<Bytes, byte[]>>as(KafkaTopics.TRACKS_AGGREGATED.value)
                        .withKeySerde(Serdes.String())
                        .withValueSerde(new MusicAggregatedAverageSerde()))
                .toStream()
                .mapValues(this::getAverage)
                .to(KafkaTopics.TRACKS_AGGREGATED_AVERAGE.value, Produced.with(Serdes.String(), new TrackAverageSerde()));

        topology = streamsBuilder.build();
        kafkaStreams = new KafkaStreams(topology, properties);

        kafkaStreams.start();

    }

    private AverageListenersTrack getAverage(final AggregatedAverageListeners average) {
        AverageListenersTrack track = new AverageListenersTrack();
        track.setArtist(average.getArtist());
        track.setTitle(average.getTitle());
        track.setAverage(average.getAverage());
        return track;
    }

    private AggregatedAverageListeners getAverageTopSongs() {
        final var avg = new AggregatedAverageListeners();
        avg.setAverageListeners(new ArrayList<>());
        avg.setAveragePlaycount(new ArrayList<>());
        return avg;
    }

    private void setNewAverage(AggregatedAverageListeners aggregator) {
        final Double listeners = aggregator.getAverageListeners().stream()
                .mapToDouble(p -> p)
                .sum();
        final Double playcount = aggregator.getAveragePlaycount().stream()
                .mapToDouble(p -> p)
                .sum();
        int avgListeners = (int) (listeners / (double) aggregator.getAverageListeners().size());
        int avgPlaycount = (int) (playcount / (double) aggregator.getAverageListeners().size());
        aggregator.setAverage(avgPlaycount/avgListeners);
    }

}
