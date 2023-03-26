package musicapi.api.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import musicapi.persistence.model.Track;
import musicapi.persistence.model.repository.ArtistRepository;
import musicapi.persistence.model.repository.TrackRepository;
import musicapi.persistencelayer.model.Artist;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaListeners {

    @Value("${topic.name.charts.artists}")
    private String topicCharts;
    @Value("${topic.name.charts.tracks.aggregated.average}")
    private String topicAggregatedAverage;
    @Value("${topic.name.lyrics.harrystyles.asitwas}")
    private String topicLyrics;

    @Autowired
    private final TrackRepository trackRepository;
    @Autowired
    private final ArtistRepository artistRepository;

   // @KafkaListener(topics = "${topic.name.charts.artists}", groupId = "music")
    public void consumeCharts(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Topic: {}", topicCharts);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(payload.value(), Map.class);
        System.out.println("test");

//        Artist artist = new Artist((String) map.get("artist"));
        //Track track = new Track(artist, (String) map.get("title"), (Integer) map.get("average"));

    }

    @KafkaListener(topics = "${topic.name.charts.tracks}", groupId = "music")
    public void consumeChartsTracks(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Topic: {}", "topTracks");
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

        ObjectMapper mapper = new ObjectMapper();
        List<Map> allTracks = mapper.readValue(payload.value(), List.class);
        System.out.println("test");

        for (Map trackData : allTracks) {
            Map artistData = (Map) trackData.get("artist");
            int newId = artistRepository.findAll().size();
            Artist artist = new Artist(newId, (String) artistData.get("name"));
            artistRepository.save(artist);
            int average = (Integer) trackData.get("playcount") / (Integer) trackData.get("listeners");


            Track track = new Track(artist, (String) trackData.get("name"), average);
            trackRepository.save(track);
        }

        log.info("Value: {}", payload.value());
        //Track track = new Track((String) map.get(0).get("artist"), (String) map.get("title"), (Integer) map.get("average"));

    }

    @KafkaListener(topics = "tracksAggregatedAverage", groupId = "music")
    public void consumeAggregatedAverage(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Topic: {}", topicAggregatedAverage);

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(payload.value(), Map.class);
        int newId = artistRepository.findAll().size();
        Artist artist = new Artist(newId, (String) map.get("name"));
        artistRepository.save(artist);
        Track track = new Track(artist, (String) map.get("title"), (Integer) map.get("average"));

        trackRepository.save(track);
    }

    @KafkaListener(topics = "${topic.name.lyrics.harrystyles.asitwas}", groupId = "music")
    public void consumeLyrics(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Topic: {}", topicLyrics);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

//        ObjectMapper mapper = new ObjectMapper();
//        Map map = mapper.readValue(payload.value(), Map.class);
//        Track track = new Track((String) map.get("artist"), (String) map.get("title"), (Integer) map.get("average"));
    }


}
