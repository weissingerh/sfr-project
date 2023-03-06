package kafkamusicproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaMusicProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMusicProducerApplication.class, args);
	}

}
