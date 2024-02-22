package registration;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class signin {

	private JFrame frame;
	private JTextField tb1;
	private JTextField tb2;
	private JPasswordField pf1;
	private JPasswordField pf2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signin window = new signin();
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
	public signin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.CYAN);
		frame.setBounds(100, 100, 705, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSignIn = new JLabel("Sign Up");
		lblSignIn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSignIn.setBounds(154, 23, 89, 25);
		frame.getContentPane().add(lblSignIn);
		
		JLabel lblSignUp = new JLabel("Sign In");
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSignUp.setBounds(461, 23, 89, 25);
		frame.getContentPane().add(lblSignUp);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(65, 94, 89, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(65, 187, 89, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(375, 93, 89, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(375, 187, 89, 25);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		tb1 = new JTextField();
		tb1.setFont(new Font("Tahoma", Font.BOLD, 13));
		tb1.setBounds(187, 97, 130, 20);
		frame.getContentPane().add(tb1);
		tb1.setColumns(10);
		
		tb2 = new JTextField();
		tb2.setFont(new Font("Tahoma", Font.BOLD, 13));
		tb2.setColumns(10);
		tb2.setBounds(511, 97, 130, 20);
		frame.getContentPane().add(tb2);
		
		pf1 = new JPasswordField();
		pf1.setBounds(187, 184, 130, 20);
		frame.getContentPane().add(pf1);
		
		pf2 = new JPasswordField();
		pf2.setBounds(511, 191, 130, 20);
		frame.getContentPane().add(pf2);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n=tb1.getText();
				String p=pf1.getText();
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reg","root","mysql");
					String q="insert into signin values('"+n+"','"+p+"')";
					java.sql.Statement st=con.createStatement();
					int rowsEffected = st.executeUpdate(q);
					JOptionPane.showMessageDialog(btnNewButton,"SignIn Done");
					con.close();

				}
				catch(SQLIntegrityConstraintViolationException e2) {
					JOptionPane.showMessageDialog(btnNewButton,"Username already Exists");
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(184, 289, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSignUp = new JButton("Sign In");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String N=tb2.getText();
				String P=pf2.getText();
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reg","root","mysql");
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select Password from signin where Name='"+N+"'");
					rs.next();
					String pass=rs.getString(1);
					if(pass.equals(P)) {
						JOptionPane.showMessageDialog(btnSignUp,"Sign In Done");
						return;
					}
					JOptionPane.showMessageDialog(btnSignUp,"Invalid Details");
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignUp.setBounds(461, 289, 89, 23);
		frame.getContentPane().add(btnSignUp);
		
	}
}
