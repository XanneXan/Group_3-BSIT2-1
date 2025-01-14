package com.mycompany.student_management_system;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

     public class Login_Frame extends JFrame implements ActionListener {
         private JTextField txtInput;
         private JPasswordField passField;
         private JLabel lblname,lbl, passJLabel, lbllogo,lblStudent, lblStudent1;
         private JButton btnlogin, btnregister;
         
         
    Login_Frame (){
        
        
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Student Management System - Login");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        
        
        lblname = new JLabel("LOG IN");
        lblname.setBounds(560, 70, 800, 50);
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
        
        
        ImageIcon imgIconLogo = new ImageIcon("logo_icon.png");
        Image scale = imgIconLogo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imgnew = new ImageIcon(scale);
        lbllogo = new JLabel(imgnew);
        lbllogo.setBounds(50, 100, 300, 300);
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