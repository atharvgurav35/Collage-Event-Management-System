import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Home {
    public Home() {
        JFrame frame = new JFrame("Home");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Home");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);



        JButton btnEvent = new JButton("Event");
        btnEvent.setBounds(650, 300, 200, 50);
        btnEvent.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnEvent);

        JButton btnStudent = new JButton("Student");
        btnStudent.setBounds(650, 400, 200, 50);
        btnStudent.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(btnStudent);

        btnEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = DatabaseConnection.getConnection(); // Get connection from DatabaseConnection class
                if (con != null) {
                    frame.dispose();
                    new Event(con);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Failed to connect to the database!",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }            }
        });

        btnStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con = DatabaseConnection.getConnection(); // Get connection from DatabaseConnection class
                if (con != null) {
                    frame.dispose();

                    new Student(con);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Failed to connect to the database!",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }            }
        });

        frame.setVisible(true);
    }
}