package com.mycompany.student_management_system;

import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Arrays;
import javax.swing.table.TableColumn;

public class Performance_Reports extends JFrame implements ActionListener {

    private JLabel lblTitle, lblId, lblPer;
    private JTextField txtId;
    private JButton btnMenu, btnGrades, btnGWA, btnAttendance, btnClear, btnSearch;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;

    
    private String[] tblColumn = {
        "Student's ID", "Student's Name", "Semester", 
        "Course 1",  "Course 2","Course 3", 
        "Course 4", "Course 5", "Course 6", "Course 7", 
         "Course 8", "Midterm", "Finals", "GWA"
    };
    // Array of column headers for the table
    private String[] tblColumn = {"Student's ID", "Student's Name", "Semester", "Course 1", 
                                    "Course 2",  "Course 3",  "Course 4", "Course 5",
                                     "Course 6", "Course 7",  "Course 8", 
                                    "Midterm", "Finals", "GWA"};

    public Performance_Reports() {
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Logo Setup
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(30, 20, 50, 50);
        lblLogo.setIcon(getCircularImageIcon(
            "C:\\Users\\Jasmine\\Documents\\Group_3-BSIT2-1\\src\\main\\java\\com\\mycompany\\student_management_system\\ADD.jpg", 50)
        );
        add(lblLogo);

        // Title Setup
        lblTitle = new JLabel("Student Management System");
        lblTitle.setBounds(90, 30, 350, 30);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add(lblTitle);

        // Performance Reports Label
        lblPer = new JLabel("Performance Reports");
        lblPer.setBounds(850, 100, 350, 30);
        lblPer.setFont(new Font("Serif", Font.BOLD, 25));
        add(lblPer);

        // Student ID Label
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

        // Ensure we have the correct number of columns
        for (int i = 0; i < tblColumn.length; i++) {
            studList.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        
        studList.getColumnModel().getColumn(0).setPreferredWidth(100); // Student's ID
        studList.getColumnModel().getColumn(1).setPreferredWidth(200); // Student's Name
        studList.getColumnModel().getColumn(2).setPreferredWidth(70);  // Semester
        studList.getColumnModel().getColumn(3).setPreferredWidth(70);  // Term
        

        
        pane = new JScrollPane(studList);
        pane.setBounds(600, 150, 720, 450);
        pane.getViewport().setBackground(Color.LIGHT_GRAY);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(pane);

        
        btnMenu.addActionListener(this);
        btnAttendance.addActionListener(this);
    }

    
    private JButton createButtonWithIcon(String text, String imagePath, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
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

    
    private int binarySearch(String id) {
        int rowCount = model.getRowCount();
        String[] studentIds = new String[rowCount];

        for (int i = 0; i < rowCount; i++) {
            studentIds[i] = model.getValueAt(i, 0).toString();
        }

        Arrays.sort(studentIds);

        int low = 0, high = studentIds.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = studentIds[mid].compareTo(id);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
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
                JOptionPane.showMessageDialog(this, "Student found at index: " + resultIndex, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found.", "Search Result", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
