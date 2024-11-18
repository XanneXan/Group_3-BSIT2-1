
package com.mycompany.student_management_system;

/**
 *
 * @author XANNE
 */
import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Student_Class extends JFrame {
    

    private JLabel lblTitle, lblName, lblId , lblSem, lblC1 , lblC2, lblC3, lblC4, lblC5, lblC6, lblC7, lblC8, lblSearch;
    private JTextField txtName, txtId, midGrade, finGrade, txtSearch;
    private JComboBox cmbSem, cmbC1, cmbC2, cmbC5, cmbC3, cmbC4, cmbC6, cmbC7, cmbC8;
    private JButton btnAdd, btnDelete, btnUpdate, btnAttendance, btnSearch, btnRefresh, btnMenu, btnClear;
    private JTable studList; 
    private JScrollPane pane;
    private DefaultTableModel model;
    
    private String []semester = {"1","2"};

   Student_Class (){
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
    lblName.setBounds(50, 200, 150, 40);
    lblName.setFont(new Font("Arial", 1, 16));
    add (lblName);
        
    lblSem = new JLabel ("Semester: ");
    lblSem.setBounds(50, 250, 150, 40);
    lblSem.setFont(new Font("Arial", 1, 16));
    add (lblSem );
    
    
    txtName = new JTextField ();
    txtName.setBounds(200, 155, 250, 25);
    add (txtName);
    
    txtId = new JTextField ();
    txtId.setBounds(200, 205, 250, 25);
    add(txtId);

    cmbSem = new JComboBox (semester);
    cmbSem.setBounds(160, 255, 70, 25);
    add (cmbSem);
    
    //Courses
    lblC1 = new JLabel ("Course 1: ");
    lblC1.setBounds(50, 300, 150, 40);
    lblC1.setFont(new Font("Arial", 1, 14));
    add (lblC1 );
    
    cmbC1 = new JComboBox ();
    cmbC1.setBounds(140, 310, 150, 20);
    add (cmbC1);
    
    lblC2 = new JLabel ("Course 2: ");
    lblC2.setBounds(50, 340, 150, 40);
    lblC2.setFont(new Font("Arial", 1, 14));
    add (lblC2 );
    
    cmbC2 = new JComboBox ();
    cmbC2.setBounds(140, 350, 150, 20);
    add (cmbC2);
    
    lblC3 = new JLabel ("Course 3: ");
    lblC3.setBounds(50, 380, 150, 40);
    lblC3.setFont(new Font("Arial", 1, 14));
    add (lblC3 );
    
    cmbC3 = new JComboBox ();
    cmbC3.setBounds(140, 390, 150, 20);
    add (cmbC3);
    
    lblC4 = new JLabel ("Course 4: ");
    lblC4.setBounds(50, 420, 150, 40);
    lblC4.setFont(new Font("Arial", 1, 14));
    add (lblC4 );
    
    cmbC4 = new JComboBox ();
    cmbC4.setBounds(140, 430, 150, 20);
    add (cmbC4);
    
    lblC5 = new JLabel ("Course 5: ");
    lblC5.setBounds(320, 300, 150, 40);
    lblC5.setFont(new Font("Arial", 1, 14));
    add (lblC5 );
    
    cmbC5 = new JComboBox ();
    cmbC5.setBounds(410, 310, 150, 20);
    add (cmbC5);
    
    lblC6 = new JLabel ("Course 6: ");
    lblC6.setBounds(320, 340, 150, 40);
    lblC6.setFont(new Font("Arial", 1, 14));
    add (lblC6 );
    
    cmbC6 = new JComboBox ();
    cmbC6.setBounds(410, 350, 150, 20);
    add (cmbC6);
    
    lblC7 = new JLabel ("Course 7: ");
    lblC7.setBounds(320, 380, 150, 40);
    lblC7.setFont(new Font("Arial", 1, 14));
    add (lblC7 );
    
    cmbC7 = new JComboBox ();
    cmbC7.setBounds(410, 390, 150, 20);
    add (cmbC7);
    
    lblC8 = new JLabel ("Course 8: ");
    lblC8.setBounds(320, 420, 150, 40);
    lblC8.setFont(new Font("Arial", 1, 14));
    add (lblC8 );
    
    cmbC8 = new JComboBox ();
    cmbC8.setBounds(410, 430, 150, 20);
    add (cmbC8);
    
    //Buttons
    
    btnAdd = new JButton ("Add");
    btnAdd.setBounds(70, 500, 120, 40);
    btnAdd.setFocusable(false);
    btnAdd.setForeground(Color.WHITE);
    btnAdd.setBackground(Color.BLUE);
    add (btnAdd);
    
    btnUpdate = new JButton ("Update");
    btnUpdate.setBounds(210, 500, 120, 40);
    btnUpdate.setFocusable(false);
    btnUpdate.setForeground(Color.WHITE);
    btnUpdate.setBackground(Color.BLUE);
    add (btnUpdate);
    
    btnDelete = new JButton ("Delete");
    btnDelete.setBounds(350, 500, 120, 40);
    btnDelete.setFocusable(false);
    btnDelete.setForeground(Color.WHITE);
    btnDelete.setBackground(Color.BLUE);
    add(btnDelete);
    
    btnAttendance = new JButton ("Attendance");
    btnAttendance.setBounds(145, 560, 120, 40);
    btnAttendance.setFocusable(false);
    btnAttendance.setForeground(Color.WHITE);
    btnAttendance.setBackground(Color.BLUE);
    add (btnAttendance);
    
    btnClear = new JButton ("Clear");
    btnClear.setBounds(285, 560, 120, 40);
    btnClear.setFocusable(false);
    btnClear.setForeground(Color.WHITE);
    btnClear.setBackground(Color.BLUE);
    add (btnClear);
    
    // Table where the information will appear
    
    model = new DefaultTableModel(5,5 );
    studList = new JTable (model);
    pane = new JScrollPane(studList);
    pane.setBounds(600, 150, 720, 450);
    pane.getViewport().setBackground(Color.lightGray);
    add(pane);
    
    //Search Function
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
    btnSearch.setForeground(Color.WHITE);
    btnSearch.setFont(new Font("Arial", 0, 10));
    btnSearch.setBackground(Color.BLUE);
    add (btnSearch);
    
    btnRefresh = new JButton ("Refresh");
    btnRefresh.setBounds(1250, 115, 70, 25);
    btnRefresh.setFocusable(false);
    btnRefresh.setForeground(Color.WHITE);
    btnRefresh.setFont(new Font("Arial", 0, 10));
    btnRefresh.setBackground(Color.BLUE);
    add (btnRefresh);
    
    //back to menu
    
    btnMenu = new JButton ("Menu");
    btnMenu.setBounds(1250, 650, 70, 25);
    btnMenu.setFocusable(false);
    btnMenu.setForeground(Color.WHITE);
    btnMenu.setFont(new Font("Arial", 0, 10));
    btnMenu.setBackground(Color.BLUE);
    add (btnMenu);
    
   
    }
}
