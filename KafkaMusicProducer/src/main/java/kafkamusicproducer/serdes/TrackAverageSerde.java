package kafkamusicproducer.serdes;

import kafkamusicproducer.model.AggregatedAverageListeners;
import kafkamusicproducer.model.AverageListenersTrack;
import kafkamusicproducer.serializers.JsonDeserializer;
import kafkamusicproducer.serializers.JsonSerializer;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;


@NoArgsConstructor
public class TrackAverageSerde implements Serde<AverageListenersTrack> {

    @Override
    public Serializer<AverageListenersTrack> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<AverageListenersTrack> deserializer() {
        return new JsonDeserializer<>(AverageListenersTrack.class);
    }

}
