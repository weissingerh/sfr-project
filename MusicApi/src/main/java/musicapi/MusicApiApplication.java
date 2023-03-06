package musicapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class MusicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicApiApplication.class, args);
	}

}
