package kafkamusicproducer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    //@Value("${topic.name.charts.artists}")
    //private String topicCharts;
    @Value("topTracks")
    private String topicTracks;

////    @Value("${topic.name.charts.tracks.aggregated}")
////    private String topicTracksAggregated;
////    @Value("${topic.name.charts.tracks.aggregated.average}")
////    private String getTopicTracksAggregatedAverage;
//    @Value("${topic.name.lyrics}")
//    private String topicLyrics;

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final KafkaTemplate<String, String> kafkaTemplateObject;

    private final ConfigHandler configHandler = new ConfigHandler();

    public void sendTracks(String key, Object value){
        final Producer<String, Object> producer =
                new KafkaProducer<>(configHandler.loadProducerJsonConfig());
        producer.send(new ProducerRecord<>(topicTracks, key, value));
        producer.close();
        //kafkaTemplateObject.send(topicTracks, message);
    }
//
////    public void sendAggregated(String message){
////        kafkaTemplateObject.send(topicTracksAggregated, message);
////    }
////
////    public void sendAggregatedAverage(String message){
////        kafkaTemplateObject.send(getTopicTracksAggregatedAverage, message);
////    }


    public void sendCharts(byte[] message){
       // kafkaTemplate.send(topicCharts, message);
    }


//    public void sendLyrics(byte[] message) {
//        kafkaTemplate.send(topicLyrics, message);
//    }
}
