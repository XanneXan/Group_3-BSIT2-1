package com.mycompany.student_management_system;

import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Course_Class extends JFrame implements ActionListener{

    private JLabel lblTitle, lblcourseName, lblCourseId , lblStudentNum, lblSearch, lblLogo;
    private JTextField txtCourseName, txtCourseId, txtStudentnum, txtSearch;
    private JButton btnAdd, btnDelete, btnUpdate, btnClear, btnSearch, btnRefresh, btnMenu,btnEdit;
    private JTable tbl;
    private JScrollPane spane;
    private DefaultTableModel mdl;
    private int editingRowIndex = -1;
    
    //store course for dynamic access
    public static ArrayList<Object[]> storeCourse = new ArrayList<>();
    
    //global varbs for mySql connection
    private Connection con;
    
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
     
    Course_Class (){
        
        //Main Frame
        setTitle("Student Management System - COURSE");
        setExtendedState(MAXIMIZED_BOTH);     
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(125, 5, 4));
        setResizable(false);

        lblTitle = new JLabel ("Student Management System");
        lblTitle.setBounds(90, 20, 600, 30);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add (lblTitle);

        //Insert Image to label
        ImageIcon gradeIcn = new ImageIcon("coursel.jpg");
        Image scale = gradeIcn.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon logoicon = new ImageIcon(scale);
        lblLogo = new JLabel(logoicon);
        lblLogo.setBounds(20, 10, 60, 60);
        add(lblLogo);

        lblCourseId = new JLabel ("Course ID: ");
        lblCourseId.setBounds(50, 190, 150, 40);
        lblCourseId.setFont(new Font("Arial", 1, 16));
        add (lblCourseId);

        lblcourseName = new JLabel ("Course Name: ");
        lblcourseName.setBounds(50, 240, 150, 40);
        lblcourseName.setFont(new Font("Arial", 1, 16));
        add (lblcourseName);

        txtCourseId = new JTextField ("");
        txtCourseId.setEditable(true);
        txtCourseId.setBounds(200, 195, 250, 25);
        add (txtCourseId);

        txtCourseName = new JTextField ("");
        txtCourseName.setEditable(true);
        txtCourseName.setBounds(200, 245, 250, 25);
        add(txtCourseName);

        // butttons' layout
        btnAdd = new JButton ("Add");
        btnAdd.setBounds(50, 400, 120, 40);
        btnAdd.setFocusable(false);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBackground(new Color(125,5,4));
        btnAdd.addActionListener(this);
        add (btnAdd);

        btnUpdate = new JButton ("Update");
        btnUpdate.setBounds(190, 400, 120, 40);
        btnUpdate.setFocusable(false);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(125,5,4));
        btnUpdate.addActionListener(this);
        add (btnUpdate);

        btnDelete = new JButton ("Delete");
        btnDelete.setBounds(330, 400, 120, 40);
        btnDelete.setFocusable(false);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(125,5,4));
        btnDelete.addActionListener(this);
        add(btnDelete);

        btnClear = new JButton ("Clear");
        btnClear.setBounds(115, 460, 120, 40);
        btnClear.setFocusable(false);
        btnClear.setForeground(Color.WHITE);
        btnClear.setBackground(new Color(125,5,4));
        btnClear.addActionListener(this);
        add (btnClear);

        btnEdit = new JButton("Edit Row");
        btnEdit.setBounds(265, 460, 120, 40);
        btnEdit.setFocusable(false);
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setBackground(new Color(125,5,4));
        btnEdit.addActionListener(this);
        add (btnEdit);

        // other buttons' layout
        lblSearch = new JLabel ("Search Course: ");
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
        btnSearch.setBackground(new Color(125,5,4));
        btnSearch.addActionListener(this);
        add (btnSearch);

        btnRefresh = new JButton ("Refresh");
        btnRefresh.setBounds(1250, 115, 70, 25);
        btnRefresh.setFocusable(false);
        btnRefresh.setFont(new Font("Arial", 0, 10));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setBackground(new Color(125,5,4));
        btnRefresh.addActionListener(this);
        add (btnRefresh);

        btnMenu = new JButton ("Menu");
        btnMenu.setBounds(1250, 650, 70, 25);
        btnMenu.setFocusable(false);
        btnMenu.setFont(new Font("Arial", 0, 10));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setBackground(new Color(125,5,4));
        btnMenu.addActionListener(this);
        add (btnMenu);

         /* Table where the information will appear
        Initializing the table model with no rows (null) and column headers (Column)
        Setting the rows to null so the table starts empty and will be populated based on user inputs */

        String[] columns = {"Course ID", "Course Name"};
        mdl = new DefaultTableModel (null, columns);
        tbl = new JTable (mdl);
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.setDefaultEditor(Object.class, null);
        tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Adjusting column sizes for the JTable to enhance readability and proper alignment
        TableColumn columnId = tbl.getColumnModel().getColumn(0);
        columnId.setPreferredWidth(360);
        tbl.getColumnModel().getColumn(0).setResizable(false);

        TableColumn columnName = tbl.getColumnModel().getColumn(1);
        columnName.setPreferredWidth(360);
        tbl.getColumnModel().getColumn(1).setResizable(false);

        spane = new JScrollPane(tbl);
        spane.setBounds(600, 150, 720, 450);
        spane.getViewport().setBackground(Color.decode("#fdecec"));
        add (spane);

        connectionMySql();
        loadData(); //data loading from database

}

    //functionalities with the buttons
    @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == btnAdd ) {
                addCourse();

        }else if (e.getSource()== btnUpdate) {
            updateCourse();
            refreshTable();
            
        }else if (e.getSource() == btnDelete) {
            deleteCourse();
            
        }else if (e.getSource() == btnClear){
            clearTextFields();
            
        }else if (e.getSource() == btnEdit){
            editRow();
            
        }else if (e.getSource() == btnSearch){
                searchStudent();
                
        }else if (e.getSource()== btnRefresh){
                refreshTable();
                
        }else if (e.getSource() == btnMenu){
            new Menu_Frame().setVisible(true);
            dispose();
            
        }
    }
    
    //load data frm sql
    private void loadData(){
        if (con != null) {
            try {
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM course");
                ResultSet rs = stmt.executeQuery();
                
                storeCourse.clear();
                while (rs.next()) {
                    
                    Object[] row = {rs.getString("courseID"), rs.getString("courseName")};
                    mdl.addRow(row);
                    storeCourse.add(row);
                            
                }
                 // Sort the data and update the table
                 sortRows();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
            }
        }
    }
    
    //to add course
    private void addCourse() {
        String courseId = txtCourseId.getText().trim();
        String courseName = txtCourseName.getText().trim();

        String courseIdPattern = "^[A-Z]{4}\\d{4}$";  

        if (!courseId.matches(courseIdPattern)) {
            JOptionPane.showMessageDialog(this, "Invalid Course ID format. Must be 4 capital letters followed by 4 digits (e.g., ABCD1234).", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this course?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            try (Connection conn = connectToDatabase()) {

                // Check for duplicate Course ID or Course Name
                String checkQuery = "SELECT COUNT(*) FROM course WHERE courseID = ? OR courseName = ?";
                try (PreparedStatement checkPst = conn.prepareStatement(checkQuery)) {
                    checkPst.setString(1, courseId);
                    checkPst.setString(2, courseName);
                    try (ResultSet rs = checkPst.executeQuery()) {
                        rs.next();
                        int count = rs.getInt(1);

                        if (count > 0) {
                            JOptionPane.showMessageDialog(this, "Course ID or Course Name already exists. Please use different values.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                // Insert data into the database
                String insertQuery = "INSERT INTO course (courseID, courseName) VALUES (?, ?)";
                try (PreparedStatement insertPst = conn.prepareStatement(insertQuery)) {
                    insertPst.setString(1, courseId);
                    insertPst.setString(2, courseName);

                    int rowsInserted = insertPst.executeUpdate();

                    if (rowsInserted > 0) {
                        clearTextFields();
                        JOptionPane.showMessageDialog(this, "Course added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        
                        // Add the new data to the JTable model
                        Object[] rowData = {courseId, courseName};
                        mdl.addRow(rowData);
                        storeCourse.add(rowData);
                        sortRows();
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error adding course to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid number of students. Please enter a valid integer.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operation cancelled.", "Notification", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void updateCourse() {
        if (editingRowIndex != -1) {// Check if a row is selected for editing
            String courseId = txtCourseId.getText().trim();
            String courseName = txtCourseName.getText().trim();

            if (courseId.isEmpty() || courseName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to update the selected course?", "Update Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                try (Connection conn = connectToDatabase()) {
                     // Validate if the ID or Name exists in the database and belongs to a different record
                    String checkQuery = "SELECT COUNT(*) FROM course WHERE (courseID = ? OR courseName = ?) AND courseID != ?";
                    try (PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
                        stmt.setString(1, courseId);       // Check for duplicate courseID
                        stmt.setString(2, courseName);    // Check for duplicate courseName
                        stmt.setString(3, courseId);      // Exclude the current course ID
                        ResultSet rs = stmt.executeQuery();
                        rs.next();
                        int count = rs.getInt(1);

                    if (count > 0) {
                        JOptionPane.showMessageDialog(this, "Course ID or Course Name already exists. Please choose different values.", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Update the record in the database
                String updateQuery = "UPDATE course SET courseName = ? WHERE courseID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                    stmt.setString(1, courseName);  // New course name
                    stmt.setString(2, courseId);   // Course ID to update
                    stmt.executeUpdate();          // Execute the update
                }

                // Update the JTable model
                if (editingRowIndex >= 0) {
                    mdl.setValueAt(courseId, editingRowIndex, 0); // Update the course ID in the table
                    mdl.setValueAt(courseName, editingRowIndex, 1); // Update the course name in the table
                } else {
                    // If no row is currently being edited, add the new course as a new row
                    mdl.addRow(new Object[]{courseId, courseName});
                }

                // Sort and refresh the table
                sortRows();

                JOptionPane.showMessageDialog(this, "Course updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearTextFields();  // Clear input fields after updating
                editingRowIndex = -1; // Reset editing row index

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating course" , "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    }
    
    //to delete
    private void deleteCourse() {
        int selectedRow = tbl.getSelectedRow();
        clearTextFields();

        if (selectedRow != -1) {
            String courseId = mdl.getValueAt(selectedRow, 0).toString();
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete the selected course?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                try (Connection conn = connectToDatabase()) {
                    if (conn == null) {
                        JOptionPane.showMessageDialog(this, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    PreparedStatement deletePst = conn.prepareStatement("DELETE FROM course WHERE courseID = ?");
                    deletePst.setString(1, courseId);

                    int rowsDeleted = deletePst.executeUpdate();

                    if (rowsDeleted > 0) {
                        mdl.removeRow(selectedRow);
                        sortRows();
                        JOptionPane.showMessageDialog(this, "Course deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete the course from the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Debugging
                    JOptionPane.showMessageDialog(this, "Error deleting course: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //clear textfield
    private void clearTextFields() {
        txtCourseName.setText("");
        txtCourseId.setText("");
        txtSearch.setText("");
        
    }
    
    //to edit a selected row in table
    private void editRow() {
        if (mdl.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "There are no courses to edit.", "No Data", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedRow = tbl.getSelectedRow();
    
        if (selectedRow != -1) {
            editingRowIndex = selectedRow;
            txtCourseId.setText(mdl.getValueAt(selectedRow, 0).toString());
            txtCourseName.setText(mdl.getValueAt(selectedRow, 1).toString());
        
        }else {
             JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }  
    }
    
    // sorting algorithm to arrange student alphabetically
    private void bubbleSort (ArrayList <Object[]> cData){
        int numCourse = cData.size();
        
        for (int i = 0; i< numCourse -1; i++){
            for (int j = 0; j < numCourse - i - 1; j++) {
            
            String c1 = cData.get(j)[1].toString().toLowerCase();
            String c2 = cData.get(j + 1)[1].toString().toLowerCase();
            
             if (c1.compareTo(c2) > 0) {
                // Swap the current student with the next student
                Object[] temp = cData.get(j);
                cData.set(j, cData.get(j + 1));
                cData.set(j + 1, temp);
                }
            }
        }                
    }

    // Sort and Update JTable
    private void sortRows() {
        bubbleSort(storeCourse); // sort
        updateTableModel(); // refresh table
    }

    // Method to Update Table Model
    private void updateTableModel() {
        mdl.setRowCount(0); // Clear the JTable
        for (Object[] row : storeCourse) {
            mdl.addRow(row); // Add rows from sorted dataRows
        }
    }
    
    // Search method to find a student by name using binary search    
    private void searchStudent() {
        String search = txtSearch.getText().trim();

        if (!search.isEmpty()) {
        Object[] result = binarySearchStudent(search); // Search student name by using binary
        
            if (result != null) {
            // Move the found student to the top of the JTable
            moveStudentToTop(result);
            txtSearch.setText("");
            JOptionPane.showMessageDialog(this, "Student found and moved to the top.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        
            } else {
            JOptionPane.showMessageDialog(this, "Student not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
            JOptionPane.showMessageDialog(this, "Please enter a name or ID to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }
  
    // Binary search method to find a student by name
   private Object[] binarySearchStudent(String search) {
        int left = 0; // left pointer
        int right = storeCourse.size() - 1; // right pointer
        
        // Continue searching while the left pointer is less than or equal to the right pointer
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Calculate the middle index
            Object[] midRow = storeCourse.get(mid); // Retrieve the row at the middle index

            String studName = midRow[1].toString().toLowerCase(); // Get student name
           

            // Compare the search input with the student's name and id
            int nameComparison = studName.compareToIgnoreCase(search); // get name in table
          
            if (nameComparison == 0 ) {
                return midRow; // Student found
            } else if (nameComparison < 0) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        return null; // Student not found
    }


    
    private void moveStudentToTop(Object[] studentRow) {
        
        storeCourse.remove(studentRow);  // Remove the student from their current position
        storeCourse.add(0, studentRow); // Add the student to the top of the list

        // Update the table model to reflect the change
        updateTableModel();
    }
    
    private void refreshTable() {
        
        loadData(); // Reload data from the database into dataRows
        updateTableModel(); // Update the JTable with the reloaded data
    }
   


    
    //method to connect to mysql
    public void connectionMySql(){
        
        try{
            con = DriverManager.getConnection(url, user, pass);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
 
}