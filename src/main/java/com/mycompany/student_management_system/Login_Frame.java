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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

     public class Login_Frame extends JFrame implements ActionListener {
         private JTextField txtInput;
         private JPasswordField passField;
         private JLabel lblname,lbl, passJLabel, lbllogo,lblStudent, lblStudent1;
         private JButton btnlogin, btnregister;
         private ImageIcon imgIconLogo, imgnew;
         private URL ImgLogo;
         
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
        
        
         try {
            ImgLogo = new URL("https://i.pinimg.com/736x/b0/85/24/b08524b00a9d25ce9d3d9bb9c0bcaa2a.jpg");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Login_Frame.class.getName()).log(Level.SEVERE, null, ex);
        }

        imgIconLogo = new ImageIcon(ImgLogo);
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
       
        
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btnregister){
            new Register_Frame ().setVisible (true);
            dispose();
            
        }if (e.getSource()== btnlogin){
            new Menu_Frame().setVisible(true);
            dispose();
            
            String txt=txtInput.getText().trim();
            String field=new String(passField.getPassword()).trim();
            
            if(txt.isEmpty()&& field.isEmpty()){
                JOptionPane.showMessageDialog(this, "PLEASE INPUT A PASSWORD AND NAME", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else if(field.isEmpty()){
                JOptionPane.showMessageDialog(this, "PLEASE INPUT A PASSWORD", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else if(txt.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "PLEASE INPUT A NAME", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "INCORRECT NAME OR PASSWORD", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
                
           }
        }
    }

