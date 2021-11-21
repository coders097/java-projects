import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ClientHandler extends Thread{
	private Socket client;
	private String nameString;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<ClientHandler> clients;
	
	public boolean isAvailable() {
		return !client.isClosed();
	}
	
	public PrintWriter getOutputChannel() {
		return this.out;
	}
	
	public ClientHandler(Socket clientSocket,ArrayList<ClientHandler> clients) throws Exception{
		this.client=clientSocket;
		this.in=new BufferedReader(new InputStreamReader(client.getInputStream()));
		this.out=new PrintWriter(client.getOutputStream(),true);
		this.clients=clients;
		Random random=new Random();
		this.nameString=((char)(65+random.nextInt(25)))+""
				+((char)(97+random.nextInt(25)))+""
				+((char)(97+random.nextInt(25)))+""
				+((char)(97+random.nextInt(25)))+""
				+((char)(97+random.nextInt(25)));
	}
	
	public String getIPAdressString() {
		return client.getInetAddress().toString();
	}
	
	public String getNameString() {
		return this.nameString;
	}
	
	public String getTimeOfJoining() {
		return new Date().toString();
	}
	
	public void outToAll(String msg) {
		for(ClientHandler crawl:clients) crawl.out.println(msg);
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				String requestString=in.readLine();
				if(requestString!=null) {
//					System.out.println(requestString);   // To print what the client has sent
					outToAll(getNameString()+" : "+requestString);
				}
//				out.println("Gotcha!");    // use this in debugging
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
