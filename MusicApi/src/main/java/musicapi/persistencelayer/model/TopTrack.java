package musicapi.persistencelayer.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Document
public class TopTrack {

//    @Id
    @Indexed(unique = true)
    private int place;
    private Date date;
    @DocumentReference(lazy = true)
    private Track track;

    public TopTrack() {
        LocalDate localDate = LocalDate.now();
        this.date = new Date(localDate.getYear(), localDate.getDayOfYear(), localDate.getDayOfMonth());
    }
}
