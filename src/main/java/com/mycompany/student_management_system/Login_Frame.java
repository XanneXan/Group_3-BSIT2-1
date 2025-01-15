
package com.mycompany.student_management_system;

/**
 *
 * @author Admin
 */

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

     public class Login_Frame extends JFrame implements ActionListener {
         
        private JTextField txtInput;
        private JPasswordField passField;
        private JLabel lblname,lbl, passJLabel, lbllogo,lblStudent, lblStudent1, lblCountdown;
        private JButton btnlogin, btnregister;

        private int loginAttempt = 0; //track number of login attempts
        private int loginCountdown = 60; // Countdown timer for locking out login after failed attempts
        private Timer timer;
        
    
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
         
    Login_Frame (){
        
        //Main frmae
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Management System - Login");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // GUI components
        lblname = new JLabel("LOG IN");
        lblname.setBounds(560, 80, 800, 50);
        lblname.setFont(new Font("Arial Black",Font.BOLD,20));
        lblname.setForeground(new Color(125, 5, 4));
        add(lblname);
        
        lblStudent = new JLabel("STUDENT MANAGEMENT");
        lblStudent.setBounds(45, 310, 800, 50);
        lblStudent.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblStudent);
        
        lblStudent1= new JLabel("SYSTEM");
        lblStudent1.setBounds(130, 340, 800, 50);
        lblStudent1.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblStudent1);
        
        lbl = new JLabel("Name:");
        lbl.setBounds(370,150 ,150, 50);
        lbl.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lbl);
        
        lblCountdown = new JLabel();
        lblCountdown.setBounds(250, 400, 350, 30);
        lblCountdown.setFont(new Font("Arial", Font.ITALIC, 14));
        lblCountdown.setForeground(Color.RED);
        add(lblCountdown);
          
        txtInput = new JTextField();
        txtInput.setBounds (470,150,265,50);
        add(txtInput);
        
        passJLabel = new JLabel("Password:");
        passJLabel.setBounds(370, 220, 210, 50);
        passJLabel.setFont(new Font("Arial Black",Font.BOLD,13));
        add(passJLabel);
       
        passField = new JPasswordField();
        passField.setBounds(470,220,265,50);
        add(passField);
        
        btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(410,320,120,60);
        btnlogin.setFont(new Font("Arial Black",Font.BOLD,13));
        btnlogin.setForeground(Color.WHITE);
        btnlogin.setBackground(new Color(125, 5, 4));
        add(btnlogin);
        
        btnregister = new JButton("REGISTER");
        btnregister.setBounds(580,320,120,60);
        btnregister.setFont(new Font("Arial Black",Font.BOLD,13));
        btnregister.setForeground(Color.WHITE);
        btnregister.setBackground(new Color(125, 5, 4));
        add(btnregister);
        
        //Insert image in the Jlabel
        ImageIcon imgIconLogo = new ImageIcon("logo_icon.png"); // load image file
        Image scale = imgIconLogo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // size of the image
        ImageIcon imgnew = new ImageIcon(scale);
        lbllogo = new JLabel(imgnew);
        lbllogo.setBounds(30, 40, 300, 300);
        add(lbllogo);
         
        btnlogin.addActionListener(this);
        btnregister.addActionListener(this);
        
        sqlConnection();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Buttons Functions 
        if(e.getSource() == btnregister) {     
            new Register_Frame().setVisible(true);
            dispose();
            
        } if(e.getSource() == btnlogin) {
            String txt = txtInput.getText().trim(); //get username from textfield
            String field = new String(passField.getPassword()).trim(); //get password from passwordfield
            
            // Check if both fields are empty
            if(txt.isEmpty()&& field.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Input a Name and Password", "ERROR", JOptionPane.ERROR_MESSAGE);
            
            // Check if password field is empty
            }else if(field.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Input a Password", "ERROR", JOptionPane.ERROR_MESSAGE);
            
            // Check if username field is empty
            }else if(txt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Input a Name", "ERROR", JOptionPane.ERROR_MESSAGE);
                 
            }else {
                
                // Proceed with database operations
                Connection con;
                PreparedStatement pst;
                PreparedStatement stmt;
                ResultSet rs;

                try {
                    con = sqlConnection(); // Establish database connection
                    if (con == null) {
                        throw new SQLException("Failed to connect to the database.");
                    }

                     // Check if the user exists in the database
                    pst = con.prepareStatement("SELECT * FROM register WHERE Username = ? AND Password = ?");
                    pst.setString(1, txt);
                    pst.setString(2, field);
                    rs = pst.executeQuery();

                    if (rs.next()) { // If the username exists
                        
                        JOptionPane.showMessageDialog(this, "WELCOME TO STUDENT MANAGEMENT SYSTEM OF GROUP 3!!!", "Successful", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                
                                // Insert the login record into the login table
                                stmt = con.prepareStatement("INSERT INTO login(Username, Password, Time) VALUES(?,?,?)");
                                stmt.setString(1, txt);
                                stmt.setString(2, field);
                                stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Log the current time

                                stmt.executeUpdate();

                            } catch(Exception ex){
                                ex.printStackTrace();
                            }
                              new Menu_Frame().setVisible(true);
                              dispose();
                              
                        } else { // If the username does not exist
                            loginAttempt++; 
                            if (loginAttempt >= 5) { // If too many failed attempt
                                startCountdown(); //start cooldown timer
                            } else {
                                JOptionPane.showMessageDialog(this, "Incorrect name, password or no existing user. Attempt " + loginAttempt + " of 5.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }    

                    } catch(Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "DATABASE ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
            }     
        }
    }
    
        // Method to start the cooldown timer after too many failed login attempts
        private void startCountdown() {
        btnlogin.setEnabled(false);
        lblCountdown.setText("Too many failed attempts. Please wait " + loginCountdown + " seconds.");
        timer = new Timer(1000, new ActionListener() {
            int timeLeft = loginCountdown;

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                lblCountdown.setText("Too many failed attempts. Please wait " + timeLeft + " seconds.");
                if (timeLeft <= 0) {
                    timer.stop();
                    btnlogin.setEnabled(true);
                    lblCountdown.setText("");
                    loginAttempt = 0; // Reset failed attempts
                }
            }
        });
        timer.start();
    }
       
 }    
