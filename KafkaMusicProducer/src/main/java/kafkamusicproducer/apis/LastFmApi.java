package kafkamusicproducer.apis;

import org.json.*;
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

    public String getArtists(){
        String url = String.format(getArtistsUrl, apiKey);
        return new RestTemplate().getForObject(url, String.class);
    }

    public String getTracks() {
        String url = String.format(getTracksUrl, apiKey);
        String response = new RestTemplate().getForObject(url, String.class);
        return this.getModifiedTracks(response);
    }

    private String getModifiedTracks(String response) {
        JSONObject jo = new JSONObject(response);
        JSONArray tracks = jo.getJSONObject("tracks").getJSONArray("track");
        JSONArray modifiedArray = new JSONArray();

        for (int i = 0; i < tracks.length(); i++)
        {
            JSONObject track = tracks.getJSONObject(i);
            String artist = track.getJSONObject("artist").getString("name");
            String title = track.getString("name");
            int playCount = track.getInt("playcount");
            JSONObject trackObject = new JSONObject();
            trackObject.put("artist", artist);
            trackObject.put("title", title);
            trackObject.put("playcount", playCount);
            modifiedArray.put(trackObject);
        }
        return modifiedArray.toString();
    }
}
