package kafkamusicproducer.serdes;

import kafkamusicproducer.model.AverageListenersPerArtist;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class MusicSerdes {

    public static Serde<AverageListenersPerArtist> averageSongs() {
        JsonSerializer<AverageListenersPerArtist> serializer = new JsonSerializer<>();
        JsonDeserializer<AverageListenersPerArtist> deserializer = new JsonDeserializer<>(AverageListenersPerArtist.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
