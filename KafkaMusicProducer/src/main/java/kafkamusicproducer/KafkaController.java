package kafkamusicproducer;

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
        topicProducer.sendCharts("sent my first charts");
    }

    @GetMapping("/lyrics")
    public void send2(){
        topicProducer.sendLyrics("sent my first lyrics");
    }
}
