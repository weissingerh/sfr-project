package kafkamusicproducer.serdes;

import kafkamusicproducer.model.AverageListenersPerArtist;
import kafkamusicproducer.serializers.JsonDeserializer;
import kafkamusicproducer.serializers.JsonSerializer;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;


@NoArgsConstructor
public class MusicAverageSerde implements Serde<AverageListenersPerArtist> {

    @Override
    public Serializer<AverageListenersPerArtist> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<AverageListenersPerArtist> deserializer() {
        return new JsonDeserializer<>(AverageListenersPerArtist.class);
    }

}
