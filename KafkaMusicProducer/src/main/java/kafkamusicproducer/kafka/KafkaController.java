package kafkamusicproducer.kafka;

import kafkamusicproducer.apis.LastFmApi;
import kafkamusicproducer.apis.MusixmatchApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;
    private final LastFmApi lastFmApi;

    private final MusixmatchApi musixmatchApi;
    @GetMapping("/charts")
    public void send1() throws Exception {
        String tracks = lastFmApi.getTracks();
        byte[] avroByteArray = AvroSerializer.fromJsonToAvro(tracks, lastFmApi.schema);
        System.out.println(tracks);
        topicProducer.sendCharts(avroByteArray);
    }

    @GetMapping("/lyrics")
    public void send2() throws Exception {
        String lyrics = new MusixmatchApi().getLyrics("As It Was", "Harry Styles");
        System.out.println(lyrics);
        byte[] serializedData = AvroSerializer.fromJsonToAvro(lyrics, musixmatchApi.schema);
        topicProducer.sendLyrics(serializedData);
    }
}
