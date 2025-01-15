/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Attendance_Sheet extends JFrame implements ActionListener {
    private JTable attendanceTable;
    private JButton presentButton, absentButton, saveButton, editButton, menuButton, searchButton, refreshButton;
    private JLabel lblTitle, lblLogo, courseLabel, dateLabel, searchLabel, attendanceLabel, lblDate;
    private JTextField searchField;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    
    //Column
    private  String[] columnNames = {"ID", "Name", "Date", "Status"};  // Array of column headers for the table
    
    private ArrayList<Object[]> dataRows = new ArrayList<>(); // Array list to store the data rows for the table
  
    private String url = "jdbc:mysql://localhost:3306/student_management_system";
    private String user = "root"; 
    private String pass = "mysqlpasswordg3"; 
    
    private Connection connectToDatabase() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: ");
            return null;
        }
    }
        
    public Attendance_Sheet() {
        
        //Main Frame
        setTitle("Student Management System - ATTENDANCE SHEET");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel ("Student Management System");
        lblTitle.setBounds(90, 20, 600, 30);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add (lblTitle);

        ImageIcon attendanceIcn = new ImageIcon("attendancel.jpg");
        Image scale = attendanceIcn.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon logoicon = new ImageIcon(scale);
        lblLogo = new JLabel(logoicon);
        lblLogo.setBounds(20, 10, 60, 60);
        add(lblLogo);
        
        attendanceLabel = new JLabel("ATTENDANCE");
        attendanceLabel.setBounds(580, 90, 600, 30);
        attendanceLabel.setFont(new Font("Arial Black", Font.BOLD, 28));
        attendanceLabel.setForeground(Color.decode("#7d0504"));
        add(attendanceLabel);

        // JLabel to display the date     
        lblDate = new JLabel("DATE: ");
        lblDate.setBounds(1130, 150, 200, 35);
        lblDate.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblDate);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(new Date());
        dateLabel = new JLabel( formattedDate);
        dateLabel.setBounds(1190, 150, 200, 35);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(dateLabel);
        
        model = new DefaultTableModel(null, columnNames);
        attendanceTable = new JTable(model);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 190, 1270, 400);
        scrollPane.getViewport().setBackground(Color.decode("#fdecec"));
        add(scrollPane);

        // Set column widths
        attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        attendanceTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(3).setPreferredWidth(200);

        // Customize table header color
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) attendanceTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(Color.PINK);
        attendanceTable.getTableHeader().setDefaultRenderer(headerRenderer);
        attendanceTable.setDefaultEditor(Object.class, null); // Disable editing for all cells
       
        presentButton = new JButton ("Present");
        presentButton.setBounds( 100, 620, 150, 50);
        presentButton.setForeground(Color.WHITE);
        presentButton.setBackground(Color.decode("#7d0504"));
        add(presentButton);
        
        absentButton = new JButton("Absent");
        absentButton.setBounds( 450, 620, 150, 50);
        absentButton.setForeground(Color.WHITE);
        absentButton.setBackground(Color.decode("#7d0504"));
        add(absentButton);
        
        saveButton = new JButton("Save");
        saveButton.setBounds( 750, 620, 150, 50);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.decode("#7d0504"));
        add(saveButton);
        
        menuButton = new JButton("Menu");
        menuButton.setBounds( 1100, 620, 150, 50);
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(Color.decode("#7d0504"));
        add(menuButton);
       
        // Add action listeners to buttons
        presentButton.addActionListener(this);
        absentButton.addActionListener(this);
        saveButton.addActionListener(this);
        menuButton.addActionListener(this);

        //load database
        infoFromDB();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
     if  (e.getSource() == presentButton) {
            int selectedRow = attendanceTable.getSelectedRow();
            if (selectedRow != -1) {
            
            String Date = dateLabel.getText(); //get current date
                    
            model.setValueAt(Date, selectedRow, 2); // et the date
            model.setValueAt("Present", selectedRow, 3); //Set "Present" status
            
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to mark attendance.");

            }
            
        } else if (e.getSource() == absentButton) {
            int selectedRow = attendanceTable.getSelectedRow();
            int seC  = attendanceTable.getSelectedColumn();
            if (selectedRow != -1) { 
            
            String Date = dateLabel.getText(); //get current date
                    
            model.setValueAt(Date, selectedRow, 2); //Set the date
            model.setValueAt("Absent", selectedRow, 3); //Set "Absent" status
            
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to mark attendance.");
            
        }
    
        } else if (e.getSource()== saveButton) {
            saveAttendance();
            
        } else if (e.getSource() == menuButton) {
            new Menu_Frame().setVisible(true);
            dispose();
            
        }
    }
    
    //loading data for student info from database
    private void infoFromDB() {
    
    try (Connection conn = connectToDatabase();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {
        
         dataRows.clear(); 

        while (rs.next()) {
            Object[] row = { rs.getString("ID"), rs.getString("Name")};
            
            dataRows.add(row); 
            model.addRow(row); 
       
        }
        
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } 
        sortRows(); 
    }
    
    //Method for saving attendance to database
    private void saveAttendance() {
        boolean allRowsComplete = true;
        
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO attendance (ID, Name, Date, Status) VALUES (?, ?, ?, ?)")) {

            //Iterate through the rows in the JTable
            for (int i = 0; i < model.getRowCount(); i++) {
                String studId = model.getValueAt(i, 0).toString();
                String studName = model.getValueAt(i, 1).toString();
                Object dateObj = model.getValueAt(i, 2);
                Object statusObj = model.getValueAt(i, 3);

                //Check if "Status" is empty
            if (statusObj == null || statusObj.toString().isEmpty()) {
                allRowsComplete = false;
                break;
            }

            //Prepare data for insertion
            String date = (dateObj != null) ? dateObj.toString() : ""; // Use empty string if date is null
            String status = statusObj.toString();

            stmt.setString(1, studId);
            stmt.setString(2, studName);
            stmt.setString(3, date);
            stmt.setString(4, status);

            stmt.addBatch();
        }

            if (allRowsComplete) {
                // Execute batch insert
                stmt.executeBatch();
                JOptionPane.showMessageDialog(this, "Attendance records saved successfully.");
                
            } else {
                JOptionPane.showMessageDialog(this, "Error: Not all rows have a 'Status'. Please complete all rows before saving.", "Incomplete Data", JOptionPane.ERROR_MESSAGE);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saving attendance", "Database Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    
    
    
    //sorting algorithm to arrange student alphabetically
    private void bubbleSort(ArrayList <Object[]> studData) {
       int numStudent = studData.size();
        
        for (int i = 0; i< numStudent-1; i++){
            for (int j = 0; j < numStudent - i - 1; j++) {
            
            String name1 = studData.get(j)[1].toString().toLowerCase();
            String name2 = studData.get(j + 1)[1].toString().toLowerCase();
            
             if (name1.compareTo(name2) > 0) {
                //Swap the current student with the next student
                Object[] temp = studData.get(j);
                studData.set(j, studData.get(j + 1));
                studData.set(j + 1, temp);
                
                }
            }
        }

        updateTableModel(); 
    }
    
    private void sortRows() {
        bubbleSort(dataRows);
        updateTableModel();
    }

    //Refresh the table model based on dataRows
    private void updateTableModel() {
        model.setRowCount(0); 
        
        for (Object[] row : dataRows) {
            model.addRow(row); 
            
        }
    }
}