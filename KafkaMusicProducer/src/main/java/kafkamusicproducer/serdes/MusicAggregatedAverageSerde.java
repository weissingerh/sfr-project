package kafkamusicproducer.serdes;

import kafkamusicproducer.model.AggregatedAverageListeners;
import kafkamusicproducer.serializers.JsonDeserializer;
import kafkamusicproducer.serializers.JsonSerializer;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;


@NoArgsConstructor
public class MusicAggregatedAverageSerde implements Serde<AggregatedAverageListeners> {

    @Override
    public Serializer<AggregatedAverageListeners> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<AggregatedAverageListeners> deserializer() {
        return new JsonDeserializer<>(AggregatedAverageListeners.class);
    }

}
