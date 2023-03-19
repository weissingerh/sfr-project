package kafkamusicproducer.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class Aggregator {

    public String aggregateFields(String values) {

        JSONObject jo = new JSONObject(values);
        JSONArray tracks = jo.getJSONObject("tracks").getJSONArray("track");
        JSONArray modifiedArray = new JSONArray();

        for (int i = 0; i < tracks.length(); i++)
        {
            JSONObject track = tracks.getJSONObject(i);
            String artist = track.getJSONObject("artist").getString("name");
            String title = track.getString("name");
            int playCount = track.getInt("playcount");
            int listeners = track.getInt("listeners");
            JSONObject trackObject = new JSONObject();
            trackObject.put("artist", artist);
            trackObject.put("title", title);
            trackObject.put("playcount", playCount);
            trackObject.put("listeners", listeners);
            modifiedArray.put(trackObject);
        }
        return modifiedArray.toString();
    }

    public String aggregateAverage(String values) {
        JSONObject jo = new JSONObject(values);
        return "";
    }
}
