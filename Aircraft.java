import java.util.concurrent.BlockingQueue;

public class Aircraft implements Runnable{
	private String status;
	private int ID;
	private BlockingQueue<Runway> runways = null;
	private Runway runway;
	
	public Aircraft(int ID, Boolean bool, BlockingQueue<Runway> runways) {
		this.ID = ID;
		this.runways = runways;
		
		// Determine if aircraft wants to land or depart based on boolean passed as the second argument.
		if (bool) {
			status = "land";
		} else {
			status = "depart";
		}
		
		System.out.println(java.time.LocalTime.now() + " An aircraft with the ID of " + ID + " has been created.");
	}

	@Override
	public void run() {
		try {
			runway = runways.take();
			System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " has been assigned " + runway.getName() + ".");
			System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " will now " + status + ".");
			if (status == "land") {
				Thread.sleep(10000);
			} else if (status == "depart") {
				Thread.sleep(5000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		runway.inc(status);
		System.out.println(java.time.LocalTime.now() + " Aircraft " + ID + " has " + status + "ed.");
		runways.add(runway);
	}
}
