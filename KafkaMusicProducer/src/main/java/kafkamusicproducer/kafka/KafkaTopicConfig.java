package kafkamusicproducer.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaTopicConfig {

//    @Value(value = "${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapAddress;
//    @Value("${application.id}")
//    private String applicationId;
////    @Value("${topic.name.charts.artists}")
////    private String topicCharts;
//    @Value("${topic.name.charts.tracks}")
//    private String topicTracks;
////    @Value("${topic.name.charts.tracks.aggregated}")
////    private String topicTracksAggregated;
////    @Value("${topic.name.charts.tracks.aggregated.average}")
////    private String topicTracksAggregatedAverage;
//    @Value("${topic.name.lyrics}")
//    private String topicLyrics;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        return new KafkaAdmin(configs);
//    }
//
////    @Bean
////    public NewTopic topArtists() {
////        return new NewTopic(topicCharts, 3, (short) 1);
////    }
//    @Bean
//    public NewTopic topTracks() {
//        return new NewTopic(topicTracks, 3, (short) 1);
//    }
////    @Bean
////    public NewTopic topTracksAggregated() {
////        return new NewTopic(topicTracksAggregated, 3, (short) 1);
////    }
////    @Bean
////    public NewTopic topTracksAggregatedAverage() {
////        return new NewTopic(topicTracksAggregatedAverage, 3, (short) 1);
////    }
//    @Bean
//    public NewTopic lyrics() {
//        return new NewTopic( topicLyrics, 3, (short) 1);
//    }
}
