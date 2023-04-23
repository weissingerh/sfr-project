package musicapi.frontend.persistencelayer.repository;

import musicapi.frontend.persistencelayer.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {

    @Query(value = "{'name' : ?0}")
    Artist findItemByName(String name);

}
