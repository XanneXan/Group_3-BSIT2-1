
package com.mycompany.student_management_system;

/**
 *
 * @author Admin
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

    public class Login_Frame extends JFrame implements ActionListener {
        private JTextField txtInput;
        private JPasswordField passField;
        private JLabel lblname,lbl, passJLabel, lbllogo,lblStudent, lblStudent1, lblCountdown;
        private JButton btnlogin, btnregister;
        private ImageIcon imgIconLogo, imgnew;
        private URL ImgLogo;
         
        private Connection registerCon; // Connection for the 'register' database
        private Connection loginCon;    // Connection for the 'login' database
        private PreparedStatement pst;
        private int loginAttempt = 0;
        private int loginCountdown = 60;
        private Timer timer;
         
    Login_Frame (){
      
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        lblname = new JLabel("LOG IN");
        lblname.setBounds(525, 70, 800, 50);
        lblname.setFont(new Font("Arial Black",Font.BOLD,20));
        lblname.setForeground(new Color(125, 5, 4));
        add(lblname);
        
        lblStudent = new JLabel("STUDENT MANAGEMENT");
        lblStudent.setBounds(65, 370, 800, 50);
        lblStudent.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblStudent);
        
        lblStudent1= new JLabel("SYSTEM");
        lblStudent1.setBounds(150, 400, 800, 50);
        lblStudent1.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblStudent1);
        
        lbl = new JLabel("Name:");
        lbl.setBounds(370,180 ,150, 50);
        lbl.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lbl);
        
        lblCountdown = new JLabel();
        lblCountdown.setBounds(80, 470, 350, 30);
        lblCountdown.setFont(new Font("Arial", Font.ITALIC, 14));
        lblCountdown.setForeground(Color.RED);
        add(lblCountdown);
          

        txtInput = new JTextField();
        txtInput.setBounds (470,180,265,50);
        add(txtInput);
        
        passJLabel = new JLabel("Password:");
        passJLabel.setBounds(370, 250, 210, 50);
        passJLabel.setFont(new Font("Arial Black",Font.BOLD,13));
        add(passJLabel);
       
        passField = new JPasswordField();
        passField.setBounds(470,250,265,50);
        add(passField);
        
        btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(410,400,120,60);
        btnlogin.setFont(new Font("Arial Black",Font.BOLD,13));
        btnlogin.setForeground(Color.WHITE);
        btnlogin.setBackground(new Color(125, 5, 4));
        add(btnlogin);
        
        btnregister = new JButton("REGISTER");
        btnregister.setBounds(580,400,120,60);
        btnregister.setFont(new Font("Arial Black",Font.BOLD,13));
        btnregister.setForeground(Color.WHITE);
        btnregister.setBackground(new Color(125, 5, 4));
        add(btnregister);
        
        
        imgIconLogo = new ImageIcon("C:\\Users\\Admin\\Downloads\\download (1).png"); 
        Image imgscaleLogo = imgIconLogo.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imgnew = new ImageIcon(imgscaleLogo);

        lbllogo = new JLabel(imgnew) {
            @Override
            protected void paintComponent(Graphics g) {
                Shape circle = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
                g.setClip(circle);
                super.paintComponent(g);
            }
        };
        lbllogo.setBounds(100, 150, 200, 200);
        lbllogo.setOpaque(true);
        lbllogo.setBackground(new Color(220, 220, 220));
        add(lbllogo);
        
        btnlogin.addActionListener(this);
        btnregister.addActionListener(this);
       
        //add method for mysql
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
                JOptionPane.showMessageDialog(this, "Please Input a Name and Password", "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }else if(field.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Input a Password", "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }else if(txt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Input a Name", "ERROR", JOptionPane.ERROR_MESSAGE);
                 
            }else {
                           
                try {
                    pst = registerCon.prepareStatement("SELECT * FROM register WHERE Username = ? AND Password = ?");
                    pst.setString(1, txt);
                    pst.setString(2, field);

                    ResultSet result = pst.executeQuery();

                    if (result.next()) {
                        JOptionPane.showMessageDialog(this, "Login", "Successful", JOptionPane.INFORMATION_MESSAGE);
                            try {
                                pst = loginCon.prepareStatement("INSERT INTO login(Username, Password) VALUES(?,?)");
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
      
        public void connectionMySql() {

            String urlReg = "jdbc:mysql://127.0.0.1:3306/student_management_system";
            String usernameReg = "root";
            String passwordReg = "mysqlpasswordg3";

            try{
                registerCon = DriverManager.getConnection(urlReg, usernameReg,passwordReg);
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            
            String urlLog = "jdbc:mysql://127.0.0.1:3306/student_management_system";
            String usernameLog = "root";
            String passwordLog = "mysqlpasswordg3";

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