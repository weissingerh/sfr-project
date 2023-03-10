package kafkamusicproducer.kafka;

import kafkamusicproducer.apis.LastFmApi;
import kafkamusicproducer.apis.MusixmatchApi;
import lombok.RequiredArgsConstructor;
import org.jmusixmatch.MusixMatchException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;
    private final LastFmApi lastFmApi;
    @GetMapping("/charts")
    public void send1() throws Exception {
        String tracks = lastFmApi.getTracks();
        byte[] avroByteArray = AvroSerializer.fromJsonToAvro(tracks, lastFmApi.schema);
        System.out.println(tracks);
        topicProducer.sendCharts(avroByteArray);
    }

    @GetMapping("/lyrics")
    public void send2() throws MusixMatchException {
        String lyrics = new MusixmatchApi().getLyrics("As It Was", "Harry Styles");
        System.out.println(lyrics);
        topicProducer.sendLyrics(lyrics);
    }
}
