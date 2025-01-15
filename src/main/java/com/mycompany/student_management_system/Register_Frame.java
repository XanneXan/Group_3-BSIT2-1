

package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.*;

/**
 *
 * @author Admin
 */

public class Register_Frame extends JFrame implements ActionListener {
    private JLabel lblpassword, lblpass, lblrpass, lbltitle, lblname, lblstud, lblsystem, lbllogo;
    private JTextField txtInput;
    private JPasswordField passField,rpassField;
    private JButton btnlogin, btnconfirm;
    
     //Establish connection to MySQL database
    private String url = "jdbc:mysql://localhost:3306/student_management_system";
    private String user = "root"; 
    private String pass = "mysqlpasswordg3"; 
    
    private Connection sqlConnection() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: ");
            return null;
        }
    }
            
    Register_Frame (){
     
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Management System - REGISTER");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        lbltitle = new JLabel("- REGISTER -");
        lbltitle.setBounds(525, 30, 800, 50);
        lbltitle.setFont(new Font("Arial Black",Font.BOLD,20));
        lbltitle.setForeground(new Color(125, 5, 4));
        add(lbltitle);
        
        lblstud = new JLabel("STUDENT MANAGEMENT");
        lblstud.setBounds(45, 310, 800, 50);
        lblstud.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblstud);
        
        lblsystem = new JLabel("SYSTEM");
        lblsystem.setBounds(130, 340, 800, 50);
        lblsystem.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblsystem);
        
        lblname = new JLabel("Name:");
        lblname.setBounds(370,100 ,150, 50);
        lblname.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblname);

        txtInput = new JTextField();
        txtInput.setBounds (470,105,265,35);
        add(txtInput);
        
        lblpassword = new JLabel("Password:");
        lblpassword.setBounds(370, 170, 210, 50);
        lblpassword.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblpassword);
       
        passField = new JPasswordField();
        passField.setBounds(470,175,265,35);
        add(passField);
        
        lblpass = new JLabel("Repeat");
        lblpass.setBounds(370, 230, 220, 50);
        lblpass.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblpass);
        
        lblrpass = new JLabel("Password:");
        lblrpass.setBounds(370, 250, 230, 50);
        lblrpass.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblrpass);
        
        rpassField = new JPasswordField();
        rpassField.setBounds(470, 245, 265, 35);
        add(rpassField);
        
        //Buttons
        btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(410,330,120,60);
        btnlogin.setFont(new Font("Arial Black",Font.BOLD,13));
        btnlogin.setForeground(Color.WHITE);
        btnlogin.setBackground(new Color(125, 5, 4));
        add(btnlogin);
        
        btnconfirm = new JButton("CONFIRM");
        btnconfirm.setBounds(580,330,120,60);
        btnconfirm.setFont(new Font("Arial Black",Font.BOLD,13));
        btnconfirm.setForeground(Color.WHITE);
        btnconfirm.setBackground(new Color(125, 5, 4));
        add(btnconfirm);
        
        ImageIcon imgIconLogo = new ImageIcon("logo_icon.png");
        Image scale = imgIconLogo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imgnew = new ImageIcon(scale);
        lbllogo = new JLabel(imgnew); 
        lbllogo.setBounds(30, 40, 300, 300);
        add(lbllogo);
        
        btnlogin.addActionListener(this);
        btnconfirm.addActionListener(this);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Button functions
        if (e.getSource() == btnconfirm) { //if the confirm button was clicked
            
            String txt = txtInput.getText().trim(); //get username from textfield
            String field = new String(passField.getPassword()).trim(); //get the password from password Field
            String rfield = new String(rpassField.getPassword()).trim(); //get the password from password Field
            
            // Check if both fields are empty
            if(txt.isEmpty()&& field.isEmpty()&& rfield.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the required fields.", "ERROR", JOptionPane.ERROR_MESSAGE);
            
            // Check if password field is empty
            } else if(field.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Input a Password.", "ERROR", JOptionPane.ERROR_MESSAGE);
            
            // Check if username field is empty    
            } else if(txt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Input a Name.", "ERROR", JOptionPane.ERROR_MESSAGE);
            
            // Check if repeat password field is empty     
            } else if(rfield.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Please Repeat the password.", "ERROR", JOptionPane.ERROR_MESSAGE);
            
            // Check if both password match    
            } else if(!field.equals(rfield)) {
                JOptionPane.showMessageDialog(this, "Password Do Not Match.", "ERROR", JOptionPane.ERROR_MESSAGE);
                
            } else {
                
                // Proceed with database operations
                Connection con = null;
                PreparedStatement pst = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {
                    con = sqlConnection(); // Establish database connection
                    if (con == null) {
                        throw new SQLException("Failed to connect to the database.");
                    }

                    // Check if the user already exists
                    pst = con.prepareStatement("SELECT * FROM register WHERE Username = ?");
                    pst.setString(1, txt);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "User already exists.", "Registration", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Insert the new user into the database
                        stmt = con.prepareStatement("INSERT INTO register (Username, Password, Time) VALUES (?, ?,?)");
                        stmt.setString(1, txt);
                        stmt.setString(2, field); // Save the confirmed password
                        stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Log the current time


                        int k = stmt.executeUpdate();
                        if (k == 1) {
                            JOptionPane.showMessageDialog(this, "Successfully Registered", "Registration", JOptionPane.INFORMATION_MESSAGE);
                            // Clear fields after successful registration
                            txtInput.setText("");
                            passField.setText("");
                            rpassField.setText("");
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "DATABASE ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } 
            }
            
        } else if (e.getSource() == btnlogin) {
            new Login_Frame().setVisible(true);
            dispose();
        }
    }
}
