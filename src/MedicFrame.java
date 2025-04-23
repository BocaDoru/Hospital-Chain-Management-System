import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MedicFrame extends JFrame {
    public static void ShowMedicFrame(Medic medic) {
        JFrame medicFrame = new JFrame("Asistent medical");
        medicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        medicFrame.setSize(250, 300); // Adjust initial size
        medicFrame.setLocation(600, 300);

        JPanel pacientiSelectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel pacientiInteractPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel selectPacientiLabel1 = new JLabel("Selecteaza un pacient inregistrat:");
        JButton pacientiInregistratiButton = new JButton("Pacienti Inregistrati");
        JLabel selectPacientiLabel2 = new JLabel("Selecteaza un pacient cu programare:");
        JButton pacientiProgramatiButton = new JButton("Pacienti Programati");
        JLabel pacientSelectatLabel = new JLabel("Pacientul selectat:");
        JButton vizualizareInfoButton = new JButton("Vizualizare informatii");
        JButton vizualizareRaporteButton = new JButton("Vizualizare raporte");
        JButton vizualizareIstoricButton = new JButton("Vizualizare istoric");

        pacientiSelectPanel.add(selectPacientiLabel1);
        pacientiSelectPanel.add(pacientiInregistratiButton);
        pacientiSelectPanel.add(selectPacientiLabel2);
        pacientiSelectPanel.add(pacientiProgramatiButton);
        pacientiInteractPanel.add(pacientSelectatLabel);
        pacientiInteractPanel.add(vizualizareInfoButton);
        pacientiInteractPanel.add(vizualizareRaporteButton);
        pacientiInteractPanel.add(vizualizareIstoricButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(pacientiSelectPanel);
        mainPanel.add(pacientiInteractPanel);

        medicFrame.setContentPane(mainPanel);
        medicFrame.setVisible(true);

        pacientiInregistratiButton.addActionListener(e -> {
            ArrayList<Pacient> pacients=Pacient.GetPacients();
            PacientiListFrame.ShowPacientiListFrame(pacientSelectatLabel,pacients);
        });

        pacientiProgramatiButton.addActionListener(e -> {
            ArrayList<Pacient> pacients=Pacient.GetPacientsMedic(medic.getCNP());
            PacientiListFrame.ShowPacientiListFrame(pacientSelectatLabel,pacients);
        });

        vizualizareInfoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(medicFrame, PacientiListFrame.pacient.AllInfo(), "Date personale", JOptionPane.INFORMATION_MESSAGE);
        });

        vizualizareRaporteButton.addActionListener(e -> {
            ArrayList<RaportMedical> raportMedicals= RaportMedical.GetRaportMedicalMedic(medic.getCNP(),PacientiListFrame.getPacient().getIdPacient());
            RaportMedicalListFrame.ShowRaportMedicalMedicListFrame(raportMedicals);
        });
        vizualizareIstoricButton.addActionListener(e -> {
            ArrayList<RaportMedical> raportMedicals=RaportMedical.GetRaportMedicalPacient(PacientiListFrame.getPacient().getIdPacient());
            RaportMedicalListFrame.ShowRaportMedicalPacientListFrame(raportMedicals);
        });
    }
}
