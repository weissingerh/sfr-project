package kafkamusicproducer.apis;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LastFmApi {

    //unktioniert nicht: @Value("${lastfm.apikey}")
    private String apiKey = "f38a02a34f90cb32fe8308daf0e8f748";
    //unktioniert nicht: @Value("${lastfm.url.getArtists}")
    private String getArtistsUrl = "https://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=%s&format=json";
    //unktioniert nicht: @Value("${lastfm.url.getTracks}")
    private String getTracksUrl = "https://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=%s&format=json";

    public String getArtists(){
        String url = String.format(getArtistsUrl, apiKey);
        return new RestTemplate().getForObject(url, String.class);
    }

    public String getTracks(){
        String url = String.format(getTracksUrl, apiKey);
        return new RestTemplate().getForObject(url, String.class);
    }
}
