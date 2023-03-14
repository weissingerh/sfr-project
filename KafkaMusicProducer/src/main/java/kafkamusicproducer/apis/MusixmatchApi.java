package kafkamusicproducer.apis;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.springframework.stereotype.Component;

@Component
public class MusixmatchApi {

    String apiKey = "741149a53a78d0473da4ee4525800b64";
    MusixMatch musixMatch = new MusixMatch(apiKey);

    public String getLyrics(String trackName, String artist) throws MusixMatchException {
        Track track = musixMatch.getMatchingTrack(trackName, artist);
        TrackData data = track.getTrack();

        String lyrics = musixMatch.getLyrics(data.getTrackId()).getLyricsBody();

        return lyrics.replace("******* This Lyrics is NOT for Commercial use *******", "");
    }
}
