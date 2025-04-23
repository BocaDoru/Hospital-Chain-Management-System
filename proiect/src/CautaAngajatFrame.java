import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CautaAngajatFrame {
    public static void ShowCautaAngajatFrame() {
        JFrame cautareFrame = new JFrame("Cautare angajat");
        cautareFrame.setSize(400, 400);
        cautareFrame.setLocation(600, 300);

        JPanel numePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel prenumePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel functiePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel numeLabel = new JLabel("Nume:");
        JTextField numeField = new JTextField();
        numeField.setPreferredSize(new Dimension(150, 30));
        numePanel.add(numeLabel);
        numePanel.add(numeField);

        JLabel prenumeLabel = new JLabel("Prenume:");
        JTextField prenumeField = new JTextField();
        prenumeField.setPreferredSize(new Dimension(150, 30));
        prenumePanel.add(prenumeLabel);
        prenumePanel.add(prenumeField);

        JLabel functieLabel = new JLabel("Functie:");
        JTextField functieField = new JTextField();
        functieField.setPreferredSize(new Dimension(150, 30));
        functiePanel.add(functieLabel);
        functiePanel.add(functieField);

        JButton cautareButton = new JButton("Cauta");
        buttonPanel.add(cautareButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(numePanel);
        mainPanel.add(prenumePanel);
        mainPanel.add(functiePanel);
        mainPanel.add(buttonPanel);

        cautareFrame.setContentPane(mainPanel);
        cautareFrame.setVisible(true);

        cautareButton.addActionListener(e -> {
            String nume = numeField.getText();
            String prenume = prenumeField.getText();
            String functie = functieField.getText();

            if(nume.isEmpty() || prenume.isEmpty() || functie.isEmpty()) {
                JOptionPane.showMessageDialog(cautareFrame, "Toate campurile sunt obligatorii!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT * FROM utilizator WHERE nume = ? AND prenume = ? AND functie = ?";
            Utilizator utilizator = null;
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            try{

                preparedStatement = Conexiune.getConnection().prepareStatement(query);
                preparedStatement.setString(1, nume);
                preparedStatement.setString(2, prenume);
                preparedStatement.setString(3, functie);
                rs = preparedStatement.executeQuery();
                if(rs.next()){
                    Utilizator utilizatorGasit = new Utilizator(rs.getString("CNP"),
                            rs.getString("nume"), rs.getString("prenume"),
                            rs.getString("adresa"), rs.getString("numar_telefon"),
                            rs.getString("email"), rs.getString("cont_IBAN"),
                            rs.getString("numar_contract"), rs.getDate("data_angajarii"),
                            rs.getString("functie"), rs.getString("parola"));
                    JOptionPane.showMessageDialog(cautareFrame, "Utilizator gasit!", "Success", JOptionPane.INFORMATION_MESSAGE );
                    cautareFrame.dispose();
                    if (utilizatorGasit != null) {
                        new AngajatFrame(utilizatorGasit);
                    } else {
                        JOptionPane.showMessageDialog(null, "Angajatul nu a fost găsit!");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(cautareFrame, "Utilizatorul nu a fost gasit!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(cautareFrame,
                        "Eroare la conectarea cu baza de date: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Afișează eroarea completă în consolă
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (preparedStatement != null) preparedStatement=null;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
