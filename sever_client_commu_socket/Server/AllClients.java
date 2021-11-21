import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.Font;

public class AllClients extends JFrame {

	private JPanel contentPane;
	private ArrayList<ClientHandler> clients;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllClients frame = new AllClients(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AllClients(ArrayList<ClientHandler> clients) {
		this.clients=clients;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 878, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Available Clients...");
		
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 25));
		list.setBounds(10, 10, 844, 549);
		contentPane.add(list);
		
		DefaultListModel<String> model=new DefaultListModel<>();
		list.setModel(model);
		
		for(ClientHandler client : clients) 
			if(client.isAvailable()) model.addElement(client.getIPAdressString()+" : "+client.getNameString()+" : "
					+client.getTimeOfJoining());
	}
}
