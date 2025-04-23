import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PacientiListFrame {
    public static Pacient pacient;

    public static Pacient getPacient() {
        return pacient;
    }

    public static void ShowPacientiListFrame(JComponent selectPacient,ArrayList<Pacient> pacients) {
        JFrame pacientiFrame = new JFrame("Lista Pacienti");
        pacientiFrame.setSize(300, 300); // Adjust initial size
        pacientiFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza pacientul:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel pacientiPanel = new JPanel();
        pacientiPanel.setLayout(new BoxLayout(pacientiPanel, BoxLayout.Y_AXIS));

        for (Pacient p : pacients) {
            JButton pacientButton = new JButton(p.getNume()+" "+ p.getPrenume());
            pacientButton.addActionListener(e -> {
                pacient = p;
                if(selectPacient instanceof JButton)
                    ((JButton)selectPacient).setText(pacient.getNume()+" "+pacient.getPrenume());
                if(selectPacient instanceof JLabel)
                    ((JLabel)selectPacient).setText("Pacientul selectat:"+pacient.getNume()+" "+pacient.getPrenume());
                pacientiFrame.dispose();
            });
            pacientiPanel.add(pacientButton);
        }

        JScrollPane pacientiScrollPane = new JScrollPane(pacientiPanel);
        mainPanel.add(pacientiScrollPane);
        pacientiFrame.setContentPane(mainPanel);
        pacientiFrame.setVisible(true);
    }
}
