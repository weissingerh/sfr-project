package kafkamusicproducer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.framework.qual.NoQualifierParameter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    private String title;
    private String artist;
    private String lyrics;
}
