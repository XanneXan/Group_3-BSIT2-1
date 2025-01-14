package com.mycompany.student_management_system;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class Performance_Reports extends JFrame {
    public Performance_Reports() {
        setTitle("Student Grades");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"#", "Subject Code", "Description", "Faculty Name", "Units", "Section Code", "Final Grade", "Grade Status"};
        Object[][] data = {
            {"1", "COMP 003", "Computer Programming 2", "Miguel, Michael Angelo Obo", "3.0", "BSIT-BN 1-1", "1.25", "P"},
            {"2", "COMP 004", "Discrete Structures 1", "Marquina, Rowell Lucero", "3.0", "BSIT-BN 1-1", "1.00", "P"},
            {"3", "CWTS 002", "Civic Welfare Training Service 2", "Atienza, Aaron Antonio", "3.0", "BSIT-BN 1-1", "1.00", "P"},
            {"4", "GEED 002", "Readings in Philippine History", "Estocado, Dennis Roselad", "3.0", "BSIT-BN 1-1", "1.50", "P"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Set custom header rendering
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.RED);
        header.setForeground(Color.WHITE);

        // Set row height and font
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        // Center align text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Performance_Reports().setVisible(true));
    }
}


//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//
//public class Performance_Reports extends JFrame implements ActionListener {
//
//    private JLabel lblTitle, lblId, lblPer;
//    private JTextField txtId;
//    private JButton btnMenu, btnGrades, btnGWA, btnAttendance, btnClear, btnSearch, btnSortNames, btnSortCourses;
//    private JTable studList;
//    private JScrollPane pane;
//    private DefaultTableModel model;
//
//    private String[] tblColumn = {
//        "Student's ID", "Student's Name", "Semester", 
//        "Course 1", "Course 2", "Course 3", 
//        "Course 4", "Course 5", "Course 6", "Course 7", 
//        "Course 8", "Midterm", "Finals", "GWA"
//    };
//
//    public Performance_Reports() {
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setLayout(null);
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Logo
//        ImageIcon performanIcon = new ImageIcon("ADD.jpg");
//        Image scale = performanIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
//        ImageIcon logoicon = new ImageIcon(scale);
//        JLabel lblLogo = new JLabel(logoicon);
//        lblLogo.setBounds(30, 20, 50, 50);
//        add(lblLogo);
//
//        // Title
//        lblTitle = new JLabel("Student Management System");
//        lblTitle.setBounds(90, 30, 350, 30);
//        lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
//        lblTitle.setForeground(Color.decode("#7d0504"));
//        add(lblTitle);
//
//        // Performance Reports
//        lblPer = new JLabel("Performance Reports");
//        lblPer.setBounds(850, 100, 350, 30);
//        lblPer.setFont(new Font("Serif", Font.BOLD, 25));
//        add(lblPer);
//
//        // Student ID
//        lblId = new JLabel("Student's ID:");
//        lblId.setBounds(50, 150, 150, 40);
//        lblId.setFont(new Font("Arial", Font.BOLD, 16));
//        add(lblId);
//
//        // Student ID TextField
//        txtId = new JTextField();
//        txtId.setBounds(170, 155, 280, 30);
//        add(txtId);
//
//        // Search Button
//        btnSearch = new JButton("Search");
//        btnSearch.setBounds(460, 155, 120, 30);
//        btnSearch.setFocusable(false);
//        btnSearch.setForeground(Color.WHITE);
//        btnSearch.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnSearch.setBackground(new Color(125, 5, 4));
//        btnSearch.addActionListener(this);
//        add(btnSearch);
//
//        // Grades Button
//        btnGrades = new JButton("Grades");
//        btnGrades.setBounds(70, 490, 120, 40);
//        btnGrades.setFocusable(false);
//        btnGrades.setForeground(Color.WHITE);
//        btnGrades.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnGrades.setBackground(new Color(125, 5, 4));
//        add(btnGrades);
//
//        // Attendance Button
//        btnAttendance = new JButton("Attendance");
//        btnAttendance.setBounds(210, 490, 120, 40);
//        btnAttendance.setFocusable(false);
//        btnAttendance.setForeground(Color.WHITE);
//        btnAttendance.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnAttendance.setBackground(new Color(125, 5, 4));
//        add(btnAttendance);
//
//        // GWA Button
//        btnGWA = new JButton("All GWA");
//        btnGWA.setBounds(350, 490, 120, 40);
//        btnGWA.setFocusable(false);
//        btnGWA.setForeground(Color.WHITE);
//        btnGWA.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnGWA.setBackground(new Color(125, 5, 4));
//        add(btnGWA);
//
//        // Menu Button
//        btnMenu = new JButton("Menu");
//        btnMenu.setBounds(145, 550, 120, 40);
//        btnMenu.setFocusable(false);
//        btnMenu.setForeground(Color.WHITE);
//        btnMenu.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnMenu.setBackground(new Color(125, 5, 4));
//        btnMenu.addActionListener(this);
//        add(btnMenu);
//
//        // Clear Button
//        btnClear = new JButton("Clear");
//        btnClear.setBounds(285, 550, 120, 40);
//        btnClear.setFocusable(false);
//        btnClear.setForeground(Color.WHITE);
//        btnClear.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnClear.setBackground(new Color(125, 5, 4));
//        add(btnClear);
//
//        // Sort by Names Button
//        btnSortNames = new JButton("Sort by Names");
//        btnSortNames.setBounds(145, 440, 120, 40);
//        btnSortNames.setFocusable(false);
//        btnSortNames.setForeground(Color.WHITE);
//        btnSortNames.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnSortNames.setBackground(new Color(125, 5, 4));
//        btnSortNames.addActionListener(this);
//        add(btnSortNames);
//
//        // Sort by Courses Button
//        btnSortCourses = new JButton("Sort by Courses");
//        btnSortCourses.setBounds(285, 440, 120, 40);
//        btnSortCourses.setFocusable(false);
//        btnSortCourses.setForeground(Color.WHITE);
//        btnSortCourses.setFont(new Font("Arial", Font.PLAIN, 10));
//        btnSortCourses.setBackground(new Color(125, 5, 4));
//        btnSortCourses.addActionListener(this);
//        add(btnSortCourses);
//
//        // Table Model Setup
//        model = new DefaultTableModel(null, tblColumn);
//        studList = new JTable(model);
//        studList.getTableHeader().setReorderingAllowed(false);
//        studList.setDefaultEditor(Object.class, null);
//        studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        for (int i = 0; i < tblColumn.length; i++) {
//            studList.getColumnModel().getColumn(i).setPreferredWidth(100);
//        }
//
//        pane = new JScrollPane(studList);
//        pane.setBounds(600, 150, 720, 450);
//        pane.getViewport().setBackground(Color.LIGHT_GRAY);
//        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        add(pane);
//
//    }
//
//   
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
//    
