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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

public class Menu_Frame extends JFrame implements ActionListener{
private JLabel lblTitle,Students,Course,Grades,Logout,Attendance,Performance,Reports,lblSystem;
private JButton btnStudents, btnCourse, btnGrade, btnLogout, btnAttendance, btnPerformanceReports;

public Menu_Frame(){
    setTitle("Student Management System");
    setSize(700, 600);
    setLayout(null);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    lblTitle = new JLabel("STUDENT MANAGEMENT");
    lblTitle.setBounds(93, 50, 500, 40);
    lblTitle.setForeground(new Color(125,5,4));
    lblTitle.setFont(new Font("Arial", Font.BOLD,39));
    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    add(lblTitle);
    
    lblSystem = new JLabel("SYSTEM");
    lblSystem.setBounds(95, 90, 500, 40);
    lblSystem.setForeground(new Color(125,5,4));
    lblSystem.setFont(new Font("Arial", Font.BOLD,39));
    lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
    add(lblSystem);
    
    Students = new JLabel("Students");
    Students.setBounds(100, 275, 100, 20);
    Students.setFont(new Font("Arial",Font.BOLD,15));
    add(Students);
    
    Course = new JLabel("Course");
    Course.setBounds(289, 275, 100, 20);
    Course.setHorizontalAlignment(SwingConstants.CENTER);
    Course.setFont(new Font("Arial",Font.BOLD,15));
    add(Course);
    
    Grades =new JLabel("Grades");
    Grades.setBounds(490, 275, 100, 20);
    Grades.setHorizontalAlignment(SwingConstants.CENTER);
    Grades.setFont(new Font("Arial",Font.BOLD,15));
    add(Grades);
    
    Attendance = new JLabel ("Attendance");
    Attendance.setBounds(160, 455, 100, 40);
    Attendance.setHorizontalAlignment(SwingConstants.CENTER);
    Attendance.setFont(new Font("Arial",Font.BOLD,15));
    add(Attendance);
    
    Performance = new JLabel("Performance");
    Performance.setBounds(405, 445, 100, 40);
    Performance.setVerticalAlignment(SwingConstants.BOTTOM);
    Performance.setFont(new Font("Arial",Font.BOLD,14));
    add(Performance);
    
    Reports = new JLabel("Reports");
    Reports.setBounds(420, 485, 100, 20);
    Reports.setVerticalAlignment(SwingConstants.BOTTOM);
    Reports.setFont(new Font("Arial",Font.BOLD,14));
    add(Reports);
 
    String StudentsURL = "https://i.pinimg.com/736x/f5/c9/69/f5c9694bf380fe9a1641ade5da6ec9a3.jpg";
    String CourseURL = "https://i.pinimg.com/474x/9f/f2/58/9ff2581c566b0ee8179e6127c3a0feb0.jpg";// the URL of every image
    String GradeURL = "https://i.pinimg.com/474x/0b/a4/eb/0ba4eb614c0dacfe8677cda72fbfed08.jpg";
    String AttendanceURL = "https://i.pinimg.com/474x/c3/58/da/c358dae512bb3f563f182aab11554516.jpg";
    String PerformanceReportsURL = "https://i.pinimg.com/474x/b6/42/15/b64215c6495ea44c4d6299f7eca6802b.jpg";
 
  try{
   URL StudentImageURL = new URL(StudentsURL);
   URL CourseImageURL = new URL(CourseURL);
   URL GradeImageURL = new URL(GradeURL);
   URL AttendanceImageURL = new URL(AttendanceURL);
   URL PerformanceReportsImageURL = new URL(PerformanceReportsURL);
     
   ImageIcon icon1 = new ImageIcon(StudentImageURL);
   ImageIcon icon2 = new ImageIcon(CourseImageURL);  
   ImageIcon icon3 = new ImageIcon(GradeImageURL); // the URL of every image and for it to be visible
   ImageIcon icon4 = new ImageIcon(AttendanceImageURL); 
   ImageIcon icon5 = new ImageIcon(PerformanceReportsImageURL);
      
   Image StudentImage= icon1.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
   Image CourseImage = icon2.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);// this is to adjust the image to b fit in the button
   Image GradesImage= icon3.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
   Image AttendancesImage = icon4.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
   Image PerformanceReportImage = icon5.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
    
   btnStudents = new JButton(new ImageIcon(StudentImage));
   btnCourse = new JButton(new ImageIcon(CourseImage));
   btnGrade = new JButton(new ImageIcon(GradesImage));
   btnAttendance = new JButton(new ImageIcon(AttendancesImage));
   btnPerformanceReports = new JButton(new ImageIcon(PerformanceReportImage));
  
    btnStudents.setBounds(70, 150, 130, 115);//Just adjust the bounds back or  up depends on alignment
    btnStudents.addActionListener(this);
    add(btnStudents);
    
    btnCourse.setBounds(275, 150, 130, 115);//same with this can also change the bounds depends on alignment
    btnCourse.addActionListener(this);
    add(btnCourse);
     
    btnGrade.setBounds(480, 150, 130, 115);//also this one i orderly change each 
    btnGrade.addActionListener(this);
    add(btnGrade);
    
    btnAttendance.setBounds(150, 340, 130, 115);//also this one i orderly change each 
    btnAttendance.addActionListener(this);
    add(btnAttendance);
       
    btnPerformanceReports.setBounds(386, 340, 130, 115);//also this one i orderly change each ;
    btnPerformanceReports.addActionListener(this);
    add(btnPerformanceReports);
    
    btnLogout = new JButton("Logout");
    btnLogout.setBounds(580, 500, 80, 30);//in this part just adjust te first number
    btnLogout.setFont(new Font("Arial",Font.PLAIN,15)); 
    btnLogout.setBackground(new Color(125,5,4));
    btnLogout.setForeground(Color.white);
    btnLogout.addActionListener(this);
    add(btnLogout);  
  }catch (Exception e){
      e.printStackTrace(); //this is for incase that the image s having an error
}
}     
    @Override
  public void actionPerformed(ActionEvent e) {
    //Buttons function
    if(e.getSource()==btnStudents){
        new Student_Class ().setVisible(true);
        dispose();

    } else if(e.getSource()==btnCourse){
        new Course_Class().setVisible(true);
        dispose();    
    
    } else if(e.getSource()==btnGrade){
        new Grade_Class().setVisible(true);
        dispose();
    
    }else if(e.getSource()==btnAttendance){
            new Attendance_Frame().setVisible(true);
            dispose();
            
    } else if(e.getSource()==btnPerformanceReports){
            new Performance_Reports().setVisible(true);
            dispose();

    } else if(e.getSource()==btnLogout){
        dispose();
        }
    }

    }


    
