package kafkamusicproducer.service;

import at.technikum.Track;
import kafkamusicproducer.apis.LastFmApi;
import kafkamusicproducer.kafka.KafkaTopics;
import kafkamusicproducer.kafka.TopicProducer;
import kafkamusicproducer.model.LastFmResponseTracks;
import kafkamusicproducer.serializers.AvroSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class MusicController {
    private final TopicProducer topicProducer;
    private final LastFmApi lastFmApi;

    private final MusicService musicService;

    @CrossOrigin
    @GetMapping("/track/{artist}/{title}")
    public void sendTracks(@PathVariable String artist, @PathVariable String title) throws Exception {
        LastFmResponseTracks tracks = lastFmApi.getSpecificTrack(artist, title);
        final Track track = Track.newBuilder()
                .setArtist(tracks.getTrack().getArtist().getName())
                .setListeners(tracks.getTrack().getListeners())
                .setPlaycount(tracks.getTrack().getPlaycount())
                .setTitle(tracks.getTrack().getName())
                .build();
        topicProducer.sendTracks(KafkaTopics.TRACKS_SOURCE_TOPIC.value, track);
    }

    @CrossOrigin
    @GetMapping("/tracksAverage")
    public void sendTrackAverage() throws Exception {
        musicService.aggregateMusicStreams(KafkaTopics.TRACKS_SOURCE_TOPIC.value);
    }

    @GetMapping("/charts")
    public void sendCharts() throws Exception {
        String tracks = lastFmApi.getArtists();
        byte[] avroByteArray = AvroSerializer.fromJsonToAvro(tracks, lastFmApi.schema);
        System.out.println(tracks);
        topicProducer.sendCharts(avroByteArray);
    }

//    @GetMapping("/lyrics")
//    public void send2() throws Exception {
//        String lyrics = new MusixmatchApi().getLyrics("As It Was", "Harry Styles");
//        System.out.println(lyrics);
//        //byte[] serializedData = AvroSerializer.fromJsonToAvro(lyrics, musixmatchApi.schema);
//        //topicProducer.sendLyrics(serializedData);
//    }


}
