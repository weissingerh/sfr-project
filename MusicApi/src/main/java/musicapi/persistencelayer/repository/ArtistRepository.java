package musicapi.persistencelayer.repository;

import musicapi.persistencelayer.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

@org.springframework.stereotype.Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {

    @Query(value = "{'name' : ?0}")
    Artist findItemByName(String name);

}
