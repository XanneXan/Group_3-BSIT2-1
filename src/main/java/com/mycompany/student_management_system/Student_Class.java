package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sebastian Rafael
 */

public class Student_Class extends JFrame implements ActionListener {
    
    private JLabel lblTitle, lblName, lblId, lblSem, lblC1, lblC2, lblC3, lblC4, lblC5, lblC6, lblC7, lblC8, lblSearch, lblLogo;
    private JTextField txtName, txtId, txtSearch;
    private JComboBox<String> cmbSem, cmbC1, cmbC2, cmbC3, cmbC4, cmbC5, cmbC6, cmbC7, cmbC8;
    private JButton btnAdd, btnDelete, btnUpdate, btnClear, btnEditRow, btnSearch, btnRefresh, btnMenu, btnInfo;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;
    private int editingRowIndex = -1;
    
    //Declaring Global variable for mySql connection
    private PreparedStatement pst;
    private Connection con;
    
    //Original format for column in the table
    private int[] originalColumnFormat;
    
    //store student for dynamic access
    private ArrayList<Object[]> storeStudent = new ArrayList<>();
    
    String[] semester = {"1", "2"};
    
    String[] columnList = {"ID", "Name", "Semester", "Course 1", "Course 2", 
                           "Course 3", "Course 4", "Course 5", "Course 6", "Course 7", "Course 8",};
    
        
    //List to store combo boxes dynamically
    private List<JComboBox<String>> comboBoxes = new ArrayList<>();
    
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
    
    Student_Class(){
        
        //Main Frame
        setTitle("Student Management System - STUDENT");
        setExtendedState(MAXIMIZED_BOTH);     
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //GUI components
        lblTitle = new JLabel ("Student Management System");
        lblTitle.setBounds(90, 20, 600, 30);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add (lblTitle);
        
        //For Image
        ImageIcon studentIcn = new ImageIcon("studentl.jpg");
        Image scale = studentIcn.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon logoicon = new ImageIcon(scale);
        lblLogo = new JLabel(logoicon);
        lblLogo.setBounds(20, 10, 60, 60);
        add(lblLogo);
        
        //Labels
        lblId = new JLabel("Student's ID: ");
        lblId.setBounds(50, 150, 150, 40);
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblId);

        lblName = new JLabel("Student's Name: ");
        lblName.setBounds(50, 200, 150, 40);
        lblName.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblName);

        lblSem = new JLabel("Semester: ");
        lblSem.setBounds(50, 250, 150, 40);
        lblSem.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblSem );
        
        //textfields
        txtName = new JTextField();
        txtName.setBounds(200, 205, 250, 25);
        add(txtName);

        txtId = new JTextField();
        txtId.setBounds(200, 155, 250, 25);
        add(txtId);

        //cmb for semester
        cmbSem = new JComboBox<>(semester);
        cmbSem.setBounds(160, 255, 70, 25);
        add(cmbSem);

        //cmb for selecting course
        lblC1 = new JLabel("Course 1: ");
        lblC1.setBounds(50, 300, 150, 40);
        lblC1.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC1 );
        cmbC1 = comboBoxLayoutBox(140, 310);

        lblC2 = new JLabel("Course 2: ");
        lblC2.setBounds(50, 340, 150, 40);
        lblC2.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC2 );
        cmbC2 = comboBoxLayoutBox(140, 350);

        lblC3 = new JLabel("Course 3: ");
        lblC3.setBounds(50, 380, 150, 40);
        lblC3.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC3 );
        cmbC3 = comboBoxLayoutBox(140, 390);

        lblC4 = new JLabel("Course 4: ");
        lblC4.setBounds(50, 420, 150, 40);
        lblC4.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC4 );
        cmbC4 = comboBoxLayoutBox(140, 430);

        lblC5 = new JLabel("Course 5: ");
        lblC5.setBounds(320, 300, 150, 40);
        lblC5.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC5 );
        cmbC5 = comboBoxLayoutBox(410, 310);

        lblC6 = new JLabel("Course 6: ");
        lblC6.setBounds(320, 340, 150, 40);
        lblC6.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC6 );
        cmbC6 = comboBoxLayoutBox(410, 350);

        lblC7 = new JLabel("Course 7: ");
        lblC7.setBounds(320, 380, 150, 40);
        lblC7.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC7 );
        cmbC7 = comboBoxLayoutBox(410, 390);

        lblC8 = new JLabel("Course 8: ");
        lblC8.setBounds(320, 420, 150, 40);
        lblC8.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblC8 );
        cmbC8 = comboBoxLayoutBox(410, 430);
        
        //add combo boxes to the list
        comboBoxes.addAll(Arrays.asList(cmbC1, cmbC2, cmbC3, cmbC4, cmbC5, cmbC6, cmbC7, cmbC8));

        //combbox 
        initializeComboBoxes();

        //set buttons
        btnAdd = new JButton("Add");
        btnAdd.setBounds(70, 500, 120, 40);
        btnAdd.setFocusable(false);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBackground(new Color(125,5,4));
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(210, 500, 120, 40);
        btnUpdate.setFocusable(false);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(125,5,4));
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(350, 500, 120, 40);
        btnDelete.setFocusable(false);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(125,5,4));
        add(btnDelete);

        btnEditRow = new JButton("Edit Row");
        btnEditRow.setBounds(145, 560, 120, 40);
        btnEditRow.setFocusable(false);
        btnEditRow.setForeground(Color.WHITE);
        btnEditRow.setBackground(new Color(125,5,4));
        add(btnEditRow);

        btnClear = new JButton("Clear");
        btnClear.setBounds(285, 560, 120, 40);
        btnClear.setFocusable(false);
        btnClear.setForeground(Color.WHITE);
        btnClear.setBackground(new Color(125,5,4));
        add(btnClear);

        //Table where the information will appear
        model = new DefaultTableModel(columnList, 0);
        studList = new JTable(model);
        studList.getTableHeader().setReorderingAllowed(false);
        studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        studList.setRowHeight(20);
        studList.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        studList.setColumnModel(columnModel());
        studList.setDefaultEditor(Object.class, null); 
        pane = new JScrollPane(studList);
        pane.setBounds(600, 150, 720, 450);
        pane.getViewport().setBackground(Color.decode("#fdecec"));
        add(pane);

        //When table refreshed it will revert the table or column section format to original
        originalColumnFormat = new int[columnList.length];
        for (int i = 0; i < columnList.length; i++) {
           originalColumnFormat[i] = studList.getColumnModel().getColumn(i).getPreferredWidth();
        }

        //Searching 
        lblSearch = new JLabel("Search Student: ");
        lblSearch.setBounds(600, 110, 150, 40);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(730, 115, 400, 25);
        add(txtSearch);
        
        //btn for searching and refresh
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1170, 115, 70, 25);
        btnSearch.setFocusable(false);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.PLAIN, 10));
        btnSearch.setBackground(new Color(125,5,4));
        add(btnSearch);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1250, 115, 70, 25);
        btnRefresh.setFocusable(false);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 10));
        btnRefresh.setBackground(new Color(125,5,4));
        add(btnRefresh);

        //menu Button
        btnMenu = new JButton("Menu");
        btnMenu.setBounds(1250, 650, 70, 25);
        btnMenu.setFocusable(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(new Font("Arial", Font.PLAIN, 10));
        btnMenu.setBackground(new Color(125,5,4));
        add(btnMenu);

        //action listeners for buttons
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEditRow.addActionListener(this);
        btnClear.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnMenu.addActionListener(this);
        
        //add and load the method for connecting to mysql
        connectionMySql();
        loadStudentFromDatabase();

    }
    
    //the format for each column of the table
    private TableColumnModel columnModel(){
        TableColumnModel columnModel = studList.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80); 
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(250);
        columnModel.getColumn(3).setPreferredWidth(250);
        columnModel.getColumn(4).setPreferredWidth(250);
        columnModel.getColumn(5).setPreferredWidth(250);
        columnModel.getColumn(6).setPreferredWidth(250);
        columnModel.getColumn(7).setPreferredWidth(250);
        columnModel.getColumn(8).setPreferredWidth(250);
        columnModel.getColumn(9).setPreferredWidth(250);
        columnModel.getColumn(10).setPreferredWidth(250);
        return columnModel;
        
    }

    //layout for combobox
    private JComboBox<String> comboBoxLayoutBox(int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(x, y, 150, 20);
        add(comboBox);
        return comboBox;
    }

    //retrieve the list of combobox
    private List<JComboBox<String>> getComboBoxes() {
        return comboBoxes;
    }

    //responsible for getting the selected course
    private ArrayList<String> getCourse() {
        ArrayList<String> selectedCourses = new ArrayList<>();
        for (JComboBox<String> cmb : getComboBoxes()) {
            String selected = (String) cmb.getSelectedItem();
            if (selected != null && !selected.isEmpty() && !selected.equals(" ")) {
                selectedCourses.add(selected);
            }
        }
        return selectedCourses;
    }
    
    //the action listener to use the method populateComboBoxes
    private void initializeComboBoxes() {
        for (JComboBox<String> comboBox : getComboBoxes()) {
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    populateComboBoxes();
                }
            });
        }

        populateComboBoxes();
    }
    
    //populate comboboxes from course Class
    private void populateComboBoxes() {
        ArrayList<String> courseList = fetchCoursesFromDatabase();
        ArrayList<String> selectedCourses = getCourse(); 

        for (JComboBox<String> combo : getComboBoxes()) {
            String currentSelection = (String) combo.getSelectedItem();

            //to prevent recursion
            ActionListener[] listeners = combo.getActionListeners();
            for (ActionListener listener : listeners) {
                combo.removeActionListener(listener);
            }

            //repopulate the combo box
            combo.removeAllItems();
            
            combo.addItem("Vacant");
            for (String course : courseList) {
                if (!selectedCourses.contains(course) || course.equals(currentSelection)) {
                    combo.addItem(course);
                }
            }

            if (currentSelection != null && !currentSelection.isEmpty()) {
                combo.setSelectedItem(currentSelection);
            } else if (combo.getItemCount() > 0) {
                combo.setSelectedIndex(0);
            }

            for (ActionListener listener : listeners) {
                combo.addActionListener(listener);
            }
        }
    }

    //this method will populate the text fields and combobox with the data from the selected row
    private void updateFields() {
        int selectedRow = studList.getSelectedRow();
        if (selectedRow != -1) {
            editingRowIndex = selectedRow;
            
            // Making name and id non editable
            txtId.setEditable(false);
            txtName.setEditable(false);
    
            txtId.setText(model.getValueAt(selectedRow, 0).toString());
            txtName.setText(model.getValueAt(selectedRow, 1).toString());
            cmbSem.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
            cmbC1.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
            cmbC2.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
            cmbC3.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
            cmbC4.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
            cmbC5.setSelectedItem(model.getValueAt(selectedRow, 7).toString());
            cmbC6.setSelectedItem(model.getValueAt(selectedRow, 8).toString());
            cmbC7.setSelectedItem(model.getValueAt(selectedRow, 9).toString());
            cmbC8.setSelectedItem(model.getValueAt(selectedRow, 10).toString());
            
        }else{
             JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    
    //method to add student in the table
    private void addStudent() {
        String ID = txtId.getText().trim();
        String studName = txtName.getText().trim();
        String sem = (String) cmbSem.getSelectedItem();
        String cmb1 = (String) cmbC1.getSelectedItem();
        String cmb2 = (String) cmbC2.getSelectedItem();
        String cmb3 = (String) cmbC3.getSelectedItem();
        String cmb4 = (String) cmbC4.getSelectedItem();
        String cmb5 = (String) cmbC5.getSelectedItem();
        String cmb6 = (String) cmbC6.getSelectedItem();
        String cmb7 = (String) cmbC7.getSelectedItem();
        String cmb8 = (String) cmbC8.getSelectedItem();
        
        ArrayList<String> selectedCourses = getCourse();
        clearFieldsAndCmBoxes();
        
        //Validation, 3 Characters and 3 digits)
        if (!ID.matches("^[A-Z]{3}\\d{3}$")) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a number with up to 3 character and 3 digits." + "Example: [ABC123]", "Error", JOptionPane.ERROR_MESSAGE);
            return;           
        }
        
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to add student?", "Confirmation", JOptionPane.YES_NO_OPTION);

        //Validate
        if (ID.isEmpty() || studName.isEmpty() || sem == null || selectedCourses.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required information.", "Error", JOptionPane.ERROR_MESSAGE);
            
        } else if(confirmation == JOptionPane.YES_OPTION) {
            try {
                // Check for duplicate ID
                PreparedStatement checkPst = con.prepareStatement("SELECT COUNT(*) FROM student WHERE ID = ?");
                checkPst.setString(1, ID);
                ResultSet rs = checkPst.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Student ID already exists. Please use a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    
                } else {
                    PreparedStatement insertPst = con.prepareStatement(
                        "INSERT INTO student (ID, Name, Semester, Course1, Course2, Course3, Course4, Course5,"
                                + " Course6, Course7, Course8) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                    insertPst.setString(1, ID);
                    insertPst.setString(2, studName);
                    insertPst.setString(3, sem);
                    insertPst.setString(4, cmb1);
                    insertPst.setString(5, cmb2);
                    insertPst.setString(6, cmb3);
                    insertPst.setString(7, cmb4);
                    insertPst.setString(8, cmb5);
                    insertPst.setString(9, cmb6);
                    insertPst.setString(10, cmb7);
                    insertPst.setString(11, cmb8);

                    int rowsInserted = insertPst.executeUpdate();

                    if (rowsInserted > 0) {
                        clearFieldsAndCmBoxes();
                        JOptionPane.showMessageDialog(this, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                        // Add the data to the JTable model
                        Object[] rowData = {ID, studName, sem, cmb1, cmb2, cmb3, cmb4, cmb5, cmb6, cmb7, cmb8};
                        model.addRow(rowData);
                        
                        //add to ArrayList
                        storeStudent.add(rowData);
                        
                        //add bubblesort
                        sortRows();
                        
                    }
                    
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Operation cancelled.", "Notification", JOptionPane.INFORMATION_MESSAGE);
            
        }
    }
    
    //method for updating student info
    private void updateStudent() {
        int selectedRow = studList.getSelectedRow();
        if (selectedRow != -1) {
            String ID = txtId.getText().trim();
            String studName = txtName.getText().trim();
            String sem = (String) cmbSem.getSelectedItem();
            String cmb1 = (String) cmbC1.getSelectedItem();
            String cmb2 = (String) cmbC2.getSelectedItem();
            String cmb3 = (String) cmbC3.getSelectedItem();
            String cmb4 = (String) cmbC4.getSelectedItem();
            String cmb5 = (String) cmbC5.getSelectedItem();
            String cmb6 = (String) cmbC6.getSelectedItem();
            String cmb7 = (String) cmbC7.getSelectedItem();
            String cmb8 = (String) cmbC8.getSelectedItem();
            
            ArrayList<String> selectedCourses = getCourse();
            clearFieldsAndCmBoxes();
            
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to update the selected student?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (ID.isEmpty() || studName.isEmpty() || selectedCourses.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Please fill in all required information.", "Error", JOptionPane.ERROR_MESSAGE);
            
            }else if (!ID.isEmpty() && !studName.isEmpty() && sem != null && !selectedCourses.isEmpty() && confirmation == JOptionPane.YES_OPTION){
                 try {
                   //Check for duplicate ID
                   pst = con.prepareStatement("SELECT COUNT(*) FROM student WHERE ID = ?");
                   pst.setString(1, ID);
                   ResultSet rs = pst.executeQuery();

                if (rs.next() && rs.getInt(1) > 0) {
                    //If ID exists, update the record
                    PreparedStatement updatePst = con.prepareStatement(
                        "UPDATE student SET Name = ?, Semester = ?, Course1 = ?, Course2 = ?, Course3 = ?, Course4 = ?, Course5 = ?, Course6 = ?, Course7 = ?, Course8 = ? WHERE ID = ?"
                    );

                    updatePst.setString(1, studName);
                    updatePst.setString(2, sem);
                    updatePst.setString(3, cmb1);
                    updatePst.setString(4, cmb2);
                    updatePst.setString(5, cmb3);
                    updatePst.setString(6, cmb4);
                    updatePst.setString(7, cmb5);
                    updatePst.setString(8, cmb6);
                    updatePst.setString(9, cmb7);
                    updatePst.setString(10, cmb8);
                    updatePst.setString(11, ID);

                    int rowsUpdated = updatePst.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Student updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                        model.setValueAt(ID, selectedRow, 0);
                        model.setValueAt(studName, selectedRow, 1);
                        model.setValueAt(sem, selectedRow, 2);
                        model.setValueAt(cmb1, selectedRow, 3);
                        model.setValueAt(cmb2, selectedRow, 4);
                        model.setValueAt(cmb3, selectedRow, 5);
                        model.setValueAt(cmb4, selectedRow, 6);
                        model.setValueAt(cmb5, selectedRow, 7);
                        model.setValueAt(cmb6, selectedRow, 8);
                        model.setValueAt(cmb7, selectedRow, 9);
                        model.setValueAt(cmb8, selectedRow, 10);
                    }

                } else {
                    PreparedStatement insertPst = con.prepareStatement("INSERT INTO student (ID, Name, Semester, Course1, Course2, Course3,"
                            + " Course4, Course5, Course6, Course7, Course8) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                    insertPst.setString(1, ID);
                    insertPst.setString(2, studName);
                    insertPst.setString(3, sem);
                    insertPst.setString(4, cmb1);
                    insertPst.setString(5, cmb2);
                    insertPst.setString(6, cmb3);
                    insertPst.setString(7, cmb4);
                    insertPst.setString(8, cmb5);
                    insertPst.setString(9, cmb6);
                    insertPst.setString(10, cmb7);
                    insertPst.setString(11, cmb8);

                    int rowsInserted = insertPst.executeUpdate();

                    if (rowsInserted > 0) {
                        clearFieldsAndCmBoxes();
                        JOptionPane.showMessageDialog(this, "Student updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        
                        //Add the data to the JTable
                        Object[] rowData = {ID, studName, sem, cmb1, cmb2, cmb3, cmb4, cmb5, cmb6, cmb7, cmb8};
                        model.addRow(rowData);
                        
                        //add to ArrayList
                        storeStudent.add(rowData);
                        
                        //add bubbble sort
                        sortRows();

                    }
                    
                }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }   
    
    //method for deleting student 
    private void deleteStudent() {
        int selectedRow = studList.getSelectedRow();
        clearFieldsAndCmBoxes(); 

        if (selectedRow != -1) {
            String studentID = model.getValueAt(selectedRow, 0).toString();
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete the selected student?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    PreparedStatement deletePst = con.prepareStatement("DELETE FROM student WHERE ID = ?");
                    deletePst.setString(1, studentID);

                    int rowsDeleted = deletePst.executeUpdate();

                    if (rowsDeleted > 0) {
                        model.removeRow(selectedRow); //Remove row from JTable model
                        storeStudent.remove(selectedRow); 
                        JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadStudentFromDatabase(); //Reload data from database
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete student from database.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error Database ", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //mehthod to clear info from textfields and cmboxess
    private void clearFieldsAndCmBoxes() {
        txtId.setEditable(true);
        txtName.setEditable(true);
        txtId.setText("");
        txtName.setText("");
        cmbSem.setSelectedIndex(0);
        for (JComboBox<String> cmb : getComboBoxes()) {
            cmb.setSelectedIndex(0);
        }
        txtSearch.setText("");
        studList.clearSelection();
    }
    
    private void updateTableModel() {
        model.setRowCount(0); //Clear the JTable
        for (Object[] row : storeStudent) {
            model.addRow(row); //Add rows from sorted dataRows
        }
    }
    
    //to search student
    private void searchStudent() {
         String search = txtSearch.getText().trim();

        if (!search.isEmpty()) {
        Object[] result = binarySearchStudent(search); //Search student name by using binary
        
            if (result != null) {
            //Move the found student to the top of the JTable
            moveStudentToTop (result);
            txtSearch.setText("");
            JOptionPane.showMessageDialog(this, "Student found and moved to the top.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        
            } else {
            JOptionPane.showMessageDialog(this, "Student not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }
    } else {
            JOptionPane.showMessageDialog(this, "Please enter a name or ID to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
        
        }
    }
    
    //binary search method
    private Object[] binarySearchStudent(String search) {
        int left = 0; // left pointer
        int right = storeStudent.size() - 1; // right pointer
        
       
        search = search.toLowerCase();  // Convert the search input to lowercase and trim spaces
        
        // Continue searching while the left pointer is less than or equal to the right pointer
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Calculate the middle index
            Object[] midRow = storeStudent.get(mid); // Retrieve the row at the middle index

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
        
        storeStudent.remove(studentRow);
        storeStudent.add(0, studentRow);

        // Update the table model to reflect the change
        updateTableModel();
    }
    
    //to fetch a added course from other class
    public ArrayList<String> fetchCoursesFromDatabase() {
        ArrayList<String> courses = new ArrayList<>();
        try (Connection conn = connectToDatabase();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT courseID, courseName FROM course")) {

            while (rs.next()) {
                String courseID = rs.getString("courseID");
                String courseName = rs.getString("courseName");

                courses.add(courseID + " - " + courseName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching courses: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return courses;
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
        bubbleSort(storeStudent); // sort
        updateTableModel(); // refresh table
    }

   

    //method to use to refresh the table on it's original state
    private void refreshTable() {
        try {
            studList.setModel(model);
            TableColumnModel columnModel = columnModel();
            studList.setColumnModel(columnModel);

            //restore column widths
            for (int i = 0; i < columnList.length; i++) {
                studList.getColumnModel().getColumn(i).setPreferredWidth(originalColumnFormat[i]);
            }

            txtSearch.setText("");
            studList.clearSelection();

            //add bubble sort
            sortRows();

            JOptionPane.showMessageDialog(this, "Table refreshed and format restored.", "Refresh", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error refreshing table: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        }
    }

    //method to load data from dataBase
    private void loadStudentFromDatabase(){
        String url = "jdbc:mysql://127.0.0.1:3306/student_management_system";
        String username = "root";
        String password = "mysqlpasswordg3";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();
                
        ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {
        
        storeStudent.clear(); // Clear dataRows to ensure fresh data is loaded

        while (rs.next()) {
            Object[] row = { rs.getString("ID"), rs.getString("Name"), rs.getString("Semester"),
                rs.getString("Course1"), rs.getString("Course2"), rs.getString("Course3"),
                rs.getString("Course4"), rs.getString("Course5"), rs.getString("Course6"),
                rs.getString("Course7"), rs.getString("Course8")};

            model.addRow(row);
            storeStudent.add(row);
       
                }
         // Sort the data and update the table
          sortRows();
        
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                
            }
             
    }
    
    //method to connect to mysql
    public void connectionMySql(){
        
        String url = "jdbc:mysql://127.0.0.1:3306/student_management_system";
        String username = "root";
        String password = "mysqlpasswordg3";
        
        try{
            con = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        
            if (e.getSource() == btnAdd) {
                addStudent();
                
            } else if (e.getSource() == btnUpdate) {
                updateStudent();
                
            } else if (e.getSource() == btnDelete) {
                deleteStudent();
                
            } else if (e.getSource() == btnClear) {
                clearFieldsAndCmBoxes();
                
            } else if (e.getSource() == btnEditRow) {  
                updateFields();    
                
            } else if (e.getSource() == btnSearch) {
                searchStudent();
                
            } else if (e.getSource() == btnRefresh) {
                refreshTable();
                
            } else if (e.getSource() == btnMenu) {
                new Menu_Frame().setVisible(true);
                dispose();
                
            }
          
    }
    
}
