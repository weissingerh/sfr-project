package musicapi.persistencelayer.repository;

import musicapi.persistencelayer.model.SongLyrics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LyricsRepository extends MongoRepository<SongLyrics, String> {
}
