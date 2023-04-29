package musicapi.frontend.persistencelayer.repository;

import musicapi.frontend.persistencelayer.model.TopTrack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopTracksRepository extends MongoRepository<TopTrack, String> {
}
