/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;


import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;

public class Grade_Class extends JFrame implements ActionListener{
    private JLabel lblTitle, lblName, lblId , lblSem, lblTerm, lblC1 , lblC2, lblC3, lblC4, lblC5, lblC6, lblC7, lblC8, 
            lblSearch, lblfin1, lblmid1, lblmid2, lblfin2;
    private JTextField txtName, txtId, midGrade, finGrade, txtSearch, txtTerm, txtSem, txtC1, txtC2, txtC5, 
            txtC3, txtC4, txtC6, txtC7, txtC8, txtG1, txtG2, txtG3, txtG4, txtG5, txtG6, txtG7, txtG8 , txtG1f, txtG2f, txtG3f, txtG4f,
            txtG5f, txtG6f, txtG7f, txtG8f ;
    private JComboBox cmbTerm;
    private JButton btnAdd, btnDelete, btnUpdate, btnEdit, btnSearch, btnRefresh, btnMenu, btnClear, btnSearch2;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;
    private int editingRowIndex = -1;  // Stores the index of the row being edited
    
    // Array of column headers for the table
    private String [] tblColumn = {"Student's ID", "Student's Name", "Semester", "Course 1", "Mid", "Fin", "Course 2", "Mid", "Fin", 
                                    "Course 3", "Mid", "Fin", "Course 4", "Mid", "Fin", "Course 5", "Mid", "Fin", "Course 6", "Mid", "Fin", "Course 7",
                                    "Mid", "Fin", "Course 8","Mid", "Fin", "Midterm Average ", "Final Average", "GWA"};
   
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

    Grade_Class (){
        
        setExtendedState(MAXIMIZED_BOTH);     
        setLayout(null);
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brit\\Documents\\Group_3-BSIT2-1\\Group_3-BSIT2-1\\src\\main\\java\\com\\mycompany\\student_management_system\\grade_icon.jpg");
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
    lblTitle = new JLabel ("Student Management System");
    lblTitle.setBounds(30, 20, 350, 30);
    lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
    lblTitle.setForeground(Color.decode("#7d0504"));
    add (lblTitle);
    
    lblId = new JLabel ("Student's ID: ");
    lblId.setBounds(50, 150, 150, 40);
    lblId.setFont(new Font("Arial", 1, 16));
    add (lblId);
    
    lblName = new JLabel ("Student's Name: ");
    lblName.setBounds(50, 210, 150, 40);
    lblName.setFont(new Font("Arial", 1, 16));
    add (lblName);
       
    lblSem = new JLabel ("Semester: ");
    lblSem.setBounds(50, 260, 150, 40);
    lblSem.setFont(new Font("Arial", 1, 16));
    add (lblSem );
    
    
    //TextFields for user input in Student ID and Name
    txtId = new JTextField ();
    txtId.setBounds(170, 155, 250, 25);
    txtId.setEditable(true);
    add(txtId);
    
    txtName = new JTextField ();
    txtName.setBounds(190, 215, 250, 25);
    txtName.setBackground(Color.LIGHT_GRAY);
    txtName.setEditable(true);
    add (txtName);
    
    //The semester will automatically appear here. It is set to non-editable
    txtSem = new JTextField ();
    txtSem.setBounds(160, 265, 70, 25);
    txtSem.setBackground(Color.LIGHT_GRAY);
    txtSem.setEditable(true);
    add (txtSem);
    
    lblSearch = new JLabel ("Search Student: ");
    lblSearch.setBounds(600, 110, 150, 40);
    lblSearch.setFont(new Font("Arial", 1, 14));
    add (lblSearch);
    
    txtSearch = new JTextField ();
    txtSearch.setBounds(730, 115, 400, 25);
    add (txtSearch);
    
    
    
    
    // User inputs for grades. The course names will automatically appear in non-editable text fields
    
    lblmid1 = new JLabel ("MID");
    lblmid1.setBounds(220, 300, 150, 40);
    lblmid1.setFont(new Font("Arial", 1, 11));
    lblmid1.setForeground(Color.decode("#7d0504"));
    add (lblmid1);
    
    lblmid2 = new JLabel ("MID");
    lblmid2.setBounds(490, 300, 150, 40);
    lblmid2.setFont(new Font("Arial", 1, 11));
    lblmid2.setForeground(Color.decode("#7d0504"));
    add (lblmid2);
    
    lblfin1 = new JLabel ("FIN");
    lblfin1.setBounds(265, 300, 150, 40);
    lblfin1.setFont(new Font("Arial", 1, 11));
    lblfin1.setForeground(Color.decode("#7d0504"));
    add (lblfin1);
    
    lblfin2 = new JLabel ("FIN");
    lblfin2.setBounds(535, 300, 150, 40);
    lblfin2.setFont(new Font("Arial", 1, 11));
    lblfin2.setForeground(Color.decode("#7d0504"));
    add (lblfin2);
    
    //Course 1 Components
    txtC1 = new JTextField ();
    txtC1.setBounds(50, 340, 145, 20);
    txtC1.setBackground(Color.LIGHT_GRAY);
    txtC1.setText("== Course 1 ==");
    txtC1.setEditable(false);
    add (txtC1);
    
    txtG1 = new JTextField ();
    txtG1.setBounds(215, 340, 35, 20);
    txtG1.setBackground(Color.decode("#ffefef"));
    txtG1.setText("0.0");
    add (txtG1);
    
    txtG1f = new JTextField ();
    txtG1f.setBounds(260, 340, 35, 20);
    txtG1f.setBackground(Color.decode("#ffefef"));
    txtG1f.setText("0.0");
    add (txtG1f);
    
    //Course 2 Components
   
    txtC2 = new JTextField ();
    txtC2.setBounds(50, 380, 145, 20);
    txtC2.setBackground(Color.LIGHT_GRAY);
    txtC2.setText("== Course 2 ==");
    txtC2.setEditable(false);
    add (txtC2);
    
    txtG2 = new JTextField ();
    txtG2.setBounds(215, 380, 35, 20);
    txtG2.setBackground(Color.decode("#ffefef"));
    txtG2.setText("0.0");
    add (txtG2);
    
    txtG2f = new JTextField ();
    txtG2f.setBounds(260, 380, 35, 20);
    txtG2f.setBackground(Color.decode("#ffefef"));
    txtG2f.setText("0.0");
    add (txtG2f);
    
    //Course 3 Components
    
    txtC3 = new JTextField ();
    txtC3.setBounds(50, 420, 145, 20);
    txtC3.setBackground(Color.LIGHT_GRAY);
    txtC3.setText("== Course 3 ==");
    txtC3.setEditable(false);
    add (txtC3);
    
    txtG3 = new JTextField ();
    txtG3.setBounds(215, 420, 35, 20);
    txtG3.setBackground(Color.decode("#ffefef"));
    txtG3.setText("0.0");
    add (txtG3);
    
    txtG3f = new JTextField ();
    txtG3f.setBounds(260, 420, 35, 20);
    txtG3f.setBackground(Color.decode("#ffefef"));
    txtG3f.setText("0.0");
    add (txtG3f);
    
    //Course 4 Components
    
    txtC4 = new JTextField ();
    txtC4.setBounds(50, 460, 145, 20);
    txtC4.setBackground(Color.LIGHT_GRAY);
    txtC4.setText("== Course 4 ==");
    txtC4.setEditable(false);
    add (txtC4);
    
    txtG4 = new JTextField ();
    txtG4.setBounds(215, 460, 35, 20);
    txtG4.setBackground(Color.decode("#ffefef"));
    txtG4.setText("0.0");
    add (txtG4);
    
    txtG4f = new JTextField ();
    txtG4f.setBounds(260, 460, 35, 20);
    txtG4f.setBackground(Color.decode("#ffefef"));
    txtG4f.setText("0.0");
    add (txtG4f);
    
    
    //Course 5 Components
    
    txtC5 = new JTextField ();
    txtC5.setBounds(320, 340, 145, 20);
    txtC5.setBackground(Color.LIGHT_GRAY);
    txtC5.setText("== Course 5 ==");
    txtC5.setEditable(false);
    add (txtC5);
    
    txtG5 = new JTextField ();
    txtG5.setBounds(485, 340, 35, 20);
    txtG5.setBackground(Color.decode("#ffefef"));
    txtG5.setText("0.0");
    add (txtG5);
    
    txtG5f = new JTextField ();
    txtG5f.setBounds(530, 340, 35, 20);
    txtG5f.setBackground(Color.decode("#ffefef"));
    txtG5f.setText("0.0");
    add (txtG5f);
    
    //Course 6 Components
    
    txtC6 = new JTextField ();
    txtC6.setBounds(320, 380, 145, 20);
    txtC6.setBackground(Color.LIGHT_GRAY);
    txtC6.setText("== Course 6 ==");
    txtC6.setEditable(false);
    add (txtC6);
    
    txtG6 = new JTextField ();
    txtG6.setBounds(485, 380, 35, 20);
    txtG6.setBackground(Color.decode("#ffefef"));
    txtG6.setText("0.0");
    add (txtG6);
    
    txtG6f = new JTextField ();
    txtG6f.setBounds(530, 380, 35, 20);
    txtG6f.setBackground(Color.decode("#ffefef"));
    txtG6f.setText("0.0");
    add (txtG6f);
    
    //Course 7 Components
    
    txtC7 = new JTextField ();
    txtC7.setBounds(320, 420, 145, 20);
    txtC7.setBackground(Color.LIGHT_GRAY);
    txtC7.setText("== Course 7 ==");
    txtC7.setEditable(false);
    add (txtC7);
    
    txtG7 = new JTextField ();
    txtG7.setBounds(485, 420, 35, 20);
    txtG7.setBackground(Color.decode("#ffefef"));
    txtG7.setText("0.0");
    add (txtG7);
    
    txtG7f = new JTextField ();
    txtG7f.setBounds(530, 420, 35, 20);
    txtG7f.setBackground(Color.decode("#ffefef"));
    txtG7f.setText("0.0");
    add (txtG7f);
    
    //Course 8 Components
   
    txtC8 = new JTextField ();
    txtC8.setBounds(320, 460, 145, 20);
    txtC8.setBackground(Color.LIGHT_GRAY);
    txtC8.setText("== Course 8 ==");
    txtC8.setEditable(false);
    add (txtC8);
    
    txtG8 = new JTextField ();
    txtG8.setBounds(485, 460, 35, 20);
    txtG8.setBackground(Color.decode("#ffefef"));
    txtG8.setText("0.0");
    add (txtG8);
    
    txtG8f = new JTextField ();
    txtG8f.setBounds(530, 460, 35, 20);
    txtG8f.setBackground(Color.decode("#ffefef"));
    txtG8f.setText("0.0");
    add (txtG8f);
   
   
    
    /* Table where the information will appear
       Initializing the table model with no rows (null) and predefined column headers (tblColumn)
       Setting the rows to null so the table starts empty and will be populated based on user inputs */
    model = new DefaultTableModel(null,tblColumn );
    studList = new JTable (model);
    studList.getTableHeader().setReorderingAllowed(false);
    studList.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    studList.setDefaultEditor(Object.class, null);
    studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
     // Adjusting column sizes for the JTable to enhance readability and proper alignment
    int preferredWidth = 200;
    for (int i = 0; i < studList.getColumnCount(); i++) {
        studList.getColumnModel().getColumn(i).setPreferredWidth(preferredWidth);
        studList.getColumnModel().getColumn(i).setResizable(false);
    }

    studList.getColumnModel().getColumn(0).setPreferredWidth(100); // Student's ID
    studList.getColumnModel().getColumn(2).setPreferredWidth(70);  // Semester

    for (int i = 4; i < studList.getColumnCount(); i++) {
        String columnName = studList.getColumnName(i);
        if (columnName.equalsIgnoreCase("Mid") || columnName.equalsIgnoreCase("Fin")) {
            studList.getColumnModel().getColumn(i).setPreferredWidth(50);
        }
    }
    
    for (int i = 0; i < studList.getColumnCount(); i++) {
        String columnName = studList.getColumnName(i).trim();
        if (columnName.equalsIgnoreCase("Midterm Average") ||
            columnName.equalsIgnoreCase("Final Average") ||
            columnName.equalsIgnoreCase("GWA")) {
            studList.getColumnModel().getColumn(i).setPreferredWidth(130);
        }
    }
   
    pane = new JScrollPane(studList);
    pane.setBounds(600, 150, 720, 450);
    pane.getViewport().setBackground(Color.decode("#fdecec"));
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    add(pane);
    
    DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) studList.getTableHeader().getDefaultRenderer();
    headerRenderer.setBackground(Color.PINK);
    studList.getTableHeader().setDefaultRenderer(headerRenderer);

    
     
    //All Buttons
    btnAdd = new JButton ("Add");
    btnAdd.setBounds(70, 530, 120, 40);
    btnAdd.setFocusable(false);
    btnAdd.setForeground(Color.WHITE);
    btnAdd.setBackground(Color.decode("#7d0504"));
    add (btnAdd);
    
    btnUpdate = new JButton ("Update");
    btnUpdate.setBounds(210, 530, 120, 40);
    btnUpdate.setFocusable(false);
    btnUpdate.setForeground(Color.WHITE);
    btnUpdate.setBackground(Color.decode("#7d0504"));
    add (btnUpdate);
    
    btnDelete = new JButton ("Delete");
    btnDelete.setBounds(350, 530, 120, 40);
    btnDelete.setFocusable(false);
    btnDelete.setForeground(Color.WHITE);
    btnDelete.setBackground(Color.decode("#7d0504"));
    add(btnDelete);
    
    btnEdit = new JButton ("Edit Row");
    btnEdit.setBounds(145, 590, 120, 40);
    btnEdit.setFocusable(false);
    btnEdit.setForeground(Color.WHITE);
    btnEdit.setBackground(Color.decode("#7d0504"));
    add (btnEdit);
    
    btnClear = new JButton ("Clear");
    btnClear.setBounds(285, 590, 120, 40);
    btnClear.setFocusable(false);
    btnClear.setForeground(Color.WHITE);
    btnClear.setBackground(Color.decode("#7d0504"));
    add (btnClear);
    
    // "Search" button to trigger the search operation
    btnSearch = new JButton ("Search");
    btnSearch.setBounds(1170, 115, 70, 25);
    btnSearch.setFocusable(false);
    btnSearch.setForeground(Color.WHITE);
    btnSearch.setFont(new Font("Arial", 0, 10));
    btnSearch.setBackground(Color.decode("#7d0504"));
    add (btnSearch);
    
    // "Refresh" button to reload or reset the data in the table
    btnRefresh = new JButton ("Refresh");
    btnRefresh.setBounds(1250, 115, 70, 25);
    btnRefresh.setFocusable(false);
    btnRefresh.setForeground(Color.WHITE);
    btnRefresh.setFont(new Font("Arial", 0, 10));
    btnRefresh.setBackground(Color.decode("#7d0504"));
    add (btnRefresh);
    

    // "Menu_Frame" button to navigate back to the main menu
    btnMenu = new JButton ("Menu");
    btnMenu.setBounds(1250, 650, 70, 25);
    btnMenu.setFocusable(false);
    btnMenu.setForeground(Color.WHITE);
    btnMenu.setFont(new Font("Arial", 0, 10));
    btnMenu.setBackground(Color.decode("#7d0504"));
    add (btnMenu);
    
    // Additional Search Button for Student ID 
    btnSearch2 = new JButton ("Search");
    btnSearch2.setBounds(450, 155, 70, 25);
    btnSearch2.setFocusable(false);
    btnSearch2.setForeground(Color.WHITE);
    btnSearch2.setFont(new Font("Arial", 0, 10));
    btnSearch2.setBackground(Color.decode("#7d0504"));
    add (btnSearch2);
    
    btnAdd.addActionListener(this);
    btnDelete.addActionListener(this);
    btnUpdate.addActionListener(this);
    btnEdit.addActionListener(this);
    btnClear.addActionListener(this);
    btnMenu.addActionListener(this);
              
    
    loadDataFromDatabase(); // Calling the loadDataFromDatabase method and to load the datas from DB authomatically
}

    @Override
    public void actionPerformed(ActionEvent e) {
       
       if (e.getSource () == btnAdd){
            addStudent();
            
       } else if (e.getSource()==btnDelete){
            removeRow ();
            
       } else if (e.getSource() == btnUpdate) {
            updateRow();
        
       } else if (e.getSource() == btnClear) {
            clearFields();
        
       } else if (e.getSource()==btnEdit){
            loadSelectedRowData();
  
      }  else if (e.getSource()==btnMenu){
            new Menu_Frame().setVisible(true);
            dispose();
       }
    }
    
    // Method for Add button
     private void addStudent() {
         
        // Collecting input values
        String studId = txtId.getText().trim();
        String studName = txtName.getText().trim();
        String sem = txtSem.getText().trim();
        String c1 = txtC1.getText().trim();
        String m1 = txtG1.getText().trim();
        String f1 = txtG1f.getText().trim();
        String c2 = txtC2.getText().trim();
        String m2 = txtG2.getText().trim();
        String f2 = txtG2f.getText().trim();
        String c3 = txtC3.getText().trim();
        String m3 = txtG3.getText().trim();
        String f3 = txtG3f.getText().trim();
        String c4 = txtC4.getText().trim();
        String m4 = txtG4.getText().trim();
        String f4 = txtG4f.getText().trim();
        String c5 = txtC5.getText().trim();
        String m5 = txtG5.getText().trim();
        String f5 = txtG5f.getText().trim();
        String c6 = txtC6.getText().trim();
        String m6 = txtG6.getText().trim();
        String f6 = txtG6f.getText().trim();
        String c7 = txtC7.getText().trim();
        String m7 = txtG7.getText().trim();
        String f7 = txtG7f.getText().trim();
        String c8 = txtC8.getText().trim();
        String m8 = txtG8.getText().trim();
        String f8 = txtG8f.getText().trim();

        // Ensure all required fields are filled
        if (studId.isEmpty() || studName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        // Validate grades (Must be 0.0 - 5.0)
        if (!isValidGrade(m1) || !isValidGrade(m2) || !isValidGrade(m3) || !isValidGrade(m4) ||
            !isValidGrade(m5) || !isValidGrade(m6) || !isValidGrade(m7) || !isValidGrade(m8) ||
            !isValidGrade(f1) || !isValidGrade(f2) || !isValidGrade(f3) || !isValidGrade(f4) ||
            !isValidGrade(f5) || !isValidGrade(f6) || !isValidGrade(f7) || !isValidGrade(f8)) {
            JOptionPane.showMessageDialog(this, "Grades must be numbers between 1.0 and 5.0.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Object[] tblRow = { studId, studName, sem, c1, m1, f1, c2, m2, f2, c3, m3, f3, 
            c4, m4, f4, c5, m5, f5, c6, m6, f6, c7, m7, f7, c8, m8, f8 };

        boolean isAddedtoDB =false; 
        
        // Add the data to the database
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(
                 "INSERT INTO grade (studId, studName, sem, course1, mid1, fin1, course2, mid2, fin2, "
                         + "course3, mid3, fin3, course4, mid4, fin4, course5, mid5, fin5, "
                         + "course6, mid6, fin6, course7, mid7, fin7, course8, mid8, fin8) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, studId);
            stmt.setString(2, studName);
            stmt.setString(3, sem);
            stmt.setString(4, c1);
            stmt.setString(5, m1);
            stmt.setString(6, f1);
            stmt.setString(7, c2);
            stmt.setString(8, m2);
            stmt.setString(9, f2);
            stmt.setString(10, c3);
            stmt.setString(11, m3);
            stmt.setString(12, f3);
            stmt.setString(13, c4);
            stmt.setString(14, m4);
            stmt.setString(15, f4);
            stmt.setString(16, c5);
            stmt.setString(17, m5);
            stmt.setString(18, f5);
            stmt.setString(19, c6);
            stmt.setString(20, m6);
            stmt.setString(21, f6);
            stmt.setString(22, c7);
            stmt.setString(23, m7);
            stmt.setString(24, f7);
            stmt.setString(25, c8);
            stmt.setString(26, m8);
            stmt.setString(27, f8);
            
                stmt.executeUpdate();
                isAddedtoDB = true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding data to the database: " + ex.getMessage());
            }
            
        if (isAddedtoDB){ //To ensure that data is only added to the JTable if it is successfully added to the database
       
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to add this?",
            "Add Confirmation", JOptionPane.YES_NO_OPTION);  // Confirmation message before adding the data

        if (confirmation == JOptionPane.YES_OPTION) {
            dataRows.add(tblRow); // Save row to ArrayList
            model.addRow(tblRow); // Display row in the table
            termsCalculation(model.getRowCount() - 1); // Perform calculations for the new row
         
            clearFields(); // Clear input fields after successful addition
            JOptionPane.showMessageDialog(this, "Grades added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    
     }    

     //Methods for removing/deleting rows
    private void removeRow() {
    // Get the index of the selected row
        int selectedRow = studList.getSelectedRow();

        if (selectedRow != -1) {
            // Ask for confirmation before proceeding
            int confirmation = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete the selected row?","Delete Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) { 
                String studId = model.getValueAt(selectedRow, 0).toString();

                // Connect to the database and delete the record
                try (Connection conn = connectToDatabase();
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM grade WHERE studId = ?")) {

                    stmt.setString(1, studId); // Use the student ID as the condition
                    int rowSelected = stmt.executeUpdate();

                    if (rowSelected > 0) {
                        // Remove the row from the table model if deletion is successful
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Row deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: Could not find the record in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error deleting data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            // Inform the user if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

     
     //For Clearing all fields 
     private void clearFields() {
        
        // Enable ID, Name, and Sem fields editing
        txtId.setEditable(true);
        txtName.setEditable(true);
        txtSem.setEditable(true);
        
        txtId.setText("");
        txtName.setText("");
        txtSem.setText("");
        txtG1.setText("0.0");
        txtG1f.setText("0.0");
        txtG2.setText("0.0");
        txtG2f.setText("0.0");
        txtG3.setText("0.0");
        txtG3f.setText("0.0");
        txtG4.setText("0.0");
        txtG4f.setText("0.0");
        txtG5.setText("0.0");
        txtG5f.setText("0.0");
        txtG6.setText("0.0");
        txtG6f.setText("0.0");
        txtG7.setText("0.0");
        txtG7f.setText("0.0");
        txtG8.setText("0.0");
        txtG8f.setText("0.0");
        txtC1.setText("== Course 1 ==");
        txtC2.setText("== Course 2 ==");
        txtC3.setText("== Course 3 ==");
        txtC4.setText("== Course 4 ==");
        txtC5.setText("== Course 5 ==");
        txtC6.setText("== Course 6 ==");
        txtC7.setText("== Course 7 ==");
        txtC8.setText("== Course 8 ==");

     }
     
     // For update button
    private void updateRow() {
        if (editingRowIndex != -1) { // Ensure a row is selected for editing
        
        // Get data from text fields
        String studId = txtId.getText().trim();
        String studName = txtName.getText().trim();
        String sem = txtSem.getText().trim();
        String c1 = txtC1.getText().trim();
        String m1 = txtG1.getText().trim();
        String f1 = txtG1f.getText().trim();
        String c2 = txtC2.getText().trim();
        String m2 = txtG2.getText().trim();
        String f2 = txtG2f.getText().trim();
        String c3 = txtC3.getText().trim();
        String m3 = txtG3.getText().trim();
        String f3 = txtG3f.getText().trim();
        String c4 = txtC4.getText().trim();
        String m4 = txtG4.getText().trim();
        String f4 = txtG4f.getText().trim();
        String c5 = txtC5.getText().trim();
        String m5 = txtG5.getText().trim();
        String f5 = txtG5f.getText().trim();
        String c6 = txtC6.getText().trim();
        String m6 = txtG6.getText().trim();
        String f6 = txtG6f.getText().trim();
        String c7 = txtC7.getText().trim();
        String m7 = txtG7.getText().trim();
        String f7 = txtG7f.getText().trim();
        String c8 = txtC8.getText().trim();
        String m8 = txtG8.getText().trim();
        String f8 = txtG8f.getText().trim();
   
        // Validate inputs
        if (!studId.isEmpty() && !studName.isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to update the selected row?",
                "Update Confirmation",
                JOptionPane.YES_NO_OPTION
            );

        if (confirmation == JOptionPane.YES_OPTION) {
           try (Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE grade SET studName = ?, sem = ?, course1 = ?, mid1 = ?, fin1 = ?, " +
                "course2 = ?, mid2 = ?, fin2 = ?, course3 = ?, mid3 = ?, fin3 = ?, " +
                "course4 = ?, mid4 = ?, fin4 = ?, course5 = ?, mid5 = ?, fin5 = ?, " +
                "course6 = ?, mid6 = ?, fin6 = ?, course7 = ?, mid7 = ?, fin7 = ?, " +
                "course8 = ?, mid8 = ?, fin8 = ? WHERE studId = ?")) {

           stmt.setString(1, studName);
           stmt.setString(2, sem);
           stmt.setString(3, c1);
           stmt.setString(4, m1);
           stmt.setString(5, f1);
           stmt.setString(6, c2);
           stmt.setString(7, m2);
           stmt.setString(8, f2);
           stmt.setString(9, c3);
           stmt.setString(10, m3);
           stmt.setString(11, f3);
           stmt.setString(12, c4);
           stmt.setString(13, m4);
           stmt.setString(14, f4);
           stmt.setString(15, c5);
           stmt.setString(16, m5);
           stmt.setString(17, f5);
           stmt.setString(18, c6);
           stmt.setString(19, m6);
           stmt.setString(20, f6);
           stmt.setString(21, c7);
           stmt.setString(22, m7);
           stmt.setString(23, f7);
           stmt.setString(24, c8);
           stmt.setString(25, m8);
           stmt.setString(26, f8);
           stmt.setString(27, studId);

           int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            // Update the JTable if database update is successful
            model.setValueAt(studId, editingRowIndex, 0);
            model.setValueAt(studName, editingRowIndex, 1);
            model.setValueAt(sem, editingRowIndex, 2);
            model.setValueAt(c1, editingRowIndex, 3);
            model.setValueAt(m1, editingRowIndex, 4);  // Midterm Grade for Course 1
            model.setValueAt(f1, editingRowIndex, 5);  // Final Grade for Course 1
            model.setValueAt(c2, editingRowIndex, 6);
            model.setValueAt(m2, editingRowIndex, 7);  // Midterm Grade for Course 2
            model.setValueAt(f2, editingRowIndex, 8);  // Final Grade for Course 2
            model.setValueAt(c3, editingRowIndex, 9);
            model.setValueAt(m3, editingRowIndex, 10); // Midterm Grade for Course 3
            model.setValueAt(f3, editingRowIndex, 11); // Final Grade for Course 3
            model.setValueAt(c4, editingRowIndex, 12);
            model.setValueAt(m4, editingRowIndex, 13); // Midterm Grade for Course 4
            model.setValueAt(f4, editingRowIndex, 14); // Final Grade for Course 4
            model.setValueAt(c5, editingRowIndex, 15);
            model.setValueAt(m5, editingRowIndex, 16); // Midterm Grade for Course 5
            model.setValueAt(f5, editingRowIndex, 17); // Final Grade for Course 5
            model.setValueAt(c6, editingRowIndex, 18);
            model.setValueAt(m6, editingRowIndex, 19); // Midterm Grade for Course 6
            model.setValueAt(f6, editingRowIndex, 20); // Final Grade for Course 6
            model.setValueAt(c7, editingRowIndex, 21);
            model.setValueAt(m7, editingRowIndex, 22); // Midterm Grade for Course 7
            model.setValueAt(f7, editingRowIndex, 23); // Final Grade for Course 7
            model.setValueAt(c8, editingRowIndex, 24);
            model.setValueAt(m8, editingRowIndex, 25); // Midterm Grade for Course 8
            model.setValueAt(f8, editingRowIndex, 26); // Final Grade for Course 8


        JOptionPane.showMessageDialog(this, "Row updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        termsCalculation(model.getRowCount() - 1); // Perform calculations for the new row
         
        clearFields();
        editingRowIndex = -1; // Reset the editing row index

        }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No row selected for editing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //method for Edit Row Button
    private void loadSelectedRowData() {
    int selectedRow = studList.getSelectedRow();
    
        if (selectedRow != -1) {
            editingRowIndex = selectedRow;
            
            // Disable ID, Name, and Sem fields to prevent editing
            txtId.setEditable(false);
            txtName.setEditable(false);
            txtSem.setEditable(false);
            
            // Populate the text fields with the data from the selected row
            txtId.setText(model.getValueAt(selectedRow, 0).toString());
            txtName.setText(model.getValueAt(selectedRow, 1).toString());
            txtSem.setText(model.getValueAt(selectedRow, 2).toString());

            // Set course 1 details
            txtC1.setText(model.getValueAt(selectedRow, 3).toString());
            txtG1.setText(model.getValueAt(selectedRow, 4).toString()); 
            txtG1f.setText(model.getValueAt(selectedRow, 5).toString()); 

            // Set course 2 details
            txtC2.setText(model.getValueAt(selectedRow, 6).toString());
            txtG2.setText(model.getValueAt(selectedRow, 7).toString());
            txtG2f.setText(model.getValueAt(selectedRow, 8).toString()); 

            // Set course 3 details
            txtC3.setText(model.getValueAt(selectedRow, 9).toString());
            txtG3.setText(model.getValueAt(selectedRow, 10).toString()); 
            txtG3f.setText(model.getValueAt(selectedRow, 11).toString());

            // Set course 4 details
            txtC4.setText(model.getValueAt(selectedRow, 12).toString());
            txtG4.setText(model.getValueAt(selectedRow, 13).toString()); 
            txtG4f.setText(model.getValueAt(selectedRow, 14).toString()); 

            // Set course 5 details
            txtC5.setText(model.getValueAt(selectedRow, 15).toString());
            txtG5.setText(model.getValueAt(selectedRow, 16).toString()); 
            txtG5f.setText(model.getValueAt(selectedRow, 17).toString()); 

            // Set course 6 details
            txtC6.setText(model.getValueAt(selectedRow, 18).toString());
            txtG6.setText(model.getValueAt(selectedRow, 19).toString());
            txtG6f.setText(model.getValueAt(selectedRow, 20).toString()); 

            // Set course 7 details
            txtC7.setText(model.getValueAt(selectedRow, 21).toString());
            txtG7.setText(model.getValueAt(selectedRow, 22).toString()); 
            txtG7f.setText(model.getValueAt(selectedRow, 23).toString()); 

            // Set course 8 details
            txtC8.setText(model.getValueAt(selectedRow, 24).toString());
            txtG8.setText(model.getValueAt(selectedRow, 25).toString()); 
            txtG8f.setText(model.getValueAt(selectedRow, 26).toString()); 

        
        }else{
             JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    
    //load data from database to JTable
    private void loadDataFromDatabase() {
    try (Connection conn = connectToDatabase();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM grade")) {
        
        // Clear existing rows in the table model
        model.setRowCount(0);

        while (rs.next()) {
            Object[] row = { rs.getString("studId"), rs.getString("studName"), rs.getString("sem"),
                rs.getString("course1"), rs.getString("mid1"), rs.getString("fin1"),
                rs.getString("course2"), rs.getString("mid2"), rs.getString("fin2"),
                rs.getString("course3"), rs.getString("mid3"), rs.getString("fin3"),
                rs.getString("course4"), rs.getString("mid4"), rs.getString("fin4"),
                rs.getString("course5"), rs.getString("mid5"), rs.getString("fin5"),
                rs.getString("course6"), rs.getString("mid6"), rs.getString("fin6"),
                rs.getString("course7"), rs.getString("mid7"), rs.getString("fin7"),
                rs.getString("course8"), rs.getString("mid8"), rs.getString("fin8"),
                rs.getString("midterm"), rs.getString("final"), rs.getString("gwa") };

            // Add the row to the table model
            model.addRow(row);
       
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    // Methods to calculate the grade in midterm and final grade average 
    private void termsCalculation(int rowIndex) {
        try {
            // Get grades for midterm (fallback to 0.0 if the field is empty)
            double g1 = parseGrade(model.getValueAt(rowIndex, 4).toString());
            double g2 = parseGrade(model.getValueAt(rowIndex, 7).toString());
            double g3 = parseGrade(model.getValueAt(rowIndex, 10).toString());
            double g4 = parseGrade(model.getValueAt(rowIndex, 13).toString());
            double g5 = parseGrade(model.getValueAt(rowIndex, 16).toString());
            double g6 = parseGrade(model.getValueAt(rowIndex, 19).toString());
            double g7 = parseGrade(model.getValueAt(rowIndex, 22).toString());
            double g8 = parseGrade(model.getValueAt(rowIndex, 25).toString());

            // Get grades for final (fallback to 0.0 if the field is empty)
            double g1f = parseGrade(model.getValueAt(rowIndex, 5).toString());
            double g2f = parseGrade(model.getValueAt(rowIndex, 8).toString());
            double g3f = parseGrade(model.getValueAt(rowIndex, 11).toString());
            double g4f = parseGrade(model.getValueAt(rowIndex, 14).toString());
            double g5f = parseGrade(model.getValueAt(rowIndex, 17).toString());
            double g6f = parseGrade(model.getValueAt(rowIndex, 20).toString());
            double g7f = parseGrade(model.getValueAt(rowIndex, 23).toString());
            double g8f = parseGrade(model.getValueAt(rowIndex, 26).toString());
            
            // Check for incomplete grades
            boolean isMidIncomplete = (g1 == 0.0 || g2 == 0.0 || g3 == 0.0 || g4 == 0.0 || g5 == 0.0 || g6 == 0.0 || g7 == 0.0 || g8 == 0.0); 
            boolean isFinIncomplete = (g1f == 0.0 || g2f == 0.0 || g3f == 0.0 || g4f == 0.0 || g5f == 0.0 || g6f == 0.0 || g7f == 0.0 || g8f == 0.0);

           
             String midtermResult;
             String finalResult;
             String gwaResult;

            // Midterm grade calculation
            if (isMidIncomplete) {
                midtermResult = "Grade Incomplete";
            } else {
                double midtermAverage = (g1 + g2 + g3 + g4 + g5 + g6 + g7 + g8) / 8;
                midtermResult = String.valueOf(midtermAverage);
            }

            // Final grade calculation
            if (isFinIncomplete) {
                finalResult = "Grade Incomplete";
            } else {
                double finalAverage = (g1f + g2f + g3f + g4f + g5f + g6f + g7f + g8f) / 8;
                finalResult = String.valueOf(finalAverage);
            }
            
            if (!midtermResult.equals("Grade Incomplete") && !finalResult.equals("Grade Incomplete")) {
                double gwa = (Double.parseDouble(midtermResult) + Double.parseDouble(finalResult)) / 2;
                gwaResult = String.valueOf(gwa);
            } else {
                gwaResult = "Grade Incomplete";
            }
            
                 // Update JTable
            model.setValueAt(midtermResult, rowIndex, 27); // Midterm Average
            model.setValueAt(finalResult, rowIndex, 28);  // Final Average
            model.setValueAt(gwaResult, rowIndex, 29);    // GWA

            // Save to database
            saveAveragesToDatabase(rowIndex, midtermResult, finalResult, gwaResult);

            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid grade input. Please ensure all grades are numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to parse grades with fallback
    private double parseGrade(String grade) {
        if (grade == null || grade.trim().isEmpty()) {
            return 0.0; // Default to 0.0 for empty or null input
        }
        try {
            return Double.parseDouble(grade); // Parse the grade value
        } catch (NumberFormatException e) {
            return 0.0; // Default to 0.0 if parsing fails
        }
    }


    // Method to save both averages to the database
    private void saveAveragesToDatabase(int rowIndex, String midtermAverage, String finalAverage, String gwa) {
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(
                 "UPDATE grade SET midterm = ?, final = ?, gwa = ? WHERE studId = ?")) {

            String studId = model.getValueAt(rowIndex, 0).toString();

            stmt.setString(1, midtermAverage); // Set midterm average
            stmt.setString(2, finalAverage);  // Set final average
            stmt.setString(3, gwa); // set GWA
            stmt.setString(4, studId);        // Set student ID

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Averages successfully saved to database");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update the database. Please try again.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    //Validate the grades 
    private boolean isValidGrade(String grade) {
        if (grade == null || grade.trim().isEmpty()) {
            return true; // Accept blank or no data as valid
        }
        try {
            double value = Double.parseDouble(grade);
            return value >= 0.0 && value <= 5.0; // Valid grade range
        } catch (NumberFormatException e) {
            return false; // Invalid if not a number
        }
    }
}