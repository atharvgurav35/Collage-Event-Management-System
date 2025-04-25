import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public login() {
        setTitle("Login Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full-screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using manual layout for precise positioning

        // Title Label
        JLabel titleLabel = new JLabel("User Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBounds(600, 100, 300, 50);
        add(titleLabel);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setBounds(550, 200, 150, 30);
        add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(700, 200, 200, 30);
        add(usernameField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setBounds(550, 250, 150, 30);
        add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(700, 250, 200, 30);
        add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(700, 300, 100, 30);
        add(loginButton);

        // Message Label
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        messageLabel.setBounds(600, 350, 300, 30);
        add(messageLabel);

        // Login Button Action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        usernameField.addActionListener(e -> passwordField.requestFocus());
        passwordField.addActionListener(e -> loginButton.doClick());

        setVisible(true);
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Connection con = DatabaseConnection.getConnection();
        if (con == null) {
            messageLabel.setText("Database connection failed!");
            messageLabel.setForeground(Color.RED);
            return;
        }

        try {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                messageLabel.setText("Login successful!");
                messageLabel.setForeground(Color.GREEN);
                JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
                this.dispose();
                new Home();
                dispose();
            } else {
                messageLabel.setText("Invalid username or password.");
                messageLabel.setForeground(Color.RED);
            }

            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            messageLabel.setText("Login error. Try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new login());
    }
}