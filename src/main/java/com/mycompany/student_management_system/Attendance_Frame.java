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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Attendance_Frame extends JFrame implements ActionListener {
    private JComboBox<String> courseComboBox;
    private JSpinner dateSpinner;
    private JTable attendanceTable;
    private JButton presentButton, absentButton, saveButton, editButton, menuButton;
    private JLabel titleLabel, logoLabel, courseLabel, dateLabel, attendanceLabel;
    private ImageIcon logoIcon;
    private Image logoImage;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private boolean isEditable = true; // To track if the status is editable
    
    private  String[] columnNames = {"ID", "Name", "Course", "Date", "Status"};  // Array of column headers for the table
    
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
        
    public Attendance_Frame() {
        setTitle("Student Management System");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title with logo
        titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setBounds(150, 30, 600, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#7d0504"));
        add(titleLabel);

        // Load and scale the logo
        logoIcon = new ImageIcon("C:\\Users\\Jasmine\\Downloads\\logo.jpg");
        logoImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(20, 20, 80, 80);
        add(logoLabel);

        // Course ComboBox for selecting the course
        courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 110, 100, 30);
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(courseLabel);

        courseComboBox = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        courseComboBox.setBounds(150, 110, 250, 25);
        courseComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        courseComboBox.addActionListener(this);
        add(courseComboBox);

        // Date Spinner for selecting the attendance date
        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(1060, 110, 100, 30);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(dateLabel);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(1115, 110, 200, 35);
        dateSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        add(dateSpinner);

        // Attendance Sheet title
        attendanceLabel = new JLabel("Attendance Sheet");
        attendanceLabel.setBounds(50, 150, 200, 30);
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(attendanceLabel);

        // Setting up the table for displaying attendance data
        model = new DefaultTableModel(null, columnNames);
        attendanceTable = new JTable(model);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 190, 1050, 450);
        scrollPane.getViewport().setBackground(Color.decode("#fdecec"));
        add(scrollPane);

        // Set column widths
        attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        attendanceTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(4).setPreferredWidth(150);

        // Customize table header color
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) attendanceTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(Color.PINK);
        attendanceTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Disable editing for all cells
        attendanceTable.setDefaultEditor(Object.class, null);

        // Create and add buttons for various actions
        presentButton = new JButton ("Present");
        presentButton.setBounds( 1170, 190, 150, 50);
        presentButton.setForeground(Color.WHITE);
        presentButton.setBackground(Color.decode("#7d0504"));
        add(presentButton);
        
        absentButton = new JButton("Absent");
        absentButton.setBounds( 1170, 250, 150, 50);
        absentButton.setForeground(Color.WHITE);
        absentButton.setBackground(Color.decode("#7d0504"));
        add(absentButton);
        
        saveButton = new JButton("Save");
        saveButton.setBounds( 1170, 310, 150, 50);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.decode("#7d0504"));
        add(saveButton);
        
        editButton = new JButton("edit");
        editButton.setBounds( 1170, 370, 150, 50);
        editButton.setForeground(Color.WHITE);
        editButton.setBackground(Color.decode("#7d0504"));
        add(editButton);
        
        menuButton = new JButton("menu");
        menuButton.setBounds( 50, 650, 70, 25);
        menuButton.setForeground(Color.WHITE);
        menuButton.setBackground(Color.decode("#7d0504"));
        add(menuButton);
      
       
        // Add action listeners to buttons
        presentButton.addActionListener(this);
        absentButton.addActionListener(this);
        saveButton.addActionListener(this);
        editButton.addActionListener(this);
        menuButton.addActionListener(this);
        
        loadStudentsFromDB();
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courseComboBox) {
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            
        } else if (e.getSource() == presentButton) {
            int selectedRow = attendanceTable.getSelectedRow();
            if (selectedRow != -1) {
            model.setValueAt("Present", selectedRow, 4); // Set "Present" in the "Status" column
             String selectedDate = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy").getFormat().format(dateSpinner.getValue());
            // Update the "Date" column (index 3) and "Status" column (index 4)
            model.setValueAt(selectedDate, selectedRow, 3); // Set the date
            model.setValueAt("Present", selectedRow, 4); // Set "Present" status
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to mark attendance.");
        }
            
        } else if (e.getSource() == absentButton) {
            int selectedRow = attendanceTable.getSelectedRow();
            if (selectedRow != -1) {
                 String selectedDate = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy").getFormat().format(dateSpinner.getValue());
            // Update the "Date" column (index 3) and "Status" column (index 4)
            model.setValueAt(selectedDate, selectedRow, 3); // Set the date
            model.setValueAt("Absent", selectedRow, 4); // Set "Absent" status
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to mark attendance.");
        }
            
        } else if (e.getSource() == saveButton) {
           saveAttendance();
           updateAttendanceSheet();
        } else if (e.getSource() == editButton) {
            
        } else if (e.getSource() == menuButton) {
            new Menu_Frame().setVisible(true);
            dispose();
        }
    }
    
    
    
    //Get data from database (ID, Name, Course)
    private void loadStudentsFromDB() {
       try (Connection conn = connectToDatabase();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM grade")) {
        
        // Clear existing rows in the table model
        model.setRowCount(0);

        while (rs.next()) {
            Object[] row = { rs.getString("studId"), rs.getString("studName"), rs.getString("course1")};

            // Add the row to the table model
            model.addRow(row);
       
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    private void saveAttendance() {
        boolean allRowsComplete = true;

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO attendance (studId, studName, course, date, status) VALUES (?, ?, ?, ?, ?)")) {

            // Iterate through the rows in the JTable
            for (int i = 0; i < model.getRowCount(); i++) {
                String studId = model.getValueAt(i, 0).toString();
                String studName = model.getValueAt(i, 1).toString();
                String course = model.getValueAt(i, 2).toString();
                Object dateObj = model.getValueAt(i, 3);
                Object statusObj = model.getValueAt(i, 4);

                // Check if "Status" is empty
            if (statusObj == null || statusObj.toString().isEmpty()) {
                allRowsComplete = false;
                break;
            }

            // Prepare data for insertion
            String date = (dateObj != null) ? dateObj.toString() : ""; // Use empty string if date is null
            String status = statusObj.toString();

            // Set the parameters for the PreparedStatement
            stmt.setString(1, studId);
            stmt.setString(2, studName);
            stmt.setString(3, course);
            stmt.setString(4, date);
            stmt.setString(5, status);

            // Add to batch for bulk insertion
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
            JOptionPane.showMessageDialog(this, "Error saving attendance: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateAttendanceSheet() {
        boolean allRowsComplete = true;

        // Check for incomplete rows in the JTable
        for (int i = 0; i < model.getRowCount(); i++) {
            Object statusAP = model.getValueAt(i, 4); // "Status" column
            if (statusAP == null || statusAP.toString().isEmpty()) {
                allRowsComplete = false;
                break;
            }
        }

        // If any row is incomplete, show an error message and exit
        if (!allRowsComplete) {
            JOptionPane.showMessageDialog(this, "Error: Not all rows have a 'Status'. Please complete all rows before saving.", "Incomplete Data", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method
        }

        try (Connection conn = connectToDatabase();
             PreparedStatement checkStmt = conn.prepareStatement(
                 "SELECT presents, absents FROM attendance_sheet WHERE studId = ?");
             PreparedStatement updateStmt = conn.prepareStatement(
                 "UPDATE attendance_sheet SET presents = ?, absents = ? WHERE studId = ?");
             PreparedStatement insertStmt = conn.prepareStatement(
                 "INSERT INTO attendance_sheet (studId, name, course, presents, absents) VALUES (?, ?, ?, ?, ?)")) {

            for (int i = 0; i < model.getRowCount(); i++) {
                String studId = model.getValueAt(i, 0).toString();
                String studName = model.getValueAt(i, 1).toString();
                String course = model.getValueAt(i, 2).toString();
                String status = model.getValueAt(i, 4).toString(); // "Status" column

                int presents = 0;
                int absents = 0;

                // Check if the student exists in the `attendance_sheet` table
                checkStmt.setString(1, studId);
                ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Retrieve existing counts
                presents = rs.getInt("presents");
                absents = rs.getInt("absents");

                // Update counts
                if (status.equalsIgnoreCase("Present")) {
                    presents++;
                } else if (status.equalsIgnoreCase("Absent")) {
                    absents++;
                }

                // Update record
                updateStmt.setInt(1, presents);
                updateStmt.setInt(2, absents);
                updateStmt.setString(3, studId);
                updateStmt.addBatch();
            } else {
                // Insert new record
                if (status.equalsIgnoreCase("Present")) {
                    presents = 1;
                } else if (status.equalsIgnoreCase("Absent")) {
                    absents = 1;
                }

                insertStmt.setString(1, studId);
                insertStmt.setString(2, studName);
                insertStmt.setString(3, course);
                insertStmt.setInt(4, presents);
                insertStmt.setInt(5, absents);
                insertStmt.addBatch();
            }
        }

            // Execute batch operations
            updateStmt.executeBatch();
            insertStmt.executeBatch();
            JOptionPane.showMessageDialog(this, "Attendance sheet updated successfully.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating attendance sheet: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    
   

}