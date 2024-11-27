/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;

/**
 *
 * @author Sebastian Rafael
 */

public class Student_Class extends JFrame implements ActionListener {
    
    private JLabel lblTitle, lblName, lblId, lblSem, lblC1, lblC2, lblC3, lblC4, lblC5, lblC6, lblC7, lblC8, lblSearch;
    private JTextField txtName, txtId, txtSearch;
    private JComboBox<String> cmbSem, cmbC1, cmbC2, cmbC3, cmbC4, cmbC5, cmbC6, cmbC7, cmbC8;
    private JButton btnAdd, btnDelete, btnUpdate, btnClear, btnEditRow, btnSearch, btnRefresh, btnMenu;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;
    private int editingRowIndex = -1;//
    
    // Original format for column in the table
    private int[] originalColumnFormat;
    //store student for dynamic access
    private ArrayList<Object[]> storeStudent = new ArrayList<>();
    
    String[] semester = {"1", "2"};
    
    //tentative list of courses, will be inputed as proxy
    String[] courses = { "N/A or Vacant", "Introduction to Computing", "Computer Programming 1",
                        "Computer Programming 2", "Data Structures and Algorithm",
                        "Structured Programming", "Object-Oriented Programming",
                        "Networking and Data Communications", "Web Development"};
    
    String[] columnList = {"ID", "Name", "Semester", "Course 1", "Grade", "Course 2", "Grade", 
                           "Course 3", "Grade", "Course 4", "Grade", "Course 5", "Grade",
                           "Course 6", "Grade", "Course 7", "Grade", "Course 8", "Grade", "Average"};
    
    Student_Class(){
        
        //Main Frame
        setExtendedState(MAXIMIZED_BOTH);     
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Labels
        lblTitle = new JLabel("Student Management System");
        lblTitle.setBounds(30, 20, 350, 30);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
        add(lblTitle);

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

        //Text Fields
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

        //set buttons
        btnAdd = new JButton("Add");
        btnAdd.setBounds(70, 500, 120, 40);
        btnAdd.setFocusable(false);
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBackground(Color.BLUE);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(210, 500, 120, 40);
        btnUpdate.setFocusable(false);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setBackground(Color.BLUE);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(350, 500, 120, 40);
        btnDelete.setFocusable(false);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBackground(Color.BLUE);
        add(btnDelete);

        btnEditRow = new JButton("Edit Row");
        btnEditRow.setBounds(145, 560, 120, 40);
        btnEditRow.setFocusable(false);
        btnEditRow.setForeground(Color.WHITE);
        btnEditRow.setBackground(Color.BLUE);
        add(btnEditRow);

        btnClear = new JButton("Clear");
        btnClear.setBounds(285, 560, 120, 40);
        btnClear.setFocusable(false);
        btnClear.setForeground(Color.WHITE);
        btnClear.setBackground(Color.BLUE);
        add(btnClear);

        //Table where the information will appear
        model = new DefaultTableModel(columnList, 0);
        studList = new JTable(model);
        studList.getTableHeader().setReorderingAllowed(false);
        studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        studList.setRowHeight(25);
        studList.setColumnModel(columnModel());
        studList.setDefaultEditor(Object.class, null);
        pane = new JScrollPane(studList);
        pane.setBounds(600, 150, 720, 450);
        pane.getViewport().setBackground(Color.lightGray);
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

        btnSearch = new JButton("Search");
        btnSearch.setBounds(1170, 115, 70, 25);
        btnSearch.setFocusable(false);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.PLAIN, 10));
        btnSearch.setBackground(Color.BLUE);
        add(btnSearch);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1250, 115, 70, 25);
        btnRefresh.setFocusable(false);
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 10));
        btnRefresh.setBackground(Color.BLUE);
        add(btnRefresh);

        //menu Button
        btnMenu = new JButton("Menu");
        btnMenu.setBounds(1250, 650, 70, 25);
        btnMenu.setFocusable(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(new Font("Arial", Font.PLAIN, 10));
        btnMenu.setBackground(Color.BLUE);
        add(btnMenu);

        //action listeners for buttons
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEditRow.addActionListener(this);
        btnClear.addActionListener(this);
        btnSearch.addActionListener(this);
        btnRefresh.addActionListener(this);

    }
    
    //the format for each column of the table
    private TableColumnModel columnModel(){
        TableColumnModel columnModel = studList.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80); 
        columnModel.getColumn(1).setPreferredWidth(250);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(150);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(7).setPreferredWidth(150);
        columnModel.getColumn(8).setPreferredWidth(50);
        columnModel.getColumn(9).setPreferredWidth(150);
        columnModel.getColumn(10).setPreferredWidth(50);
        columnModel.getColumn(11).setPreferredWidth(150);
        columnModel.getColumn(12).setPreferredWidth(50);
        columnModel.getColumn(13).setPreferredWidth(150);
        columnModel.getColumn(14).setPreferredWidth(50);
        columnModel.getColumn(15).setPreferredWidth(150);
        columnModel.getColumn(16).setPreferredWidth(50);
        columnModel.getColumn(17).setPreferredWidth(150);
        columnModel.getColumn(18).setPreferredWidth(50);
        columnModel.getColumn(19).setPreferredWidth(150);
        return columnModel;
    }
    
    //method for easy manipulation of the comboboxes
    private JComboBox<String>[] getComboBoxes() {
        return new JComboBox[]{cmbC1, cmbC2, cmbC3, cmbC4, cmbC5, cmbC6, cmbC7, cmbC8};
    }
    
    //method responsible for getting the info in each comboBoxes
    private ArrayList<String> getCourse() {
        ArrayList<String> selectedCourses = new ArrayList<>();
        for (JComboBox<String> cmb : getComboBoxes()) {
            String selected = (String) cmb.getSelectedItem();
            if (selected != null && !selected.isEmpty() && !selected.equals("N/A or Vacant")) {
                selectedCourses.add(selected);
            }
        }
        return selectedCourses;
    }
    
    //method for setting the layout for all cmboxes
    private JComboBox<String> comboBoxLayoutBox(int x, int y) {
        JComboBox<String> comboBoxLayoutBox = new JComboBox<>(courses);
        comboBoxLayoutBox.setBounds(x, y, 150, 20);
        comboBoxLayoutBox.setSelectedIndex(0);
      
        //selected item in the cmbBoxes will be excluded
        comboBoxLayoutBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> selectedCourses = getCourse();
                for (JComboBox<String> combo : getComboBoxes()) {
                    combo.setEnabled(true);
                    combo.removeAll();// This method results in some form of shifting of the comboBox layout, but still usable

                    for (String course : courses) {
                        boolean isSelected = selectedCourses.contains(course);
                        if (isSelected && !course.equals(combo.getSelectedItem())) {
                            combo.removeItem(course);
                        }
                    }
                }
            }
        });
      
        add(comboBoxLayoutBox);
        return comboBoxLayoutBox;
    }
    
    //this method will populate the text fields and cmBoxes with the data from the selected row
    private void updateFields() {
        int selectedRow = studList.getSelectedRow();
        if (selectedRow != -1) {
            editingRowIndex = selectedRow;
    
            txtId.setText(model.getValueAt(selectedRow, 0).toString());
            txtName.setText(model.getValueAt(selectedRow, 1).toString());
            cmbSem.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
            cmbC1.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
            cmbC2.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
            cmbC3.setSelectedItem(model.getValueAt(selectedRow, 7).toString());
            cmbC4.setSelectedItem(model.getValueAt(selectedRow, 9).toString());
            cmbC5.setSelectedItem(model.getValueAt(selectedRow, 11).toString());
            cmbC6.setSelectedItem(model.getValueAt(selectedRow, 13).toString());
            cmbC7.setSelectedItem(model.getValueAt(selectedRow, 15).toString());
            cmbC8.setSelectedItem(model.getValueAt(selectedRow, 17).toString());
            
        }else{
             JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    
    //method to add student in the table
    private void addStudent() {
        String grade = "N/A";
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
        
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to add student?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (!ID.isEmpty() && !studName.isEmpty() && sem != null && !selectedCourses.isEmpty() && confirmation == JOptionPane.YES_OPTION) {

            Object[] rowData = {ID, studName, sem, 
                cmb1, grade, cmb2, grade, cmb3, grade, cmb4, grade, 
                cmb5, grade, cmb6, grade, cmb7, grade, cmb8, grade, grade};
 
            storeStudent.add(rowData);//save to arrayList
            model.addRow(rowData);

            JOptionPane.showMessageDialog(this, "Student Added.", "Succesful.", JOptionPane.INFORMATION_MESSAGE);
            
        }else if(confirmation == JOptionPane.NO_OPTION){
           //it will not show anything
        }else {
            JOptionPane.showMessageDialog(this, "Please fill in all required information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //methpd for updating student info
    private void updateStudent() {
        int selectedRow = studList.getSelectedRow();
        if (selectedRow != -1) {
            String grade = "N/A";
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

            if (!ID.isEmpty() && !studName.isEmpty() && sem != null && !selectedCourses.isEmpty() && confirmation == JOptionPane.YES_OPTION) {
                
            model.setValueAt(ID, editingRowIndex, 0);
            model.setValueAt(studName, editingRowIndex, 1);
            model.setValueAt(sem, editingRowIndex, 2);
            model.setValueAt(cmb1, editingRowIndex, 3);
            model.setValueAt(grade, editingRowIndex, 4);
            model.setValueAt(cmb2, editingRowIndex, 5);
            model.setValueAt(grade, editingRowIndex, 6);
            model.setValueAt(cmb3, editingRowIndex, 7);
            model.setValueAt(grade, editingRowIndex, 8);
            model.setValueAt(cmb4, editingRowIndex, 9);
            model.setValueAt(grade, editingRowIndex, 10);
            model.setValueAt(cmb5, editingRowIndex, 11);
            model.setValueAt(grade, editingRowIndex, 12);
            model.setValueAt(cmb6, editingRowIndex, 13);
            model.setValueAt(grade, editingRowIndex, 14);
            model.setValueAt(cmb7, editingRowIndex, 15);
            model.setValueAt(grade, editingRowIndex, 16);
            model.setValueAt(cmb8, editingRowIndex, 17);
            model.setValueAt(grade, editingRowIndex, 18);
            model.setValueAt(grade, editingRowIndex, 19);

            JOptionPane.showMessageDialog(this, "Student updated successfully.", "Successful", JOptionPane.INFORMATION_MESSAGE);
                
            }else if(confirmation == JOptionPane.NO_OPTION){
             //it will not show anything
            }else {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
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
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected student?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                storeStudent.remove(selectedRow);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //mehthod to clear info from textfields and cmboxess
    private void clearFieldsAndCmBoxes() {
        txtId.setText("");
        txtName.setText("");
        cmbSem.setSelectedIndex(0);
        for (JComboBox<String> cmb : getComboBoxes()) {
            cmb.setSelectedIndex(0);
        }
        studList.clearSelection();
    }
    
    //method to use to search student in the table
    private void searchStudent() {
        String searchName = txtSearch.getText().trim().toLowerCase();
        if (searchName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a student name to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel searchModel = new DefaultTableModel(columnList, 0);
        for (Object[] student : storeStudent) {
            String studentName = ((String) student[1]).toLowerCase();
            if (studentName.contains(searchName)) {
                searchModel.addRow(student);
            }
        }
        if (searchModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Student not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }

        studList.setModel(searchModel);
        studList.setColumnModel(columnModel());
    }
    
    //method to use to refresh the table on it's original state
    private void refreshTable() {
        studList.setModel(model);
        TableColumnModel columnModel = columnModel();
        studList.setColumnModel(columnModel);
        for (int i = 0; i < columnList.length; i++) {
            studList.getColumnModel().getColumn(i).setPreferredWidth(originalColumnFormat[i]);
        }
        
        txtSearch.setText("");
        studList.clearSelection();

        JOptionPane.showMessageDialog(this, "Table refreshed and format restored.", "Refresh", JOptionPane.INFORMATION_MESSAGE);
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
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
                dispose();//proxy
            }
                
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(this, "There is something wrong.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}

