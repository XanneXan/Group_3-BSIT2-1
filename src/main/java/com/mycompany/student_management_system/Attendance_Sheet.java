/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.student_management_system;

/**
 *
 * @author Jasmine
 */
import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Attendance_Sheet extends JFrame implements ActionListener {

    private JTextField searchField;
    private JTable attendanceTable;
    private JButton backButton, searchButton, clearButton;
    private DefaultTableModel tableModel;

    public Attendance_Sheet() {
        setTitle("Student Management System");
        setExtendedState(MAXIMIZED_BOTH); // Maximized window
        setLayout(null); // Absolute positioning for components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Title label
        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT");
        titleLabel.setBounds(100, 30, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#7d0504"));
        add(titleLabel);

        // Add a logo
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Jasmine\\Downloads\\logo.jpg");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(20, 20, 80, 80);
        add(logoLabel);

        // Search label
        JLabel searchLabel = new JLabel("Search Student:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        searchLabel.setBounds(850, 25, 150, 30);
        add(searchLabel);

        // Search field
        searchField = new JTextField();
        searchField.setBounds(970, 25, 230, 30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(searchField);

        // Search button
        searchButton = createButton("Search", 1210, 25, 100, 30);
        add(searchButton);

        // Clear button
        clearButton = createButton("Clear", 1320, 25, 100, 30);
        add(clearButton);

        // Attendance sheet label
        JLabel attendanceLabel = new JLabel("Attendance Sheet");
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        attendanceLabel.setBounds(50, 100, 200, 30);
        add(attendanceLabel);

        // Attendance table
        String[] columns = {"ID", "Name", "Course", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        attendanceTable = new JTable(tableModel);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Customize table appearance
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 140, 1250, 500);
        scrollPane.getViewport().setBackground(Color.decode("#fdecec"));
        add(scrollPane);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) attendanceTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(Color.PINK);
        attendanceTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Back button
        backButton = createButton("Back", 1320, 660, 100, 30); 
        add(backButton);

        // Add action listeners
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(Color.decode("#7d0504"));
        button.setForeground(Color.WHITE);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                // Search for students in the table
                searchStudent(searchText);
            } else {
                System.out.println("Search field is empty!");
            }
        } else if (e.getSource() == clearButton) {
            // Clear the search field and reset the table
            searchField.setText("");
            resetTable();
        } else if (e.getSource() == backButton) {
            System.out.println("Returning to previous menu...");
            dispose();
        }
    }

    private void searchStudent(String searchText) {
        
        tableModel.setRowCount(0); // Clear the table first
        tableModel.addRow(new Object[]{"", "", "", ""}); // Example row
        tableModel.addRow(new Object[]{"", "", "", ""});
        System.out.println("Search performed for: " + searchText);
    }

    private void resetTable() {
        // Reset the table to its original state
        tableModel.setRowCount(0); // Clear all rows
    }

}