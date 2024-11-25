/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sebastian Rafael
 */
public class Attendance extends JFrame{
    
    private JLabel lblTitle, lblSearch, lblDate;
    private JSpinner dateSpinner;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, backbtn, submitbtn, cancelbtn;
    private JTable attendanceTable;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
   
    Attendance(){
        
        setTitle("Student Management System");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        lblTitle = new JLabel ("Attendance");
        lblTitle.setBounds(30, 20, 350, 30);setExtendedState(MAXIMIZED_BOTH);     
        lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
        add (lblTitle);
        
        lblDate = new JLabel("Date:");
        lblDate.setBounds(1110, 90, 100, 30);
        lblDate.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblDate);

        //set Jspinner to select a date
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(1165, 90, 150, 30);
        add(dateSpinner);

        lblSearch = new JLabel("Search Student:");
        lblSearch.setBounds(50, 90, 150, 30);
        lblSearch.setFont(new Font("Arial", 1, 14));
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(200, 90, 650, 25);
        add(txtSearch);

        //set the Buttonss
        btnSearch = new JButton("Search");
        btnSearch.setBounds(900, 90, 90, 25);
        //btnRefresh.setFocusable(false);
        btnSearch.setFont(new Font("Arial", 0, 10));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(Color.BLUE);
        add(btnSearch);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1000, 90, 90, 25);
        //btnRefresh.setFocusable(false);
        btnRefresh.setFont(new Font("Arial", 0, 10));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setBackground(Color.BLUE);
        add(btnRefresh);
        
        submitbtn = new JButton("Submit");
        submitbtn.setBounds(1165, 145, 120, 40);
        //submitbtn.setFocusable(false);
        submitbtn.setFont(new Font("Arial", 0, 10));        
        submitbtn.setForeground(Color.WHITE);
        submitbtn.setBackground(Color.BLUE);
        add(submitbtn);
        
        cancelbtn = new JButton("Cancel");
        cancelbtn.setBounds(1165, 210, 120, 40);
        //cancelbtn.setFocusable(false);
        cancelbtn.setFont(new Font("Arial", 0, 10));
        cancelbtn.setForeground(Color.WHITE);
        cancelbtn.setBackground(Color.BLUE);
        add(cancelbtn);

        backbtn = new JButton("Back");
        backbtn.setBounds(1165, 650, 120, 40);
        //btnRefresh.setFocusable(false);
        backbtn.setFont(new Font("Arial", 0, 10));
        backbtn.setForeground(Color.WHITE);
        backbtn.setBackground(Color.BLUE);
        add(backbtn);

        //set the table, example lang yung array of strings, para ma-visualize 
        String[] columnStrings = {"Student ID", " Course ID", "Course", "Student Name", "Present", "Absent"};
        
        String[][] rowStrings = {{"2023-0408-BN-0", "2023-911-IT", "BSIT", "Bruno Mars"},
                                       {"2023-092-BN-0",  "2023-451-IT", "BSIT", "Lady Gaga"},
                                       {"2023-236-BN-0",  "2023-904-IT", "BSIT", "Taylor Swift"}};
        
        model = new DefaultTableModel(rowStrings, columnStrings);
        
        attendanceTable = new JTable(model);
        scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 135, 1055, 550);
        scrollPane.getViewport().setBackground(Color.lightGray);
        add(scrollPane);
        
        
    }
    
}
