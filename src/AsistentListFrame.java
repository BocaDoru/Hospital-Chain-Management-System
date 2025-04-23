import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class AsistentListFrame extends JFrame {
    private static AsistentMedical asistentMedical;

    public static AsistentMedical getAsistentMedical() {
        return asistentMedical;
    }

    public static void ShowAsistentListFrame(int idUnitate,JButton selectAsistentButton) {
        JFrame asistentFrame = new JFrame("Asistenti medicali");
        asistentFrame.setSize(300, 300); // Adjust initial size
        asistentFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza ora:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel mediciPanel = new JPanel();
        mediciPanel.setLayout(new BoxLayout(mediciPanel, BoxLayout.Y_AXIS));

        ArrayList<AsistentMedical> asistentMedicals = AsistentMedical.GetAsistentMedicals(idUnitate);
        for (AsistentMedical asistent : asistentMedicals) {
                JButton asistentButton = new JButton(asistent.getNume() + " " + asistent.getPrenume());
                asistentButton.addActionListener(e -> {
                    asistentMedical = asistent;
                    selectAsistentButton.setText(asistent.getNume() + " " + asistent.getPrenume());
                    asistentFrame.dispose();
                });
                mediciPanel.add(asistentButton);
        }

        JScrollPane mediciScrollPane = new JScrollPane(mediciPanel);
        mainPanel.add(mediciScrollPane);
        asistentFrame.setContentPane(mainPanel);
        asistentFrame.setVisible(true);
    }
}
