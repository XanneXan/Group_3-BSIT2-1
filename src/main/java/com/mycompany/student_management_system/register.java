/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class register extends JFrame implements ActionListener {
    private JLabel lblpassword, lblpass, lblrpass, lbltitle, lblname;
    private JTextField txtInput;
    private JPasswordField passField,rpassField;
    private JButton btnlogin, btnconfirm;
    
            
    register (){
     
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        
        lbltitle = new JLabel("Student Management System");
        lbltitle.setBounds(80, 50, 800, 50);
        lbltitle.setFont(new Font("Arial Black",Font.BOLD,20));
        add(lbltitle);
        
        lblname = new JLabel("Name:");
        lblname.setBounds(60,180 ,150, 50);
        lblname.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblname);

        txtInput = new JTextField();
        txtInput.setBounds (165,180,265,50);
        add(txtInput);
        
        lblpassword = new JLabel("Password:");
        lblpassword.setBounds(60, 250, 210, 50);
        lblpassword.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblpassword);
       
        passField = new JPasswordField();
        passField.setBounds(165,250,265,50);
        add(passField);
        
        lblpass = new JLabel("Repeat");
        lblpass.setBounds(60, 310, 220, 50);
        lblpass.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblpass);
        
        lblrpass = new JLabel("Password:");
        lblrpass.setBounds(60, 330, 230, 50);
        lblrpass.setFont(new Font("Arial Black",Font.BOLD,13));
        add(lblrpass);
        
        rpassField = new JPasswordField();
        rpassField.setBounds(165, 320, 265, 50);
        add(rpassField);
        
        //Buttons
        
        btnlogin = new JButton("LOG IN");
        btnlogin.setBounds(50,400,120,60);
        btnlogin.setFont(new Font("Arial Black",Font.BOLD,13));
        add(btnlogin);
        
        btnconfirm = new JButton("CONFIRM");
        btnconfirm.setBounds(310,400,120,60);
        btnconfirm.setFont(new Font("Arial Black",Font.BOLD,13));
        add(btnconfirm);
        
        
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
            new Login();
        }       
      }
    }
    