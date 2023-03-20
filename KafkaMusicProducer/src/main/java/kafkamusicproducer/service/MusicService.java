package kafkamusicproducer.service;

import at.technikum.Track;
import kafkamusicproducer.model.AverageListenersPerArtist;
import kafkamusicproducer.serdes.MusicSerdes;
import kafkamusicproducer.util.ConfigHandler;
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

    public void aggregateMusicStreams() {
        final Properties properties = configHandler.loadConsumerJsonConfig();
        final StreamsBuilder streamsBuilder = new StreamsBuilder();

        streamsBuilder.<String, Track>stream("topTracks")
                .groupBy((key, value) -> value.getArtist())
                .aggregate(this::getAverageTopSongs, (key, value, aggregator) -> {
                    aggregator.getAverageListeners().add(value.getListeners());
                    setNewAverage(aggregator);
                    return aggregator;
                }, Materialized.<String, AverageListenersPerArtist, KeyValueStore<Bytes, byte[]>>as("topTracksAggregated")
                        .withValueSerde(MusicSerdes.averageSongs()))
                .toStream()
                .to("topTracksAveragePlaysPerListener", Produced.with(Serdes.String(), MusicSerdes.averageSongs()));

        final Topology topology = streamsBuilder.build();
        final KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);

        kafkaStreams.start();

    }

    private AverageListenersPerArtist getAverageTopSongs() {
        final var avg = new AverageListenersPerArtist();
        avg.setAverageListeners(new ArrayList<>());
        return avg;
    }

    private void setNewAverage(AverageListenersPerArtist aggregator) {
        final Double sum = aggregator.getAverageListeners().stream()
                .mapToDouble(p -> p)
                .sum();
        aggregator.setAverage((int) (sum / (double) aggregator.getAverageListeners().size()));
    }

}
