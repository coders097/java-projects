import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class RoundRobin {

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
		System.out.println("Enter the time quantum");
		int timeQuantum=scanner.nextInt();
		
		Collections.sort(stream,(a,b)->a.arrivalTime-b.arrivalTime);
		
		Map<String,String> processInfo=new HashMap<>();
		for(Process temp:stream) {
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
		}
		
		Queue<Process> queue=new LinkedList<>();
		List<Pair> ganttChart=new ArrayList<>();
		int startTime=0;
		
		int i=0;
		while(i<stream.size()) {
			if(queue.isEmpty()) {
				startTime=stream.get(i).arrivalTime;
				queue.offer(stream.get(i++));
			}
			while(!queue.isEmpty()) {
				Process currProcess=queue.poll();
				ganttChart.add(new Pair(currProcess.name, startTime));
				startTime+=(currProcess.burstTime>timeQuantum)?timeQuantum:currProcess.burstTime;
				ganttChart.add(new Pair(currProcess.name, startTime));
				
				while(i<stream.size() && stream.get(i).arrivalTime<=startTime) {
					queue.offer(stream.get(i++));
				}
				
				if(currProcess.burstTime>timeQuantum) {
					currProcess.burstTime=currProcess.burstTime-timeQuantum;
					queue.offer(currProcess);						
				}
					
			}
		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Process> stream=new ArrayList<>();
		stream.add(new Process("P0",0, 5));
		stream.add(new Process("P1",1, 6));
		stream.add(new Process("P2",2, 3));
		stream.add(new Process("P3",3, 1));
		stream.add(new Process("P4",4, 5));
		stream.add(new Process("P5",6, 4));
		
		Collections.sort(stream,(a,b)->a.arrivalTime-b.arrivalTime);
		
		Map<String,String> processInfo=new HashMap<>();
		for(Process temp:stream) {
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
		}
		
		int timeQuantum=4;
		Queue<Process> queue=new LinkedList<>();
		List<Pair> ganttChart=new ArrayList<>();
		int startTime=0;
		
		int i=0;
		while(i<stream.size()) {
			if(queue.isEmpty()) {
				startTime=stream.get(i).arrivalTime;
				queue.offer(stream.get(i++));
			}
			while(!queue.isEmpty()) {
				Process currProcess=queue.poll();
//				if(currProcess.burstTime>timeQuantum) {
//					
//					ganttChart.add(new Pair(currProcess.name, startTime));
//					startTime+=timeQuantum;
//					ganttChart.add(new Pair(currProcess.name, startTime));
//					
//					while(i<stream.size() && stream.get(i).arrivalTime<=startTime) {
//						queue.offer(stream.get(i++));
//					}
//					
//					currProcess.burstTime=currProcess.burstTime-timeQuantum;
//					queue.offer(currProcess);
//					
//				}else {
//					
//					ganttChart.add(new Pair(currProcess.name, startTime));
//					startTime+=currProcess.burstTime;
//					ganttChart.add(new Pair(currProcess.name, startTime));
//					
//					while(i<stream.size() && stream.get(i).arrivalTime<=startTime) {
//						queue.offer(stream.get(i++));
//					}
//					
//				}
					
				ganttChart.add(new Pair(currProcess.name, startTime));
				startTime+=(currProcess.burstTime>timeQuantum)?timeQuantum:currProcess.burstTime;
				ganttChart.add(new Pair(currProcess.name, startTime));
				
				while(i<stream.size() && stream.get(i).arrivalTime<=startTime) {
					queue.offer(stream.get(i++));
				}
				
				if(currProcess.burstTime>timeQuantum) {
					currProcess.burstTime=currProcess.burstTime-timeQuantum;
					queue.offer(currProcess);						
				}
					
			}
		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
		
	}

}
