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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class enrolledStudent_Frame extends JFrame implements ActionListener {

    private JTextField searchField;
    private JTable attendanceTable;
    private JButton backButton, searchButton, clearButton;
    private DefaultTableModel tableModel;
    private ImageIcon logoIcon;
    private JLabel titleLabel, logoLabel, searchLabel, attendanceLabel, courseLabel;
    private Image scaledLogo;
    private JComboBox <String> courseComboBox;
    JScrollPane scrollPane;
    
    
    
    private String[] columns = {"ID", "Name", "Course", "Presents", "Absents"};
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

    public enrolledStudent_Frame () {
        setTitle("Student Management System");
        setExtendedState(MAXIMIZED_BOTH); // Maximized window
        setLayout(null); // Absolute positioning for components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(30, 20, 50, 50); 
        add(lblLogo);
        
        // Title label
        titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setBounds(150, 30, 600, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#7d0504"));
        add(titleLabel);
        
        // Course ComboBox for selecting the course
        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 110, 100, 30);
        courseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(courseLabel);
        
        attendanceLabel = new JLabel("Enrolled Students");
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        attendanceLabel.setBounds(50, 150, 200, 30);
        add(attendanceLabel);
        
        
        courseComboBox = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        courseComboBox.setBounds(130, 110, 250, 25);
        courseComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        courseComboBox.addActionListener(this);
        add(courseComboBox);
      

        // Search label
        searchLabel = new JLabel("Search Student:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        searchLabel.setBounds(780, 140, 150, 30);
        add(searchLabel);

        // Search field
        searchField = new JTextField();
        searchField.setBounds(900, 140, 230, 25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(searchField);
        
        searchButton = new JButton("Search");
        searchButton.setBounds( 1140, 140, 70, 25);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.decode("#7d0504"));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 11));
        add(searchButton);
        
        clearButton = new JButton("Clear");
        clearButton.setBounds( 1230, 140, 70, 25);
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.decode("#7d0504"));
        clearButton.setFont(new Font("Arial", Font.PLAIN, 11));
        add(clearButton);
        
        backButton = new JButton("Back");
        backButton.setBounds( 1220, 660, 100, 30); 
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#7d0504"));
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        add(backButton);
        
        
        // Attendance table
        tableModel = new DefaultTableModel(null, columns);
        attendanceTable = new JTable(tableModel);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Customize table appearance
        scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 180, 1250, 430);
        scrollPane.getViewport().setBackground(Color.decode("#fdecec"));
        add(scrollPane);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) attendanceTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(Color.PINK);
        attendanceTable.getTableHeader().setDefaultRenderer(headerRenderer);


        // Add action listeners
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);
        
        loadStudentsFromDB();
    
    }

   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchText = searchField.getText().trim();
            
        } else if (e.getSource() == clearButton) {
            
        } else if (e.getSource() == backButton) {
            new Menu_Frame().setVisible(true);
            dispose();
        }
    }

    private void loadStudentsFromDB() {
       try (Connection conn = connectToDatabase();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM attendance_sheet")) {
        
        // Clear existing rows in the table model
        tableModel.setRowCount(0);

        while (rs.next()) {
            Object[] row = { rs.getString("studId"), rs.getString("name"), rs.getString("course"),
             rs.getString("presents"),  rs.getString("absents")};

            // Add the row to the table model
            tableModel.addRow(row);
         
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    
}
