package kafkamusicproducer.kafka;

import kafkamusicproducer.apis.LastFmApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final TopicProducer topicProducer;

    @GetMapping("/charts")
    public void send1(){
        String artists = new LastFmApi().getArtists();
        System.out.println(artists);
        topicProducer.sendCharts(artists);
    }

    @GetMapping("/lyrics")
    public void send2(){
        String tracks = new LastFmApi().getTracks();
        topicProducer.sendLyrics(tracks);
    }
}
