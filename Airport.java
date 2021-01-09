import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Airport {
	public static void main(String[] args) {
		Random rand = new Random();
		
		// Create a blocking queue to store runways.
		// The size is fixed to 3 as there will only be 3 runways.
		BlockingQueue<Runway> runways = new ArrayBlockingQueue<Runway>(3);
		
		// Create a thread pool for the aircraft objects.
		// The size is fixed to 3 as there cannot be more than 3 aircrafts executing at the same time as it is limited to the number of runway objects.
		ExecutorService apool  = Executors.newFixedThreadPool(3);
		
		// Generate 3 runway objects and add it to the runway queue.
		for (int i = 1; i <= 3; i++) {
			runways.add(new Runway(i));
		}
		
		// Generate 10000 aircrafts at random intervals.
		for(int i = 1; i <= 10000; i++) {
			try {
				// Wait between 0 to 5 seconds to generate a new aircraft.
				Thread.sleep(rand.nextInt(5001));
				// Add generated aircraft into queue.
				// Pass a random boolean to decide if the aircraft wants to land or depart.
				apool.submit(new Aircraft(i, rand.nextBoolean(), runways));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		apool.shutdown();
		
		try {
			if (apool.awaitTermination(100, TimeUnit.SECONDS)) {
				for (int i = 1; i <= 3; i++) {
					runways.take().printReport();
				}
			}
		} catch (InterruptedException e) {
		}
	}
}
