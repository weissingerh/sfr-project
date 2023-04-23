package kafkamusicproducer.service;

import at.technikum.Track;
import kafkamusicproducer.apis.LastFmApi;
import kafkamusicproducer.apis.MusixmatchApi;
import kafkamusicproducer.kafka.KafkaTopics;
import kafkamusicproducer.kafka.TopicProducer;
import kafkamusicproducer.model.LastFmResponseChartsArtists;
import kafkamusicproducer.model.LastFmResponseTrack;
import kafkamusicproducer.model.LastFmResponseTracksObject;
import kafkamusicproducer.model.Song;
import kafkamusicproducer.serializers.AvroSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kafka")
public class MusicController {
    private final TopicProducer topicProducer;
    private final LastFmApi lastFmApi;
    private final MusixmatchApi musixmatchApi;
    private final MusicService musicService;

    @CrossOrigin
    @GetMapping("/track/{artist}/{title}")
    public void sendTracks(@PathVariable String artist, @PathVariable String title) throws Exception {
        LastFmResponseTrack tracks = lastFmApi.getSpecificTrack(artist, title);
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

    @GetMapping("/charts/tracks")
    public void sendChartsTracks() throws Exception {
        LastFmResponseTracksObject tracks = lastFmApi.getChartTracks();
        topicProducer.sendChartsTracks(tracks.getTracks().getTrack());
    }
    @GetMapping("/charts/artists")
    public void sendChartsArtists() throws Exception {
        LastFmResponseChartsArtists tracks = lastFmApi.getArtists();
        topicProducer.sendCharts(tracks.getArtists());
    }

    @GetMapping("/lyrics/{artist}/{title}")
    public void sendLyrics(@PathVariable String artist, @PathVariable String title) throws Exception {
        Song song = new MusixmatchApi().getLyrics(title, artist);
//        byte[] serializedData = AvroSerializer.fromJsonToAvro(lyrics, musixmatchApi.schema);
        topicProducer.sendLyrics(song);
    }


}
