package musicapi.frontend.persistencelayer.repository;

import musicapi.frontend.persistencelayer.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends MongoRepository<Track, String> {

//    @Query(value = "{'id' : ?0}", fields = "{'id' : -1}")
//    int findItemByName(String id);

}
