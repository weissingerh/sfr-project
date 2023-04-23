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
//		timer.schedule(new TimerTask() {
//			@SneakyThrows
//			@Override
//			public void run() {
				new RestTemplate().getForObject("http://localhost:8080/kafka/charts/tracks", ByteArraySerializer.class);
//			}
//		}, 10000, 60000);
//
//		Thread.sleep(10000);

		//lyrics api calls um die db zu füllen
		new RestTemplate().getForObject("http://localhost:8080/kafka/lyrics/Harry Styles/As It Was", ByteArraySerializer.class);
		new RestTemplate().getForObject("http://localhost:8080/kafka/lyrics/Cher/Believe", ByteArraySerializer.class);
		new RestTemplate().getForObject("http://localhost:8080/kafka/lyrics/50 Cents/Candy Shop", ByteArraySerializer.class);
		new RestTemplate().getForObject("http://localhost:8080/kafka/lyrics/Taylor Swift/Anti-Hero", ByteArraySerializer.class);
		new RestTemplate().getForObject("http://localhost:8080/kafka/lyrics/Miley Cyrus/Flowers", ByteArraySerializer.class);

		//Öffnen des aggregierenden Streams
//		new RestTemplate().getForObject("http://localhost:8080/kafka/tracksAverage", String.class);

		//Danach kann nochetwas ausgeführt werden
	}

}
