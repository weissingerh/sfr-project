package kafkamusicproducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.charts}")
    private String topicCharts;
    @Value("${topic.name.lyrics}")
    private String topicLyrics;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendCharts(String message){
        log.info("Payload: {}", message);
        kafkaTemplate.send(topicCharts, message);
    }

    public void sendLyrics(String message){
        log.info("Payload: {}", message);
        kafkaTemplate.send(topicLyrics, message);
    }
}
