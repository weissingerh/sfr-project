package musicapi.persistence.model.repository;

import musicapi.persistence.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

@org.springframework.stereotype.Repository
public interface TrackRepository extends MongoRepository<Track, String> {
}
