package kafkamusicproducer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    private final KafkaTemplate<String, Object> kafkaTemplateObject;

    private final ConfigHandler configHandler = new ConfigHandler();

    public void sendTracks(String artistTopic, Object value){
        kafkaTemplateObject.send(artistTopic, value);
    }

    public void sendCharts(Object charts){
        kafkaTemplateObject.send(KafkaTopics.CHARTS_TOP_ARTISTS.value, charts);
    }

    public void sendChartsTracks(Object charts){
        kafkaTemplateObject.send(KafkaTopics.CHARTS_TOP_TRACKS.value, charts);
    }

    public void sendChartsAvro(byte[] message){
        //Serializer muss auf Avro umgestellt werden
        final Producer<String, Object> producer =
                new KafkaProducer<>(configHandler.loadAvroConfig());
        producer.send(new ProducerRecord<>(KafkaTopics.CHARTS_TOP_ARTISTS.value, message));
        producer.close();
    }


    public void sendLyrics(Object lyrics) {
        kafkaTemplateObject.send(KafkaTopics.LYRICS.value, lyrics);
        //Serializer muss auf Avro umgestellt werden
//        final Producer<String, Object> producer =
//                new KafkaProducer<>(configHandler.loadAvroConfig());
//        producer.send(new ProducerRecord<>(KafkaTopics.LYRICS.value, lyrics));
//        producer.close();
    }
}
