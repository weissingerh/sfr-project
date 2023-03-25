package kafkamusicproducer.apis;

import kafkamusicproducer.model.LastFmResponseTracks;
import kafkamusicproducer.model.LastFmTrack;
import org.json.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LastFmApi {
    public final String schema = "{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"topTracks\",\"namespace\":\"at.technikum\",\"doc\":\"Top tracks provided by last fm api\",\"fields\":[{\"name\":\"artist\",\"type\":\"string\"},{\"name\":\"playcount\",\"type\":\"int\"},{\"name\":\"title\",\"type\":\"string\"}]}}";
    private String apiKey = "f38a02a34f90cb32fe8308daf0e8f748";
    //unktioniert nicht: @Value("${lastfm.url.getArtists}")

    private String getArtistsUrl = "https://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=%s&format=json";
    //unktioniert nicht: @Value("${lastfm.url.getTracks}")
    private String getTracksUrl = "https://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=%s&format=json&limit=10";
    private String getSpecificTrackUrl = "https://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=%s&artist=%s&track=%s&format=json";

    public String getArtists(){
        String url = String.format(getArtistsUrl, apiKey);
        return new RestTemplate().getForObject(url, String.class);
    }

    public LastFmResponseTracks getSpecificTrack(String artist, String title) {
        String url = String.format(getSpecificTrackUrl, apiKey, artist, title);
        //String response = new RestTemplate().getForObject(url, String.class);
       // return response;
        final ResponseEntity<LastFmResponseTracks> response =
                new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(null, null), LastFmResponseTracks.class);

        return response.getBody();
        //return this.getModifiedTracks(response);
    }

}
