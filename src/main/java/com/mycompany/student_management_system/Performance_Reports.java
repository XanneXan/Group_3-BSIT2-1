package com.mycompany.student_management_system;

import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Performance_Reports extends JFrame implements ActionListener {

    private JLabel lblTitle, lblId, lblPer;
    private JTextField txtId;
    private JButton btnMenu, btnGrades, btnGWA, btnAttendance, btnClear, btnSearch;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;

    private String[] tblColumn = {
        "Student's ID", "Student's Name", "Semester", 
        "Course 1", "Course 2", "Course 3", 
        "Course 4", "Course 5", "Course 6", "Course 7", 
        "Course 8", "Midterm", "Finals", "GWA"
    };

    public Performance_Reports() {
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Logo
        ImageIcon performanIcon = new ImageIcon("ADD.jpg");
        Image scale = performanIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon logoicon = new ImageIcon(scale);
        JLabel lblLogo = new JLabel(logoicon);
        lblLogo.setBounds(30, 20, 50, 50);
        add(lblLogo);

        // Title
        lblTitle = new JLabel("Student Management System");
        lblTitle.setBounds(90, 30, 350, 30);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add(lblTitle);

        // Performance Reports
        lblPer = new JLabel("Performance Reports");
        lblPer.setBounds(850, 100, 350, 30);
        lblPer.setFont(new Font("Serif", Font.BOLD, 25));
        add(lblPer);

        // Student ID
        lblId = new JLabel("Student's ID:");
        lblId.setBounds(50, 150, 150, 40);
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblId);

        // Student ID TextField
        txtId = new JTextField();
        txtId.setBounds(170, 155, 280, 30);
        add(txtId);

        // Search Button
        btnSearch = new JButton("Search");
        btnSearch.setBounds(460, 155, 120, 30);
        btnSearch.setFocusable(false);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.PLAIN, 10));
        btnSearch.setBackground(new Color(125, 5, 4));
        add(btnSearch);

        // Grades Button
        btnGrades = new JButton("Grades");
        btnGrades.setBounds(70, 490, 120, 40);
        btnGrades.setFocusable(false);
        btnGrades.setForeground(Color.WHITE);
        btnGrades.setFont(new Font("Arial", Font.PLAIN, 10));
        btnGrades.setBackground(new Color(125, 5, 4));
        add(btnGrades);

        // Attendance Button
        btnAttendance = new JButton("Attendance");
        btnAttendance.setBounds(210, 490, 120, 40);
        btnAttendance.setFocusable(false);
        btnAttendance.setForeground(Color.WHITE);
        btnAttendance.setFont(new Font("Arial", Font.PLAIN, 10));
        btnAttendance.setBackground(new Color(125, 5, 4));
        add(btnAttendance);

        // GWA Button
        btnGWA = new JButton("All GWA");
        btnGWA.setBounds(350, 490, 120, 40);
        btnGWA.setFocusable(false);
        btnGWA.setForeground(Color.WHITE);
        btnGWA.setFont(new Font("Arial", Font.PLAIN, 10));
        btnGWA.setBackground(new Color(125, 5, 4));
        add(btnGWA);

        // Menu Button
        btnMenu = new JButton("Menu");
        btnMenu.setBounds(145, 550, 120, 40);
        btnMenu.setFocusable(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFont(new Font("Arial", Font.PLAIN, 10));
        btnMenu.setBackground(new Color(125, 5, 4));
        add(btnMenu);

        // Clear Button
        btnClear = new JButton("Clear");
        btnClear.setBounds(285, 550, 120, 40);
        btnClear.setFocusable(false);
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.PLAIN, 10));
        btnClear.setBackground(new Color(125, 5, 4));
        add(btnClear);

        // Table Model Setup
        model = new DefaultTableModel(null, tblColumn);
        studList = new JTable(model);
        studList.getTableHeader().setReorderingAllowed(false);
        studList.setDefaultEditor(Object.class, null);
        studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < tblColumn.length; i++) {
            studList.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        pane = new JScrollPane(studList);
        pane.setBounds(600, 150, 720, 450);
        pane.getViewport().setBackground(Color.LIGHT_GRAY);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(pane);

        btnMenu.addActionListener(this);
        btnSearch.addActionListener(this);
    }

    private int binarySearch(String id) {
        int target;
        try {
            target = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a numerical ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        int rowCount = model.getRowCount();
        if (rowCount == 0) {
            JOptionPane.showMessageDialog(this, "The table is empty. There are no students to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        int[] studentIds = new int[rowCount];
        int[] originalIndices = new int[rowCount];

        for (int i = 0; i < rowCount; i++) {
            try {
                studentIds[i] = Integer.parseInt(model.getValueAt(i, 0).toString());
                originalIndices[i] = i;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Non-numeric ID detected in the table.", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }

        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = i + 1; j < rowCount; j++) {
                if (studentIds[i] > studentIds[j]) {
                    int tempId = studentIds[i];
                    studentIds[i] = studentIds[j];
                    studentIds[j] = tempId;

                    int tempIndex = originalIndices[i];
                    originalIndices[i] = originalIndices[j];
                    originalIndices[j] = tempIndex;
                }
            }
        }

        int left = 0, right = studentIds.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (studentIds[mid] == target) {
                return originalIndices[mid];
            } else if (studentIds[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnMenu) {
            new Menu_Frame().setVisible(true);
            dispose();
        } else if (e.getSource() == btnSearch) {
            String studentId = txtId.getText().trim();
            if (studentId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Student ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int resultIndex = binarySearch(studentId);

            if (resultIndex != -1) {
                JOptionPane.showMessageDialog(this, "Student found at table row: " + (resultIndex + 1), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                studList.setRowSelectionInterval(resultIndex, resultIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found.", "Search Result", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

    
