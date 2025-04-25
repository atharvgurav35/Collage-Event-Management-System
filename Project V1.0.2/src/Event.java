import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Event extends JFrame {
    private Connection cn; // Database connection

    public Event(Connection con) {
        cn = con; // Store the connection

        JFrame frame = new JFrame("College Event Management System");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);



        // Create MenuBar
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



        JMenu eventReportMenu = new JMenu("Generate Report of Event");

        // Add Menu Items
        JMenuItem byEventDate = new JMenuItem("By Date");
        JMenuItem byCategory = new JMenuItem("By Category");
        JMenuItem byEventName = new JMenuItem("By Name");
        JMenuItem byEventID = new JMenuItem("By ID");
        JMenuItem allEvent = new JMenuItem("All Event");

        eventReportMenu.add(byEventDate);
        eventReportMenu.add(byCategory);
        eventReportMenu.add(byEventName);
        eventReportMenu.add(byEventID);
        eventReportMenu.add(allEvent);
        menuBar.add(eventReportMenu);
        frame.setJMenuBar(menuBar);
        int x = 500, y = 150;

        JPanel eventDetailsPanel = new JPanel();
        eventDetailsPanel.setLayout(null); // Set null layout for custom positioning
        eventDetailsPanel.setBounds(x-30, y-100, 570, 590);  // Adjust panel size and position
        eventDetailsPanel.setBorder(new LineBorder(Color.BLACK, 2));

        // Title Label
        JLabel titleLabel = new JLabel("Event Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(x+150, y-50, 200, 30);
        frame.add(titleLabel);



        // Event ID
        JLabel labelID = new JLabel("ID:");
        labelID.setBounds(x, y, 100, 25);
        JTextField fieldID = new JTextField();
        fieldID.setBounds(x + 100, y, 200, 25);
        frame.add(labelID);
        frame.add(fieldID);

        // Event Name
        y += 40;
        JLabel labelName = new JLabel("Name:");
        labelName.setBounds(x, y, 100, 25);
        JTextField fieldName = new JTextField();
        fieldName.setBounds(x + 100, y, 200, 25);
        frame.add(labelName);
        frame.add(fieldName);

        // Search Buttons
        JButton SEBI = new JButton("Search by ID");
        SEBI.setBounds(x + 320, y - 40, 200, 25);
        JButton SEBN = new JButton("Search by Name");
        SEBN.setBounds(x + 320, y, 200, 25);
        frame.add(SEBI);
        frame.add(SEBN);

        // Category
        y += 40;
        JLabel labelCategory = new JLabel("Category:");
        labelCategory.setBounds(x, y, 100, 25);
        frame.add(labelCategory);

String[] eventCategories = new String[]{
    "Academic Events",
    "Competitions",
    "Performances",
    "Exhibitions",
    "Workshops & Seminars",
    "Sports & Fitness",
    "Cultural Events",
    "Social Impact & Awareness",
    "Networking & Meetups"
};

        JComboBox<String> feildCategory = new JComboBox<>(eventCategories);
        feildCategory.addItem("Select Category");
        feildCategory.setSelectedItem("Select Category");
        feildCategory.setBounds(x + 100, y, 250, 25);
        frame.add(feildCategory);

//        JComboBox<String> eventDropdown = new JComboBox<>();
//        eventDropdown.setBounds(x+100, y, 150, 25); // Positioning dropdown
//        eventDropdown.addItem("Select Event");

        // Host
        y += 40;
        JLabel labelHost = new JLabel("Host:");
        labelHost.setBounds(x, y, 100, 25);
        JTextField fieldHost = new JTextField();
        fieldHost.setBounds(x + 100, y, 250, 25);
        frame.add(labelHost);
        frame.add(fieldHost);

        // Organizer
        y += 40;
        JLabel labelOrganizer = new JLabel("Organizer:");
        labelOrganizer.setBounds(x, y, 100, 25);
        JTextField fieldOrganizer = new JTextField();
        fieldOrganizer.setBounds(x + 100, y, 250, 25);
        frame.add(labelOrganizer);
        frame.add(fieldOrganizer);

        // Date
        y += 40;
        JLabel date1 = new JLabel("Date (YYYY-MM-DD):");
        date1.setBounds(x, y, 130, 25);
        JTextField dateField = new JTextField();
        dateField.setBounds(x + 150, y, 200, 25);
        frame.add(date1);
        frame.add(dateField);

        // Time
        y += 40;
        JLabel time1 = new JLabel("Time (HH:MM):");
        time1.setBounds(x, y, 110, 25);
        JTextField timeField = new JTextField();
        timeField.setBounds(x + 150, y, 200, 25);
        frame.add(time1);
        frame.add(timeField);

        // Venue
        y += 40;
        JLabel labelVenue = new JLabel("Venue:");
        labelVenue.setBounds(x, y, 100, 25);
        JTextField fieldVenue = new JTextField();
        fieldVenue.setBounds(x + 100, y, 250, 25);
        frame.add(labelVenue);
        frame.add(fieldVenue);

        // Buttons
        y += 50;
        JButton btnClear1 = new JButton("Clear");
        btnClear1.setBounds(x, y, 100, 30);
        JButton btnAdd1 = new JButton("Add");
        btnAdd1.setBounds(x + 125, y, 100, 30);
        JButton btnUpdate1 = new JButton("Update");
        btnUpdate1.setBounds(x, y + 40, 100, 30);
        JButton btnDelete1 = new JButton("Delete");
        btnDelete1.setBounds(x + 125, y + 40, 100, 30);

        JButton VAE = new JButton("View All Events");
        VAE.setBounds(x+123, y+90, 200, 30);
        frame.add(VAE);

        frame.add(btnClear1);
        frame.add(btnAdd1);
        frame.add(btnUpdate1);
        frame.add(btnDelete1);
        frame.add(eventDetailsPanel);

        fieldID.addActionListener(e -> fieldName.requestFocus());
        fieldName.addActionListener(e -> fieldHost.requestFocus());
        fieldHost.addActionListener(e -> fieldOrganizer.requestFocus());
        fieldOrganizer.addActionListener(e -> dateField.requestFocus());
        dateField.addActionListener(e -> timeField.requestFocus());
        timeField.addActionListener(e -> fieldVenue.requestFocus());
        fieldVenue.addActionListener(e -> fieldID.requestFocus());
        frame.setVisible(true);

        try {
            Statement stm = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            PreparedStatement stmt = null;
            ResultSet trs = null;

            String query1 = "SELECT MAX(id) FROM event";

            // Execute query
            stmt = cn.prepareStatement(query1);
            ResultSet rs1 = stmt.executeQuery();

            // Retrieve max ID and set it to the text field
            if (rs1.next()) {
                int maxId1 = rs1.getInt(1)+1;  // Get the max ID from result set
                fieldID.setText(String.valueOf(maxId1)); // Set in JTextField
            }

            allEvent.addActionListener(e -> {
                try {
                    // Database Query to Fetch All Events
                    String queryEvents = "SELECT * FROM event";
                    PreparedStatement pst = cn.prepareStatement(queryEvents);
                    ResultSet rsEvents = pst.executeQuery();

                    // JTable Model for Event Details
                    DefaultTableModel eventModel = new DefaultTableModel();
                    eventModel.setColumnIdentifiers(new String[]{"Event ID", "Event Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"});

                    boolean eventsFound = false;
                    while (rsEvents.next()) {
                        eventModel.addRow(new Object[]{
                                rsEvents.getInt(1),
                                rsEvents.getString(2),
                                rsEvents.getString(3),
                                rsEvents.getString(4),
                                rsEvents.getString(5),
                                rsEvents.getString(6),
                                rsEvents.getString(7),
                                rsEvents.getString(8)
                        });
                        eventsFound = true;
                    }

                    if (!eventsFound) {
                        JOptionPane.showMessageDialog(null, "No events found!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    // Create JTable for Event Details
                    JTable eventTable = new JTable(eventModel);

                    // Pass the eventTable to constructor
                    new generateReport(eventTable, "All Events");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex, "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            timeField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    String text = timeField.getText();

                    // Allow only numbers and block alphabets & special characters
                    if (!(Character.isDigit(c) || c == ':') || text.length() >= 5) {
                        e.consume(); // Block invalid input
                    }
                }


            });

            byEventDate.addActionListener(e -> {
                // Create a panel for date selection
                JPanel datePanel = new JPanel();
                datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));

                JLabel label2 = new JLabel("Enter Date (YYYY-MM-DD):");
                datePanel.add(label2);

                JTextField dt = new JTextField(10);
                datePanel.add(dt);

                // Key Listener for Validation
                dt.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        String text = dt.getText();

                        // Allow only numbers and block alphabets & special characters
                        if (!(Character.isDigit(c) || c == '-') || text.length() >= 10) {
                            e.consume(); // Block invalid input
                        }
                    }


                });

                // Show dialog for date input
                int option = JOptionPane.showConfirmDialog(null, datePanel, "Select a Date", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String dateText = dt.getText().trim();

                    // Validate Date Format
                    LocalDate selectedDate3;
                    try {
                        selectedDate3 = LocalDate.parse(dateText);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Date Format! Use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        // Query for events on selected date
                        String query3 = "SELECT * FROM event WHERE date = ?";
                        PreparedStatement pst = cn.prepareStatement(query3);
                        pst.setString(1, selectedDate3.toString());
                        ResultSet rs = pst.executeQuery();

                        DefaultTableModel model = new DefaultTableModel();
                        model.setColumnIdentifiers(new String[]{"Event ID", "Event Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"});

                        boolean eventFound = false;
                        while (rs.next()) {
                            model.addRow(new Object[]{
                                    rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getString(5), rs.getString(6),
                                    rs.getString(7), rs.getString(8)
                            });
                            eventFound = true;
                        }

                        if (!eventFound) {
                            JOptionPane.showMessageDialog(null, "No events found for this date!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JTable eventTable = new JTable(model);
                            new generateReport(eventTable, "Event By Date");
                        }

                        // Close resources
                        rs.close();
                        pst.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            byCategory.addActionListener(e -> {

                // Create a panel to hold the category selection components
                JPanel categoryPanel = new JPanel();
                categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));  // Stack components vertically

                // Label
                JLabel bylabelCategory = new JLabel("Select Category:");
                categoryPanel.add(bylabelCategory);

                // Category JComboBox (Assuming eventCategories is already defined)
                JComboBox<String> comboBoxCategory = new JComboBox<>(eventCategories);
                comboBoxCategory.addItem("Select Category");
                comboBoxCategory.setSelectedItem("Select Category");
                categoryPanel.add(comboBoxCategory);

                // Show the category selection panel in a dialog
                int option = JOptionPane.showConfirmDialog(null, categoryPanel, "Select an Event Category", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    // Get the selected category from combo box
                    String selectedCategory = (String) comboBoxCategory.getSelectedItem();

                    // Validate selected category
                    if (selectedCategory.equals("Select Category")) {
                        JOptionPane.showMessageDialog(null, "Please select a valid category!", "Invalid Category", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        // Database Query to Fetch Events in the Selected Category
                        String queryCategory = "SELECT * FROM event WHERE category = ?";
                        PreparedStatement pst = cn.prepareStatement(queryCategory);
                        pst.setString(1, selectedCategory);
                        ResultSet rs = pst.executeQuery();

                        // JTable Model
                        DefaultTableModel model = new DefaultTableModel();
                        model.setColumnIdentifiers(new String[]{"Event ID", "Event Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"}); // Adjust columns

                        boolean eventFound = false;
                        while (rs.next()) {
                            model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
                            eventFound = true;
                        }

                        if (!eventFound) {
                            JOptionPane.showMessageDialog(null, "No events found for this category!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }

                        // Create JTable with Data
                        JTable eventTable = new JTable(model);

                        // Generate PDF Report
                        new generateReport(eventTable, selectedCategory);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex, "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            byEventID.addActionListener(e -> {

                // Create a panel to hold the ID selection components
                JPanel idPanel = new JPanel();
                idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.Y_AXIS));  // Stack components vertically

                // Label
                JLabel bylabelID = new JLabel("Enter Event ID:");
                idPanel.add(bylabelID);

                // Event ID JTextField
                JTextField eventIDField = new JTextField(10);
                idPanel.add(eventIDField);

                // Show the ID selection panel in a dialog
                int option = JOptionPane.showConfirmDialog(null, idPanel, "Select an Event by ID", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    // Get the event ID from text field
                    String eventIDText = eventIDField.getText().trim();

                    // Validate event ID
                    if (eventIDText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid event ID!", "Invalid ID", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        // Parse the event ID as an integer
                        int eventID = Integer.parseInt(eventIDText);

                        // Database Query to Fetch Event by ID
                        String queryID = "SELECT * FROM event WHERE id = ?";
                        PreparedStatement pst = cn.prepareStatement(queryID);
                        pst.setInt(1, eventID);
                        ResultSet rs = pst.executeQuery();

                        // JTable Model for Event Details
                        DefaultTableModel eventModel = new DefaultTableModel();
                        eventModel.setColumnIdentifiers(new String[]{"Event ID", "Event Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"}); // Adjust columns

                        boolean eventFound = false;
                        while (rs.next()) {
                            // Add the event row from the result set
                            eventModel.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
                            eventFound = true;
                        }

                        if (!eventFound) {
                            JOptionPane.showMessageDialog(null, "No event found with this ID!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }

                        // Database Query to Fetch Students Who Attended the Event (with JOIN)
                        String queryStudents = "SELECT s.rno, s.name, s.phone, s.admission_year, s.class " +
                                "FROM student s " +
                                "JOIN student_event es ON s.rno = es.student_rno " +  // Join with junction table
                                "WHERE es.event_id = ?";
                        pst = cn.prepareStatement(queryStudents);
                        pst.setInt(1, eventID);
                        ResultSet rsStudents = pst.executeQuery();

                        // JTable Model for Student Details
                        DefaultTableModel studentModel = new DefaultTableModel();
                        studentModel.setColumnIdentifiers(new String[]{"Roll No", "Name", "Phone", "Admission Year", "Class"}); // Adjust columns

                        boolean studentsFound = false;
                        while (rsStudents.next()) {
                            // Add the student row from the result set
                            studentModel.addRow(new Object[]{rsStudents.getString(1), rsStudents.getString(2), rsStudents.getString(3), rsStudents.getInt(4), rsStudents.getString(5)});
                            studentsFound = true;
                        }


                        // Create JTable for Event Details
                        JTable eventTable = new JTable(eventModel);

                        // Create JTable for Student Details
                        JTable studentTable = new JTable(studentModel);


                        // Optionally, generate PDF report for both event and student tables
                        new generateReport(eventTable, studentTable, studentsFound, "Student");

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Event ID!", "Invalid ID Format", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex, "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            byEventName.addActionListener(e -> {


                // Create a panel to hold the name selection components
                JPanel namePanel = new JPanel();
                namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));  // Stack components vertically

                // Label
                JLabel bylabelName = new JLabel("Enter Event Name:");
                namePanel.add(bylabelName);

                // Event Name JTextField
                JTextField eventNameField = new JTextField(10);
                namePanel.add(eventNameField);


                // Show the name selection panel in a dialog
                int option = JOptionPane.showConfirmDialog(null, namePanel, "Select an Event by Name", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    // Get the event name from text field
                    String eventNameText = eventNameField.getText().trim();

                    // Validate event name
                    if (eventNameText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid event name!", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        // Database Query to Fetch Event by Name (Case-Insensitive Search)
                        String queryName = "SELECT * FROM event WHERE LOWER(name) = LOWER(?)";
                        PreparedStatement pst = cn.prepareStatement(queryName);
                        pst.setString(1, eventNameText);
                        ResultSet rs = pst.executeQuery();

                        // JTable Model for Event Details
                        DefaultTableModel eventModel = new DefaultTableModel();
                        eventModel.setColumnIdentifiers(new String[]{"Event ID", "Event Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"}); // Adjust columns

                        boolean eventFound = false;
                        while (rs.next()) {
                            // Add the event row from the result set
                            eventModel.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
                            eventFound = true;
                        }

                        if (!eventFound) {
                            JOptionPane.showMessageDialog(null, "No event found with this name!", "No Data", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }

                        // Database Query to Fetch Students Who Attended the Event (with JOIN)
                        String queryStudents = "SELECT s.rno, s.name, s.phone, s.admission_year, s.class " +
                                "FROM student s " +
                                "JOIN student_event es ON s.rno = es.student_rno " +
                                "JOIN event e ON es.event_id = e.id " +
                                "WHERE LOWER(e.name) = LOWER(?)";

                        pst = cn.prepareStatement(queryStudents);
                        pst.setString(1, eventNameText);
                        ResultSet rsStudents = pst.executeQuery();

                        // JTable Model for Student Details
                        DefaultTableModel studentModel = new DefaultTableModel();
                        studentModel.setColumnIdentifiers(new String[]{"Roll No", "Name", "Phone", "Admission Year", "Class"}); // Adjust columns

                        boolean studentsFound = false;
                        while (rsStudents.next()) {
                            // Add the student row from the result set
                            studentModel.addRow(new Object[]{rsStudents.getString(1), rsStudents.getString(2), rsStudents.getString(3), rsStudents.getInt(4), rsStudents.getString(5)});
                            studentsFound = true;
                        }

                        // Create JTable for Event Details
                        JTable eventTable = new JTable(eventModel);

                        // Create JTable for Student Details
                        JTable studentTable = new JTable(studentModel);

                        // Generate PDF report for both event and student tables
                        new generateReport(eventTable, studentTable, studentsFound, "Student");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex, "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            SEBN.addActionListener(e -> {
                try {
                    String name = JOptionPane.showInputDialog("Enter Event Name:");
                    if (name != null && !name.trim().isEmpty()) {
                        String sql = "SELECT * FROM event WHERE name like ?";
                        PreparedStatement pstmt = cn.prepareStatement(sql);
                        pstmt.setString(1, "%" + name + "%");
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(frame, "Event Found: \n " + rs.getInt(1) + " ) " + rs.getString("name"));

                        } else {
                            JOptionPane.showMessageDialog(frame, "Event Not Found");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            SEBI.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Event ID:"));
                    if (id >= 0) {
                        String sql = "SELECT * FROM event WHERE id = ?";
                        PreparedStatement pstmt = cn.prepareStatement(sql);
                        pstmt.setInt(1, id);
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(frame, "Event Found: \n " + rs.getInt(1) + " ) " + rs.getString("name"));
                        } else {
                            JOptionPane.showMessageDialog(frame, "Event Not Found");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnAdd1.addActionListener(e -> {
                try {


// Combine day, month, and year into a formatted date string
                    int id = Integer.parseInt(fieldID.getText());
                    String name = fieldName.getText();
                    String category = (String) feildCategory.getSelectedItem();
                    String host = fieldHost.getText();
                    String organizer = fieldOrganizer.getText();

                    String dateText = dateField.getText().trim();
                    LocalDate date = LocalDate.parse(dateText);
                    String timeText = timeField.getText().trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime time = LocalTime.parse(timeText, formatter);

                    String venue = fieldVenue.getText();
                    // Get the selected time from combo boxes


// Combine hour, minute, and AM/PM into a formatted time string
                    String selectedTime = timeText;

                    // Check for null or invalid values before inserting into the database
                    if (id >= 0 && name != null && category != null && host != null && organizer != null && date != null && selectedTime != null && venue != null) {
                        String sql = "INSERT INTO event VALUES (?,?,?,?,?,?,?,?)";
                        PreparedStatement pstmt = cn.prepareStatement(sql);
                        pstmt.setInt(1, id);
                        pstmt.setString(2, name);
                        pstmt.setString(3, category);
                        pstmt.setString(4, host);
                        pstmt.setString(5, organizer);
                        pstmt.setString(6, dateText);
                        pstmt.setString(7, selectedTime);
                        pstmt.setString(8, venue);

                        int rowsInserted = pstmt.executeUpdate();
                       // eventDropdown.removeAllItems();
                      //  eventDropdown.addItem("Select Event");

                        String query4 = "SELECT name FROM Event";

                        PreparedStatement stmt4 = cn.prepareStatement(query4);
                        ResultSet trs4 = stmt4.executeQuery();

                        // Add event names to JComboBox
                        while (trs4.next()) {
                           // eventDropdown.addItem(trs4.getString("name"));
                        }
                        JOptionPane.showMessageDialog(frame, rowsInserted > 0 ? "Event added successfully!" : "Error adding event.", "Status", JOptionPane.INFORMATION_MESSAGE);

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnClear1.addActionListener(e -> {
                try {
                    // Clear input fields
                    fieldID.setText("");
                    fieldName.setText("");
                    feildCategory.setSelectedItem("Select Category"); // Fixed typo
                    fieldHost.setText("");
                    fieldOrganizer.setText("");
                    timeField.setText("");
                    dateField.setText("");
                    fieldVenue.setText("");

                    // Query to get the maximum ID
                    String q = "SELECT MAX(id) FROM event";

                    // Execute query
                    PreparedStatement p = cn.prepareStatement(q);
                    ResultSet r = p.executeQuery();

                    // Retrieve max ID and set it to the text field
                    if (r.next()) {
                        int maxId1 = r.getInt(1)+1;  // Get the max ID from result set
                        fieldID.setText(String.valueOf(maxId1)); // Set in JTextField
                    }

                    // Close resources
                } catch (Exception ex) {
                    ex.printStackTrace(); // Print error if occurs
                }
            });

            btnDelete1.addActionListener(e -> {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Event ID to delete:"));
                    if (id >= 0) {

                        String sql = "DELETE FROM event WHERE id = ?";
                        PreparedStatement pstmt = cn.prepareStatement(sql);
                        pstmt.setInt(1, id);
                        int rowsDeleted = pstmt.executeUpdate();
                        String query4 = "SELECT name FROM Event";
                        PreparedStatement stmt4 = cn.prepareStatement(query4);
                        ResultSet trs4 = stmt4.executeQuery();

                        // eventDropdown.removeAllItems();
                        //eventDropdown.addItem("Select Event");

                        // Add event names to JComboBox
                        while (trs4.next()) {
                          //  eventDropdown.addItem(trs4.getString("name"));
                        }
                        JOptionPane.showMessageDialog(frame, rowsDeleted > 0 ? "Event deleted successfully!" : "Event not found.", "Status", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            });
            VAE.addActionListener(e -> {
                try {
                    // Updated query to match the table structure
                    String query2 = "SELECT id, name, category, host, organizer, date, time, venue FROM event";
                    PreparedStatement stmt2 = cn.prepareStatement(query2);
                    ResultSet rs = stmt2.executeQuery();

                    // Define column names for events
                    String[] columnNames = {"Event ID", "Name", "Category", "Host", "Organizer", "Date", "Time", "Venue"};

                    // Create DefaultTableModel and add column names
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                    // Populate model with data from ResultSet
                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("category"),
                                rs.getString("host"),
                                rs.getString("organizer"),
                                rs.getString("date"),
                                rs.getString("time"),
                                rs.getString("venue")
                        });
                    }

                    rs.close();
                    stmt2.close();

                    // Create JTable with model
                    JTable table = new JTable(model) {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false; // Disable cell editing
                        }
                    };

                    // Adjust column widths to fit content
                    for (int i = 0; i < table.getColumnCount(); i++) {
                        TableColumn column = table.getColumnModel().getColumn(i);
                        int width = 0;

                        // Calculate the width of each column based on its content
                        for (int row = 0; row < table.getRowCount(); row++) {
                            Object value = table.getValueAt(row, i);
                            if (value != null) {
                                width = Math.max(width, value.toString().length());
                            }
                        }

                        // Set the preferred width to the maximum length of the content + padding
                        column.setPreferredWidth(width * 10); // Adjust multiplier for spacing
                    }

                    // Set auto-resizing behavior to ensure that all content fits
                    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                    // Create JScrollPane for the table
                    JScrollPane scrollPane = new JScrollPane(table);

                    // Set a preferred size for the scroll pane to adjust the overall dialog size
                    scrollPane.setPreferredSize(new java.awt.Dimension(1000, 400)); // Adjust the width and height as needed

                    // Show table inside JOptionPane with increased size
                    JOptionPane.showMessageDialog(frame, scrollPane, "All Events", JOptionPane.PLAIN_MESSAGE);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });



        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
