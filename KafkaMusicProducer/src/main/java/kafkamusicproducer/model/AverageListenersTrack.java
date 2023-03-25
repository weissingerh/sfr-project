package kafkamusicproducer.model;

import lombok.Data;

@Data
public class AverageListenersTrack {
    private String artist;
    private String title;
    private int average;
}
