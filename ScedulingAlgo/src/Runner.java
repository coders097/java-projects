import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice=1;
		Scanner scanner=new Scanner(System.in);
		
		while(choice==1) {
			System.out.println("Types of Schedulings Present");
			System.out.println("1. FCFS");
			System.out.println("2. SJF");
			System.out.println("3. SRTF");
			System.out.println("4. Round Robin");
			System.out.println("Enter your choice: 1/2/3/4");
			choice=scanner.nextInt();
			
			if(choice<1 || choice>4) {
				choice=1;
				System.out.println("Wrong Choice");
				continue;
			}
			
			switch (choice) {
				case 1:
					FCFS.run();
					break;
				case 2:
					SJF.run();
					break;
				case 3:
					SRTF.run();
					break;
				case 4:
					RoundRobin.run();
					break;
			}
			
			System.out.println("Do you wanna continue again? No=0 Yes=1");
			choice=scanner.nextInt();
			if(choice==0) {
				break;
			}
		}
		scanner.close();
	}

}
