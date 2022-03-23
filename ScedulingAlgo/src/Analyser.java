import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analyser {

	private List<Pair> ganttChart;
	private Map<String,String> processInfo; // string => 'arrivaltime bursttime'
	public Analyser(List<Pair> ganttChart,Map<String,String> processInfo) {
		// TODO Auto-generated constructor stub
//		Turn Around Time = Completion Time - Arrival Time   
//      Waiting Time = Turn Around Time - Burst Time  
		this.ganttChart=ganttChart;
		this.processInfo=processInfo;
	}
	
	public void analyse() {
		List<String> processes=new ArrayList<>();
		Map <String,Integer> completionTimes=new HashMap<>();
		Map <String,Integer> arrivalTimes=new HashMap<>();
		Map <String,Integer> burstTimes=new HashMap<>();
		
		// Load
		for(String trav:processInfo.keySet()) {
			String values[]=processInfo.get(trav).split(" ");
			arrivalTimes.put(trav,Integer.parseInt(values[0]));
			burstTimes.put(trav,Integer.parseInt(values[1]));
			processes.add(trav);
		}
		for(Pair trav:ganttChart) {
			completionTimes.put(trav.process,trav.time);
		}
		//
		System.out.println("*********Gantt Chart*************");
		for(Pair temp:ganttChart) {
			System.out.println(temp.process+" : "+temp.time);
		}
		System.out.println();
		System.out.println("*********Completion Times********");
		for(String process:completionTimes.keySet()) {
			int res=completionTimes.get(process);
			System.out.println(process+"  :  "+res);
		}
		System.out.println();
		System.out.println("*********Turn Around Times********");
		double total=0;
		for(String process:processes) {
			int res=completionTimes.get(process)-arrivalTimes.get(process);
			System.out.println(process+"  :  "+res);
			total+=res;
		}
		System.out.println("Average Turn Around Time = "+(total/processes.size()));
		System.out.println();
		System.out.println("*********Waiting Times********");
		total=0;
		for(String process:processes) {
			int res=completionTimes.get(process)-arrivalTimes.get(process)-burstTimes.get(process);
			System.out.println(process+"  :  "+res);
			total+=res;
		}
		System.out.println("Average Waiting Time = "+(total/processes.size()));
	}

}
