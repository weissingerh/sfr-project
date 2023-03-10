package kafkamusicproducer;

import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

import java.util.Timer;
import java.util.TimerTask;

@EnableKafka
@SpringBootApplication
public class KafkaMusicProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMusicProducerApplication.class, args);

		//Einmal pro Minute werden die charts aktualisiert
		//Kann dann später auch erhöht werden auf eine Stunde
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				new RestTemplate().getForObject("http://localhost:8080/kafka/charts", ByteArraySerializer.class);
				new RestTemplate().getForObject("http://localhost:8080/kafka/lyrics", String.class);
			}
		}, 10000, 60000);

		//Danach kann nochetwas ausgeführt werden
	}

}
