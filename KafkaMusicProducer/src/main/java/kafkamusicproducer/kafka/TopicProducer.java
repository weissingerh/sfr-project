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

    @Value("${topic.name.charts.artists}")
    private String topicCharts;
    @Value("${topic.name.lyrics}")
    private String topicLyrics;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendCharts(byte[] message){
        kafkaTemplate.send(topicCharts, message);
    }

    public void sendLyrics(byte[] message) {
        kafkaTemplate.send(topicLyrics, message);
    }
}
