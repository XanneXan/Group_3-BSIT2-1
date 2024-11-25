/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public class Course_Class extends JFrame implements ActionListener{

    private JLabel lblTitle, lblcourseName, lblCourseId , lblStudentNum, lblSearch;
    private JTextField txtCourseName, txtCourseId, txtStudentnum, txtSearch;
    private JButton btnAdd, btnDelete, btnUpdate, btnClear, btnAllCourse, btnSearch, btnRefresh, btnMenu, backbtn;
    private JTable studList, table;
    private JScrollPane scrollPane;
     
    Course_Class (){
        
            setTitle("Student Management System");
            setExtendedState(MAXIMIZED_BOTH);     
            setLayout(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
            lblTitle = new JLabel ("Student Management System");
            lblTitle.setBounds(30, 20, 350, 30);setExtendedState(MAXIMIZED_BOTH);     
            lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
            add (lblTitle);

            lblCourseId = new JLabel ("Course ID: ");
            lblCourseId.setBounds(50, 190, 150, 40);
            lblCourseId.setFont(new Font("Arial", 1, 16));
            add (lblCourseId);

            lblcourseName = new JLabel ("Course Name: ");
            lblcourseName.setBounds(50, 240, 150, 40);
            lblcourseName.setFont(new Font("Arial", 1, 16));
            add (lblcourseName);

            lblStudentNum = new JLabel ("Number of Student: ");
            lblStudentNum.setBounds(50, 290, 250, 40);
            lblStudentNum.setFont(new Font("Arial", 1, 16));
            add (lblStudentNum);

            txtCourseId = new JTextField ("");
            txtCourseId.setEditable(true);
            txtCourseId.setBounds(200, 195, 250, 25);
            add (txtCourseId);

            txtCourseName = new JTextField ("");
            txtCourseName.setEditable(true);
            txtCourseName.setBounds(200, 245, 250, 25);
            add(txtCourseName);

            txtStudentnum = new JTextField ();
            txtStudentnum.setBackground(Color.WHITE);
            txtStudentnum.setEditable(false);
            txtStudentnum.setBounds(215, 295, 100, 25);
            add(txtStudentnum);

            //add butttons
            btnAdd = new JButton ("Add");
            btnAdd.setBounds(50, 400, 120, 40);
            btnAdd.setFocusable(false);
            btnAdd.setForeground(Color.WHITE);
            btnAdd.setBackground(Color.BLUE);
            add (btnAdd);

            btnUpdate = new JButton ("Update");
            btnUpdate.setBounds(190, 400, 120, 40);
            btnUpdate.setFocusable(false);
            btnUpdate.setForeground(Color.WHITE);
            btnUpdate.setBackground(Color.BLUE);
            add (btnUpdate);

            btnDelete = new JButton ("Delete");
            btnDelete.setBounds(330, 400, 120, 40);
            btnDelete.setFocusable(false);
            btnDelete.setForeground(Color.WHITE);
            btnDelete.setBackground(Color.BLUE);
            add(btnDelete);

            btnClear = new JButton ("Clear");
            btnClear.setBounds(115, 460, 120, 40);
            btnClear.setFocusable(false);
            btnClear.setForeground(Color.WHITE);
            btnClear.setBackground(Color.BLUE);
            add (btnClear);

            btnAllCourse = new JButton ("Courses");
            btnAllCourse.setBounds(265, 460, 120, 40);
            btnAllCourse.setFocusable(false);
            btnAllCourse.setForeground(Color.WHITE);
            btnAllCourse.setBackground(Color.BLUE);
            add (btnAllCourse);

            // Table where the information will appear
            studList = new JTable ();
            scrollPane = new JScrollPane(studList);
            scrollPane.setBounds(600, 150, 720, 450);
            scrollPane.getViewport().setBackground(Color.lightGray);
            add(scrollPane);

            //Search Function
            lblSearch = new JLabel ("Search Student: ");
            lblSearch.setBounds(600, 110, 150, 40);
            lblSearch.setFont(new Font("Arial", 1, 14));
            add (lblSearch);

            txtSearch = new JTextField ();
            txtSearch.setBounds(730, 115, 400, 25);
            add (txtSearch);

            btnSearch = new JButton ("Search");
            btnSearch.setBounds(1170, 115, 70, 25);
            btnSearch.setFocusable(false);
            btnSearch.setFont(new Font("Arial", 0, 10));
            btnSearch.setForeground(Color.WHITE);
            btnSearch.setBackground(Color.BLUE);
            add (btnSearch);

            btnRefresh = new JButton ("Refresh");
            btnRefresh.setBounds(1250, 115, 70, 25);
            btnRefresh.setFocusable(false);
            btnRefresh.setFont(new Font("Arial", 0, 10));
            btnRefresh.setForeground(Color.WHITE);
            btnRefresh.setBackground(Color.BLUE);
            add (btnRefresh);

            //set button for menu
            btnMenu = new JButton ("Menu");
            btnMenu.setBounds(1250, 650, 70, 25);
            btnMenu.setFocusable(false);
            btnMenu.setFont(new Font("Arial", 0, 10));
            btnMenu.setForeground(Color.WHITE);
            btnMenu.setBackground(Color.BLUE);
            add (btnMenu);

            //set button action list
            btnAllCourse.addActionListener(this);
   
    }

        @Override
        public void actionPerformed (ActionEvent e) {
            if(e.getSource() == btnAllCourse){
                courseFrame play = new courseFrame();
                play.setVisible(true);
            }
        }

    //adding new frame to see all course
    private class courseFrame extends JFrame{
           
        public courseFrame (){   
            
            setTitle("Student Management System");
            setExtendedState(MAXIMIZED_BOTH);     
            setLayout(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
            lblTitle = new JLabel ("Course");
            lblTitle.setBounds(30, 20, 350, 30);setExtendedState(MAXIMIZED_BOTH);     
            lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
            add (lblTitle);
            
            //adding table to show the courses and students
            table = new JTable();
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(50, 135, 1020, 550);
            scrollPane.getViewport().setBackground(Color.lightGray);
            add(scrollPane);
            
            //same with the main frame
            lblSearch = new JLabel ("Search Student: ");
            lblSearch.setBounds(50, 90, 150, 40);
            lblSearch.setFont(new Font("Arial", 1, 14));
            add (lblSearch);

            txtSearch = new JTextField ("");
            txtSearch.setEditable(true);
            txtSearch.setBounds(200, 95, 650, 25);
            add (txtSearch);

            btnSearch = new JButton ("Search");
            btnSearch.setBounds(900, 95, 70, 25);
            btnSearch.setFocusable(false);
            btnSearch.setFont(new Font("Arial", 0, 10));
            btnSearch.setForeground(Color.WHITE);
            btnSearch.setBackground(Color.BLUE);
            add (btnSearch);

            btnRefresh = new JButton ("Refresh");
            btnRefresh.setBounds(1000, 95, 70, 25);
            btnRefresh.setFocusable(false);
            btnRefresh.setFont(new Font("Arial", 0, 10));
            btnRefresh.setForeground(Color.WHITE);
            btnRefresh.setBackground(Color.BLUE);
            add (btnRefresh);
            
            //dispose the current frame go back to main frame
            backbtn = new JButton ("Back");
            backbtn.setBounds(1150, 650, 120, 40);
            backbtn.setFocusable(false);
            backbtn.setFont(new Font("Arial", 0, 10));
            backbtn.setForeground(Color.WHITE);
            backbtn.setBackground(Color.BLUE);
            add (backbtn);

            
        }
        
    }
}



