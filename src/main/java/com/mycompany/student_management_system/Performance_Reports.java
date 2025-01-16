package com.mycompany.student_management_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Performance_Reports extends JFrame implements ActionListener {

    private JLabel lblTitle, lblStuInfo, lblName, lblId, lblSem, lblGwa, lblSearch;
    private JTextField txtSearch;
    private JButton btnMenu, btnSearch;
    private JLabel semlabelField, namelabelField, idlabelField, semGwaField, lblLogo;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;

    private String[] tblColumn = {"Course Name", "Grade", "Remarks"};

    private String url = "jdbc:mysql://localhost:3306/student_management_system";
    private String user = "root";
    private String pass = "mysqlpasswordg3";

    public Performance_Reports() {

        setTitle("Student Management System - PERFORMANCE REPORT");
        setSize(1920, 1080);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitle = new JLabel("Student Management System");
        lblTitle.setBounds(90, 20, 600, 30);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add(lblTitle);

        ImageIcon attendanceIcn = new ImageIcon("performancel.jpg");
        Image scale = attendanceIcn.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(scale));
        lblLogo.setBounds(20, 10, 60, 60);
        add(lblLogo);

        lblStuInfo = new JLabel("Student Information");
        lblStuInfo.setBounds(180, 150, 350, 80);
        lblStuInfo.setForeground(Color.decode("#7d0504"));
        lblStuInfo.setFont(new Font("Arial", Font.BOLD, 28));
        add(lblStuInfo);

        lblName = createLabel("Name", 250, 200);
        namelabelField = createLabel("", 450, 200);

        lblId = createLabel("ID", 250, 250);
        idlabelField = createLabel("", 450, 250);

        lblSem = createLabel("Semester", 250, 300);
        semlabelField = createLabel("", 450, 300);

        lblGwa = createLabel("GWA", 890, 230);
        semGwaField = new JLabel(" ");
        semGwaField.setFont(new Font("Arial", Font.BOLD, 20));
        semGwaField.setHorizontalAlignment(SwingConstants.CENTER);
        semGwaField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        semGwaField.setBounds(1050, 240, 80, 50);
        add(semGwaField);

        lblSearch = createLabel("Search Student", 650, 70);

        txtSearch = new JTextField();
        txtSearch.setBounds(820, 95, 300, 30);
        add(txtSearch);

        btnSearch = createButton("Search", 1140, 95);
        btnMenu = createButton("Menu", 1220, 650);

        model = new DefaultTableModel(tblColumn, 0);
        studList = new JTable(model);
        studList.setRowHeight(20);
        studList.setColumnModel(columnModel());
        studList.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        studList.getTableHeader().setReorderingAllowed(false);
        studList.setDefaultEditor(Object.class, null);
        studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        pane = new JScrollPane(studList);
        pane.setBounds(160, 380, 1010, 270);
        pane.getViewport().setBackground(Color.decode("#fdecec"));
        add(pane);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 350, 80);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label);
        return label;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 30);
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(125, 5, 4));
        button.addActionListener(this);
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            studentSearch();
        } else if (e.getSource() == btnMenu) {
            new Menu_Frame().setVisible(true);
            dispose();
        }
    }

    private TableColumnModel columnModel() {
        TableColumnModel columnModel = studList.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(340);
        columnModel.getColumn(1).setPreferredWidth(340);
        columnModel.getColumn(2).setPreferredWidth(340);
        return columnModel;
    }

    private void studentSearch() {
        String searchInput = txtSearch.getText().trim();

        if (searchInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID or Name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String studentQuery = "SELECT ID, Name, semester FROM student WHERE ID = ? OR Name = ?";
            PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
            studentStmt.setString(1, searchInput);
            studentStmt.setString(2, searchInput);
            ResultSet studentRs = studentStmt.executeQuery();

            if (studentRs.next()) {
                idlabelField.setText(studentRs.getString("ID"));
                namelabelField.setText(studentRs.getString("Name"));
                semlabelField.setText(studentRs.getString("semester"));

                String gradeQuery = "SELECT gwa FROM grade WHERE studId = ?";
                PreparedStatement gradeStmt = conn.prepareStatement(gradeQuery);
                gradeStmt.setString(1, studentRs.getString("ID"));
                ResultSet gradeRs = gradeStmt.executeQuery();

                if (gradeRs.next()) {
                    String finalGrade = gradeRs.getString("gwa");
                    semGwaField.setText(finalGrade != null ? finalGrade : "N/A");
                } else {
                    semGwaField.setText("N/A");
                }

                String gradesQuery = "SELECT course1, course2, course3, course4, course5, course6, course7, course8, " +
                     "mid1, mid2, mid3, mid4, mid5, mid6, mid7, mid8, " +
                     "fin1, fin2, fin3, fin4, fin5, fin6, fin7, fin8 " +
                                        "FROM grade WHERE studId = ?";
                
                   PreparedStatement gradesStmt = conn.prepareStatement(gradesQuery);
                   gradesStmt.setString(1, studentRs.getString("ID"));
                   ResultSet gradesRs = gradesStmt.executeQuery();

                   model.setRowCount(0); // Clear the table model

                   if (gradesRs.next()) { // Assuming one row per student in the grade table
                       for (int i = 1; i <= 8; i++) {
                           String courseName = gradesRs.getString("course" + i);
                           String midValue = gradesRs.getString("mid" + i);
                           String finValue = gradesRs.getString("fin" + i);

                           // Skip rows with empty or invalid data
                           if (courseName == null || courseName.equals("--") || 
                               midValue == null || midValue.equals("--") || 
                               finValue == null || finValue.equals("--")) {
                               continue;
                           }

                           // Parse grades, defaulting to 0 if invalid
                           double mid = parseGrade(midValue);
                           double fin = parseGrade(finValue);
                           double grade = (mid + fin) / 2;

                           // Determine remarks
                           String remarks = grade == 0.0 ? "Incomplete" : grade <= 3.0 ? "Passed" : "Failed";

                           // Add the row to the table model
                           if (!courseName.isEmpty()) {
                               model.addRow(new Object[]{courseName, grade, remarks});
                           }
                       }
                   } else {
                       JOptionPane.showMessageDialog(this, "No grades found for the student.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                   }       
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseGrade(String gradeStr) {
        try {
            return Double.parseDouble(gradeStr);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
