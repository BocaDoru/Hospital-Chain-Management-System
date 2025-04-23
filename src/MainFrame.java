import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(Utilizator utilizator) {
        // Frame settings
        setTitle("Main Frame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a main panel with a background image
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding around edges

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Transparent panel to show the background
        JLabel titleLabel = new JLabel("Meniul Principal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // Make text white for contrast
        titlePanel.add(titleLabel);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1, 10, 10)); // 4 rows, 1 column, spacing between buttons
        buttonPanel.setOpaque(false); // Transparent panel

        // Create buttons
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e->{
            SwingUtilities.invokeLater(() -> {
                LoginFrame.ShowLoginFrame();
                this.dispose();
                LoginFrame.setUtilizator(null);
            });
        });
        JButton viewInfoButton = new JButton("Vizualizare Informatii Personale");
        JButton viewScheduleButton = new JButton("Afisare Orar");
        JButton workButton = new JButton("Munca");
        JButton viewSalariuButton=new JButton("Vezi salar");
        JButton viewProfitButton=new JButton("Vezi profit medic");

        // Add buttons to panel
        if(utilizator instanceof Medical)
            buttonPanel.add(viewSalariuButton);
        if(utilizator instanceof Medic)
            buttonPanel.add(viewProfitButton);
        buttonPanel.add(viewInfoButton);
        buttonPanel.add(viewScheduleButton);
        buttonPanel.add(workButton);
        buttonPanel.add(logoutButton);

        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        workButton.addActionListener(e->{
            switch(utilizator.getFunctie())
            {
                case "Receptioner":
                    Receptioner receptioner=Receptioner.GetReceptioner(utilizator);
                    ProgramareFrame.ShowProgramareFrame(receptioner);
                    break;
                case "Asistent Medical":
                    AsistentMedical asistentMedical=AsistentMedical.GetAsistentMedical(utilizator);
                    AsistentFrame.ShowAsistentFrame(asistentMedical);
                    break;
                case "Medic":
                    Medic medic=Medic.GetMedic(utilizator);
                    MedicFrame.ShowMedicFrame(medic);
                    break;
                case "Admin":
                    new ShowAdmin("Admin").setVisible(true);
                    break;
                case "Super Admin":
                    new ShowAdmin("Super Admin").setVisible(true);
                    break;
                case "Expert Financiar Contabil":
                    new ShowModul2().setVisible(true);
                    break;
                case "Inspector Resurse Umane":
                    CautaAngajatFrame.ShowCautaAngajatFrame();
                    break;

            };
        });
        viewSalariuButton.addActionListener(e->{
            if(utilizator instanceof Medic)
                new ShowExpertFinanciarSalarii().showSalarMedic((Medic)utilizator);
            if(utilizator instanceof Medical)
                new ShowExpertFinanciarSalarii().showSalarAsistent((Medical)utilizator);
        });

        viewProfitButton.addActionListener(e->{
            if(utilizator instanceof Medic) {
                ShowExpertFinanciarProfit sh=new ShowExpertFinanciarProfit();
                sh.setVisible(false);
                sh.monthSelect("Profit per Medic", utilizator.getCNP(), 0, null);
                //new ShowExpertFinanciarProfit().monthSelect("Profit per Medic", utilizator.getCNP(), 0, null);
            }
        });
        viewScheduleButton.addActionListener(e->{
            DisplayOrarMedicalFrame displayOrarMedical = new DisplayOrarMedicalFrame(utilizator);
            displayOrarMedical.setVisible(true);
        });
        viewInfoButton.addActionListener(e->{
            JOptionPane.showMessageDialog(mainPanel, utilizator.AllInfo(), "Date personale", JOptionPane.INFORMATION_MESSAGE);
        });
        // Set the main panel as content pane
        setContentPane(mainPanel);
    }


}

// Custom panel to allow for a background image
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        try {
            // Load the background image
            backgroundImage = new ImageIcon("C:\\Users\\bocai\\Downloads\\background2014.jpg").getImage();
        } catch (Exception e) {
            System.err.println("Failed to load background image.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
