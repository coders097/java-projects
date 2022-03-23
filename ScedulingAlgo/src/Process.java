
public class Process {
	
	private static final int NOPRIORITY=0;
	
	String name;
	int arrivalTime;
	int burstTime;
	int priority;
	Process(String name,int arrivalTime,int burstTime){
		this.name=name;
		this.arrivalTime=arrivalTime;
		this.burstTime=burstTime;
		this.priority=NOPRIORITY;
	}
	
	public Process(String name,int arrivalTime,int burstTime,int priority) {
		this.name=name;
		this.arrivalTime=arrivalTime;
		this.burstTime=burstTime;
		this.priority=priority;
	}
}
