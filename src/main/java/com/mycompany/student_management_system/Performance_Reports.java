
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

public class Performance_Reports extends JFrame implements ActionListener{
    
    private JLabel lblTitle, lblStuInfo, lblName, lblSep, lblId, lblSem, lblGwa, lblSearch;
    private JTextField txtSearch;
    private JButton btnMenu, btnSearch;
    private JLabel picHolder, semlabelField, namelabelField, idlabelField, semGwaField, lblLogo;
    private JTable studList;
    private JPanel container, boxPanel;
    private JScrollPane pane;
    private DefaultTableModel model;
    
    private String[] tblColumn = {"Course Name", "Grade", "Remarks"};
    
    //for database
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
    
    public Performance_Reports() {
        
        //Main Frame
        setTitle("Student Management System - Performance Report");
        setSize(1920, 1080);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
        lblTitle = new JLabel ("Student Management System");
        lblTitle.setBounds(90, 20, 600, 30);
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add (lblTitle);

        ImageIcon attendanceIcn = new ImageIcon("performancel.jpg");
        Image scale = attendanceIcn.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon logoicon = new ImageIcon(scale);
        lblLogo = new JLabel(logoicon);
        lblLogo.setBounds(20, 10, 60, 60);
        add(lblLogo);

        //Student Information
        lblStuInfo = new JLabel("Student Information");
        lblStuInfo.setBounds(180, 200, 350, 80);
        lblStuInfo.setForeground(Color.decode("#7d0504"));
        lblStuInfo.setFont(new Font("Arial", Font.BOLD, 28));
        add(lblStuInfo);
        
        //Add labels
        lblName = new JLabel("Name");
        lblName.setBounds(250, 250, 350, 80);
        lblName.setForeground(Color.BLACK);
        lblName.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblName);
        
        lblSep = new JLabel(" :");
        lblSep.setBounds(400, 250, 350, 80);
        lblSep.setForeground(Color.BLACK);
        lblSep.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblSep);
        
        namelabelField = new JLabel(" ");
        namelabelField.setBounds(450, 250, 300, 80);
        namelabelField.setFont(new Font("Arial", Font.BOLD, 20));
        add(namelabelField);
        
        lblId = new JLabel("ID");
        lblId.setBounds(250, 300, 350, 80);
        lblId.setForeground(Color.BLACK);
        lblId.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblId);

        lblSep = new JLabel(" :");
        lblSep.setBounds(400, 300, 350, 80);
        lblSep.setForeground(Color.BLACK);
        lblSep.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblSep);

        idlabelField = new JLabel(" ");
        idlabelField.setBounds(450, 300, 300, 80);
        idlabelField.setFont(new Font("Arial", Font.BOLD, 20));
        add(idlabelField);

        lblSem = new JLabel("Semester");
        lblSem.setBounds(250, 350, 350, 80);
        lblSem.setForeground(Color.BLACK);
        lblSem.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblSem);
        
        lblSep = new JLabel(" :");
        lblSep.setBounds(400, 350, 350, 80);
        lblSep.setForeground(Color.BLACK);
        lblSep.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblSep);
        
        semlabelField = new JLabel(" ");
        semlabelField.setBounds(450, 350, 300, 80);
        semlabelField.setFont(new Font("Arial", Font.BOLD, 20));
        add(semlabelField);
        
        lblGwa = new JLabel("GWA");
        lblGwa.setBounds(890, 280, 350, 80);
        lblGwa.setForeground(Color.BLACK);
        lblGwa.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblGwa);
        
        lblSep = new JLabel(" :");
        lblSep.setBounds(1010, 280, 350, 80);
        lblSep.setForeground(Color.BLACK);
        lblSep.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblSep);

        semGwaField = new JLabel(" ");
        semGwaField.setFont(new Font("Arial", Font.BOLD, 20));
        semGwaField.setHorizontalAlignment(SwingConstants.CENTER); 

        boxPanel = new JPanel();
        boxPanel.setBackground(Color.LIGHT_GRAY);
        boxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        boxPanel.setBounds(0, 0, 80, 50);
        boxPanel.setLayout(new BorderLayout()); 
        boxPanel.add(semGwaField, BorderLayout.CENTER); 

        container = new JPanel();
        container.setBounds(1050, 290, 80, 50);
        container.setLayout(null); 
        container.add(boxPanel);
        add(container);

        lblSearch = new JLabel("Search Student");
        lblSearch.setBounds(650, 120, 350, 80);
        lblSearch.setForeground(Color.BLACK);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblSearch);
        
        //add textfield
        txtSearch = new JTextField();
        txtSearch.setBounds(820, 145, 300, 30);
        add(txtSearch);
        
        //add Buttons and action listene to each
        btnSearch = new JButton("Search");
        btnSearch.setBounds(1140, 145, 150, 30);
        btnSearch.setFocusable(false);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBackground(new Color(125,5,4));
        btnSearch.addActionListener(this);
        add(btnSearch);
        
        btnMenu = new JButton("Menu");
        btnMenu.setBounds(1260, 700, 100, 30);
        btnMenu.setFocusable(false);
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setBackground(new Color(125,5,4));
        btnMenu.addActionListener(this);
        add(btnMenu);
        
        // Table Model Setup
        model = new DefaultTableModel(tblColumn, 0);
        studList = new JTable(model);
        studList.setRowHeight(20);
        studList.setColumnModel(columnModel());
        studList.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        studList.getTableHeader().setReorderingAllowed(false);
        studList.setDefaultEditor(Object.class, null);
        studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        pane = new JScrollPane(studList);
        pane.setBounds(160, 430, 1010, 300);
        pane.getViewport().setBackground(Color.lightGray);
        add(pane);

        //add action listeners
        btnSearch.addActionListener(this);
        btnMenu.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSearch) {
            studentSearch();
            
        }else if(e.getSource() == btnMenu) {
            new Menu_Frame().setVisible(true);
            dispose();
            
        }        
    }
    
    //for table column formats
     private TableColumnModel columnModel(){
        TableColumnModel columnModel = studList.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(340);
        columnModel.getColumn(1).setPreferredWidth(340);
        columnModel.getColumn(2).setPreferredWidth(340);
        return columnModel;
        
    }
    
    // Search students ID or Name in the database then load their information 
    private void studentSearch() {
        String searchInput = txtSearch.getText().trim();

        if (searchInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student ID or Name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try(Connection conn = connectToDatabase()) {
            String studentQuery = "SELECT ID, Name, semester FROM student WHERE ID = ? OR Name = ?";
            PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
            studentStmt.setString(1, searchInput);
            studentStmt.setString(2, searchInput);  
            ResultSet studentRs = studentStmt.executeQuery();

            if (studentRs.next()) {
                idlabelField.setText(studentRs.getString("ID"));
                namelabelField.setText(studentRs.getString("Name"));
                String semester = studentRs.getString("semester");
                semlabelField.setText(semester);
                
                String gradeQuery = "SELECT final FROM grade WHERE studId = ?";  // Corrected query to fetch final grade
                PreparedStatement gradeStmt = conn.prepareStatement(gradeQuery);
                gradeStmt.setString(1, studentRs.getString("ID"));

                ResultSet gradeRs = gradeStmt.executeQuery();

                if (gradeRs.next()) {
                    String gwa = gradeRs.getString("final");
                    semGwaField.setText(gwa);  // Set the GWA value in the field
                } else {
                    semGwaField.setText("No Grade");
                }

                //get courses and grades
                String gradesQuery = "SELECT course1, course2, course3, course4, course5, course6, course7, course8, " +
                                     "mid1, fin1, mid2, fin2, mid3, fin3, mid4, fin4, mid5, fin5, mid6, fin6, mid7, fin7, mid8, fin8 " +
                                     "FROM grade " +
                                     "WHERE studId = ?";

                PreparedStatement gradesStmt = conn.prepareStatement(gradesQuery);
                gradesStmt.setString(1, studentRs.getString("ID"));
                ResultSet gradesRs = gradesStmt.executeQuery();

                model.setRowCount(0); 

                if (gradesRs.next()) {
                    for (int i = 1; i <= 8; i++) {
                        String courseName = gradesRs.getString("course" + i);
                        String midValue = gradesRs.getString("mid" + i);
                        String finValue = gradesRs.getString("fin" + i);

                        //Skip if courseName, midterms, or finals is "--" or vacant
                        if ("--".equals(courseName) || "--".equals(midValue) || "--".equals(finValue)) {
                            continue;
                        }
                        //parse mid and finals, then if invalid input turns into default 0
                        double mid = midValue.equals("--") ? 0 : Double.parseDouble(midValue);
                        double fin = finValue.equals("--") ? 0 : Double.parseDouble(finValue);

                        double grade = (mid + fin) / 2;

                        if (courseName != null && !courseName.isEmpty()) {
                            String remarks = grade <= 3.00 ? "Passed" : "Failed";
                            
                            Object[] row = {courseName, grade, remarks};
                            
                            model.addRow(row);
                            
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No grades found for the student.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    
                }
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error retrieving data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid grade format: " + e.getMessage(), "Data Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }

}
