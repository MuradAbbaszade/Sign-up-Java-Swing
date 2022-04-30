import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JPasswordField password;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(119, 45, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(119, 82, 46, 14);
		contentPane.add(lblPassword);
		
		name = new JTextField();
		name.setBounds(175, 42, 133, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(175, 80, 133, 17);
		contentPane.add(password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.RED);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setBounds(184, 183, 91, 23);
		contentPane.add(btnLogin);
		
		JLabel lblSignUp = new JLabel("Sign up if you don't have an account..");
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSignUp.setBounds(19, 138, 256, 14);
		contentPane.add(lblSignUp);
		
		JButton btnSignUp = new JButton("SignUp");
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBackground(Color.RED);
		btnSignUp.setBounds(276, 134, 144, 23);
		contentPane.add(btnSignUp);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register register = new Register();
				DatabaseConnection database = new DatabaseConnection();
				try {
					ResultSet rs = database.getStatement().executeQuery("Select *from users WHERE name='"+name.getText()+"' AND password='"+password.getText()+"'");
					if(rs.next()){
						JOptionPane.showMessageDialog(register, "Login successful.");
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(register, "Incorrect username or password");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register register = new Register();
				register.setVisible(true);
				setVisible(false);
				
			}
		});
	}
	}

