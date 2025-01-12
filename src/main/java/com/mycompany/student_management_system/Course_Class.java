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
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Course_Class extends JFrame implements ActionListener{

    private JLabel lblTitle, lblcourseName, lblCourseId , lblStudentNum, lblSearch;
    private JTextField txtCourseName, txtCourseId, txtStudentnum, txtSearch;
    private JButton btnAdd, btnDelete, btnUpdate, btnClear, btnSearch, btnRefresh, btnMenu,btnEdit;
    private JTable tbl;
    private JScrollPane spane;
    private DefaultTableModel mdl;
    private int editingRowIndex = -1;
    
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
        // system layout
            setTitle("Student Management System");
            setExtendedState(MAXIMIZED_BOTH);     
            setLayout(null);
            setBackground(new Color(125, 5, 4));
            setResizable(false);
           Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Brit\\Documents\\Group_3-BSIT2-1\\Group_3-BSIT2-1\\src\\main\\java\\com\\mycompany\\student_management_system\\course_icon.jpg");
           setIconImage(icon);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getRootPane().setBorder(BorderFactory.createLineBorder(new Color(125,5,4),5));
            getContentPane().setBackground(new Color(240,240,240));
        
            lblTitle = new JLabel ("Student Management System");
            lblTitle.setBounds(30, 20, 350, 30);setExtendedState(MAXIMIZED_BOTH);     
            lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
            lblTitle.setForeground(new Color(125,5,4));
            add (lblTitle);

            lblCourseId = new JLabel ("Course ID: ");
            lblCourseId.setBounds(50, 190, 150, 40);
            lblCourseId.setFont(new Font("Arial", 1, 16));
            add (lblCourseId);

            lblcourseName = new JLabel ("Course Name: ");
            lblcourseName.setBounds(50, 240, 150, 40);
            lblcourseName.setFont(new Font("Arial", 1, 16));
            add (lblcourseName);

            lblStudentNum = new JLabel ("Number of Students: ");
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
            txtStudentnum.setEditable(true);
            txtStudentnum.setBounds(215, 295, 100, 25);
            add(txtStudentnum);

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
    
            String[] columns = {"Course ID", "Course Name", "Number of Students Enrolled"};
            mdl = new DefaultTableModel (null, columns);
            tbl = new JTable (mdl);
            tbl.getTableHeader().setReorderingAllowed(false);
            tbl.setDefaultEditor(Object.class, null);
            tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            // Adjusting column sizes for the JTable to enhance readability and proper alignment
            TableColumn columnId = tbl.getColumnModel().getColumn(0);
            columnId.setPreferredWidth(250);
            tbl.getColumnModel().getColumn(0).setResizable(false);
            
            TableColumn columnName = tbl.getColumnModel().getColumn(1);
            columnName.setPreferredWidth(250);
            tbl.getColumnModel().getColumn(1).setResizable(false);
            
            TableColumn columnStudentNum = tbl.getColumnModel().getColumn(2);
            columnStudentNum.setPreferredWidth(220);
            tbl.getColumnModel().getColumn(2).setResizable(false);
            
            spane = new JScrollPane(tbl);
            spane.setBounds(550, 150, 720, 450);
            spane.getViewport().setBackground(Color.LIGHT_GRAY);
            add (spane);
            
            loadData(); //data loading from database
    }
    //functionalities with the buttons
 @Override
    public void actionPerformed(ActionEvent e) { 
        if (e.getSource() == btnAdd ) {
            addCourse();
        }
        else if (e.getSource()== btnUpdate) {
            updateCourse();
            }
 
        else if (e.getSource() == btnDelete) {
            deleteCourse();
                }
        else if (e.getSource() == btnClear){
            clearTextFields();
        }
        else if (e.getSource() == btnEdit){
            editRow();
        }
        else if (e.getSource() == btnSearch){
                searchCourse();
            }
        else if (e.getSource()== btnRefresh){
                refreshTbl();
            } 
        else if (e.getSource() == btnMenu){
            new Menu_Frame().setVisible(true);
            dispose();
            }
    }
    //functionalities
    
     private void loadData(){
         Connection conn = connectToDatabase();
        if (conn != null) {
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM course_table");
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    mdl.addRow(new Object[]{rs.getString("courseID"), rs.getString("courseName"), rs.getInt("studentNum")});
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
            }
        }
}

    private void addCourse() {
            String courseId = txtCourseId.getText();
            String courseName = txtCourseName.getText();
            String studentNum = txtStudentnum.getText();
            String courseIdPattern = "^[A-Z]{4}\\d{4}$";
            
            boolean isAddedtoDB = false; 
            
        if (courseId.isEmpty() && courseName.isEmpty() && studentNum.isEmpty()){
                  JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
       if (!courseId.matches(courseIdPattern)){
                   JOptionPane.showMessageDialog(this, "Invalid Course ID format. Must be 4 capital letters followed by 4 numbers (e.g., ABCD1234).");
        return;
}
        try {
        int numStudents = Integer.parseInt(studentNum);
        if (numStudents < 0) {
            JOptionPane.showMessageDialog(this, "Number of Students must be a positive number.");
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Number of Students must be a valid integer.");
        return;
    }
         try (Connection conn = connectToDatabase();
        PreparedStatement stmt = conn.prepareStatement(
          "INSERT INTO course_table (courseID, courseName, studentNum) VALUES (?, ?, ?)")) {
            stmt.setString(1, courseId);
            stmt.setString(2, courseName);
            stmt.setString(3, studentNum);
            stmt.executeUpdate();
             isAddedtoDB = true; 
        } 
         catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Error adding data to the database: " + ex.getMessage());
    }

        if (isAddedtoDB) {
    JOptionPane.showMessageDialog(this, "Course added to the database successfully!");
    } 
       else {
    JOptionPane.showMessageDialog(this, "Failed to add the course.");
            }
        }
  private void updateCourse() {
    if (editingRowIndex != -1) {  

        String courseId = txtCourseId.getText().trim();
        String courseName = txtCourseName.getText().trim();
        String studentNum = txtStudentnum.getText().trim();

        if (courseId.isEmpty() || courseName.isEmpty() || studentNum.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String courseIdPattern = "^[A-Z]{4}\\d{4}$";
        if (!courseId.matches(courseIdPattern)) {
            JOptionPane.showMessageDialog(this, "Invalid Course ID format. Must be 4 capital letters followed by 4 numbers (e.g., ABCD1234).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (Connection conn = connectToDatabase()) {
            String checkQuery = "SELECT COUNT(*) FROM course_table WHERE courseID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
                stmt.setString(1, courseId);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "Course ID already exists. Please choose a different ID.", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error checking course ID: " + ex.getMessage());
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to update the selected course?", 
            "Update Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            mdl.setValueAt(courseId, editingRowIndex, 0);
            mdl.setValueAt(courseName, editingRowIndex, 1);
            mdl.setValueAt(studentNum, editingRowIndex, 2);

            try (Connection conn = connectToDatabase()) {
                String udQue = "UPDATE course_table SET courseName = ?, studentNum = ? WHERE courseID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(udQue)) {
                    stmt.setString(1, courseName);  
                    stmt.setString(2, studentNum);   
                    stmt.setString(3, courseId);     
                    stmt.executeUpdate();            
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating course: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(this, "Course updated successfully!");
            clearTextFields();
            editingRowIndex = -1;  
        }
    } else {
        JOptionPane.showMessageDialog(this, "No row selected for editing.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
     private void deleteCourse() {
            if (mdl.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "There are no courses to delete.", "No Data", JOptionPane.WARNING_MESSAGE);
        return;
        }
           int selectedRow = tbl.getSelectedRow(); 
            if (selectedRow != -1) {
        String courseId = mdl.getValueAt(selectedRow, 0).toString(); 
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Remove the selected course from the database
            try (Connection conn = connectToDatabase();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM course_table WHERE courseID = ?")) {
                stmt.setString(1, courseId);
                int rowsAffected = stmt.executeUpdate(); // Executes the delete operation

                if (rowsAffected > 0) {
                    mdl.removeRow(selectedRow); 
                    JOptionPane.showMessageDialog(this, "Course deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete the course from the database.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting course: " + e.getMessage());
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a course to delete!");
    }

        }
   
    private void clearTextFields() {
        txtCourseName.setText("");
        txtCourseId.setText("");
        txtStudentnum.setText("");
        txtSearch.setText("");
    }

    private void editRow() {
         if (mdl.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "There are no courses to edit.", "No Data", JOptionPane.WARNING_MESSAGE);
        return;
    }
        int selectedRow = tbl.getSelectedRow();
    
        if (selectedRow != -1) {
           editingRowIndex = selectedRow;
            // Populate the text fields with the data from the selected row
            txtCourseId.setText(mdl.getValueAt(selectedRow, 0).toString());
            txtCourseName.setText(mdl.getValueAt(selectedRow, 1).toString());
            txtStudentnum.setText(mdl.getValueAt(selectedRow, 2).toString());
        
        }else{
             JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
          }
        
    }
 private void searchCourse() {
        sortingAl(false);
         String crs = txtSearch.getText().toLowerCase(); // Get the searched course id or name from the text field
         boolean found = false;
   for (int i = 0; i < mdl.getRowCount(); i++) {
        String courseId = mdl.getValueAt(i, 0).toString().toLowerCase();
        String courseName = mdl.getValueAt(i, 1).toString().toLowerCase();
   if (courseId.contains(crs) || courseName.contains(crs)) {
            tbl.setRowSelectionInterval(i, i); 
            found = true;
            break;
        }
    } 
     if (!found) {
        int index = binarySearch(crs); //binary search method
        
     if (index != -1) {
            tbl.setRowSelectionInterval(index, index); 
            found = true;
        }
    }
     if (!found) {
        JOptionPane.showMessageDialog(this, "No matching course found.");
    }
}
   private void sortingAl(boolean par) {   //sorting algorithm method
    int n = mdl.getRowCount();
    
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
            String courseId1 = mdl.getValueAt(j, 0).toString();
            String courseId2 = mdl.getValueAt(j + 1, 0).toString();
           if (courseId1.compareToIgnoreCase(courseId2) > 0) {
                swapRows(j, j + 1);
                }
               
            }
        }
}
   private void swapRows(int row1, int row2) {
    int columnCount = mdl.getColumnCount();
    for (int col = 0; col < columnCount; col++) {
        Object temp = mdl.getValueAt(row1, col);
        mdl.setValueAt(mdl.getValueAt(row2, col), row1, col);
        mdl.setValueAt(temp, row2, col);
    }
}
    private int binarySearch(String crs) { //binary search method
    int left = 0;
    int right = mdl.getRowCount() - 1;

    while (left <= right) {
        int mid = (left + right) / 2;
        String courseName = mdl.getValueAt(mid, 1).toString().toLowerCase(); // course ID is in the first column

        if (courseName.equalsIgnoreCase(crs)) {
            return mid; // Course found!
        }
        if (courseName.compareToIgnoreCase(crs) < 0) {
            left = mid + 1; // Searching in the right half
        } else {
            right = mid - 1; // Searching in the left half
        }
    }
    return -1; // Course not found!
}
    private void refreshTbl() {
         try (Connection conn = connectToDatabase()) {
        String query = "SELECT * FROM course_table";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Clear the JTable before inserting new data
            mdl.setRowCount(0); 

            while (rs.next()) {
                String courseId = rs.getString("courseID");
                String courseName = rs.getString("courseName");
                String studentNum = rs.getString("studentNum");

                // Add data to the table model
                mdl.addRow(new Object[]{courseId, courseName, studentNum});
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
    }
}
}