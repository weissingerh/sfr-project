package kafkamusicproducer.kafka;

import kafkamusicproducer.apis.LastFmApi;
import kafkamusicproducer.apis.MusixmatchApi;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;
    private final LastFmApi lastFmApi;
    @Value("${topic.name.charts.tracks}")
    private String topicTracks;
    private final MusixmatchApi musixmatchApi;

    @Value("${topic.name.charts.tracks.aggregated}")
    private String topicTracksAggregated;
    @Value("${topic.name.charts.tracks.aggregated.average}")
    private String topicTracksAggregatedAverage;

    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapAddress;
    @Value("${application.id}")
    private String applicationId;

    @GetMapping("/tracks")
    public void send0() throws Exception {
        String tracks = lastFmApi.getArtists();
        topicProducer.sendAggregated(tracks);
    }

    //@GetMapping("/charts")
    //    public void send1() throws Exception {
    //        String tracks = lastFmApi.getArtists();
    //        byte[] avroByteArray = AvroSerializer.fromJsonToAvro(tracks, lastFmApi.schema);
    //        System.out.println(tracks);
    //        topicProducer.sendCharts(avroByteArray);
    //    }

    @GetMapping("/lyrics")
    public void send2() throws Exception {
        String lyrics = new MusixmatchApi().getLyrics("As It Was", "Harry Styles");
        System.out.println(lyrics);
        byte[] serializedData = AvroSerializer.fromJsonToAvro(lyrics, musixmatchApi.schema);
        topicProducer.sendLyrics(serializedData);
    }


}
