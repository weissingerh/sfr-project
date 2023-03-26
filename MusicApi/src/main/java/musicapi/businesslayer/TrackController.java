package musicapi.businesslayer;

import lombok.AllArgsConstructor;
import musicapi.persistence.model.Track;
import musicapi.persistence.model.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/tracks")
@AllArgsConstructor
public class TrackController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private final TrackRepository trackRepository;

    @GetMapping("getAll")
    public List<Track> getAllTracks() {
        LOG.info("Retrieving all tracks.");
        return trackRepository.findAll();
    }

    @GetMapping("/{trackId}")
    public void getUser(@PathVariable String trackId) {
        LOG.info("Getting user with ID: {}.", trackId);
        //Optional trackRepositoryOne = trackRepository.findOne(trackId);
        //return trackRepositoryOne;
    }


}
