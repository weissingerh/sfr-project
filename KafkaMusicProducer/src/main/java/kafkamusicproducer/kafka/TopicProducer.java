package kafkamusicproducer.kafka;

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
    private final KafkaTemplate<String, byte[]> kafkaTemplateByte;

    public void sendCharts(byte[] message){
        kafkaTemplateByte.send(topicCharts, message);
    }

    public void sendLyrics(String message) {
        log.info("Payload: {}", message);
        System.out.println("Payload " +message);
        kafkaTemplate.send(topicLyrics, message);
    }
}
