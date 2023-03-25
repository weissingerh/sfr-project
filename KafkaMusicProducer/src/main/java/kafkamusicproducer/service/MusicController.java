package kafkamusicproducer.service;

import at.technikum.Track;
import kafkamusicproducer.apis.LastFmApi;
import kafkamusicproducer.kafka.TopicProducer;
import kafkamusicproducer.model.LastFmResponseTracks;
import kafkamusicproducer.serializers.AvroSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class MusicController {
    private final TopicProducer topicProducer;
    private final LastFmApi lastFmApi;

    private final MusicService musicService;

    @CrossOrigin
    @GetMapping("/tracks")
    public void sendTracks() throws Exception {
        LastFmResponseTracks tracks = lastFmApi.getSpecificTrack();
        final Track track = Track.newBuilder()
                .setArtist(tracks.getTrack().getArtist().getName())
                .setListeners(tracks.getTrack().getListeners())
                .setPlaycount(tracks.getTrack().getPlaycount())
                .setTitle(tracks.getTrack().getName())
                .build();
        topicProducer.sendTracks("key", track);
    }

    @CrossOrigin
    @GetMapping("/tracksAverage")
    public void sendTrackAverage() throws Exception {
        musicService.aggregateMusicStreams();
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
