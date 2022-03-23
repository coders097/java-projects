import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrioritySchedulingWithRoundRobin {

	// This application works for processes where we have all the processes and want them
	// Not a stream
	
	public static List<Process> getSamePriorityProcesses(PriorityQueue<Process> queue){
		List<Process> processes=new ArrayList<>();
		int priorityDefination=queue.peek().priority;
		while(!queue.isEmpty() && (queue.peek().priority==priorityDefination)) {
			processes.add(queue.poll());
		}
		return processes;
	}
	
	public static void main(String[] args) {
		List <Process> stream=new ArrayList<>();
		stream.add(new Process("P1", 0, 4,3));
		stream.add(new Process("P2", 0, 5,2));
		stream.add(new Process("P3", 0, 8,2));
		stream.add(new Process("P4", 0, 7,1));
		stream.add(new Process("P5", 0, 3,3));
		
		Collections.sort(stream,(a,b)->a.arrivalTime-b.arrivalTime);
		
		int timeQuantum=2;
		int startTime=0;
		PriorityQueue<Process> queue=new PriorityQueue<>((a,b)->a.priority-b.priority);
		List<Pair> ganttChart=new ArrayList<>();
		for(Process trav:stream) queue.offer(trav);
		
		while(!queue.isEmpty()) {
			List<Process> samePriorityProcesses=getSamePriorityProcesses(queue);
			if(samePriorityProcesses.size()==1) {
				ganttChart.add(new Pair(samePriorityProcesses.get(0).name, startTime));
				startTime=startTime+samePriorityProcesses.get(0).burstTime;
				ganttChart.add(new Pair(samePriorityProcesses.get(0).name, startTime));
			}else {
				startTime=roundRobin(samePriorityProcesses, timeQuantum, startTime, ganttChart);
			}
		}
		
		for(Pair trav:ganttChart)
			System.out.println(trav.process+" : "+trav.time);
	}
	
	public static int roundRobin(List <Process> stream,int timeQuantum,int startTime,List<Pair> ganttChart) {
		Queue<Process> queue=new LinkedList<>();
		int i=0;
		while(i<stream.size()) {
			if(queue.isEmpty()) {
				if(stream.get(i).arrivalTime>startTime)
					startTime=stream.get(i).arrivalTime;
				queue.offer(stream.get(i++));
			}
			while(!queue.isEmpty()) {
				Process currProcess=queue.poll();
				ganttChart.add(new Pair(currProcess.name, startTime));
				startTime+=(currProcess.burstTime>timeQuantum)?timeQuantum:currProcess.burstTime;
				ganttChart.add(new Pair(currProcess.name, startTime));
				
				while(i<stream.size() && stream.get(i).arrivalTime<=startTime) 
					queue.offer(stream.get(i++));
				
				if(currProcess.burstTime>timeQuantum) {
					currProcess.burstTime=currProcess.burstTime-timeQuantum;
					queue.offer(currProcess);						
				}
					
			}
		}
		return startTime;
	}

}
