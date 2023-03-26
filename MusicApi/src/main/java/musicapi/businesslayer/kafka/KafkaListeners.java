package musicapi.businesslayer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import musicapi.persistence.model.Track;
import musicapi.persistence.model.repository.TrackRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

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

    //@KafkaListener(topics = "${topic.name.charts.artists}", groupId = "music")
    public void consumeCharts(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicCharts);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

    }



    @KafkaListener(topics = "tracksAggregatedAverage", groupId = "music")
    public void consumeAggregatedAverage(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        log.info("Topic: {}", topicAggregatedAverage);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(payload.value(), Map.class);

        Track track = new Track((String) map.get("artist"), (String) map.get("title"), (Integer) map.get("average"));

        log.info("Saving user." + map);
        trackRepository.save(track);

    }


    @KafkaListener(topics = "${topic.name.lyrics.harrystyles.asitwas}", groupId = "music")
    public void consumeLyrics(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicLyrics);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());
    }


}
