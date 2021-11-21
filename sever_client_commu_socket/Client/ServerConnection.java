import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.DefaultListModel;
public class ServerConnection extends Thread{
	private Socket server;
	private BufferedReader in;
	private DefaultListModel<String> list;
	
	public ServerConnection(Socket s,DefaultListModel<String> model) throws Exception{
		this.server=s;
		this.list=model;
		this.in=new BufferedReader(new InputStreamReader(server.getInputStream()));
	}
	
	public void run() {
		try {
			while(true) {
				String reString=in.readLine();
				if(reString==null) break;
				list.addElement(reString);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
}
