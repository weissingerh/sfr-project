package musicapi.frontend.persistencelayer.repository;

import musicapi.frontend.persistencelayer.model.SongLyrics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LyricsRepository extends MongoRepository<SongLyrics, String> {
}
