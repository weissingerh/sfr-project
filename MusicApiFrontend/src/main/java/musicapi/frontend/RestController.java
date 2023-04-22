package musicapi.frontend;

import lombok.AllArgsConstructor;
import musicapi.frontend.persistencelayer.model.Artist;
import musicapi.frontend.persistencelayer.model.Track;
import musicapi.frontend.persistencelayer.repository.ArtistRepository;
import musicapi.frontend.persistencelayer.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
@AllArgsConstructor
public class RestController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final TrackRepository trackRepository;
    @Autowired
    private final ArtistRepository artistRepository;

    @GetMapping("/test")
    public String index(Model model) {
        LOG.info("Index Page");
        model.addAttribute("eventName", "music api 2023");
        return "index1";
    }

    @GetMapping("/index")
    public String getTracks(Model model) {

       // Artist artist = new Artist(artistRepository.findAll().size(), "Nina Chuba");
       // artistRepository.save(artist);
       // Track track = new Track(artist, "Freitag", 10);
      //  trackRepository.save(track);
        LOG.info("Retrieving all artists.");
       // List<Track> tracks = trackRepository.findAll();
        List<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "index";
    }

}
