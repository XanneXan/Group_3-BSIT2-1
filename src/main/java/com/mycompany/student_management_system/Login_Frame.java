package com.mycompany.student_management_system;

/**
 *
 * @author Sebastian Rafael
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

    public class Login_Frame extends JFrame implements ActionListener {
         
        private final JTextField txtInput;
        private final JPasswordField passField;
        private final JLabel lblname, lbl, passJLabel, lblCountdown;
        private final JButton btnlogin, btnregister;
         
        private Connection registerCon; // Connection for the 'register' database
        private Connection loginCon;    // Connection for the 'login' database
        private PreparedStatement pst;
        private int loginAttempt = 0;
        private int loginCountdown = 60;
        private Timer timer;
         
    Login_Frame() {
      
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        lblname = new JLabel("Student Management System");
        lblname.setBounds(80, 50, 800, 50);
        lblname.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblname);
        
        lbl = new JLabel("Name:");
        lbl.setBounds(60,180 ,150, 50);
        lbl.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lbl);

        txtInput = new JTextField();
        txtInput.setBounds (165,180,265,40);
        add(txtInput);
        
        passJLabel = new JLabel("Password:");
        passJLabel.setBounds(60, 250, 210, 50);
        passJLabel.setFont(new Font("Arial Black",Font.BOLD,13));
        add(passJLabel);
       
        passField = new JPasswordField();
        passField.setBounds(165,250,265,40);
        add(passField);
        
        btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(80,400,120,45);
        btnlogin.setFont(new Font("Arial Black",Font.BOLD,13));
        add(btnlogin);
        
        btnregister = new JButton("REGISTER");
        btnregister.setBounds(280,400,120,45);
        btnregister.setFont(new Font("Arial Black",Font.BOLD,13));
        add(btnregister);
        
        lblCountdown = new JLabel();
        lblCountdown.setBounds(80, 470, 350, 30);
        lblCountdown.setFont(new Font("Arial", Font.ITALIC, 14));
        lblCountdown.setForeground(Color.RED);
        add(lblCountdown);
          
        btnlogin.addActionListener(this);
        btnregister.addActionListener(this);
       
        //call the method to establish mysql
        connectionMySql();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == btnregister) {     
            new Register_Frame().setVisible(true);
            dispose();
            
        } if(e.getSource() == btnlogin) {
          
            String txt = txtInput.getText().trim();
            String field = new String(passField.getPassword()).trim();
            
            if(txt.isEmpty()&& field.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PLEASE INPUT A PASSWORD AND NAME", "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }else if(field.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PLEASE INPUT A PASSWORD", "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }else if(txt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PLEASE INPUT A NAME", "ERROR", JOptionPane.ERROR_MESSAGE);
                 
            }else {
                           
                try {
                    pst = registerCon.prepareStatement("SELECT * FROM register_table WHERE Username = ? AND Password = ?");
                    pst.setString(1, txt);
                    pst.setString(2, field);

                    ResultSet result = pst.executeQuery();

                    if (result.next()) {
                        JOptionPane.showMessageDialog(this, "Login", "Successful", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                pst = loginCon.prepareStatement("INSERT INTO login_table(Username,Password) VALUES(?,?)");
                                pst.setString(1, txt);
                                pst.setString(2, field);
                                pst.executeUpdate();

                            } catch(Exception ex){
                                ex.printStackTrace();
                            }
                              new Menu_Frame().setVisible(true);
                              dispose();
                              
                        } else {
                            loginAttempt++; 
                            if (loginAttempt >= 5) {
                                startCountdown();
                            } else {
                                JOptionPane.showMessageDialog(this, "Incorrect name or password. Attempt " + loginAttempt + " of 5.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }    

                    } catch(Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "DATABASE ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
            }     
        }
    }
   
        public void connectionMySql() {

            String urlReg = "jdbc:mysql://127.0.0.1:3306/register";
            String usernameReg = "root";
            String passwordReg = "Queenworks";

            try{
                registerCon = DriverManager.getConnection(urlReg, usernameReg,passwordReg);
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            
            String urlLog = "jdbc:mysql://127.0.0.1:3306/login";
            String usernameLog = "root";
            String passwordLog = "Queenworks";

            try{
               loginCon = DriverManager.getConnection(urlLog,usernameLog,passwordLog);
            } catch(SQLException ex){
                ex.printStackTrace();
            }
    } 
        
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