package musicapi.persistencelayer.repository;

import musicapi.persistencelayer.model.Artist;
import musicapi.persistencelayer.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@org.springframework.stereotype.Repository
public interface TrackRepository extends MongoRepository<Track, String> {

//    @Query(value = "{'id' : ?0}", fields = "{'id' : -1}")
//    int findItemByName(String id);

}
