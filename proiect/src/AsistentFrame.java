import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AsistentFrame extends JFrame {
    public static void ShowAsistentFrame(AsistentMedical asistentMedical) {
        JFrame asistentFrame = new JFrame("Asistent medical");
        asistentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asistentFrame.setSize(250, 300); // Adjust initial size
        asistentFrame.setLocation(600, 300);

        JPanel pacientiSelectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel pacientiInteractPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel selectPacientiLabel1 = new JLabel("Selecteaza un pacient inregistrat:");
        JButton pacientiInregistratiButton = new JButton("Pacienti Inregistrati");
        JLabel selectPacientiLabel2 = new JLabel("Selecteaza un pacient cu programare:");
        JButton pacientiProgramatiButton = new JButton("Pacienti Programati");
        JLabel pacientSelectatLabel = new JLabel("Pacientul selectat:");
        JButton vizualizareInfoButton = new JButton("Vizualizare informatii");
        JButton vizualizareRaporteButton = new JButton("Vizualizare raporte");

        pacientiSelectPanel.add(selectPacientiLabel1);
        pacientiSelectPanel.add(pacientiInregistratiButton);
        pacientiSelectPanel.add(selectPacientiLabel2);
        pacientiSelectPanel.add(pacientiProgramatiButton);
        pacientiInteractPanel.add(pacientSelectatLabel);
        pacientiInteractPanel.add(vizualizareInfoButton);
        pacientiInteractPanel.add(vizualizareRaporteButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(pacientiSelectPanel);
        mainPanel.add(pacientiInteractPanel);

        asistentFrame.setContentPane(mainPanel);
        asistentFrame.setVisible(true);

        pacientiInregistratiButton.addActionListener(e -> {
            ArrayList<Pacient> pacients=Pacient.GetPacients();
            PacientiListFrame.ShowPacientiListFrame(pacientSelectatLabel, pacients);
        });

        pacientiProgramatiButton.addActionListener(e -> {
            ArrayList<Pacient> pacients=Pacient.GetPacientsAsistent(asistentMedical.getCNP());
            PacientiListFrame.ShowPacientiListFrame(pacientSelectatLabel,pacients);
        });

        vizualizareInfoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(asistentFrame, PacientiListFrame.pacient.AllInfo(), "Date personale", JOptionPane.INFORMATION_MESSAGE);
        });

        vizualizareRaporteButton.addActionListener(e -> {
            ArrayList<RaportMedical> raportMedicals = RaportMedical.GetRaportMedicalAsistent(asistentMedical.getCNP(),PacientiListFrame.getPacient().getIdPacient());
            RaportMedicalListFrame.ShowRaportMedicalAsistentListFrame(raportMedicals);
        });
    }
}
