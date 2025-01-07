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

public class Performance_Reports extends JFrame implements ActionListener {
    
    private JLabel lblTitle, lblId, lblPer;
    private JTextField txtId;
    private JButton btnMenu, btnGrades, btnGWA, btnAttendance, btnClear;
    private JTable studList;
    private JScrollPane pane;
    private DefaultTableModel model;

    // Array of column headers for the table
    private String[] tblColumn = {"Student's ID", "Student's Name", "Semester", "Term", "Course 1", "Grade", 
                                    "Course 2", "Grade", "Course 3", "Grade", "Course 4", "Course 5", 
                                    "Grade", "Course 6", "Grade", "Course 7", "Grade", "Course 8", 
                                    "Grade", "Midterm", "Finals", "GWA"};

    Performance_Reports() {
        setExtendedState(MAXIMIZED_BOTH);     
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adding a circular logo aligned to the left of the title
        JLabel lblLogo = new JLabel();
        lblLogo.setBounds(30, 20, 50, 50); // Position and size for the logo
        lblLogo.setIcon(getCircularImageIcon("C:\\Users\\Jasmine\\Documents\\Group_3-BSIT2-1\\src\\main\\java\\com\\mycompany\\student_management_system\\ADD.jpg", 50));
        add(lblLogo);

        lblTitle = new JLabel("Student Management System");
        lblTitle.setBounds(90, 30, 350, 30); // Adjusted to align next to the logo
        lblTitle.setFont(new Font("Serif", Font.BOLD, 25));
        lblTitle.setForeground(Color.decode("#7d0504"));
        add(lblTitle);

        lblPer = new JLabel("Performance Reports");
        lblPer.setBounds(850, 100, 350, 30);
        lblPer.setFont(new Font("Serif", Font.BOLD, 25));
        add(lblPer);

        lblId = new JLabel("Student's ID:");
        lblId.setBounds(50, 150, 150, 40);
        lblId.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(170, 155, 280, 30);
        add(txtId);

        // Adding images to buttons
        btnGrades = createButtonWithIcon("Grades", "path/to/grades-icon.png", 70, 490);
        add(btnGrades);

        btnAttendance = createButtonWithIcon("Attendance", "path/to/attendance-icon.png", 210, 490);
        add(btnAttendance);

        btnGWA = createButtonWithIcon("All GWA", "path/to/gwa-icon.png", 350, 490);
        add(btnGWA);

        btnMenu = createButtonWithIcon("Menu", "path/to/menu-icon.png", 145, 550);
        add(btnMenu);

        btnClear = createButtonWithIcon("Clear", "path/to/clear-icon.png", 285, 550);
        add(btnClear);

        // Table setup
        model = new DefaultTableModel(null, tblColumn);
        studList = new JTable(model);
        studList.getTableHeader().setReorderingAllowed(false);
        studList.setDefaultEditor(Object.class, null);
        studList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Adjusting column sizes
        for (int i = 0; i < studList.getColumnCount(); i++) {
            studList.getColumnModel().getColumn(i).setPreferredWidth(200);
            studList.getColumnModel().getColumn(i).setResizable(false); 
        }
        
        pane = new JScrollPane(studList);
        pane.setBounds(600, 150, 720, 450);
        pane.getViewport().setBackground(Color.LIGHT_GRAY);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(pane);

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
      btnAttendance.addActionListener(this);
   }
        btnMenu.addActionListener(this);
    }

    private JButton createButtonWithIcon(String text, String imagePath, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 120, 40);
        button.setFocusable(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(125, 5, 4)); // Updated color to #7d0504
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
        if (e.getSource() == btnMenu) {
            new Menu_Frame().setVisible(true);
            dispose();
        } else if (e.getSource()==  btnAttendance){
            new Attendance_Sheet().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        new Performance_Reports().setVisible(true);
    }
}
