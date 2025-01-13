/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

/**
 *
 * @author XANNE
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu_Frame extends JFrame implements ActionListener {
    private JLabel lblTitle, lblSystem, Students, Course, Grades, Attendance, Performance, Reports;
    private JButton btnStudents, btnCourse, btnGrade, btnLogout, btnAttendance, btnPerformanceReports;

    public Menu_Frame() {
        setTitle("Student Management System");
        setSize(700, 600);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Title
        lblTitle = new JLabel("STUDENT MANAGEMENT");
        lblTitle.setBounds(93, 50, 500, 40);
        lblTitle.setForeground(new Color(125, 5, 4));
        lblTitle.setFont(new Font("Serif", Font.BOLD, 39));
        add(lblTitle);

        lblSystem = new JLabel("SYSTEM");
        lblSystem.setBounds(270, 90, 500, 40);
        lblSystem.setForeground(new Color(125, 5, 4));
        lblSystem.setFont(new Font("Serif", Font.BOLD, 39));
        add(lblSystem);

        // In this part was thee src or the source codeof the images for it to show as the buttons also 
        ImageIcon studentIcon = new ImageIcon("students_icon.jpg");
        Image scale = studentIcon.getImage().getScaledInstance(130, 115, Image.SCALE_SMOOTH);
        ImageIcon logoicon = new ImageIcon(scale);
        btnStudents = new JButton (logoicon);
        btnStudents.setBounds(70, 150, 130, 115);
        btnStudents.addActionListener(this);
        add(btnStudents); 
            
        ImageIcon courseIcon = new ImageIcon("course_icon.jpg");
        Image scale2 = courseIcon.getImage().getScaledInstance(130, 115, Image.SCALE_SMOOTH);
        ImageIcon logoicon2 = new ImageIcon(scale);
        btnCourse = new JButton (logoicon);
        btnCourse.setBounds(275, 150, 130, 115);
        btnCourse.addActionListener(this);
        add(btnCourse); 
        
        ImageIcon gradeIcon = new ImageIcon("grade_icon.jpg");
        Image scale3 = gradeIcon.getImage().getScaledInstance(130, 115, Image.SCALE_SMOOTH);
        ImageIcon logoicon3 = new ImageIcon(scale);
        btnGrade = new JButton (logoicon);
        btnGrade.setBounds(480, 150, 130, 115);
        btnGrade.addActionListener(this);
        add(btnGrade); 
        
        ImageIcon attendanceIcon = new ImageIcon("attendance_icon.jpg");
        Image scale4 = attendanceIcon.getImage().getScaledInstance(130, 115, Image.SCALE_SMOOTH);
        ImageIcon logoicon4 = new ImageIcon(scale);
        btnAttendance = new JButton (logoicon);
        btnAttendance.setBounds(150, 340, 130, 115);
        btnAttendance.addActionListener(this);
        add(btnAttendance); 
        
        ImageIcon performancereportsIcon = new ImageIcon("performance_icon.jpg");
        Image scale5 = performancereportsIcon.getImage().getScaledInstance(130, 115, Image.SCALE_SMOOTH);
        ImageIcon logoicon5 = new ImageIcon(scale);
        btnPerformanceReports = new JButton (logoicon);
        btnPerformanceReports.setBounds(386, 340, 130, 115);
        btnPerformanceReports.addActionListener(this);
        add(btnPerformanceReports); 
        

        Students = new JLabel("Students");
        Students.setBounds(80, 270, 100, 20);
        Students.setFont(new Font("Arial", Font.BOLD, 15));
        Students.setHorizontalAlignment(SwingConstants.CENTER);
        add(Students);

        Course = new JLabel("Course");
        Course.setBounds(290, 266, 100, 20);
        Course.setFont(new Font("Arial", Font.BOLD, 15));
        Course.setHorizontalAlignment(SwingConstants.CENTER);
        add(Course);

        Grades = new JLabel("Grades");
        Grades.setBounds(495, 265, 100, 20);
        Grades.setFont(new Font("Arial", Font.BOLD, 15));
        Grades.setHorizontalAlignment(SwingConstants.CENTER);
        add(Grades);

        Attendance = new JLabel("Attendance");
        Attendance.setBounds(165, 455, 100, 20);
        Attendance.setFont(new Font("Arial", Font.BOLD, 15));
        Attendance.setHorizontalAlignment(SwingConstants.CENTER); //ths are the buttons, bounds,fonts,the alignment 
        add(Attendance);

        Performance = new JLabel("Performance");
        Performance.setBounds(400, 455, 100, 20);
        Performance.setFont(new Font("Arial", Font.BOLD, 15));
        Performance.setHorizontalAlignment(SwingConstants.CENTER);
        add(Performance);

        Reports = new JLabel("Reports");
        Reports.setBounds(420, 470, 100, 20);
        Reports.setVerticalAlignment(SwingConstants.BOTTOM);
        Reports.setFont(new Font("Arial", Font.BOLD, 14));
        add(Reports);

        // Logout Button
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(580, 500, 80, 30);
        btnLogout.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogout.setBackground(new Color(125, 5, 4));
        btnLogout.setForeground(Color.white);
        btnLogout.addActionListener(this);
        add(btnLogout);
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStudents) {
            new Student_Class().setVisible(true);
            dispose();
        } else if (e.getSource() == btnCourse) {
            new Course_Class().setVisible(true);
            dispose();
        } else if (e.getSource() == btnGrade) {
            new Grade_Class().setVisible(true);
            dispose();
        } else if (e.getSource() == btnAttendance) {
            new Attendance_Frame().setVisible(true);
            dispose();
        } else if (e.getSource() == btnPerformanceReports) {
            new Performance_Reports().setVisible(true);
            dispose();
        } else if (e.getSource() == btnLogout) {
            dispose();
        }
    }

   
}