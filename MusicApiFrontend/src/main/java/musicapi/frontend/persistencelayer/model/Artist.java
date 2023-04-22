package musicapi.frontend.persistencelayer.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Data
@Getter
@Setter
public class Artist {

    @Id
    private int id;
    @Indexed(unique = true)
    private String name;
    @DocumentReference(lazy = true, lookup = "{ 'artist' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Track track;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
