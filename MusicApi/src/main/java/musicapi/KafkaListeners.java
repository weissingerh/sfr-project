package musicapi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaListeners {

    @Value("${topic.name.charts}")
    private String topicCharts;

    @Value("${topic.name.lyrics}")
    private String topicLyrics;

    @KafkaListener(topics = "${topic.name.charts}", groupId = "music")
    public void consumeCharts(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicCharts);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

    }

    @KafkaListener(topics = "${topic.name.charts}", groupId = "music")
    public void consumeCharts1(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicCharts);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

    }

    @KafkaListener(topics = "${topic.name.lyrics}", groupId = "music")
    public void consumeLyrics(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", topicLyrics);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Value: {}", payload.value());

    }

}
