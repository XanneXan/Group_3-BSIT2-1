package com.mycompany.student_management_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Performance_Reports extends JFrame implements ActionListener {

    private JLabel lblTitle, lblId, lblPer;
    private JTextField txtId;
    private JButton btnMenu, btnGrades, btnGWA, btnAttendance, btnClear, btnSearch, btnSortNames, btnSortCourses;
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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        btnSearch.addActionListener(this);
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
        btnMenu.addActionListener(this);
        add(btnMenu);

        // Clear Button
        btnClear = new JButton("Clear");
        btnClear.setBounds(285, 550, 120, 40);
        btnClear.setFocusable(false);
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.PLAIN, 10));
        btnClear.setBackground(new Color(125, 5, 4));
        add(btnClear);

        // Sort by Names Button
        btnSortNames = new JButton("Sort by Names");
        btnSortNames.setBounds(145, 440, 120, 40);
        btnSortNames.setFocusable(false);
        btnSortNames.setForeground(Color.WHITE);
        btnSortNames.setFont(new Font("Arial", Font.PLAIN, 10));
        btnSortNames.setBackground(new Color(125, 5, 4));
        btnSortNames.addActionListener(this);
        add(btnSortNames);

        // Sort by Courses Button
        btnSortCourses = new JButton("Sort by Courses");
        btnSortCourses.setBounds(285, 440, 120, 40);
        btnSortCourses.setFocusable(false);
        btnSortCourses.setForeground(Color.WHITE);
        btnSortCourses.setFont(new Font("Arial", Font.PLAIN, 10));
        btnSortCourses.setBackground(new Color(125, 5, 4));
        btnSortCourses.addActionListener(this);
        add(btnSortCourses);

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

    }

    private void sortTable(int columnIndex, boolean ascending) {
        int rowCount = model.getRowCount();
        String[][] tableData = new String[rowCount][model.getColumnCount()];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                tableData[i][j] = model.getValueAt(i, j).toString();
            }
        }

        Arrays.sort(tableData, (a, b) -> ascending
            ? a[columnIndex].compareToIgnoreCase(b[columnIndex])
            : b[columnIndex].compareToIgnoreCase(a[columnIndex]));

        model.setRowCount(0);
        for (String[] row : tableData) {
            model.addRow(row);
        }
    }

    private ImageIcon getCircularImageIcon(String imagePath, int size) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImage circularImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = circularImage.createGraphics();
            g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, size, size));
            g2.drawImage(image, 0, 0, size, size, null);
            g2.dispose();

            return new ImageIcon(circularImage);
        } catch (Exception e) {
            System.out.println("Error loading circular image: " + e.getMessage());
            return null;
        }


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

    
