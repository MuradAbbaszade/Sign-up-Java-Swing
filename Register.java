import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JPasswordField password;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAd = new JLabel("Name");
		lblAd.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAd.setForeground(Color.BLACK);
		lblAd.setBackground(Color.WHITE);
		lblAd.setBounds(106, 55, 46, 14);
		contentPane.add(lblAd);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(106, 93, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblSifre = new JLabel("Password");
		lblSifre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSifre.setBounds(106, 133, 46, 14);
		contentPane.add(lblSifre);
		
		name = new JTextField();
		name.setBounds(187, 54, 170, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		email = new JTextField();
		email.setBounds(187, 92, 170, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(187, 132, 170, 20);
		contentPane.add(password);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			Login login = new Login();
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConnection database = new DatabaseConnection();
				boolean name_control=false;
				boolean email_control =false;
				try {
					ResultSet rs=database.getStatement().executeQuery("Select *FROM istifadeciler Where ad='"+name.getText()+"'");
					if(rs.next()){
						name_control=true;
					}
					ResultSet rs1=database.getStatement().executeQuery("Select *FROM istifadeciler Where email='"+email.getText()+"'");
					if(rs1.next()){
						email_control=true;
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(name.getText().equals("")|email.getText().equals("")|password.getText().equals("")){
						JOptionPane.showMessageDialog(login,"Fill in all fields.");
					}					
					else if(email.getText().indexOf("@")==-1){
						JOptionPane.showMessageDialog(login,"Email is wrong.");
					}
					else if(name_control|email_control){
						JOptionPane.showMessageDialog(login,"This user is already registered.");
					}
					else{
						database.getStatement().executeUpdate("Insert INTO users (name,email,password) VALUES('"+name.getText()+"','"+email.getText()+"','"+password.getText()+"')");
						JOptionPane.showMessageDialog(login,"Registration Successful.");
						setVisible(false);
						login.setVisible(true);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSignUp.setBackground(Color.RED);
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setBounds(154, 189, 143, 23);
		contentPane.add(btnSignUp);
	}

}
