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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private ImageIcon logoIcon;
    private JLabel titleLabel, logoLabel, searchLabel, attendanceLabel;
    private Image scaledLogo;
    JScrollPane scrollPane;
    
    private String[] columns = {"ID", "Name", "Course", "Presents", "Absents"};
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

    public Attendance_Sheet() {
        setTitle("Student Management System");
        setExtendedState(MAXIMIZED_BOTH); // Maximized window
        setLayout(null); // Absolute positioning for components
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(30, 20, 50, 50); 
        lblLogo.setIcon(getCircularImageIcon("C:\\Users\\Jasmine\\Documents\\Group_3-BSIT2-1\\src\\main\\java\\com\\mycompany\\student_management_system\\ADD.jpg", 50));
        add(lblLogo);
        
        // Title label
        titleLabel = new JLabel("STUDENT MANAGEMENT SYSTEM");
        titleLabel.setBounds(150, 30, 600, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#7d0504"));
        add(titleLabel);
        
        attendanceLabel = new JLabel("Attendance Sheet");
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        attendanceLabel.setBounds(50, 100, 200, 30);
        add(attendanceLabel);

        // Add a logo
        logoIcon = new ImageIcon("C:\\Users\\Jasmine\\Downloads\\logo.jpg");
        scaledLogo = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(20, 20, 80, 80);
        add(logoLabel);

        // Search label
        searchLabel = new JLabel("Search Student:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        searchLabel.setBounds(750, 100, 150, 30);
        add(searchLabel);

        // Search field
        searchField = new JTextField();
        searchField.setBounds(870, 100, 230, 25);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(searchField);
        
        searchButton = new JButton("Search");
        searchButton.setBounds( 1110, 100, 70, 25);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.decode("#7d0504"));
        searchButton.setFont(new Font("Arial", Font.PLAIN, 11));
        add(searchButton);
        
        clearButton = new JButton("Clear");
        clearButton.setBounds( 1200, 100, 70, 25);
        clearButton.setForeground(Color.WHITE);
        clearButton.setBackground(Color.decode("#7d0504"));
        clearButton.setFont(new Font("Arial", Font.PLAIN, 11));
        add(clearButton);
        
        backButton = new JButton("Back");
        backButton.setBounds( 1220, 660, 100, 30); 
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.decode("#7d0504"));
        backButton.setFont(new Font("Arial", Font.PLAIN, 12));
        add(backButton);
        
        
        // Attendance table
        tableModel = new DefaultTableModel(null, columns);
        attendanceTable = new JTable(tableModel);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Customize table appearance
        scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 140, 1250, 500);
        scrollPane.getViewport().setBackground(Color.decode("#fdecec"));
        add(scrollPane);

        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) attendanceTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(Color.PINK);
        attendanceTable.getTableHeader().setDefaultRenderer(headerRenderer);


        // Add action listeners
        searchButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);
        
        loadStudentsFromDB();
    
    }

    private JButton createButtonWithIcon(String text, String imagePath, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 40);
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(125, 5, 4)); 
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            button.setIcon(icon);
        } catch (Exception e) {
            System.out.println("Error loading image: " + imagePath);
        }
        return button;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String searchText = searchField.getText().trim();
            
        } else if (e.getSource() == clearButton) {
            
        } else if (e.getSource() == backButton) {
            new Performance_Reports().setVisible(true);
            dispose();
        }
    }

    private void loadStudentsFromDB() {
       try (Connection conn = connectToDatabase();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM attendance_sheet")) {
        
        // Clear existing rows in the table model
        tableModel.setRowCount(0);

        while (rs.next()) {
            Object[] row = { rs.getString("studId"), rs.getString("name"), rs.getString("course"),
             rs.getString("presents"),  rs.getString("absents")};

            // Add the row to the table model
            tableModel.addRow(row);
         
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error loading data from the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    
}
