
package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Performance_Reports extends JFrame implements ActionListener{
    
    private JLabel lblTitle, lblId, lblPer;
    private JTextField txtId;
    private JButton btnMenu, btnGrades, btnGWA, btnAttendance, btnClear;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;
    
    // Array of column headers for the table
    private String [] tblColumn = {"Student's ID", "Student's Name", "Semester", "Term", "Course 1", "Grade", "Course 2","Grade", 
                                    "Course 3","Grade", "Course 4","Grade", "Course 5", "Grade", "Course 6", "Grade", "Course 7",
                                    "Grade", "Course 8","Grade", "Midterm", "Finals", "GWA"};
    
    private ArrayList<Object[]> dataRows = new ArrayList<>(); // Array list to store the data rows for the table
    
Performance_Reports (){
        setExtendedState(MAXIMIZED_BOTH);     
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    lblTitle = new JLabel ("Student Management System");
    lblTitle.setBounds(30, 20, 350, 30);
    lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
    add (lblTitle);
    
    lblPer = new JLabel ("Performance Reports");
    lblPer.setBounds(850, 100, 350, 30);
    lblPer.setFont(new Font("Serif", Font.BOLD, 25));
    add (lblPer);
    
    lblId = new JLabel ("Student's ID: ");
    lblId.setBounds(50, 150, 150, 40);
    lblId.setFont(new Font("Arial", 1, 16));
    add (lblId);
    
    txtId = new JTextField ();
    txtId.setBounds(170, 155, 280, 30);
    add(txtId);
    
    
    
    btnGrades = new JButton ("Grades");
    btnGrades.setBounds(70, 490, 120, 40);
    btnGrades.setFocusable(false);
    btnGrades.setForeground(Color.WHITE);
    btnGrades.setBackground(Color.BLUE);
    add (btnGrades);
    
    btnAttendance = new JButton ("Attendance");
    btnAttendance.setBounds(210, 490, 120, 40);
    btnAttendance.setFocusable(false);
    btnAttendance.setForeground(Color.WHITE);
    btnAttendance.setBackground(Color.BLUE);
    add(btnAttendance);
    
    btnGWA = new JButton ("All GWA");
    btnGWA.setBounds(350, 490, 120, 40);
    btnGWA.setFocusable(false);
    btnGWA.setForeground(Color.WHITE);
    btnGWA.setBackground(Color.BLUE);
    add(btnGWA);
    
    btnMenu = new JButton ("Menu");
    btnMenu.setBounds(145, 550, 120, 40);
    btnMenu.setFocusable(false);
    btnMenu.setForeground(Color.WHITE);
    btnMenu.setBackground(Color.BLUE);
    add (btnMenu);
    
    btnClear = new JButton ("Clear");
    btnClear.setBounds(285, 550, 120, 40);
    btnClear.setFocusable(false);
    btnClear.setForeground(Color.WHITE);
    btnClear.setBackground(Color.BLUE);
    add (btnClear);
    
    
    
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
    
    
      btnMenu.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnMenu){
            new Menu_Frame().setVisible(true);
            dispose();
        }
    }
}
