public class Runway {
	private int landCount = 0;
	private int departCount = 0;
	private int name;
	
	public Runway(int name) {
		this.name = name;
	}
	
	public void printReport() {
		System.out.println("Runway " + name + " has a total of "+ landCount + " landings.");
		System.out.println("Runway " + name + " has a total of "+ departCount + " departures.");
		System.out.println("Runway " + name + " has a combined total of "+ getCount() + " uses.");
	}

	public void inc(String status) {
		if (status.equals("land")) {
			landCount++;
		} else {
			departCount++;
		}
	}

	public String getName() {
		return "Runway " + name;
	}

	public int getLandCount() {
		return landCount;
	}

	public int getDepartCount() {
		return departCount;
	}
	
	public int getCount() {
		return departCount + landCount;
	}
}