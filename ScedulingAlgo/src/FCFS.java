import java.util.*;


class Pair{
	String process;
	int time;
	Pair(String process,int time){
		this.process=process;
		this.time=time;
	}
}
public class FCFS {
	public static void run() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Give Input ---->");
		System.out.println("Enter the no of processes");
		Queue<Process> processes=new LinkedList<>();
		int n=scanner.nextInt();
		System.out.println("Enter the processes format: name arrival_time burst_time");
		while(n-->0) {
			String processName=scanner.next();
			int arrivalTime=scanner.nextInt();
			int burstTime=scanner.nextInt();
			processes.offer(new Process(processName, arrivalTime, burstTime));
		}
		
		Map<String,String> processInfo=new HashMap<>();		
		
		int startTime=0;
		
		List<Pair> ganttChart=new LinkedList<>();
		
		while(!processes.isEmpty()) {
			Process temp=processes.poll();
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
			ganttChart.add(new Pair(temp.name,startTime));
			startTime+=temp.burstTime;
			ganttChart.add(new Pair(temp.name,startTime));
		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
	}
	public static void main(String[] args) {
		Queue<Process> processes=new LinkedList<>();
		Map<String,String> processInfo=new HashMap<>();
		processes.offer(new Process("P0",0,6));
		processes.offer(new Process("P1",1,8));
		processes.offer(new Process("P2",2,10));
		processes.offer(new Process("P3",3,12));
		
		
		int startTime=0;
		
		List<Pair> ganttChart=new LinkedList<>();
		
		while(!processes.isEmpty()) {
			Process temp=processes.poll();
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
			ganttChart.add(new Pair(temp.name,startTime));
			startTime+=temp.burstTime;
			ganttChart.add(new Pair(temp.name,startTime));
		}
		
//		while(!ganttChart.isEmpty()) {
//			Pair temp=ganttChart.poll();
//			System.out.println(temp.process+" : "+temp.time);
//		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
	}

}
