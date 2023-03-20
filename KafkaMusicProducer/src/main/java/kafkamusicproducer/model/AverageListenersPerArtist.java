package kafkamusicproducer.model;

import lombok.Data;

import java.util.List;

@Data
public class AverageListenersPerArtist {

    private String artist;
    private List<Integer> averageListeners;
    private int average;
}
