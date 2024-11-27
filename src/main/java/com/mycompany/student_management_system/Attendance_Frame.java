package com.mycompany.student_management_system;


import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class Attendance_Frame extends JFrame implements ActionListener{
    private JLabel lblTitle, lblSearch, lblDate;
    private JSpinner dateSpinner;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh, backbtn, submitbtn, cancelbtn;
    private JTable attendanceTable;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public Attendance_Frame(){
        setTitle("Student Management System");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        lblTitle = new JLabel ("Attendance");
        lblTitle.setBounds(30, 20, 350, 30);setExtendedState(MAXIMIZED_BOTH);     
        lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
        add (lblTitle);
        
        lblDate = new JLabel("Date:");
        lblDate.setBounds(1110, 90, 100, 30);
        lblDate.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblDate);
        
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(1165, 90, 150, 30);
        add(dateSpinner);
        
        lblSearch = new JLabel("Search Student:");
        lblSearch.setBounds(50, 90, 150, 30);
        lblSearch.setFont(new Font("Arial", 1, 14));
        add(lblSearch);
        
        txtSearch = new JTextField();
        txtSearch.setBounds(200, 90, 650, 25);
        add(txtSearch);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(900, 90, 90, 25);
        
        btnSearch.setFont(new Font("Arial", 0, 10));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(Color.BLUE);
        add(btnSearch);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(1000, 90, 90, 25);
        btnRefresh.setFont(new Font("Arial", 0, 10));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setBackground(Color.BLUE);
        add(btnRefresh);
        
        submitbtn = new JButton("Submit");
        submitbtn.setBounds(1165, 145, 120, 40);
        submitbtn.setFont(new Font("Arial", 0, 10));
        submitbtn.setForeground(Color.WHITE);
        submitbtn.setBackground(Color.BLUE);
        add(submitbtn);

        cancelbtn = new JButton("Cancel");
        cancelbtn.setBounds(1165, 210, 120, 40);
        cancelbtn.setFont(new Font("Arial", 0, 10));
        cancelbtn.setForeground(Color.WHITE);
        cancelbtn.setBackground(Color.BLUE);
        add(cancelbtn);
        
        backbtn = new JButton("Menu");
        backbtn.setBounds(1165, 650, 120, 40);
        backbtn.setFont(new Font("Arial", 0, 10));
        backbtn.setForeground(Color.WHITE);
        backbtn.setBackground(Color.BLUE);
        add(backbtn);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Attendance"}, 0);//Initialize Table Model
        attendanceTable = new JTable(model);
        scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 135, 1055, 550);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        add(scrollPane);
        
        btnSearch.addActionListener((ActionListener) this); //Add Action Listener for Buttons
        btnRefresh.addActionListener((ActionListener) this);
        submitbtn.addActionListener((ActionListener) this);
        cancelbtn.addActionListener((ActionListener) this);
        backbtn.addActionListener((ActionListener) this);
    }
    @Override 
    
    // Method to Handle Button Actions
    public void actionPerformed(ActionEvent event){

        if (event.getSource() == btnSearch){
        //no function as of now
        
        }else if(event.getSource() == btnRefresh) {
        //no function as of now
        
        } else if (event. getSource() == submitbtn){ //Submitting Attendance Function
            int option = JOptionPane.showConfirmDialog(this,"Submit Attendance?","Confirm",JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this, "Attendance Submitted");
            }
            
        }else if (event.getSource() == cancelbtn) { //Cancel button function
            int option = JOptionPane.showConfirmDialog(this,"Are you sure you want to cancel?","Confirm",JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this, "Attendance Canceled");
            }
        }else if (event.getSource() == backbtn) {
            new Menu_Frame().setVisible(true);
            dispose();
        }
    }
    
}
        
            
            
            
            
        
            
            