import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RaportMedicalListFrame extends JFrame {
    private static RaportMedical raportMedical;

    public static RaportMedical getTime() {
        return raportMedical;
    }

    public static void ShowRaportMedicalAsistentListFrame(ArrayList<RaportMedical> raportMedicals) {
        JFrame raportFrame = new JFrame("Rapoarte");
        raportFrame.setSize(300, 300); // Adjust initial size
        raportFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza raportul:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel mediciPanel = new JPanel();
        mediciPanel.setLayout(new BoxLayout(mediciPanel, BoxLayout.Y_AXIS));

        int i=1;
        for (RaportMedical r : raportMedicals) {
            JButton raportButton = new JButton("Raport" + i + " " + r.getZi());
            raportButton.addActionListener(e -> {
                raportMedical = r;
                RaportMedicalFrame.ShowRaportAsistentFrame(r);
                raportFrame.dispose();
            });
            i++;
            mediciPanel.add(raportButton);
        }

        JScrollPane mediciScrollPane = new JScrollPane(mediciPanel);
        mainPanel.add(mediciScrollPane);
        raportFrame.setContentPane(mainPanel);
        raportFrame.setVisible(true);
    }

    public static void ShowRaportMedicalMedicListFrame(ArrayList<RaportMedical> raportMedicals) {
        JFrame raportFrame = new JFrame("Rapoarte");
        raportFrame.setSize(300, 300); // Adjust initial size
        raportFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza raportul:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel mediciPanel = new JPanel();
        mediciPanel.setLayout(new BoxLayout(mediciPanel, BoxLayout.Y_AXIS));

        int i=1;
        for (RaportMedical r : raportMedicals) {
            JButton raportButton = new JButton("Raport" + i + " " + r.getZi());
            raportButton.addActionListener(e -> {
                raportMedical = r;
                RaportMedicalFrame.ShowRaportMedicFrame(r);
                raportFrame.dispose();
            });
            i++;
            mediciPanel.add(raportButton);
        }

        JScrollPane mediciScrollPane = new JScrollPane(mediciPanel);
        mainPanel.add(mediciScrollPane);
        raportFrame.setContentPane(mainPanel);
        raportFrame.setVisible(true);
    }

    public static void ShowRaportMedicalPacientListFrame(ArrayList<RaportMedical> raportMedicals) {
        JFrame raportFrame = new JFrame("Rapoarte");
        raportFrame.setSize(300, 300); // Adjust initial size
        raportFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza raportul:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel mediciPanel = new JPanel();
        mediciPanel.setLayout(new BoxLayout(mediciPanel, BoxLayout.Y_AXIS));

        int i=1;
        for (RaportMedical r : raportMedicals) {
            JButton raportButton = new JButton("Raport" + i + " " + r.getZi());
            raportButton.addActionListener(e -> {
                raportMedical = r;
                RaportMedicalFrame.ShowRaportIstoricFrame(r);
                raportFrame.dispose();
            });
            i++;
            mediciPanel.add(raportButton);
        }

        JScrollPane mediciScrollPane = new JScrollPane(mediciPanel);
        mainPanel.add(mediciScrollPane);
        raportFrame.setContentPane(mainPanel);
        raportFrame.setVisible(true);
    }
}
