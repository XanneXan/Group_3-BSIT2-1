package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Attendance_Frame extends JFrame implements ActionListener {
    private JComboBox<String> courseComboBox;
    private JSpinner dateSpinner;
    private JTable attendanceTable;
    private JButton presentButton, absentButton, saveButton, editButton, menuButton;
    private DefaultTableModel model;
    private boolean isEditable = true; // To track if the status is editable

    public Attendance_Frame() {
        setTitle("Student Management System");
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Title with logo
        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT");
        titleLabel.setBounds(100, 30, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#7d0504"));
        add(titleLabel);

        // Load and scale the logo
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Jasmine\\Downloads\\logo.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(20, 20, 80, 80);
        add(logoLabel);

        // Course ComboBox for selecting the course
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 110, 100, 30);
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(courseLabel);

        courseComboBox = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        courseComboBox.setBounds(150, 110, 250, 35);
        courseComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        courseComboBox.addActionListener(this);
        add(courseComboBox);

        // Date Spinner for selecting the attendance date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(1110, 110, 100, 30);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(dateLabel);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(1165, 110, 200, 35);
        dateSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        add(dateSpinner);

        // Attendance Sheet title
        JLabel attendanceLabel = new JLabel("Attendance Sheet");
        attendanceLabel.setBounds(50, 150, 200, 30);
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(attendanceLabel);

        // Setting up the table for displaying attendance data
        String[] columnNames = {"ID", "Name", "Course", "Date", "Status"};
        model = new DefaultTableModel(columnNames, 0);
        attendanceTable = new JTable(model);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 190, 1100, 550);
        scrollPane.getViewport().setBackground(Color.decode("#fdecec"));
        add(scrollPane);

        // Set column widths
        attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        attendanceTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        attendanceTable.getColumnModel().getColumn(4).setPreferredWidth(150);

        // Customize table header color
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) attendanceTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(Color.PINK);
        attendanceTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Disable editing for all cells
        attendanceTable.setDefaultEditor(Object.class, null);

        // Create and add buttons for various actions
        presentButton = createButton("Present", 1200, 190);
        absentButton = createButton("Absent", 1200, 250);
        saveButton = createButton("Save", 1200, 310);
        editButton = createButton("Edit", 1200, 370);
        menuButton = createButton("Menu", 50, 750);

        // Add action listeners to buttons
        presentButton.addActionListener(this);
        absentButton.addActionListener(this);
        saveButton.addActionListener(this);
        editButton.addActionListener(this);
        menuButton.addActionListener(this);
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 50);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(Color.decode("#7d0504"));
        button.setForeground(Color.WHITE);
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courseComboBox) {
            String selectedCourse = (String) courseComboBox.getSelectedItem();
            loadStudentsForCourse(selectedCourse);
        } else if (e.getSource() == presentButton) {
            markAttendance("Present");
        } else if (e.getSource() == absentButton) {
            markAttendance("Absent");
        } else if (e.getSource() == saveButton) {
            saveAttendance();
        } else if (e.getSource() == editButton) {
            toggleEditMode();
        } else if (e.getSource() == menuButton) {
            

            dispose();
            Student_Management_System.main(new String[]{});
        }
    }

    private void loadStudentsForCourse(String course) {
        model.setRowCount(0); 
        if (course.equals("Course 1")) {
            model.addRow(new Object[]{"", "", "", dateSpinner.getValue(), ""});
            model.addRow(new Object[]{"", "", "", dateSpinner.getValue(), ""});
        } else if (course.equals("")) {
            model.addRow(new Object[]{"", "", "", dateSpinner.getValue(), ""});
            model.addRow(new Object[]{"", "", "", dateSpinner.getValue(), ""});
        } else if (course.equals("")) {
            model.addRow(new Object[]{"", "", "", dateSpinner.getValue(), ""});
            model.addRow(new Object[]{"", "", "", dateSpinner.getValue(), ""});
        }
    }

    private void markAttendance(String status) {
        if (!isEditable) {
            JOptionPane.showMessageDialog(this, "Attendance status is not editable after saving. Press 'Edit' to make changes.");
            return;
        }

        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow != -1) {
            model.setValueAt(status, selectedRow, 4); 
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to mark attendance.");
        }
    }

    private void saveAttendance() {
        for (int i = 0; i < model.getRowCount(); i++) {
            String status = (String) model.getValueAt(i, 4);
            if (status == null || status.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All students must have attendance marked before saving.");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Attendance saved successfully.");
        isEditable = false;
        presentButton.setEnabled(false);
        absentButton.setEnabled(false);
    }

    private void toggleEditMode() {
        if (!isEditable) {
            isEditable = true;
            presentButton.setEnabled(true);
            absentButton.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Editing is now enabled. You can modify attendance.");
        } else {
            JOptionPane.showMessageDialog(this, "Attendance is already in edit mode.");
        }
    }
}