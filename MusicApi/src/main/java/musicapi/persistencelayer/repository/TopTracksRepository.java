package musicapi.persistencelayer.repository;

import musicapi.persistencelayer.model.TopTrack;
import org.springframework.data.mongodb.repository.MongoRepository;

@org.springframework.stereotype.Repository
public interface TopTracksRepository extends MongoRepository<TopTrack, String> {
}
