package com.mycompany.student_management_system;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

     public class Login_Frame extends JFrame implements ActionListener {
         private JTextField txtInput;
         private JPasswordField passField;
         private JLabel lblname,lbl, passJLabel;
         private JButton btnlogin, btnregister;
         
         
    Login_Frame (){
      
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
        txtInput.setBounds (165,180,265,50);
        add(txtInput);
        
        passJLabel = new JLabel("Password:");
        passJLabel.setBounds(60, 250, 210, 50);
        passJLabel.setFont(new Font("Arial Black",Font.BOLD,13));
        add(passJLabel);
       
        passField = new JPasswordField();
        passField.setBounds(165,250,265,50);
        add(passField);
        
        btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(50,400,120,60);
        btnlogin.setFont(new Font("Arial Black",Font.BOLD,13));
        add(btnlogin);
        
        btnregister = new JButton("REGISTER");
        btnregister.setBounds(310,400,120,60);
        btnregister.setFont(new Font("Arial Black",Font.BOLD,13));
        add(btnregister);
        
        
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