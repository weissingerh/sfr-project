package kafkamusicproducer;

import lombok.SneakyThrows;
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

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(KafkaMusicProducerApplication.class, args);

		//Einmal pro Minute werden die charts aktualisiert
		//Kann dann später auch erhöht werden auf eine Stunde
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@SneakyThrows
			@Override
			public void run() {
//				new RestTemplate().getForObject("http://localhost:8080/kafka/track/cher/believe", ByteArraySerializer.class);
//				new RestTemplate().getForObject("http://localhost:8080/kafka/track/cher/after all", ByteArraySerializer.class);
//				new RestTemplate().getForObject("http://localhost:8080/kafka/lyrics", ByteArraySerializer.class);
				new RestTemplate().getForObject("http://localhost:8080/kafka/charts/tracks", ByteArraySerializer.class);
			}
		}, 10000, 60000);

		Thread.sleep(10000);
		//Öffnen des aggregierenden Streams
//		new RestTemplate().getForObject("http://localhost:8080/kafka/tracksAverage", String.class);

		//Danach kann nochetwas ausgeführt werden
	}

}
