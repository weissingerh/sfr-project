package musicapi.api.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import musicapi.persistencelayer.model.TopTrack;
import musicapi.persistencelayer.model.Track;
import musicapi.persistencelayer.repository.ArtistRepository;
import musicapi.persistencelayer.repository.TopTracksRepository;
import musicapi.persistencelayer.repository.TrackRepository;
import musicapi.persistencelayer.model.Artist;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

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
    private final TopTracksRepository topTrackRepository;
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
        List<TopTrack> topTracks = new ArrayList<>();

        for (int i = 0; i < allTracks.size(); i++) {

            Map trackData = allTracks.get(i);
            Map artistData = (Map) trackData.get("artist");
            Artist artist = getArtistByName((String) artistData.get("name"));
            int average = (Integer) trackData.get("playcount") / (Integer) trackData.get("listeners");

            //generates id and we have an object to create in db if not available
            Track track = new Track(artist, (String) trackData.get("name"), average);
            trackRepository.save(track);

            TopTrack topTrack = new TopTrack();
            topTrack.setTrack(track);
            topTrack.setPlace(i+1);
            topTracks.add(topTrack);

        }

        topTrackRepository.saveAll(topTracks);

    }

    @KafkaListener(topics = "tracksAggregatedAverage", groupId = "music")
    public void consumeAggregatedAverage(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Topic: {}", topicAggregatedAverage);

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(payload.value(), Map.class);

        Artist artist = getArtistByName((String) map.get("name"));
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


    public Artist getArtistByName(String name) {
        Artist artist = artistRepository.findItemByName(name);
        if(artist == null){
            int newId = artistRepository.findAll().size();
            artist = new Artist(newId, name);
            artistRepository.save(artist);
        }
        return artist;
    }

}
