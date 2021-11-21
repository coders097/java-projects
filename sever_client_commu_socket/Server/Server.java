import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Server {

	private JFrame frame;
	private JTextField textField;
	private boolean serverStatus;
	private ServerSocket listener;
	private static ArrayList<ClientHandler> clients;
	private ServerThread serverThread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		this.serverStatus=false;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 535, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("SERVER");
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("SERVER");
		lblNewLabel.setForeground(UIManager.getColor("Button.disabledForeground"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		lblNewLabel.setBounds(98, 10, 311, 81);
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 82, 480, 5);
		frame.getContentPane().add(separator);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setBounds(251, 115, 127, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(135, 115, 104, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("START");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(serverStatus==true) {
					JOptionPane.showMessageDialog(null,"Server Already Running!");
				}else {
					if(textField.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null,"Please Enter a PORT!");
						return;
					}
					try {
						listener=new ServerSocket(Integer.parseInt(textField.getText()));
						clients=new ArrayList<>();
						serverStatus=true;
						
						serverThread=new ServerThread(listener, clients);
						serverThread.start();
						JOptionPane.showMessageDialog(null,"Server Started now at PORT "+textField.getText());
					}catch (Exception qqq) {
						JOptionPane.showMessageDialog(null,"Something already running in this PORT!");
					}
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 40));
		btnNewButton.setBounds(131, 186, 258, 81);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(serverStatus==false) {
					JOptionPane.showMessageDialog(null,"Server not running!");
				}else {
					serverStatus=false;
					try {
						listener.close();
						serverThread=null;
					}catch (Exception ej) {
						// TODO: handle exception
					}
				}
			}
		});
		btnStop.setForeground(Color.WHITE);
		btnStop.setFont(new Font("Arial", Font.PLAIN, 40));
		btnStop.setBorderPainted(false);
		btnStop.setBackground(Color.DARK_GRAY);
		btnStop.setBounds(131, 288, 258, 81);
		frame.getContentPane().add(btnStop);
		
		JButton btnClients = new JButton("CLIENTS");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(serverStatus==true) {
					if(clients.size()==0) {
						JOptionPane.showMessageDialog(null,"No clients are available right now!");
					}else {
						AllClients allClients=new AllClients(clients);
						allClients.setVisible(true);
					}
				}else {
					JOptionPane.showMessageDialog(null,"Server not started");
				}
			}
		});
		btnClients.setForeground(Color.WHITE);
		btnClients.setFont(new Font("Arial", Font.PLAIN, 40));
		btnClients.setBorderPainted(false);
		btnClients.setBackground(Color.DARK_GRAY);
		btnClients.setBounds(131, 392, 258, 81);
		frame.getContentPane().add(btnClients);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 606, 480, 5);
		frame.getContentPane().add(separator_1);
		
		JLabel lblNewLabel_2 = new JLabel("made by BISWAMOHAN DWARI");
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(46, 621, 431, 42);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnRespond = new JButton("RESPOND");
		btnRespond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(serverStatus==false) {
					JOptionPane.showMessageDialog(null,"Server not started yet!");
					return;
				}else if(clients.size()==0) {
					JOptionPane.showMessageDialog(null,"No clients Available now!");
					return;
				}
				String msgString=JOptionPane.showInputDialog("Enter your response to all users!");
				if(msgString==null) return;
				for(ClientHandler crawl:clients) crawl.getOutputChannel().println("[SERVER] : "+msgString);
			}
		});
		btnRespond.setForeground(Color.WHITE);
		btnRespond.setFont(new Font("Arial", Font.PLAIN, 40));
		btnRespond.setBorderPainted(false);
		btnRespond.setBackground(Color.DARK_GRAY);
		btnRespond.setBounds(131, 493, 258, 81);
		frame.getContentPane().add(btnRespond);
	}
}
