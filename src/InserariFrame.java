import javax.swing.*;
import java.awt.*;

public class InserariFrame {
    public static void ShowSignupUtilizatorFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:");
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
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
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
            String cnp = cnpField.getText();
            String nume = nameField.getText();
            String prenume = prenumeField.getText();
            String adresa = adresaField.getText();
            String telefon = telefonField.getText();
            String email = emailField.getText();
            String iban = ibanField.getText();
            String contract = contractField.getText();
            String data = dataField.getText();
            String functie = functieField.getText();
            String parola = new String(passwordField.getPassword());

            int ok = 1;

            // Validate inputs
            if (cnp.isEmpty() || nume.isEmpty() || prenume.isEmpty() || adresa.isEmpty() || telefon.isEmpty()
                    || email.isEmpty() || iban.isEmpty() || contract.isEmpty() || data.isEmpty()
                    || functie.isEmpty() || parola.isEmpty()) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cnp.length() != 13) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "CNP-ul trebuie sa aiba 13 cifre!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (telefon.length() != 10||!telefon.startsWith("07")) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Numarul de telefon este invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (parola.length() < 6) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Parola trebuie sa aiba cel putin 6 caractere!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (!email.contains("@") || !email.contains(".")) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Email invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (Utilizator.VerificareUtilizator(email)) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Email deja existent!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Placeholder sign-up logic
            boolean signupSuccess = false;
            if (ok == 1) {
                signupSuccess = Utilizator.InsertUtilizator(cnp, nume, prenume, adresa, telefon, email, iban, contract, data, functie, parola);
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare cu succes! Te rugam sa te loghezi.");
                signupFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
        });
    }

    public static void ShowSignupMedicalFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:");
        JTextField cnpField = new JTextField();
        JLabel salariuLabel = new JLabel("Salariu:");
        JTextField salariuField = new JTextField();
        JLabel oreLabel = new JLabel("Numar ore contractuale:");
        JTextField oreField = new JTextField();

        // Add all inputs to the form panel
        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
        formPanel.add(salariuLabel);
        formPanel.add(salariuField);
        formPanel.add(oreLabel);
        formPanel.add(oreField);


        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
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
            String cnp = cnpField.getText();
            String salariu = salariuField.getText();
            String ore = oreField.getText();

            int ok = 1;

            // Validate inputs
            if (cnp.isEmpty() || salariu.isEmpty() || ore.isEmpty()) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cnp.length() != 13) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "CNP-ul trebuie sa aiba 13 cifre!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Placeholder sign-up logic
            boolean signupSuccess = false;
            if (ok == 1) {
                signupSuccess = Medical.InsertMedical(cnp, Float.parseFloat(salariu), Integer.parseInt(ore));
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare cu succes!");
                signupFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
        });
    }
    public static void ShowSignupMedicFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:");
        JTextField cnpField = new JTextField();
        JLabel specialitateLabel = new JLabel("Specialitate:");
        JTextField specialitateField = new JTextField();
        JLabel gradLabel = new JLabel("Grad:");
        JTextField gradField = new JTextField();
        JLabel cod_parafaLabel = new JLabel("Cod parafa:");
        JTextField cod_parafaField = new JTextField();
        JLabel procent_serviciiLabel = new JLabel("Procent servicii:");
        JTextField procent_serviciiField = new JTextField();
        JLabel titlu_stintificLabel = new JLabel("Titlu stintific:");
        JTextField titlu_stintificField = new JTextField();
        JLabel post_didacticLabel = new JLabel("Post didactic:");
        JTextField post_didacticField = new JTextField();

        // Add all inputs to the form panel
        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
        formPanel.add(specialitateLabel);
        formPanel.add(specialitateField);
        formPanel.add(gradLabel);
        formPanel.add(gradField);
        formPanel.add(cod_parafaLabel);
        formPanel.add(cod_parafaField);
        formPanel.add(procent_serviciiLabel);
        formPanel.add(procent_serviciiField);
        formPanel.add(titlu_stintificLabel);
        formPanel.add(titlu_stintificField);
        formPanel.add(post_didacticLabel);
        formPanel.add(post_didacticField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
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
            String cnp = cnpField.getText();
            String speciali = specialitateField.getText();
            String grad = gradField.getText();
            String cod_parafa = cod_parafaField.getText();
            String procent_servicii = procent_serviciiField.getText();
            String titlu_stintific = titlu_stintificField.getText();
            String post_didactic = post_didacticField.getText();

            int ok = 1;

            // Validate inputs
            if (cnp.isEmpty() || speciali.isEmpty() || grad.isEmpty() || cod_parafa.isEmpty() || procent_servicii.isEmpty()
                    || titlu_stintific.isEmpty() || post_didactic.isEmpty()) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cnp.length() != 13) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "CNP-ul trebuie sa aiba 13 cifre!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Placeholder sign-up logic
            boolean signupSuccess = false;
            if (ok == 1) {
                signupSuccess = Medic.InsertMedic(cnp, speciali, grad, cod_parafa, Integer.parseInt(procent_servicii), titlu_stintific, post_didactic);
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare cu succes!");
                signupFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
        });
    }
    public static void ShowSignupMedicCompetentaFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:");
        JTextField cnpField = new JTextField();
        JLabel competentaLabel = new JLabel("Id Competenta:");
        JTextField competentaField = new JTextField();

        // Add all inputs to the form panel
        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
        formPanel.add(competentaLabel);
        formPanel.add(competentaField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
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
            String cnp = cnpField.getText();
            String competenta = competentaField.getText();

            int ok = 1;

            // Validate inputs
            if (cnp.isEmpty() || competenta.isEmpty()) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cnp.length() != 13) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "CNP-ul trebuie sa aiba 13 cifre!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Placeholder sign-up logic
            boolean signupSuccess = false;
            if (ok == 1) {
                signupSuccess = Medic.InsertMedicCompetenta(cnp,Integer.parseInt(competenta));
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare cu succes!");
                signupFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
        });
    }

    public static void ShowSignupMedicUnitateFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:");
        JTextField cnpField = new JTextField();
        JLabel unitateLabel = new JLabel("Id unitate:");
        JTextField unitateField = new JTextField();

        // Add all inputs to the form panel
        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
        formPanel.add(unitateLabel);
        formPanel.add(unitateField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
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
            String cnp = cnpField.getText();
            String unitate = unitateField.getText();

            int ok = 1;

            // Validate inputs
            if (cnp.isEmpty() || unitate.isEmpty()) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cnp.length() != 13) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "CNP-ul trebuie sa aiba 13 cifre!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Placeholder sign-up logic
            boolean signupSuccess = false;
            if (ok == 1) {
                signupSuccess = Medic.InsertMedicUnitate(cnp,Integer.parseInt(unitate));
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare cu succes!");
                signupFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
        });
    }

    public static void ShowSignupAsistentFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:");
        JTextField cnpField = new JTextField();
        JLabel id_unitateLabel = new JLabel("Id unitate:");
        JTextField id_unitateField = new JTextField();
        JLabel tipLabel = new JLabel("Tip:");
        JTextField tipField = new JTextField();
        JLabel gradLabel = new JLabel("Grad:");
        JTextField gradField = new JTextField();

        // Add all inputs to the form panel
        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
        formPanel.add(id_unitateLabel);
        formPanel.add(id_unitateField);
        formPanel.add(tipLabel);
        formPanel.add(tipField);
        formPanel.add(gradLabel);
        formPanel.add(gradField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
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
            String cnp = cnpField.getText();
            String id_unitate = id_unitateField.getText();
            String tip = tipField.getText();
            String grad = gradField.getText();

            int ok = 1;

            // Validate inputs
            if (cnp.isEmpty() || id_unitate.isEmpty() || tip.isEmpty() || grad.isEmpty()) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cnp.length() != 13) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "CNP-ul trebuie sa aiba 13 cifre!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Placeholder sign-up logic
            boolean signupSuccess = false;
            if (ok == 1) {
                signupSuccess = AsistentMedical.InsertAsistent(cnp, Integer.parseInt(id_unitate), tip, grad);
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare cu succes!");
                signupFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
        });
    }

    public static void ShowSignupReceptionerFrame() {
        JFrame signupFrame = new JFrame("Sign Up");
        signupFrame.setSize(400, 600); // Adjusted size to fit more fields
        signupFrame.setLocation(600, 200);

        // Panels for user input and buttons
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 11 rows for the inputs, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels and fields for each input
        JLabel cnpLabel = new JLabel("CNP:");
        JTextField cnpField = new JTextField();
        JLabel unitateLabel = new JLabel("Id unitate:");
        JTextField unitateField = new JTextField();

        // Add all inputs to the form panel
        formPanel.add(cnpLabel);
        formPanel.add(cnpField);
        formPanel.add(unitateLabel);
        formPanel.add(unitateField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");
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
            String cnp = cnpField.getText();
            String unitate = unitateField.getText();

            int ok = 1;

            // Validate inputs
            if (cnp.isEmpty() || unitate.isEmpty()) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (cnp.length() != 13) {
                ok = 0;
                JOptionPane.showMessageDialog(signupFrame, "CNP-ul trebuie sa aiba 13 cifre!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Placeholder sign-up logic
            boolean signupSuccess = false;
            if (ok == 1) {
                signupSuccess = Receptioner.InsertReceptioner(cnp,Integer.parseInt(unitate));
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare cu succes!");
                signupFrame.dispose();

            } else if (ok == 1) { // If InsertUtilizator fails after all validations pass
                JOptionPane.showMessageDialog(signupFrame, "Inregistrare esuata. Te rugam sa incerci din nou.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            signupFrame.dispose();
        });
    }
}
