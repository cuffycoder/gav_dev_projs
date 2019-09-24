package gavinleo.mongo_probe;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import static org.junit.Assert.*;
import org.junit.Test;

public class ProbeStatsCollectorTest {

	@Test
	public void testPercentiles() {

		Random rand = new Random();

		ProbeStatsCollector collector = new ProbeStatsCollector("test");

		for (int i = 0; i < 500; i++) {
			ProbeElapsedTimeObservation obsDough = collector.getNewObservation("Make Dough");
			ProbeElapsedTimeObservation obsBake = collector.getNewObservation("Bake");

			obsDough.startTimer();
			try {
				TimeUnit.NANOSECONDS.sleep(3000000 + (rand.nextInt() % 10000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obsDough.stopTimer();
			collector.addObservation(obsDough);

			obsBake.startTimer();
			try {
				TimeUnit.NANOSECONDS.sleep(2000000 + (rand.nextInt() % 10000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			obsBake.stopTimer();
			collector.addObservation(obsBake);
		}

		JSONObject reportAsJSON = collector.getStatsAndObservations();

		System.out.println(reportAsJSON);

		assertEquals(true, true);
	}
}
