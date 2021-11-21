import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
public class Client {
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private boolean status=false;
	private Socket socket;
	private PrintWriter out;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
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
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Client");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 541, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 25));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(389, 10, 128, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(313, 10, 66, 39);
		frame.getContentPane().add(lblNewLabel_1);
		
		JList list = new JList();
		list.setFont(new Font("Arial", Font.PLAIN, 25));
		list.setBackground(SystemColor.menu);
		list.setBounds(10, 59, 507, 599);
		frame.getContentPane().add(list);
		DefaultListModel<String> model=new DefaultListModel<>();
		list.setModel(model);
		
		
		JButton btnNewButton = new JButton("CONNECT");
		JButton btnDisconnect = new JButton("DISCONNECT");
		
		
		// for connect button
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null,"Enter the PORT!");
					return;
				}
				try {
					socket=new Socket("localhost",Integer.parseInt(textField.getText()));
					ServerConnection serverConnection=new ServerConnection(socket,model);
					out=new PrintWriter(socket.getOutputStream(),true);
					serverConnection.start();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Error Connecting to the PORT "+textField.getText());
					return;
				}
				status=true;
				JOptionPane.showMessageDialog(null,"Connected to PORT "+textField.getText());
				btnNewButton.setVisible(false);
				btnDisconnect.setVisible(true);
			}
		});
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 10, 128, 39);
		frame.getContentPane().add(btnNewButton);
		
		// for disconnect button
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status=false;
				try {
					socket.close();
				}catch(Exception e1) {
					
				}
				JOptionPane.showMessageDialog(null,"Disconnected Successfully!");
				btnDisconnect.setVisible(false);
				btnNewButton.setVisible(true);
			}
		});
		btnDisconnect.setForeground(Color.WHITE);
		btnDisconnect.setFont(new Font("Arial", Font.PLAIN, 15));
		btnDisconnect.setBorderPainted(false);
		btnDisconnect.setBackground(Color.DARK_GRAY);
		btnDisconnect.setVisible(false);
		btnDisconnect.setBounds(148, 10, 155, 39);
		frame.getContentPane().add(btnDisconnect);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 668, 431, 53);
		textField_1.setFont(new Font("Arial", Font.PLAIN, 20));
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(status==false) {
					JOptionPane.showMessageDialog(null,"Hey! Connect First!");
				}else {
					if(!textField_1.getText().trim().equals(""))
					out.println(textField_1.getText());
				}
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 45));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(451, 668, 66, 55);
		frame.getContentPane().add(btnNewButton_1);
	}
}
