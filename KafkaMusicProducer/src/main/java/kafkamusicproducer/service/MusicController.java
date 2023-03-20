package kafkamusicproducer.service;

import kafkamusicproducer.apis.LastFmApi;
import kafkamusicproducer.apis.MusixmatchApi;
import kafkamusicproducer.kafka.TopicProducer;
import kafkamusicproducer.serdes.MusicSerdes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class MusicController {
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

    private final MusicService musicService;

    @CrossOrigin
    @GetMapping("/tracks")
    public void send0() throws Exception {
        musicService.aggregateMusicStreams();
       // String tracks = lastFmApi.getArtists();
     //   topicProducer.sendAggregated(tracks);
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
        //byte[] serializedData = AvroSerializer.fromJsonToAvro(lyrics, musixmatchApi.schema);
        //topicProducer.sendLyrics(serializedData);
    }


}
