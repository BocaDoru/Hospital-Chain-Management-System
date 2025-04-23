import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private static Utilizator utilizator;

    public static Utilizator getUtilizator() {
        return utilizator;
    }

    public static void setUtilizator(Utilizator utilizator) {
        LoginFrame.utilizator = utilizator;
    }

    public static void ShowLoginFrame() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 300); // Adjust initial size
        loginFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(150, 25));
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        passField.setPreferredSize(new Dimension(150, 25));
        JButton loginButton = new JButton("Login");

        // Add components to the panels
        userPanel.add(emailLabel);
        userPanel.add(emailField);
        passwordPanel.add(passLabel);
        passwordPanel.add(passField);
        buttonPanel.add(loginButton);

        // Main panel with vertical alignment
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(userPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer between rows
        mainPanel.add(passwordPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createVerticalGlue()); // Flexible space below

        loginFrame.setContentPane(mainPanel);
        loginFrame.setVisible(true);

        // Login button action
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            // Call your database login logic here
            utilizator = Utilizator.Login(email, password);
            boolean loginSuccess = utilizator!=null;
            // Replace with actual logic

            if (loginSuccess) {
                JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                loginFrame.dispose();
                switch(utilizator.getFunctie())
                {
                    case "Receptioner":
                        Receptioner receptioner=Receptioner.GetReceptioner(utilizator);
                        utilizator=receptioner;
                        break;
                    case "Asistent Medical":
                        AsistentMedical asistentMedical=AsistentMedical.GetAsistentMedical(utilizator);
                        utilizator=asistentMedical;
                        break;
                    case "Medic":
                        Medic medic=Medic.GetMedic(utilizator);
                        utilizator=medic;
                        break;
                };
                new MainFrame(utilizator).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
