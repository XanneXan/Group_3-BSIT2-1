package com.mycompany.student_management_system;

import java.awt.Color;
import java.awt.Font;
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
        titleLabel.setBounds(70, 20, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.decode("#7d0504"));
        add(titleLabel);

        // Load and add the logo beside the title
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\Jasmine\\Downloads\\logo.jpg");
        Image logoImage = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setBounds(20, 20, 80, 80);
        add(logoLabel);

        // Course ComboBox
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 90, 100, 30);
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(courseLabel);

        courseComboBox = new JComboBox<>(new String[]{"Course 1", "Course 2", "Course 3"});
        courseComboBox.setBounds(150, 90, 250, 35);
        courseComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        courseComboBox.addActionListener(this);
        add(courseComboBox);

        // Date Spinner
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(1110, 90, 100, 30);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(dateLabel);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setBounds(1165, 90, 200, 35);
        dateSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        add(dateSpinner);

        // Attendance Sheet Label
        JLabel attendanceLabel = new JLabel("Attendance Sheet");
        attendanceLabel.setBounds(50, 130, 200, 30);
        attendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(attendanceLabel);

        // Attendance Table
        String[] columnNames = {"ID", "Name", "Course", "Date", "Status"};
        model = new DefaultTableModel(columnNames, 0); // Set column names
        attendanceTable = new JTable(model);
        attendanceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        attendanceTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(50, 170, 1100, 550);
        scrollPane.getViewport().setBackground(Color.decode("#fdecec"));
        add(scrollPane);

        // Adjust column widths
        attendanceTable.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        attendanceTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
        attendanceTable.getColumnModel().getColumn(2).setPreferredWidth(200); // Course
        attendanceTable.getColumnModel().getColumn(3).setPreferredWidth(200); // Date
        attendanceTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Status

        // Make column headers pink
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) attendanceTable.getTableHeader().getDefaultRenderer();
        headerRenderer.setBackground(Color.PINK);
        attendanceTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Disable editing for all cells
        attendanceTable.setDefaultEditor(Object.class, null);

        // Buttons
        presentButton = createButton("Present", 1200, 170);
        absentButton = createButton("Absent", 1200, 230);
        saveButton = createButton("Save", 1200, 290);
        editButton = createButton("Edit", 1200, 350);
        menuButton = createButton("Menu", 50, 750);

        // Add Action Listeners
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
            JOptionPane.showMessageDialog(this, "Returning to Menu");
        }
    }

    private void loadStudentsForCourse(String course) {
        model.setRowCount(0); // Clear the table
        // Dummy data: Replace with actual database query results
        if (course.equals("Course 1")) {
            model.addRow(new Object[]{"1", "Student 1", "Course 1", dateSpinner.getValue(), ""});
            model.addRow(new Object[]{"2", "Student 2", "Course 1", dateSpinner.getValue(), ""});
        } else if (course.equals("Course 2")) {
            model.addRow(new Object[]{"3", "Student 3", "Course 2", dateSpinner.getValue(), ""});
        } else if (course.equals("Course 3")) {
            model.addRow(new Object[]{"4", "Student 4", "Course 3", dateSpinner.getValue(), ""});
        }
    }

    private void markAttendance(String status) {
        if (!isEditable) {
            JOptionPane.showMessageDialog(this, "Attendance status is locked after saving. Press 'Edit' to make changes.");
            return;
        }

        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow != -1) {
            model.setValueAt(status, selectedRow, 4); // Set "Status" column
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
        isEditable = false; // After saving, set editable to false
        presentButton.setEnabled(false); // Disable the Present button
        absentButton.setEnabled(false);  // Disable the Absent button
    }

    private void toggleEditMode() {
        if (!isEditable) {
            isEditable = true; // Allow editing again
            presentButton.setEnabled(true); // Re-enable the Present button
            absentButton.setEnabled(true);  // Re-enable the Absent button
            JOptionPane.showMessageDialog(this, "Editing is now enabled. You can modify attendance.");
        } else {
            JOptionPane.showMessageDialog(this, "Attendance is already in edit mode.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Attendance_Frame().setVisible(true));
    }
}

