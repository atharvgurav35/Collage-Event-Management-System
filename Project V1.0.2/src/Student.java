import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student {
    private Connection cn;
    public Student(Connection con) {
        cn = con;
        JFrame frame = new JFrame("Student");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel StudentDetailsPanel = new JPanel();
        StudentDetailsPanel.setLayout(null); // Set null layout for custom positioning
        StudentDetailsPanel.setBounds(550, 100, 550, 550);  // Adjust panel size and position
        StudentDetailsPanel.setBorder(new LineBorder(Color.BLACK, 2));

        int x = 700;
        int y = 130;

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        JMenuItem studentItem = new JMenuItem("Student");
        JMenuItem Home = new JMenuItem("Home");
        JMenuItem eventItem = new JMenuItem("Event");

        // Adding menu items to the menu
        menu.add(studentItem);
        menu.add(Home);
        menu.add(eventItem);
        Home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Home();
            }
        });

        studentItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Student(cn);
            }
        });

        eventItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Event(cn);
            }
        });


        JMenu studentReportMenu = new JMenu("Generate Report of Student");
        menuBar.add(studentReportMenu);

        JMenuItem byName = new JMenuItem("By Name");
        studentReportMenu.add(byName);

        JMenuItem byRno = new JMenuItem("By Roll No.");
        studentReportMenu.add(byRno);

        JMenuItem allStud = new JMenuItem("All Student.");
        studentReportMenu.add(allStud);

        frame.setJMenuBar(menuBar);

        JLabel Student = new JLabel("Student Details");
        Student.setFont(new Font("Arial", Font.BOLD, 20));
        Student.setBounds(x, y, 200, 25);
        frame.add(Student);

        x = 600;
        y = 200;
        JLabel labelRollNo = new JLabel("Roll No:");
        labelRollNo.setBounds(x, y, 100, 25);
        frame.add(labelRollNo);
        JTextField fieldRollNo = new JTextField();
        fieldRollNo.setBounds(x + 100, y, 150, 25);
        frame.add(fieldRollNo);

        y += 40;
        JLabel labelStudentName = new JLabel("Name:");
        labelStudentName.setBounds(x, y, 100, 25);
        frame.add(labelStudentName);
        JTextField fieldStudentName = new JTextField();
        fieldStudentName.setBounds(x + 100, y, 150, 25);
        frame.add(fieldStudentName);

        JButton SSBI = new JButton("Search Student by Roll No");
        SSBI.setBounds(x + 280, y - 40, 200, 25);
        frame.add(SSBI);

        JButton SSBN = new JButton("Search Student by Name");
        SSBN.setBounds(x + 280, y, 200, 25);
        frame.add(SSBN);

        y += 40;
        JLabel labelPhone = new JLabel("Phone:");
        labelPhone.setBounds(x, y, 100, 25);
        frame.add(labelPhone);
        JTextField fieldPhone = new JTextField();
        fieldPhone.setBounds(x + 100, y, 150, 25);
        frame.add(fieldPhone);

        y += 40;
        JLabel labelClass = new JLabel("Class:");
        labelClass.setBounds(x, y, 100, 25);
        frame.add(labelClass);
        JTextField fieldClass = new JTextField();
        fieldClass.setBounds(x + 100, y, 150, 25);
        frame.add(fieldClass);

        y += 40;
        JLabel labelAdmissionYear = new JLabel("Admission Year:");
        labelAdmissionYear.setBounds(x, y, 100, 25);
        frame.add(labelAdmissionYear);
        JTextField fieldAdmissionYear = new JTextField();
        fieldAdmissionYear.setBounds(x + 100, y, 150, 25);
        frame.add(fieldAdmissionYear);

        y += 40;
        JLabel label = new JLabel("Choose Event:");
        label.setBounds(x, y, 120, 25);
        frame.add(label);

        JComboBox<String> eventDropdown = new JComboBox<>();
        eventDropdown.setBounds(x + 100, y, 150, 25);
        eventDropdown.addItem("Select Event");
        frame.add(eventDropdown);

        y += 40;
        JButton btnClr2 = new JButton("Clear");
        btnClr2.setBounds(x, y + 30, 100, 30);
        frame.add(btnClr2);

        JButton btnAdd2 = new JButton("Add");
        btnAdd2.setBounds(x + 140, y + 30, 100, 30);
        frame.add(btnAdd2);

        y += 45;
        JButton btnUpdate2 = new JButton("Update");
        btnUpdate2.setBounds(x, y + 30, 100, 30);
        frame.add(btnUpdate2);

        JButton btnDelete2 = new JButton("Delete");
        btnDelete2.setBounds(x + 140, 30 + y, 100, 30);
        frame.add(btnDelete2);

        JButton VAS = new JButton("View All Students");
        VAS.setBounds(600, 500 + 60, 200, 30);
        frame.add(VAS);
        frame.add(StudentDetailsPanel);

        fieldRollNo.addActionListener(e -> fieldStudentName.requestFocus());
        fieldStudentName.addActionListener(e -> fieldPhone.requestFocus());
        fieldPhone.addActionListener(e -> fieldClass.requestFocus());
        fieldClass.addActionListener(e -> fieldAdmissionYear.requestFocus());
        fieldAdmissionYear.addActionListener(e -> fieldRollNo.requestFocus());

        try
        {
            eventDropdown.removeAllItems();
            eventDropdown.addItem("Select Event");


            String query = "SELECT name FROM Event";

            PreparedStatement stmt = cn.prepareStatement(query);
            ResultSet trs = stmt.executeQuery();

            // Add event names to JComboBox
            while (trs.next()) {
                eventDropdown.addItem(trs.getString("name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }

        byName.addActionListener(e -> {
            // Create a panel for student name input
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));

            // Label
            JLabel nameLabel = new JLabel("Enter Student Name:");
            namePanel.add(nameLabel);

            // Student Name JTextField
            JTextField studentNameField = new JTextField(10);
            namePanel.add(studentNameField);

            // Show input dialog
            int option = JOptionPane.showConfirmDialog(null, namePanel, "Search Student by Name", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                // Get student name from text field
                String studentNameText = studentNameField.getText().trim();

                // Validate input
                if (studentNameText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid student name!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Database Query to Fetch Student by Name
                    String queryStudent = "SELECT * FROM student WHERE name LIKE ?";
                    PreparedStatement pst = cn.prepareStatement(queryStudent);
                    pst.setString(1, "%" + studentNameText + "%"); // Allow partial match
                    ResultSet rsStudent = pst.executeQuery();

                    // JTable Model for Student Details
                    DefaultTableModel studentModel = new DefaultTableModel();
                    studentModel.setColumnIdentifiers(new String[]{"Roll No", "Name", "Phone", "Class", "Addmission Year"});

                    boolean studentFound = false;
                    int studentRno = -1;
                    while (rsStudent.next()) {
                        studentRno = rsStudent.getInt(1);
                        studentModel.addRow(new Object[]{rsStudent.getInt(1), rsStudent.getString(2), rsStudent.getString(3), rsStudent.getString(4), rsStudent.getInt(5)});
                        studentFound = true;
                    }

                    if (!studentFound) {
                        JOptionPane.showMessageDialog(null, "No student found with this name!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Database Query to Fetch Events Attended by Student
                    String queryEvents = "SELECT e.id, e.name, e.category, e.host, e.organizer, e.date, e.time, e.venue " +
                            "FROM event e " +
                            "JOIN student_event es ON e.id = es.event_id " +
                            "WHERE es.student_rno = ?";
                    pst = cn.prepareStatement(queryEvents);
                    pst.setInt(1, studentRno);
                    ResultSet rsEvents = pst.executeQuery();

                    // JTable Model for Event Details
                    DefaultTableModel eventModel = new DefaultTableModel();
                    eventModel.setColumnIdentifiers(new String[]{"Event ID", "Event Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"});

                    boolean eventsFound = false;
                    while (rsEvents.next()) {
                        eventModel.addRow(new Object[]{rsEvents.getInt(1), rsEvents.getString(2), rsEvents.getString(3), rsEvents.getString(4), rsEvents.getString(5), rsEvents.getString(6), rsEvents.getString(7), rsEvents.getString(8)});
                        eventsFound = true;
                    }

                    // Create JTable for Student Details
                    JTable studentTable = new JTable(studentModel);

                    // Create JTable for Event Details
                    JTable eventTable = new JTable(eventModel);

                    // Generate report
                    new generateReport(studentTable, eventTable, eventsFound,"Event");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex, "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        allStud.addActionListener(e -> {
            try {
                // Database Query to Fetch All Students
                String queryStudents = "SELECT * FROM student";
                PreparedStatement pst = cn.prepareStatement(queryStudents);
                ResultSet rsStudents = pst.executeQuery();

                // JTable Model for Student Details
                DefaultTableModel studentModel = new DefaultTableModel();
                studentModel.setColumnIdentifiers(new String[]{"Roll No", "Name", "Phone", "Class", "Admission Year"});

                boolean studentsFound = false;
                while (rsStudents.next()) {
                    studentModel.addRow(new Object[]{
                            rsStudents.getInt(1),
                            rsStudents.getString(2),
                            rsStudents.getString(3),
                            rsStudents.getString(4),
                            rsStudents.getInt(5)
                    });
                    studentsFound = true;
                }

                if (!studentsFound) {
                    JOptionPane.showMessageDialog(null, "No students found!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Create JTable for Student Details
                JTable studentTable = new JTable(studentModel);

                // Pass the studentTable to constructor
                new generateReport(studentTable,"All Student");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex, "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        byRno.addActionListener(e -> {
            // Create a panel for student roll number input
            JPanel rnoPanel = new JPanel();
            rnoPanel.setLayout(new BoxLayout(rnoPanel, BoxLayout.Y_AXIS));

            // Label
            JLabel rnoLabel = new JLabel("Enter Student Roll No:");
            rnoPanel.add(rnoLabel);

            // Student Roll Number JTextField
            JTextField studentRnoField = new JTextField(10);
            rnoPanel.add(studentRnoField);

            // Show input dialog
            int option = JOptionPane.showConfirmDialog(null, rnoPanel, "Search Student by Roll No", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                // Get student roll number from text field
                String studentRnoText = studentRnoField.getText().trim();

                // Validate input
                if (studentRnoText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid student roll number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Parse roll number as an integer
                    int studentRno = Integer.parseInt(studentRnoText);

                    // Database Query to Fetch Student by Roll Number
                    String queryStudent = "SELECT * FROM student WHERE rno = ?";
                    PreparedStatement pst = cn.prepareStatement(queryStudent);
                    pst.setInt(1, studentRno);
                    ResultSet rsStudent = pst.executeQuery();

                    // JTable Model for Student Details
                    DefaultTableModel studentModel = new DefaultTableModel();
                    studentModel.setColumnIdentifiers(new String[]{"Roll No", "Name", "Phone", "Class", "Admission Year"});

                    boolean studentFound = false;
                    while (rsStudent.next()) {
                        studentModel.addRow(new Object[]{rsStudent.getInt(1), rsStudent.getString(2), rsStudent.getString(3), rsStudent.getString(4), rsStudent.getInt(5)});
                        studentFound = true;
                    }

                    if (!studentFound) {
                        JOptionPane.showMessageDialog(null, "No student found with this roll number!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Database Query to Fetch Events Attended by Student
                    String queryEvents = "SELECT e.id, e.name, e.category, e.host, e.organizer, e.date, e.time, e.venue " +
                            "FROM event e " +
                            "JOIN student_event es ON e.id = es.event_id " +
                            "WHERE es.student_rno = ?";
                    pst = cn.prepareStatement(queryEvents);
                    pst.setInt(1, studentRno);
                    ResultSet rsEvents = pst.executeQuery();

                    // JTable Model for Event Details
                    DefaultTableModel eventModel = new DefaultTableModel();
                    eventModel.setColumnIdentifiers(new String[]{"Event ID", "Event Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"});

                    boolean eventsFound = false;
                    while (rsEvents.next()) {
                        eventModel.addRow(new Object[]{rsEvents.getInt(1), rsEvents.getString(2), rsEvents.getString(3), rsEvents.getString(4), rsEvents.getString(5), rsEvents.getString(6), rsEvents.getString(7), rsEvents.getString(8)});
                        eventsFound = true;
                    }

                    // Create JTable for Student Details
                    JTable studentTable = new JTable(studentModel);

                    // Create JTable for Event Details
                    JTable eventTable = new JTable(eventModel);

                    // Generate report
                    new generateReport(studentTable, eventTable, eventsFound, "Event");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric roll number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex, "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        SSBN.addActionListener(e -> {
            try {
                String name = JOptionPane.showInputDialog("Enter Student Name:");
                if (name != null && !name.trim().isEmpty()) {
                    String sql = "SELECT * FROM student WHERE name like ?";
                    PreparedStatement pstmt = cn.prepareStatement(sql);
                    pstmt.setString(1,"%"+ name+"%");
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Student Found: \n " +rs.getInt(1) + " ) "+ rs.getString("name"));

                    } else {
                        JOptionPane.showMessageDialog(frame, "Student Not Found");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        SSBI.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ROll Number :"));
                if (id >=0) {
                    String sql = "SELECT * FROM student WHERE rno = ?";
                    PreparedStatement pstmt = cn.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(frame, "Student Found: \n " +rs.getInt(1) + " ) "+ rs.getString("name"));
                    } else {
                        JOptionPane.showMessageDialog(frame, "Student Not Found");
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        fieldPhone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = fieldPhone.getText();

                // Allow only numbers and block alphabets & special characters
                if (!Character.isDigit(c) || text.length() >= 10) {
                    e.consume(); // Block invalid input
                }
            }


        });
        fieldRollNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = fieldRollNo.getText();

                // Allow only numbers and block alphabets & special characters
                if (!Character.isDigit(c) || text.length() >= 4) {
                    e.consume(); // Block invalid input
                }
            }


        });

        btnAdd2.addActionListener(e -> {
            try {
                int id = Integer.parseInt(fieldRollNo.getText().trim());
                String name = fieldStudentName.getText().trim();
                String ph = fieldPhone.getText().trim();
                String cls = fieldClass.getText().trim();
                int admission = Integer.parseInt(fieldAdmissionYear.getText().trim());
                String selectedName = (String) eventDropdown.getSelectedItem();
                int ide = -1; // Default ID

                // Validate input fields
                if (name.isEmpty() || ph.isEmpty() || cls.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Fetch event ID using PreparedStatement
                String eventQuery = "SELECT id FROM event WHERE name = ?";
                try (PreparedStatement stmt1 = cn.prepareStatement(eventQuery)) {
                    stmt1.setString(1, selectedName);
                    ResultSet rs = stmt1.executeQuery();
                    if (rs.next()) {
                        ide = rs.getInt("id");
                    }
                    rs.close();
                }


                // Check if student already exists
                boolean studentExists = false;
                String checkStudentQuery = "SELECT COUNT(*) FROM student WHERE rno = ?";
                try (PreparedStatement checkStmt = cn.prepareStatement(checkStudentQuery)) {
                    checkStmt.setInt(1, id);
                    ResultSet rs = checkStmt.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        studentExists = true;
                    }
                    rs.close();
                }

                if (!studentExists) {
                    // Insert into student table
                    String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = cn.prepareStatement(sql)) {
                        pstmt.setInt(1, id);
                        pstmt.setString(2, name);
                        pstmt.setString(3, ph);
                        pstmt.setString(4, cls);
                        pstmt.setInt(5, admission);
                        pstmt.executeUpdate();
                    }
                }

                // Insert into student_event table (Allow multiple events for one student)
                String eventInsertSQL = "INSERT INTO student_event VALUES (?, ?)";
                try (PreparedStatement pstmt = cn.prepareStatement(eventInsertSQL)) {
                    pstmt.setInt(1, id);
                    pstmt.setInt(2, ide);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Student assigned to event successfully!", "Status", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
        btnClr2.addActionListener(e -> {

            fieldRollNo.setText("");
            fieldStudentName.setText("");
            fieldClass.setText("");
            fieldAdmissionYear.setText("");
            fieldPhone.setText("");
            eventDropdown.setSelectedItem("Select Event");



        });



        btnDelete2.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter student ID to delete:"));
                if (id >= 0) {
                    String sql = "DELETE FROM student WHERE rno = ?";
                    PreparedStatement pstmt = cn.prepareStatement(sql);
                    pstmt.setInt(1, id);
                    int rowsDeleted = pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, rowsDeleted > 0 ? "student deleted successfully!" : "student not found.", "Status", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnUpdate2.addActionListener(e -> {
            try {
                int id = Integer.parseInt(fieldRollNo.getText().trim());

                if (id >= 0) {
                    // Step 1: Fetch existing student details
                    String fetchSql = "SELECT name, phone, class, admission_year FROM student WHERE rno = ?";
                    PreparedStatement fetchStmt = cn.prepareStatement(fetchSql);
                    fetchStmt.setInt(1, id);
                    ResultSet rs = fetchStmt.executeQuery();

                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(frame, "No records found for Roll No: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Get existing values
                    String existingName = rs.getString("name");
                    String existingPhone = rs.getString("phone");
                    String existingClass = rs.getString("class");
                    int existingAdmissionYear = rs.getInt("admission_year");
                    rs.close();
                    fetchStmt.close();

                    // Step 2: Get updated values from user (use existing values if fields are empty)
                    String name = fieldStudentName.getText().trim().isEmpty() ? existingName : fieldStudentName.getText();
                    String phone = fieldPhone.getText().trim().isEmpty() ? existingPhone : fieldPhone.getText();
                    String cls = fieldClass.getText().trim().isEmpty() ? existingClass : fieldClass.getText();
                    int admissionYear = fieldAdmissionYear.getText().trim().isEmpty() ? existingAdmissionYear : Integer.parseInt(fieldAdmissionYear.getText().trim());

                    // Step 3: Update the student record
                    String updateSql = "UPDATE student SET name=?, phone=?, class=?, admission_year=? WHERE rno=?";
                    PreparedStatement pstmt = cn.prepareStatement(updateSql);
                    pstmt.setString(1, name);
                    pstmt.setString(2, phone);
                    pstmt.setString(3, cls);
                    pstmt.setInt(4, admissionYear);
                    pstmt.setInt(5, id);
                    pstmt.executeUpdate();
                    pstmt.close();

                    // Step 4: Update the student_event table
                    String selectedEventName = (String) eventDropdown.getSelectedItem();
                    if (selectedEventName != null && !selectedEventName.isEmpty()) {
                        // Fetch the event ID from the event table
                        String eventQuery = "SELECT id FROM event WHERE name = ?";
                        PreparedStatement eventStmt = cn.prepareStatement(eventQuery);
                        eventStmt.setString(1, selectedEventName);
                        ResultSet eventRs = eventStmt.executeQuery();

                        if (eventRs.next()) {
                            int newEventId = eventRs.getInt("id");

                            // Check if the student is already assigned to an event
                            String checkEventQuery = "SELECT COUNT(*) FROM student_event WHERE student_rno = ?";
                            PreparedStatement checkStmt = cn.prepareStatement(checkEventQuery);
                            checkStmt.setInt(1, id);
                            ResultSet checkRs = checkStmt.executeQuery();
                            checkRs.next();
                            int eventCount = checkRs.getInt(1);
                            checkRs.close();
                            checkStmt.close();

                            if (eventCount > 0) {
                                // Update existing event assignment
                                String updateEventSql = "UPDATE student_event SET event_id = ? WHERE student_rno = ?";
                                PreparedStatement updateEventStmt = cn.prepareStatement(updateEventSql);
                                updateEventStmt.setInt(1, newEventId);
                                updateEventStmt.setInt(2, id);
                                updateEventStmt.executeUpdate();
                                updateEventStmt.close();
                            } else {
                                // Insert new event assignment
                                String insertEventSql = "INSERT INTO student_event (student_rno, event_id) VALUES (?, ?)";
                                PreparedStatement insertEventStmt = cn.prepareStatement(insertEventSql);
                                insertEventStmt.setInt(1, id);
                                insertEventStmt.setInt(2, newEventId);
                                insertEventStmt.executeUpdate();
                                insertEventStmt.close();
                            }
                        }
                        eventRs.close();
                        eventStmt.close();
                    }

                    JOptionPane.showMessageDialog(frame, "Student and event assignment updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid number format: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });


        VAS.addActionListener(e -> {
            try {
                String query2 = "SELECT rno, name, phone, class, admission_year FROM student";
                PreparedStatement stmt2 = cn.prepareStatement(query2);
                ResultSet rs = stmt2.executeQuery();

                // Define column names
                String[] columnNames = {"Roll No", "Name", "Phone", "Class", "Admission Year"};

                // Create DefaultTableModel and add column names
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                // Populate model with data from ResultSet
                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("rno"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("class"),
                            rs.getInt("admission_year")
                    });
                }

                rs.close();

                // Create JTable with model
                JTable table = new JTable(model) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Disable cell editing
                    }
                };
                JScrollPane scrollPane = new JScrollPane(table);

                // Show table inside JOptionPane
                JOptionPane.showMessageDialog(frame, scrollPane, "All Students", JOptionPane.PLAIN_MESSAGE);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        frame.setVisible(true);
    }

}
