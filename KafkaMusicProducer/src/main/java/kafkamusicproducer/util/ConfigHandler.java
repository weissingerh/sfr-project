package kafkamusicproducer.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import kafkamusicproducer.serdes.MusicSerdes;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static java.util.stream.Collectors.toMap;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.common.serialization.Serdes.Double;
import static org.apache.kafka.common.serialization.Serdes.Long;
import static org.apache.kafka.streams.StreamsConfig.*;

public class ConfigHandler {


    public Properties loadConsumerJsonConfig() {
        Properties config = loadConfig();
        config.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //config.put(VALUE_DESERIALIZER_CLASS_CONFIG, .class);
        //config.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Long().getClass());
        config.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, MusicSerdes.averageSongs().getClass());
        config.put("specific.avro.reader", true);
        return config;
    }

    protected Properties loadConfig() {

        Properties envProps = this.loadEnvProperties();
        Properties config = new Properties();
        config.putAll(envProps);

        config.put(APPLICATION_ID_CONFIG, envProps.getProperty("application.id"));
        config.put(BOOTSTRAP_SERVERS_CONFIG, envProps.getProperty("spring.kafka.producer.bootstrap-servers"));
        config.put(SCHEMA_REGISTRY_URL_CONFIG, envProps.getProperty("spring.kafka.properties.schema.registry.url"));

        config.put(REPLICATION_FACTOR_CONFIG, 1);
        // config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, envProps.getProperty("offset.reset.policy"));

        config.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

        config.put(ConsumerConfig.GROUP_ID_CONFIG, "music");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return config;
    }

    protected Properties loadEnvProperties() {
        final Config load = ConfigFactory.load();
        final Map<String, Object> map = load.entrySet()
                .stream()
                // ignore java.* and system properties
                .filter(entry -> Stream
                        .of("java", "user", "sun", "os", "http", "ftp", "line", "file", "awt", "gopher", "socks", "path")
                        .noneMatch(s -> entry.getKey().startsWith(s)))
                .peek(
                        filteredEntry -> System.out.println(filteredEntry.getKey() + " : " + filteredEntry.getValue().unwrapped()))
                .collect(toMap(Map.Entry::getKey, y -> y.getValue().unwrapped()));
        Properties props = new Properties();
        props.putAll(map);
        return props;
    }
}
