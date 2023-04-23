package musicapi.frontend;

import lombok.AllArgsConstructor;
import musicapi.frontend.persistencelayer.model.*;
import musicapi.frontend.persistencelayer.repository.ArtistRepository;
import musicapi.frontend.persistencelayer.repository.LyricsRepository;
import musicapi.frontend.persistencelayer.repository.TopTracksRepository;
import musicapi.frontend.persistencelayer.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final TrackRepository trackRepository;
    @Autowired
    private final ArtistRepository artistRepository;
    @Autowired
    private final LyricsRepository lyricsRepository;
    @Autowired
    private final TopTracksRepository topTracksRepository;

    @GetMapping("/artists")
    public String getArtists(Model model) {
        List<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artists";
    }
    @GetMapping("/tracks")
    public String getTracks(Model model) {
        LOG.info("Retrieving all Tracks.");
        List <Track> tracks = trackRepository.findAll();
        model.addAttribute("tracks", tracks);
        return "tracks";
    }

    @GetMapping("/tracks/charts")
    public String getTracksCharts(Model model) {
        LOG.info("Retrieving all Charts.");
        List<TopTrack> topTracks = topTracksRepository.findAll();
        model.addAttribute("topTracks", topTracks);
        return "toptracks";
    }

    @GetMapping("/lyrics")
    public String getLyricsTracks(Model model) {
        LOG.info("Retrieving all available Lyrics.");
        List<SongLyrics> lyrics = lyricsRepository.findAll();
        model.addAttribute("availableLyrics", lyrics);
        return "lyrics";
    }
    @GetMapping("/lyrics/{id}")
    public String getLyrics(@PathVariable String id,  Model model) {
        LOG.info("Retrieving Lyrics with id " + id );
        List<SongLyrics> lyrics = lyricsRepository.findAll();
        for (SongLyrics song : lyrics) {
            if(song.getId() == Integer.parseInt(id))
                model.addAttribute("song", song);
        }
        return "song";
    }

}
