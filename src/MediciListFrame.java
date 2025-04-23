import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MediciListFrame extends JFrame {
    private static Medic medic;

    public static Medic getMedic() {
        return medic;
    }

    public static void ShowMediciListFrame(ArrayList<Medic> medics,JButton selectMedicButton) {
        JFrame mediciFrame = new JFrame("Lista Pacienti");
        mediciFrame.setSize(300, 300); // Adjust initial size
        mediciFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza medicul:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel mediciPanel = new JPanel();
        mediciPanel.setLayout(new BoxLayout(mediciPanel, BoxLayout.Y_AXIS));

        for (Medic m : medics) {
            JButton medicButton = new JButton(m.getNume() + " " + m.getPrenume());
            medicButton.addActionListener(e -> {
                medic = m;
                selectMedicButton.setText(medic.getNume()+" "+medic.getPrenume());
                mediciFrame.dispose();
            });
            mediciPanel.add(medicButton);
        }

        JScrollPane mediciScrollPane = new JScrollPane(mediciPanel);
        mainPanel.add(mediciScrollPane);
        mediciFrame.setContentPane(mainPanel);
        mediciFrame.setVisible(true);
    }
}
