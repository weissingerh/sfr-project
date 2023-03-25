package kafkamusicproducer.model;

import lombok.Data;

import java.util.List;

@Data
public class AggregatedAverageListeners {

    private String artist;
    private String title;
    private List<Integer> averageListeners;
    private List<Integer> averagePlaycount;
    private int average;
}
