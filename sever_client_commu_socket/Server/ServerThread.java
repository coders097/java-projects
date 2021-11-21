import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
	private ServerSocket listener;
	private ArrayList<ClientHandler> clients;
	
	public ServerThread(ServerSocket listener,ArrayList<ClientHandler> clients) {
		this.listener=listener;
		this.clients=clients;
	}
	public void run() {
		while(true) {
			System.out.println("[SERVER] Waiting for client connection...");
			Socket client=null;
			try {
				client = listener.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			}
			System.out.println("[SERVER] Connected to client...");
			ClientHandler clientThread=null;
			try {
				clientThread = new ClientHandler(client,clients);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			clients.add(clientThread);
			clientThread.start();
		}
	}

}
