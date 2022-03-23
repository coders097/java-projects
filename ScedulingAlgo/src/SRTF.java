import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SRTF {
	// Preemptive SJF
	public static void run() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Give Input ---->");
		System.out.println("Enter the no of processes");
		List<Process> streamList=new LinkedList<>();
		int n=scanner.nextInt();
		System.out.println("Enter the processes format: name arrival_time burst_time");
		while(n-->0) {
			String processName=scanner.next();
			int arrivalTime=scanner.nextInt();
			int burstTime=scanner.nextInt();
			streamList.add(new Process(processName, arrivalTime, burstTime));
		}
		Collections.sort(streamList,(a,b)->a.arrivalTime-b.arrivalTime);
		
		Map<String,String> processInfo=new HashMap<>();
		for(Process temp:streamList) {
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
		}
		
		int startTime=0;
		List<Pair> ganttChart=new ArrayList<>();
		Process runningInstanceProcess=null;
		PriorityQueue<Process> queue=new PriorityQueue<>((a,b)->a.burstTime-b.burstTime);
		int i=0;
		
		while(i<streamList.size()) {
			if(queue.isEmpty()) {
				startTime=streamList.get(i).arrivalTime;
				queue.offer(streamList.get(i++));
			}
			while(!queue.isEmpty()) {
				runningInstanceProcess=queue.poll();
				if((i<streamList.size()) && startTime+runningInstanceProcess.burstTime>streamList.get(i).arrivalTime) {
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
					int currentRunningInstanceRuntime=(streamList.get(i).arrivalTime-startTime);
					startTime=startTime+currentRunningInstanceRuntime;
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
					
					runningInstanceProcess.burstTime=runningInstanceProcess.burstTime-currentRunningInstanceRuntime;
					queue.offer(runningInstanceProcess);
					runningInstanceProcess=null;
					
					queue.offer(streamList.get(i++));
				}else {
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
					startTime+=runningInstanceProcess.burstTime;
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
				}
				
			}
		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
	}
	public static void main(String[] args) {
		List<Process> streamList=new ArrayList<>();
		streamList.add(new Process("P1",0,8));
		streamList.add(new Process("P2",1,4));
		streamList.add(new Process("P3",2,2));
		streamList.add(new Process("P4",3,1));
		streamList.add(new Process("P5",4,3));
		streamList.add(new Process("P6",5,2));
		Collections.sort(streamList,(a,b)->a.arrivalTime-b.arrivalTime);
		
		Map<String,String> processInfo=new HashMap<>();
		for(Process temp:streamList) {
			processInfo.put(temp.name,temp.arrivalTime+" "+temp.burstTime);
		}
		
		int startTime=0;
		List<Pair> ganttChart=new ArrayList<>();
		Process runningInstanceProcess=null;
		PriorityQueue<Process> queue=new PriorityQueue<>((a,b)->a.burstTime-b.burstTime);
		int i=0;
		
		while(i<streamList.size()) {
			if(queue.isEmpty()) {
				startTime=streamList.get(i).arrivalTime;
				queue.offer(streamList.get(i++));
			}
			while(!queue.isEmpty()) {
				runningInstanceProcess=queue.poll();
				
//				if(i<streamList.size()) {
//					if(startTime+runningInstanceProcess.burstTime>streamList.get(i).arrivalTime) {
//						ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
//						int currentRunningInstanceRuntime=(streamList.get(i).arrivalTime-startTime);
//						startTime=startTime+currentRunningInstanceRuntime;
//						ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
//						
//						runningInstanceProcess.burstTime=runningInstanceProcess.burstTime-currentRunningInstanceRuntime;
//						queue.offer(runningInstanceProcess);
//						runningInstanceProcess=null;
//						
//						queue.offer(streamList.get(i++));
//					}else {
//						ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
//						startTime+=runningInstanceProcess.burstTime;
//						ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
//					}
//				}else {
//					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
//					startTime+=runningInstanceProcess.burstTime;
//					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
//				}
				
				if((i<streamList.size()) && startTime+runningInstanceProcess.burstTime>streamList.get(i).arrivalTime) {
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
					int currentRunningInstanceRuntime=(streamList.get(i).arrivalTime-startTime);
					startTime=startTime+currentRunningInstanceRuntime;
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
					
					runningInstanceProcess.burstTime=runningInstanceProcess.burstTime-currentRunningInstanceRuntime;
					queue.offer(runningInstanceProcess);
					runningInstanceProcess=null;
					
					queue.offer(streamList.get(i++));
				}else {
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
					startTime+=runningInstanceProcess.burstTime;
					ganttChart.add(new Pair(runningInstanceProcess.name, startTime));
				}
				
			}
		}
		
		Analyser analyser=new Analyser(ganttChart, processInfo);
		analyser.analyse();
	}

}
