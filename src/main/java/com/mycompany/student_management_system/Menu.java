/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

/**
 *
 * @author XANNE
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
private JLabel lblTitle,Students,Course,Grades,Logout;
private JButton btnStudents, btnCourse, btnGrades, btnLogout;

public Menu(){
    setTitle("Student Management System");
    setSize(700, 600);
    setLayout(null);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    lblTitle = new JLabel("Student Management System");
    lblTitle.setBounds(100, 50, 500, 40);
    lblTitle.setFont(new Font("Arial", Font.BOLD,33));
    add(lblTitle);
    
    btnStudents = new JButton("STUDENTS");
    btnStudents.setBounds(225, 170, 240, 60);
    btnStudents.setFont(new Font("Arial",Font.PLAIN,20));
    btnStudents.setBackground(Color.blue);
    btnStudents.setForeground(Color.white);//for the font color
    btnStudents.addActionListener(this);
    add(btnStudents);
    
    btnCourse = new JButton("COURSE");
    btnCourse.setBounds(225, 260, 240, 60);
    btnCourse.setFont(new Font("Arial",Font.PLAIN,20));
    btnCourse.setBackground(Color.blue);
    btnCourse.setForeground(Color.white);
    btnCourse.addActionListener(this);
    add(btnCourse);
   
    
    //Buttons
    btnGrades = new JButton("GRADES");
    btnGrades.setBounds(225, 350, 240, 60);
    btnGrades.setFont(new Font("Arial",Font.PLAIN,20));
    btnGrades.setBackground(Color.blue);
    btnGrades.setForeground(Color.white);
    btnGrades.addActionListener(this);
    add(btnGrades);
    
    btnLogout = new JButton("Logout");
    btnLogout.setBounds(580, 500, 80, 30);
    btnLogout.setFont(new Font("Arial",Font.PLAIN,15)); 
    btnLogout.setBackground(Color.blue);
    btnLogout.setForeground(Color.white);
    btnLogout.addActionListener(this);
    add(btnLogout);
    
}
  
    @Override
  public void actionPerformed(ActionEvent e) {
      //Buttons Function
    if(e.getSource()==btnStudents){
        new Student_Class ().setVisible(true);
        dispose();

    } else if(e.getSource()==btnCourse){
        new Course_Class().setVisible(true);
        dispose();
     
    
    } else if(e.getSource()==btnGrades){
        new Grade_Class().setVisible(true);
        dispose();
     
    
        }
    }
}
