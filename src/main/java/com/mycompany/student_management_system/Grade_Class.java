/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

/**
 *
 * @author XANNE
 */
import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;

public class Grade_Class extends JFrame implements ActionListener{
    private JLabel lblTitle, lblName, lblId , lblSem, lblTerm, lblC1 , lblC2, lblC3, lblC4, lblC5, lblC6, lblC7, lblC8, lblSearch;
    private JTextField txtName, txtId, midGrade, finGrade, txtSearch, txtTerm, txtSem, txtC1, txtC2, txtC5, 
            txtC3, txtC4, txtC6, txtC7, txtC8, txtG1, txtG2, txtG3, txtG4, txtG5, txtG6, txtG7, txtG8;
    private JComboBox cmbTerm;
    private JButton btnAdd, btnDelete, btnUpdate, btnEdit, btnSearch, btnRefresh, btnMenu, btnClear, btnSearch2;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;
    private int editingRowIndex = -1;  // Stores the index of the row being edited
    
    // Array of column headers for the table
    private String [] tblColumn = {"Student's ID", "Student's Name", "Semester", "Term", "Course 1", "Grade", "Course 2","Grade", 
                                    "Course 3","Grade", "Course 4","Grade", "Course 5", "Grade", "Course 6", "Grade", "Course 7",
                                    "Grade", "Course 8","Grade", "Midterm", "Final", "GWA"};
   
    private ArrayList<Object[]> dataRows = new ArrayList<>(); // Array list to store the data rows for the table
    private String [] term = {"Midterm","Final"}; // Array for ComboBox options (Terms)

    Grade_Class (){
        setExtendedState(MAXIMIZED_BOTH);     
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
    lblTitle = new JLabel ("Student Management System");
    lblTitle.setBounds(30, 20, 350, 30);
    lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
    add (lblTitle);
    
    lblId = new JLabel ("Student's ID: ");
    lblId.setBounds(50, 150, 150, 40);
    lblId.setFont(new Font("Arial", 1, 16));
    add (lblId);
    
    lblName = new JLabel ("Student's Name: ");
    lblName.setBounds(50, 220, 150, 40);
    lblName.setFont(new Font("Arial", 1, 16));
    add (lblName);
       
    lblSem = new JLabel ("Semester: ");
    lblSem.setBounds(50, 270, 150, 40);
    lblSem.setFont(new Font("Arial", 1, 16));
    add (lblSem );
    
    lblTerm = new JLabel ("Term: ");
    lblTerm.setBounds(300, 270, 150, 40);
    lblTerm.setFont(new Font("Arial", 1, 16));
    add (lblTerm );
    
    //TextFields for user input in Student ID and Name
    txtId = new JTextField ();
    txtId.setBounds(170, 155, 250, 25);
    add(txtId);
    
    txtName = new JTextField ();
    txtName.setBounds(190, 225, 250, 25);
    txtName.setBackground(Color.LIGHT_GRAY);
    add (txtName);
    
    //The semester will automatically appear here. It is set to non-editable
    txtSem = new JTextField ();
    txtSem.setBounds(160, 275, 70, 25);
    txtSem.setBackground(Color.LIGHT_GRAY);
    //txtSem.setEditable(false);
    add (txtSem);
    
    cmbTerm = new JComboBox(term);
    cmbTerm.setBounds(370, 275, 80, 25);
    add (cmbTerm);
    
    lblSearch = new JLabel ("Search Student: ");
    lblSearch.setBounds(600, 110, 150, 40);
    lblSearch.setFont(new Font("Arial", 1, 14));
    add (lblSearch);
    
    txtSearch = new JTextField ();
    txtSearch.setBounds(730, 115, 400, 25);
    add (txtSearch);
    
    
    // User inputs for grades. The course names will automatically appear in non-editable text fields
    //Course 1 Components
    lblC1 = new JLabel ("Course 1");
    lblC1.setBounds(50, 330, 150, 40);
    lblC1.setFont(new Font("Arial", 1, 12));
    add (lblC1);
    
    txtC1 = new JTextField ();
    txtC1.setBounds(110, 340, 135, 20);
    txtC1.setBackground(Color.LIGHT_GRAY);
    txtC1.setEditable(false);
    add (txtC1);
    
    txtG1 = new JTextField ();
    txtG1.setBounds(255, 340, 35, 20);
    txtG1.setBackground(Color.CYAN);
    add (txtG1);
    
    //Course 2 Components
    lblC2 = new JLabel ("Course 2");
    lblC2.setBounds(50, 370, 150, 40);
    lblC2.setFont(new Font("Arial", 1, 12));
    add (lblC2 );
    
    txtC2 = new JTextField ();
    txtC2.setBounds(110, 380, 135, 20);
    txtC2.setBackground(Color.LIGHT_GRAY);
    txtC2.setEditable(false);
    add (txtC2);
    
    txtG2 = new JTextField ();
    txtG2.setBounds(255, 380, 35, 20);
    txtG2.setBackground(Color.CYAN);
    add (txtG2);
    
    //Course 3 Components
    lblC3 = new JLabel ("Course 3");
    lblC3.setBounds(50, 410, 150, 40);
    lblC3.setFont(new Font("Arial", 1, 12));
    add (lblC3 );
    
    txtC3 = new JTextField ();
    txtC3.setBounds(110, 420, 135, 20);
    txtC3.setBackground(Color.LIGHT_GRAY);
    txtC3.setEditable(false);
    add (txtC3);
    
    txtG3 = new JTextField ();
    txtG3.setBounds(255, 420, 35, 20);
    txtG3.setBackground(Color.CYAN);
    add (txtG3);
    
    //Course 4 Components
    lblC4 = new JLabel ("Course 4");
    lblC4.setBounds(50, 450, 150, 40);
    lblC4.setFont(new Font("Arial", 1, 12));
    add (lblC4 );
    
    txtC4 = new JTextField ();
    txtC4.setBounds(110, 460, 135, 20);
    txtC4.setBackground(Color.LIGHT_GRAY);
    txtC4.setEditable(false);
    add (txtC4);
    
    txtG4 = new JTextField ();
    txtG4.setBounds(255, 460, 35, 20);
    txtG4.setBackground(Color.CYAN);
    add (txtG4);
    
    //Course 5 Components
    lblC5 = new JLabel ("Course 5");
    lblC5.setBounds(320, 330, 150, 40);
    lblC5.setFont(new Font("Arial", 1, 12));
    add (lblC5 );
    
    txtC5 = new JTextField ();
    txtC5.setBounds(380, 340, 135, 20);
    txtC5.setBackground(Color.LIGHT_GRAY);
    txtC5.setEditable(false);
    add (txtC5);
    
    txtG5 = new JTextField ();
    txtG5.setBounds(525, 340, 35, 20);
    txtG5.setBackground(Color.CYAN);
    add (txtG5);
    
    //Course 6 Components
    lblC6 = new JLabel ("Course 6");
    lblC6.setBounds(320, 370, 150, 40);
    lblC6.setFont(new Font("Arial", 1, 12));
    add (lblC6 );
    
    txtC6 = new JTextField ();
    txtC6.setBounds(380, 380, 135, 20);
    txtC6.setBackground(Color.LIGHT_GRAY);
    txtC6.setEditable(false);
    add (txtC6);
    
    txtG6 = new JTextField ();
    txtG6.setBounds(525, 380, 35, 20);
    txtG6.setBackground(Color.CYAN);
    add (txtG6);
    
    //Course 7 Components
    lblC7 = new JLabel ("Course 7");
    lblC7.setBounds(320, 410, 150, 40);
    lblC7.setFont(new Font("Arial", 1, 12));
    add (lblC7 );
    
    txtC7 = new JTextField ();
    txtC7.setBounds(380, 420, 135, 20);
    txtC7.setBackground(Color.LIGHT_GRAY);
    txtC7.setEditable(false);
    add (txtC7);
    
    txtG7 = new JTextField ();
    txtG7.setBounds(525, 420, 35, 20);
    txtG7.setBackground(Color.CYAN);
    add (txtG7);
    
    //Course 8 Components
    lblC8 = new JLabel ("Course 8");
    lblC8.setBounds(320, 450, 150, 40);
    lblC8.setFont(new Font("Arial", 1, 12));
    add (lblC8 );
    
    txtC8 = new JTextField ();
    txtC8.setBounds(380, 460, 135, 20);
    txtC8.setBackground(Color.LIGHT_GRAY);
    txtC8.setEditable(false);
    add (txtC8);
    
    txtG8 = new JTextField ();
    txtG8.setBounds(525, 460, 35, 20);
    txtG8.setBackground(Color.CYAN);
    add (txtG8);
   
   
    
    /* Table where the information will appear
       Initializing the table model with no rows (null) and predefined column headers (tblColumn)
       Setting the rows to null so the table starts empty and will be populated based on user inputs */
    model = new DefaultTableModel(null,tblColumn );
    studList = new JTable (model);
    studList.getTableHeader().setReorderingAllowed(false);
    studList.setDefaultEditor(Object.class, null);
    studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    
   

    // Adjusting column sizes for the JTable to enhance readability and proper alignment
     int preferredWidth = 200; 
        for (int i = 0; i < studList.getColumnCount(); i++) {
            studList.getColumnModel().getColumn(i).setPreferredWidth(preferredWidth);
            studList.getColumnModel().getColumn(i).setResizable(false); 
        }

    TableColumn columnId = studList.getColumnModel().getColumn(0);
        columnId.setPreferredWidth(100);
        
    TableColumn ColumnSem = studList.getColumnModel().getColumn(2);
        ColumnSem.setPreferredWidth(70);
    
    TableColumn ColumnTerm = studList.getColumnModel().getColumn(3);
    ColumnTerm.setPreferredWidth(70);
    
    TableColumn columnG1 = studList.getColumnModel().getColumn(5);
        columnG1.setPreferredWidth(70);
    
    TableColumn columnG2 = studList.getColumnModel().getColumn(7);
        columnG2.setPreferredWidth(70);
        
    TableColumn columnG3 = studList.getColumnModel().getColumn(9);
        columnG3.setPreferredWidth(70);
        
    TableColumn columnG4 = studList.getColumnModel().getColumn(11);
        columnG4.setPreferredWidth(70); 
        
    TableColumn columnG5 = studList.getColumnModel().getColumn(13);
        columnG5.setPreferredWidth(70); 
        
    TableColumn columnG6 = studList.getColumnModel().getColumn(15);
        columnG6.setPreferredWidth(70);    
    
    TableColumn columnG7 = studList.getColumnModel().getColumn(17);
        columnG7.setPreferredWidth(70);    
    
    TableColumn columnG8 = studList.getColumnModel().getColumn(19);
        columnG8.setPreferredWidth(70);  
        
    TableColumn ColumnMid = studList.getColumnModel().getColumn(20);
        ColumnMid.setPreferredWidth(70); 
    
    TableColumn ColumnFin = studList.getColumnModel().getColumn(21);
        ColumnFin.setPreferredWidth(70); 
        
    TableColumn ColumnGwa = studList.getColumnModel().getColumn(22);
        ColumnGwa.setPreferredWidth(70); 
   
        
    pane = new JScrollPane(studList);
    pane.setBounds(600, 150, 720, 450);
    pane.getViewport().setBackground(Color.lightGray);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    add(pane);
    
     
    //All Buttons
    btnAdd = new JButton ("Add");
    btnAdd.setBounds(70, 530, 120, 40);
    btnAdd.setFocusable(false);
    btnAdd.setForeground(Color.WHITE);
    btnAdd.setBackground(Color.BLUE);
    add (btnAdd);
    
    btnUpdate = new JButton ("Update");
    btnUpdate.setBounds(210, 530, 120, 40);
    btnUpdate.setFocusable(false);
    btnUpdate.setForeground(Color.WHITE);
    btnUpdate.setBackground(Color.BLUE);
    add (btnUpdate);
    
    btnDelete = new JButton ("Delete");
    btnDelete.setBounds(350, 530, 120, 40);
    btnDelete.setFocusable(false);
    btnDelete.setForeground(Color.WHITE);
    btnDelete.setBackground(Color.BLUE);
    add(btnDelete);
    
    btnEdit = new JButton ("Edit Row");
    btnEdit.setBounds(145, 590, 120, 40);
    btnEdit.setFocusable(false);
    btnEdit.setForeground(Color.WHITE);
    btnEdit.setBackground(Color.BLUE);
    add (btnEdit);
    
    btnClear = new JButton ("Clear");
    btnClear.setBounds(285, 590, 120, 40);
    btnClear.setFocusable(false);
    btnClear.setForeground(Color.WHITE);
    btnClear.setBackground(Color.BLUE);
    add (btnClear);
    
    // "Search" button to trigger the search operation
    btnSearch = new JButton ("Search");
    btnSearch.setBounds(1170, 115, 70, 25);
    btnSearch.setFocusable(false);
    btnSearch.setForeground(Color.WHITE);
    btnSearch.setFont(new Font("Arial", 0, 10));
    btnSearch.setBackground(Color.BLUE);
    add (btnSearch);
    
    // "Refresh" button to reload or reset the data in the table
    btnRefresh = new JButton ("Refresh");
    btnRefresh.setBounds(1250, 115, 70, 25);
    btnRefresh.setFocusable(false);
    btnRefresh.setForeground(Color.WHITE);
    btnRefresh.setFont(new Font("Arial", 0, 10));
    btnRefresh.setBackground(Color.BLUE);
    add (btnRefresh);
    

    // "Menu_Frame" button to navigate back to the main menu
    btnMenu = new JButton ("Menu");
    btnMenu.setBounds(1250, 650, 70, 25);
    btnMenu.setFocusable(false);
    btnMenu.setForeground(Color.WHITE);
    btnMenu.setFont(new Font("Arial", 0, 10));
    btnMenu.setBackground(Color.BLUE);
    add (btnMenu);
    
    // Additional Search Button for Student ID 
    btnSearch2 = new JButton ("Search");
    btnSearch2.setBounds(450, 155, 70, 25);
    btnSearch2.setFocusable(false);
    btnSearch2.setForeground(Color.WHITE);
    btnSearch2.setFont(new Font("Arial", 0, 10));
    btnSearch2.setBackground(Color.BLUE);
    add (btnSearch2);
    
    btnAdd.addActionListener(this);
    btnDelete.addActionListener(this);
    btnUpdate.addActionListener(this);
    btnEdit.addActionListener(this);
    btnClear.addActionListener(this);
    btnMenu.addActionListener(this);
            
    
    
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
    
     private void addStudent() {
    // Collecting input values
        String studId = txtId.getText().trim();
        String studName = txtName.getText().trim();
        String sem = txtSem.getText().trim();
        String term = cmbTerm.getSelectedItem().toString();
        String c1 = txtC1.getText().trim();
        String g1 = txtG1.getText().trim();
        String c2 = txtC2.getText().trim();
        String g2 = txtG2.getText().trim();
        String c3 = txtC3.getText().trim();
        String g3 = txtG3.getText().trim();
        String c4 = txtC4.getText().trim();
        String g4 = txtG4.getText().trim();
        String c5 = txtC5.getText().trim();
        String g5 = txtG5.getText().trim();
        String c6 = txtC6.getText().trim();
        String g6 = txtG6.getText().trim();
        String c7 = txtC7.getText().trim();
        String g7 = txtG7.getText().trim();
        String c8 = txtC8.getText().trim();
        String g8 = txtG8.getText().trim();

        // Ensure all required fields are filled
        if (studId.isEmpty() || studName.isEmpty() || sem.isEmpty() || term.isEmpty() ||
            g1.isEmpty() || g2.isEmpty() || g3.isEmpty() || g4.isEmpty() ||
            g5.isEmpty() || g6.isEmpty() || g7.isEmpty() || g8.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        // Validate grades (Must be 1.0-5.0)
        if (!isValidGrade(g1) || !isValidGrade(g2) || !isValidGrade(g3) || !isValidGrade(g4) ||
            !isValidGrade(g5) || !isValidGrade(g6) || !isValidGrade(g7) || !isValidGrade(g8)) {
            JOptionPane.showMessageDialog(this, "Grades must be numbers between 1.0 and 5.0.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        Object[] tblRow = { studId, studName, sem, term,
                            c1, g1, c2, g2, c3, g3, c4, g4,
                            c5, g5, c6, g6, c7, g7, c8, g8 };

        // Confirmation message before adding the data
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to add this?",
            "Add Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            dataRows.add(tblRow); // Save row to ArrayList
            model.addRow(tblRow); // Display row in the table
            termsCalculation(model.getRowCount() - 1); // Perform calculations for the new row

            clearFields(); // Clear input fields after successful addition
            JOptionPane.showMessageDialog(this, "Grades added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

     //Methods for removing/deleting rows
     private void removeRow (){
          
         int selectRow = studList.getSelectedRow();
         if (selectRow !=-1) {
            
             //Confirmation message before the row be deleted to the table
            int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete the selected rows?", 
            "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                
                
            if (confirmation == JOptionPane.YES_OPTION){
                dataRows.remove(selectRow);
                model.removeRow(selectRow);
            }
                
         } else {
           JOptionPane.showMessageDialog(this, "Please Select a Row to be Deleted.", "Input Error", JOptionPane.ERROR_MESSAGE);
          
           }
     }
     
     //For Clearing all fields 
     private void clearFields() {
    
            txtId.setText("");
            txtName.setText("");
            txtSem.setText("");
            cmbTerm.setSelectedIndex(0);
            txtG1.setText("");
            txtG2.setText("");
            txtG3.setText("");
            txtG4.setText("");
            txtG5.setText("");
            txtG6.setText("");
            txtG7.setText("");
            txtG8.setText("");
            txtC1.setText("");
            txtC2.setText("");
            txtC3.setText("");
            txtC4.setText("");
            txtC5.setText("");
            txtC6.setText("");
            txtC7.setText("");
            txtC8.setText("");
     
     }
     
     private void updateRow() {
        if (editingRowIndex != -1) { // Ensure a row is selected for editing
            
            // Get updated data from text fields
            String studId = txtId.getText().trim();
            String studName = txtName.getText().trim();
            String sem = txtSem.getText().trim();
            String term = cmbTerm.getSelectedItem().toString();
            String c1 = txtC1.getText().trim();
            String g1 = txtG1.getText().trim();
            String c2 = txtC2.getText().trim();
            String g2 = txtG2.getText().trim();
            String c3 = txtC3.getText().trim();
            String g3 = txtG3.getText().trim();
            String c4 = txtC4.getText().trim();
            String g4 = txtG4.getText().trim();
            String c5 = txtC5.getText().trim();
            String g5 = txtG5.getText().trim();
            String c6 = txtC6.getText().trim();
            String g6 = txtG6.getText().trim();
            String c7 = txtC7.getText().trim();
            String g7 = txtG7.getText().trim();
            String c8 = txtC8.getText().trim();
            String g8 = txtG8.getText().trim();
        
        

        // Validate inputs
        if (!studId.isEmpty() && !studName.isEmpty() && !sem.isEmpty() && !term.isEmpty()) {
            
            int confirmation = JOptionPane.showConfirmDialog(this, 
                     "Are you sure you want to update the selected rows?",  "Update Confirmation", JOptionPane.YES_NO_OPTION);
                
                
            if (confirmation == JOptionPane.YES_OPTION){
            // Update data in the table model
                model.setValueAt(studId, editingRowIndex, 0);
                model.setValueAt(studName, editingRowIndex, 1);
                model.setValueAt(sem, editingRowIndex, 2);
                model.setValueAt(term, editingRowIndex, 3);
                model.setValueAt(c1, editingRowIndex, 4);
                model.setValueAt(g1, editingRowIndex, 5);
                model.setValueAt(c2, editingRowIndex, 6);
                model.setValueAt(g2, editingRowIndex, 7);
                model.setValueAt(c3, editingRowIndex, 8);
                model.setValueAt(g3, editingRowIndex, 9);
                model.setValueAt(c4, editingRowIndex, 10);
                model.setValueAt(g4, editingRowIndex, 11);
                model.setValueAt(c5, editingRowIndex, 12);
                model.setValueAt(g5, editingRowIndex, 13);
                model.setValueAt(c6, editingRowIndex, 14);
                model.setValueAt(g6, editingRowIndex, 15);
                model.setValueAt(c7, editingRowIndex, 16);
                model.setValueAt(g7, editingRowIndex, 17);
                model.setValueAt(c8, editingRowIndex, 18);
                model.setValueAt(g8, editingRowIndex, 19);
          
        JOptionPane.showMessageDialog(this, "Row updated successfully.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);

            clearFields();
            editingRowIndex = -1; // Reset the editing row index
            termsCalculation(editingRowIndex);
            }  
            
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
             } else {
                JOptionPane.showMessageDialog(this, "No row selected for editing.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
     
    private void loadSelectedRowData() {
    int selectedRow = studList.getSelectedRow();
    
        if (selectedRow != -1) {
            editingRowIndex = selectedRow;
            
                // Populate the text fields with the data from the selected row
                txtId.setText(model.getValueAt(selectedRow, 0).toString());
                txtName.setText(model.getValueAt(selectedRow, 1).toString());
                txtSem.setText(model.getValueAt(selectedRow, 2).toString());
                cmbTerm.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
                txtC1.setText(model.getValueAt(selectedRow, 4).toString());
                txtG1.setText(model.getValueAt(selectedRow, 5).toString());
                txtC2.setText(model.getValueAt(selectedRow, 6).toString());
                txtG2.setText(model.getValueAt(selectedRow, 7).toString());
                txtC3.setText(model.getValueAt(selectedRow, 8).toString());
                txtG3.setText(model.getValueAt(selectedRow, 9).toString());
                txtC4.setText(model.getValueAt(selectedRow, 10).toString());
                txtG4.setText(model.getValueAt(selectedRow, 11).toString());
                txtC5.setText(model.getValueAt(selectedRow, 12).toString());
                txtG5.setText(model.getValueAt(selectedRow, 13).toString());
                txtC6.setText(model.getValueAt(selectedRow, 14).toString());
                txtG6.setText(model.getValueAt(selectedRow, 15).toString());
                txtC7.setText(model.getValueAt(selectedRow, 16).toString());
                txtG7.setText(model.getValueAt(selectedRow, 17).toString());
                txtC8.setText(model.getValueAt(selectedRow, 18).toString());
                txtG8.setText(model.getValueAt(selectedRow, 19).toString());
        
        }else{
             JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
 
    private void termsCalculation(int rowIndex) {
    
        // Get grades from the table model
        double g1 = Double.parseDouble(model.getValueAt(rowIndex, 5).toString());
        double g2 = Double.parseDouble(model.getValueAt(rowIndex, 7).toString());
        double g3 = Double.parseDouble(model.getValueAt(rowIndex, 9).toString());
        double g4 = Double.parseDouble(model.getValueAt(rowIndex, 11).toString());
        double g5 = Double.parseDouble(model.getValueAt(rowIndex, 13).toString());
        double g6 = Double.parseDouble(model.getValueAt(rowIndex, 15).toString());
        double g7 = Double.parseDouble(model.getValueAt(rowIndex, 17).toString());
        double g8 = Double.parseDouble(model.getValueAt(rowIndex, 19).toString());

        // Calculate average
        double average = (g1 + g2 + g3 + g4 + g5 + g6 + g7 + g8) / 8;

        // Determine column based on term
        int selectedTerm = cmbTerm.getSelectedIndex();
            if (selectedTerm==0) {
                model.setValueAt(average, rowIndex, 20); 
            
            } else if (selectedTerm==1) {
                model.setValueAt(average, rowIndex, 21); 

    } else {
        JOptionPane.showMessageDialog(this, "Invalid grade input. Please ensure all grades are numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Validate the grades 
    private boolean isValidGrade(String grade) {
        try {
            double value = Double.parseDouble(grade);
            return value >= 1.0 && value <= 5.0;
        } catch (NumberFormatException e) {
            return false;
        }
    }       
}

