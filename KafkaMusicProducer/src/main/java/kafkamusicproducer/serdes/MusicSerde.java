package kafkamusicproducer.serdes;

import at.technikum.Track;
import kafkamusicproducer.serializers.JsonDeserializer;
import kafkamusicproducer.serializers.JsonSerializer;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

@NoArgsConstructor
public class MusicSerde implements Serde<Track> {
    @Override
    public Serializer<Track> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<Track> deserializer() {
        return new JsonDeserializer<>(Track.class);
    }

}
