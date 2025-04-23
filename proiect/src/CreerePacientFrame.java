import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CreerePacientFrame extends JFrame {
    public static void ShowCreerePacientFrame() {
        JFrame pacientFrame = new JFrame("Creaza pacient");
        pacientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pacientFrame.setSize(400, 600); // Adjusted size to fit more fields
        pacientFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Nume:");
        JTextField nameField = new JTextField();
        JLabel prenumeLabel = new JLabel("Prenume:");
        JTextField prenumeField = new JTextField();
        JLabel dataLabel = new JLabel("Data nasterii:");
        JFormattedTextField dataField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        JLabel adresaLabel = new JLabel("Adresa:");
        JTextField adresaField = new JTextField();
        JLabel telefonLabel = new JLabel("Numar Telefon:");
        JTextField telefonField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        // Add all inputs to the form panel
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(prenumeLabel);
        formPanel.add(prenumeField);
        formPanel.add(adresaLabel);
        formPanel.add(adresaField);
        formPanel.add(telefonLabel);
        formPanel.add(telefonField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(dataLabel);
        formPanel.add(dataField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Creaza pacient");
        JButton backButton = new JButton("Back");
        buttonPanel.add(signupButton);
        buttonPanel.add(backButton);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(buttonPanel);

        pacientFrame.setContentPane(mainPanel);
        pacientFrame.setVisible(true);

        // Sign Up button action
        signupButton.addActionListener(e -> {
            String nume = nameField.getText();
            String prenume = prenumeField.getText();
            String adresa = adresaField.getText();
            String telefon = telefonField.getText();
            String email = emailField.getText();
            Date data = Date.valueOf(dataField.getText());

            int ok = 1;

            if (telefon.length() != 10||!telefon.startsWith("07")) {
                ok = 0;
                JOptionPane.showMessageDialog(pacientFrame, "Numarul de telefon este invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (!email.contains("@") || !email.contains(".")) {
                ok = 0;
                JOptionPane.showMessageDialog(pacientFrame, "Email invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (Utilizator.VerificareUtilizator(email)) {
                ok = 0;
                JOptionPane.showMessageDialog(pacientFrame, "Email deja existent!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            boolean createSuccess = false;
            if (ok == 1) {
                createSuccess = Pacient.CreazaPacient(nume,prenume, data,adresa,telefon,email);
            }

            if (createSuccess) {
                JOptionPane.showMessageDialog(pacientFrame, "Inregistrare cu succes!");
                pacientFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(pacientFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            pacientFrame.dispose();
        });
    }
}