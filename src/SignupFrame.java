import javax.swing.*;
import java.awt.*;

public class SignupFrame {
    public static void ShowSignupFrame(String CNP) {
        JFrame signupFrame = new JFrame("Sign Up");
        //signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:"+CNP);
        JTextField cnpField = new JTextField();
        JLabel nameLabel = new JLabel("Nume:");
        JTextField nameField = new JTextField();
        JLabel prenumeLabel = new JLabel("Prenume:");
        JTextField prenumeField = new JTextField();
        JLabel adresaLabel = new JLabel("Adresa:");
        JTextField adresaField = new JTextField();
        JLabel telefonLabel = new JLabel("Numar Telefon:");
        JTextField telefonField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel ibanLabel = new JLabel("Cont IBAN:");
        JTextField ibanField = new JTextField();
        JLabel contractLabel = new JLabel("Numar Contract:");
        JTextField contractField = new JTextField();
        JLabel dataLabel = new JLabel("Data Angajarii:");
        JTextField dataField = new JTextField();
        JLabel functieLabel = new JLabel("Functie:");
        JTextField functieField = new JTextField();
        JLabel passwordLabel = new JLabel("Parola:");
        JPasswordField passwordField = new JPasswordField();
        cnpField.setEditable(false);
        cnpField.setDisabledTextColor(Color.BLACK);
        // Add all inputs to the form panel
        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
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
        formPanel.add(ibanLabel);
        formPanel.add(ibanField);
        formPanel.add(contractLabel);
        formPanel.add(contractField);
        formPanel.add(dataLabel);
        formPanel.add(dataField);
        formPanel.add(functieLabel);
        formPanel.add(functieField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Salveaza schimbarile");
        JButton backButton = new JButton("Back to Login");
        buttonPanel.add(signupButton);
        buttonPanel.add(backButton);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        mainPanel.add(buttonPanel);

        signupFrame.setContentPane(mainPanel);
        signupFrame.setVisible(true);

        // Sign Up button action
        signupButton.addActionListener(e -> {
            //String cnp = cnpField.getText();
            String nume = nameField.getText();
            nume =nume.isEmpty()?null:nume;

            String prenume = prenumeField.getText();
            prenume =prenume.isEmpty()?null:prenume;
            String adresa = adresaField.getText();
            adresa = adresa.isEmpty()?null:adresa;
            String telefon = telefonField.getText();
            telefon = telefon.isEmpty()?null:telefon;
            String email = emailField.getText();
            email = email.isEmpty()?null:email;
            String iban = ibanField.getText();
            iban = iban.isEmpty()?null:iban;
            String contract = contractField.getText();
            contract = contract.isEmpty()?null:contract;
            String data = dataField.getText();
            data = data.isEmpty()?null:data;
            String functie = functieField.getText();
            functie = functie.isEmpty()?null:functie;
            //String parola = new String(passwordField.getPassword());

            int ok = 1;

            // Validate inputs



            // boolean signupSuccess = false;
            if (ok == 1) {
                // signupSuccess = Utilizator.Signup(cnp, nume, prenume, adresa, telefon, email, iban, contract, data, functie, parola);
                ProceduriSql pr=new ProceduriSql();
                pr.callEditUser(CNP,nume,prenume,adresa,telefon,email,iban,contract,data,functie);
            }




        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
            LoginFrame.ShowLoginFrame();
        });
    }
}
