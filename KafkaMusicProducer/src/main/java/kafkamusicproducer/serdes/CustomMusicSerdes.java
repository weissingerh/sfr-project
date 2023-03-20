package kafkamusicproducer.serdes;

import kafkamusicproducer.model.AverageListenersPerArtist;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class CustomMusicSerdes extends Serdes.WrapperSerde {

    public CustomMusicSerdes(Serializer serializer, Deserializer deserializer) {
        super(serializer, deserializer);
    }

    public static Serde<AverageListenersPerArtist> averageSongs() {
        JsonSerializer<AverageListenersPerArtist> serializer = new JsonSerializer<>();
        JsonDeserializer<AverageListenersPerArtist> deserializer = new JsonDeserializer<>(AverageListenersPerArtist.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
