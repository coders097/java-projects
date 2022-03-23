import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class SJF {
	// Non - preemptive
	public static void run() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Give Input ---->");
		System.out.println("Enter the no of processes");
		List<Process> stream=new LinkedList<>();
		int n=scanner.nextInt();
		System.out.println("Enter the processes format: name arrival_time burst_time");
		while(n-->0) {
			String processName=scanner.next();
			int arrivalTime=scanner.nextInt();
			int burstTime=scanner.nextInt();
			stream.add(new Process(processName, arrivalTime, burstTime));
		}
		Collections.sort(stream,(a,b)->a.arrivalTime-b.arrivalTime);
		
		Map<String,String> processInfo=new HashMap<>();
		for(Process temp:stream) {
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
		}
		
		PriorityQueue<Process> queue=new PriorityQueue<>((a,b)->a.burstTime-b.burstTime);
		int startTime=0;
		
		List<Pair> ganttChart=new LinkedList<>();
		int i=0;
		while(i<stream.size()) {
			if(queue.isEmpty()) {
				startTime=stream.get(i).arrivalTime;
				queue.offer(stream.get(i++));
			}
			while(!queue.isEmpty()) {
				Process temp=queue.poll();
				ganttChart.add(new Pair(temp.name,startTime));
				startTime+=temp.burstTime;
				ganttChart.add(new Pair(temp.name,startTime));
				while(i<stream.size() && stream.get(i).arrivalTime<=startTime) {
					queue.offer(stream.get(i++));
				}
			}
		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Process> stream=new LinkedList<>();
		stream.add(new Process("P1",1,7));
		stream.add(new Process("P2",3,3));
		stream.add(new Process("P3",6,2));
		stream.add(new Process("P4",7,10));
		stream.add(new Process("P5",9,8));
		Collections.sort(stream,(a,b)->a.arrivalTime-b.arrivalTime);
		
		Map<String,String> processInfo=new HashMap<>();
		for(Process temp:stream) {
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
		}
		
		PriorityQueue<Process> queue=new PriorityQueue<>((a,b)->a.burstTime-b.burstTime);
		int startTime=0;
		
		List<Pair> ganttChart=new LinkedList<>();
		int i=0;
		while(i<stream.size()) {
			if(queue.isEmpty()) {
				startTime=stream.get(i).arrivalTime;
				queue.offer(stream.get(i++));
			}
			while(!queue.isEmpty()) {
				Process temp=queue.poll();
				ganttChart.add(new Pair(temp.name,startTime));
				startTime+=temp.burstTime;
				ganttChart.add(new Pair(temp.name,startTime));
				while(i<stream.size() && stream.get(i).arrivalTime<=startTime) {
					queue.offer(stream.get(i++));
				}
			}
		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
		
	}

}
