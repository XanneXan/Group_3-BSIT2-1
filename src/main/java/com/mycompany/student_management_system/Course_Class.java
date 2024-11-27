package com.mycompany.student_management_system;

import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Course_Class extends JFrame implements ActionListener{

    private JLabel lblTitle, lblcourseName, lblCourseId , lblStudentNum, lblSearch;
    private JTextField txtCourseName, txtCourseId, txtStudentnum, txtSearch;
    private JButton btnAdd, btnDelete, btnUpdate, btnClear, btnCourses, btnSearch, btnRefresh, btnMenu,btnEdit;
    private JTable tbl;
    private JScrollPane spane;
    private DefaultTableModel mdl;
    private int editingRowIndex = -1;
     
    Course_Class (){
        // system layout
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

            // butttons' layout
            btnAdd = new JButton ("Add");
            btnAdd.setBounds(50, 400, 120, 40);
            btnAdd.setFocusable(false);
            btnAdd.setForeground(Color.WHITE);
            btnAdd.setBackground(Color.BLUE);
            btnAdd.addActionListener(this);
            add (btnAdd);

            btnUpdate = new JButton ("Update");
            btnUpdate.setBounds(190, 400, 120, 40);
            btnUpdate.setFocusable(false);
            btnUpdate.setForeground(Color.WHITE);
            btnUpdate.setBackground(Color.BLUE);
            btnUpdate.addActionListener(this);
            add (btnUpdate);

            btnDelete = new JButton ("Delete");
            btnDelete.setBounds(330, 400, 120, 40);
            btnDelete.setFocusable(false);
            btnDelete.setForeground(Color.WHITE);
            btnDelete.setBackground(Color.BLUE);
            btnDelete.addActionListener(this);
            add(btnDelete);

            btnClear = new JButton ("Clear");
            btnClear.setBounds(115, 460, 120, 40);
            btnClear.setFocusable(false);
            btnClear.setForeground(Color.WHITE);
            btnClear.setBackground(Color.BLUE);
            btnClear.addActionListener(this);
            add (btnClear);

            btnEdit = new JButton("Edit Row");
            btnEdit.setBounds(265, 460, 120, 40);
            btnEdit.setFocusable(false);
            btnEdit.setForeground(Color.WHITE);
            btnEdit.setBackground(Color.BLUE);
            btnEdit.addActionListener(this);
            add (btnEdit);
            
            btnCourses = new JButton ("Courses");
            btnCourses.setBounds(190, 520, 120, 40);
            btnCourses.setFocusable(false);
            btnCourses.setForeground(Color.WHITE);
            btnCourses.setBackground(Color.BLUE);
            btnCourses.addActionListener(this);
            add (btnCourses);

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

            // other buttons' layout
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
            btnSearch.addActionListener(this);
            add (btnSearch);

            btnRefresh = new JButton ("Refresh");
            btnRefresh.setBounds(1250, 115, 70, 25);
            btnRefresh.setFocusable(false);
            btnRefresh.setFont(new Font("Arial", 0, 10));
            btnRefresh.setForeground(Color.WHITE);
            btnRefresh.setBackground(Color.BLUE);
            btnRefresh.addActionListener(this);
            add (btnRefresh);

            btnMenu = new JButton ("Menu");
            btnMenu.setBounds(1250, 650, 70, 25);
            btnMenu.setFocusable(false);
            btnMenu.setFont(new Font("Arial", 0, 10));
            btnMenu.setForeground(Color.WHITE);
            btnMenu.setBackground(Color.BLUE);
            btnMenu.addActionListener(this);
            add (btnMenu);
   
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
        else if (e.getSource()== btnCourses) {
                cfPlay();
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
                menuPlay();
            }
    }

    private void addCourse() {
            String courseId = txtCourseId.getText();
            String courseName = txtCourseName.getText();
            String studentNum = txtStudentnum.getText();
            String courseIdPattern = "^[A-Z]{4}\\d{4}$";
            
            if (!courseId.isEmpty() && !courseName.isEmpty()){
                if (!courseId.matches(courseIdPattern)){
                    JOptionPane.showMessageDialog(this, "Invalid Course ID format. Must be 4 capital letters followed by 4 numbers (e.g., ABCD1234).");
                    clearTextFields();
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(this, "Add this Course?", "Confirm Add", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION){
                 mdl.addRow(new Object[]{courseId, courseName, studentNum});
                 clearTextFields();
                 JOptionPane.showMessageDialog(this, "Course Added Successfully!");
                }
        }
            else {
                JOptionPane.showMessageDialog(this, "Please input both!");
                
            }
            }
    private void updateCourse() {
         if (mdl.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "There are no courses to update.", "No Data", JOptionPane.WARNING_MESSAGE);
        return;
    }
        if (editingRowIndex != -1) { // Ensure a row is selected for editing
            // Get updated data from text fields
            String courseId = txtCourseId.getText().trim();
            String courseName = txtCourseName.getText().trim();
            String studentNum = txtStudentnum.getText().trim();

        // Validate inputs
        if (!courseId.isEmpty() && !courseName.isEmpty()) {
            
            int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to update the selected rows?", 
            "Update Confirmation", JOptionPane.YES_NO_OPTION);
                
            if (confirmation == JOptionPane.YES_OPTION){
            // Update data in the table model
            mdl.setValueAt(courseId, editingRowIndex, 0);
            mdl.setValueAt(courseName, editingRowIndex, 1);
            mdl.setValueAt(studentNum, editingRowIndex, 2);
            JOptionPane.showMessageDialog(this, "Row updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            clearTextFields();
            editingRowIndex = -1; // Reset the editing row index
            }  
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
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
                    if (selectedRow != -1){
                        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                        
                        if (confirm == JOptionPane.YES_OPTION){
                       
                        mdl.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Course Deleted Successfully!");
                        }
                    }
                    else  {
                        JOptionPane.showMessageDialog(this, "Please select a course to delete!");
                    }
        }
    private void cfPlay() {
        courseFrame play = new courseFrame();
        play.setVisible(true);
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
        
        //database
    }

    private void refreshTbl() {
        txtSearch.setText("");
        tbl.clearSelection();
    }

    private void menuPlay() {
        this.dispose();
    }
    
    
    }
    

    //adding new frame to see all course
   class courseFrame extends JFrame implements ActionListener{ 
     
     private JButton searchbtn, refreshbtn, backbtn;
     private JLabel lblTitle, lblSearch;
     private JTable tbl;
     private JScrollPane spane;
     private JTextField txtSearch;
     
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
            tbl = new JTable();
            spane = new JScrollPane(tbl);
            spane.setBounds(50, 135, 1020, 550);
            spane.getViewport().setBackground(Color.lightGray);
            add(spane);
            
            //same with the main frame
            lblSearch = new JLabel ("Search Student: ");
            lblSearch.setBounds(50, 90, 150, 40);
            lblSearch.setFont(new Font("Arial", 1, 14));
            add (lblSearch);

            txtSearch = new JTextField ("");
            txtSearch.setEditable(true);
            txtSearch.setBounds(200, 95, 650, 25);
            add (txtSearch);

            searchbtn = new JButton ("Search");
            searchbtn.setBounds(900, 95, 70, 25);
            searchbtn.setFocusable(false);
            searchbtn.setFont(new Font("Arial", 0, 10));
            searchbtn.setForeground(Color.WHITE);
            searchbtn.setBackground(Color.BLUE);
            searchbtn.addActionListener(this);
            add (searchbtn);

            refreshbtn = new JButton ("Refresh");
            refreshbtn.setBounds(1000, 95, 70, 25);
            refreshbtn.setFocusable(false);
            refreshbtn.setFont(new Font("Arial", 0, 10));
            refreshbtn.setForeground(Color.WHITE);
            refreshbtn.setBackground(Color.BLUE);
            refreshbtn.addActionListener(this);
            add (refreshbtn);
            
            //dispose the current frame and go back to the main frame
            backbtn = new JButton ("Back");
            backbtn.setBounds(1150, 650, 120, 40);
            backbtn.setFocusable(false);
            backbtn.setFont(new Font("Arial", 0, 10));
            backbtn.setForeground(Color.WHITE);
            backbtn.setBackground(Color.BLUE);
            backbtn.addActionListener(this);
            add (backbtn);

            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()== searchbtn){
                search();
            }
            else if (e.getSource() == refreshbtn) {
                refresh();
            }
            else if (e.getSource() == backbtn){
                back();
            }
        }

    private void search() {
        //search student to database
    }

    private void refresh() {
        txtSearch.setText("");
        tbl.clearSelection();
    }

    private void back() {
        this.dispose();
    }    
    }
