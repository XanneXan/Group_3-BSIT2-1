
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

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

/**
 *
 * @author Admin
 */
public class Register_Frame extends JFrame implements ActionListener {
    private JLabel lblpassword, lblpass, lblrpass, lbltitle, lblname, lblstud, lblsystem, lbllogo;
    private JTextField txtInput;
    private JPasswordField passField,rpassField;
    private JButton btnlogin, btnconfirm;
    private ImageIcon imgIconLogo, imgnew;
    private URL ImgLogo;
    
            
    Register_Frame (){
     
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        lbltitle = new JLabel("REGISTER");
        lbltitle.setBounds(525, 70, 800, 50);
        lbltitle.setFont(new Font("Arial Black",Font.BOLD,20));
        lbltitle.setForeground(new Color(125, 5, 4));
        add(lbltitle);
        
        lblstud = new JLabel("STUDENT MANAGEMENT");
        lblstud.setBounds(65, 370, 800, 50);
        lblstud.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblstud);
        
         lblsystem = new JLabel("SYSTEM");
        lblsystem.setBounds(150, 400, 800, 50);
        lblsystem.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lblsystem);
        
        lblname = new JLabel("Name:");
        lblname.setBounds(370,180 ,150, 50);
        lblname.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblname);

        txtInput = new JTextField();
        txtInput.setBounds (470,180,265,50);
        add(txtInput);
        
        lblpassword = new JLabel("Password:");
        lblpassword.setBounds(370, 250, 210, 50);
        lblpassword.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblpassword);
       
        passField = new JPasswordField();
        passField.setBounds(470,250,265,50);
        add(passField);
        
        lblpass = new JLabel("Repeat");
        lblpass.setBounds(370, 310, 220, 50);
        lblpass.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblpass);
        
        lblrpass = new JLabel("Password:");
        lblrpass.setBounds(370, 330, 230, 50);
        lblrpass.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblrpass);
        
        rpassField = new JPasswordField();
        rpassField.setBounds(470, 320, 265, 50);
        add(rpassField);
        
        //Buttons
        
        btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(410,400,120,60);
        btnlogin.setFont(new Font("Arial Black",Font.BOLD,13));
        btnlogin.setForeground(Color.WHITE);
        btnlogin.setBackground(new Color(125, 5, 4));
        add(btnlogin);
        
        btnconfirm = new JButton("CONFIRM");
        btnconfirm.setBounds(580,400,120,60);
        btnconfirm.setFont(new Font("Arial Black",Font.BOLD,13));
        btnconfirm.setForeground(Color.WHITE);
        btnconfirm.setBackground(new Color(125, 5, 4));
        add(btnconfirm);
        
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
        btnconfirm.addActionListener(this);
        
    }

    //Button function
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnconfirm) { //if the confirm button was clicked
            String txt=txtInput.getText().trim(); //get the text input from the txtInput field
            String field=new String(passField.getPassword()).trim(); //get the password entered in the passField
            String rfield=new String(rpassField.getPassword()).trim(); //get the password entered in the rpassField
            
            if(txt.isEmpty()&& field.isEmpty()&& rfield.isEmpty()){
                JOptionPane.showMessageDialog(this, "PLEASE FILL ALL FIELD", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else if(field.isEmpty()){
                JOptionPane.showMessageDialog(this, "PLEASE INPUT A PASSWORD", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else if(txt.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "PLEASE INPUT A NAME", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else if(!field.equals(rfield)){
                JOptionPane.showMessageDialog(this, "PASSWORD DO NOT MATCH", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(this, "SUCCESSFULLY REGISTERED");
                txtInput.setText("");
                passField.setText("");
                rpassField.setText("");
                
            
            }
        }else if(e.getSource()==btnlogin){
            new Login_Frame();
        }       
      }
    }

    