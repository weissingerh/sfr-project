package musicapi.persistencelayer.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongLyrics {

    @Id
    private int id;
    @Indexed(unique = true)
    private String title;
    @DocumentReference(lazy = true)
    private Artist artist;
    private String lyrics;

}
