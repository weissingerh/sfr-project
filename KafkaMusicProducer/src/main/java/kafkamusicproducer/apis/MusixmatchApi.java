package kafkamusicproducer.apis;

import kafkamusicproducer.model.Song;
import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MusixmatchApi {
    public String schema = "{\"type\" : \"record\",\"name\" : \"lyrics\",\"namespace\" : \"at.technikum\",\"doc\" : \"Lyrics from musixmatch api\",\"fields\" : [ {\"name\" : \"artist\",\"type\" : \"string\"}, {\"name\" : \"name\",\"type\" : \"string\"}, {\"name\" : \"lyrics\",\"type\" : \"string\"} ]}";
    String apiKey = "741149a53a78d0473da4ee4525800b64";
    MusixMatch musixMatch = new MusixMatch(apiKey);

    public Song getLyrics(String trackName, String artist) throws MusixMatchException {
        Track track = musixMatch.getMatchingTrack(trackName, artist);
        TrackData data = track.getTrack();

        String lyrics = musixMatch.getLyrics(data.getTrackId()).getLyricsBody();
        lyrics = lyrics.replace("******* This Lyrics is NOT for Commercial use *******", "");
        return new Song(trackName, artist, lyrics);
    }

    private String getModifiedLyrics(String lyrics, String trackName, String artist){
        String modifiedLyrics = lyrics.replace("******* This Lyrics is NOT for Commercial use *******", "");
        JSONObject jsonLyrics = new JSONObject();
        jsonLyrics.put("lyrics", modifiedLyrics);
        jsonLyrics.put("title", trackName);
        jsonLyrics.put("artist", artist);
        return jsonLyrics.toString();
    }
}
