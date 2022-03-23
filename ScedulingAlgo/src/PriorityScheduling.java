import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityScheduling {
	
	// Non - preemptive

	public static void main(String[] args) {
		
		List<Process> stream=new ArrayList<Process>();
		stream.add(new Process("P0",0,10,5));
		stream.add(new Process("P1",1,6,4));
		stream.add(new Process("P2",3,2,2));
		stream.add(new Process("P3",5,4,0));
		
		Collections.sort(stream,(a,b)->a.arrivalTime-b.arrivalTime);
		List<Pair> ganttChart=new ArrayList<>();
		int startTime=0;
		
		PriorityQueue<Process> queue=new PriorityQueue<>((a,b)->a.priority-b.priority);
		int i=0;
		while(i<stream.size()) {
			if(queue.isEmpty()) {
				startTime=stream.get(i).arrivalTime;
				queue.offer(stream.get(i++));
			}
			while(!queue.isEmpty()) {
				Process currProcess=queue.poll();
				
				ganttChart.add(new Pair(currProcess.name, startTime));
				startTime+=currProcess.burstTime;
				ganttChart.add(new Pair(currProcess.name, startTime));
				
				while(i<stream.size() && stream.get(i).arrivalTime<=startTime) 
					queue.offer(stream.get(i++));
			}
		}
		
		for(Pair trav:ganttChart) 
			System.out.println(trav.process+" : "+trav.time);
	}

}
