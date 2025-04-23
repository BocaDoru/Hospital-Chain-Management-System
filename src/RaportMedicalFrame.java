import javax.swing.*;
import java.awt.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RaportMedicalFrame extends JFrame {
    public static String investigatii="";
    public static void ShowRaportAsistentFrame(RaportMedical raportMedical) {
        JFrame raportFrame = new JFrame("Raport Medical");
        raportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raportFrame.setSize(600, 600); // Adjust initial size
        raportFrame.setLocation(600, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        ArrayList<ServiciuMedical> serviciuMedicals=ServiciuMedical.GetServiciuRaport(raportMedical.getIdRaportMedical());
        for (ServiciuMedical serviciuMedical : serviciuMedicals) {
            JPanel serviciuMedicalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel denumireLabel = new JLabel(serviciuMedical.getDenumire());
            JTextField valoareTextField = new JTextField();
            valoareTextField.setPreferredSize(new Dimension(150, 30));
            valoareTextField.addActionListener(e -> {
                investigatii+=serviciuMedical.getDenumire()+" "+valoareTextField.getText();
                valoareTextField.setEditable(false);
            });
            serviciuMedicalPanel.add(denumireLabel);
            serviciuMedicalPanel.add(valoareTextField);
            mainPanel.add(serviciuMedicalPanel);
        }

        JPanel finishPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        finishPanel.add(okButton);
        finishPanel.add(cancelButton);

        mainPanel.add(finishPanel);

        okButton.addActionListener(e -> {
            if( RaportMedical.SetInvestigatii(raportMedical.getIdRaportMedical(), investigatii))
                JOptionPane.showMessageDialog(raportFrame, "Investigatii salvate!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(raportFrame, "A aparut o eroare", "Error", JOptionPane.ERROR_MESSAGE);
        });
        cancelButton.addActionListener(e -> {
            raportFrame.dispose();
        });

        raportFrame.setContentPane(mainPanel);
        raportFrame.setVisible(true);
    }

    public static void ShowRaportMedicFrame(RaportMedical raportMedical) {
        JFrame raportFrame = new JFrame("Raport Medical");
        raportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raportFrame.setSize(600, 600); // Adjust initial size
        raportFrame.setLocation(600, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Medic medic=Medic.GetMedic(raportMedical.getCNPMedic());
        JPanel medicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel numeMedicLabel = new JLabel("Medic:"+medic.getNume()+" "+medic.getPrenume());
        medicPanel.add(numeMedicLabel);
        mainPanel.add(medicPanel);

        JPanel medicRecomandarePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel recomandareLabel = new JLabel("Medic recomandare:");
        JButton selectMedicRecomandareButton = new JButton("Selecteaza medic recomandare");
        medicRecomandarePanel.add(recomandareLabel);
        medicRecomandarePanel.add(selectMedicRecomandareButton);
        mainPanel.add(medicRecomandarePanel);

        AsistentMedical asistentMedical=AsistentMedical.GetAsistentMedical(raportMedical.getCNPAsistent());
        JPanel asistentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel asistentNumeLabel = new JLabel("Asistent:"+asistentMedical.getNume()+" "+asistentMedical.getPrenume());
        asistentPanel.add(asistentNumeLabel);
        mainPanel.add(asistentPanel);

        ArrayList<ServiciuMedical> serviciuMedicals=ServiciuMedical.GetServiciuRaport(raportMedical.getIdRaportMedical());
        JPanel serviciiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton adaugaServiciuButton=new JButton("Adauga Serviciu");
        serviciiPanel.add(adaugaServiciuButton);
        for (ServiciuMedical serviciuMedical : serviciuMedicals) {
            JPanel serviciuMedicalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel denumireLabel = new JLabel(serviciuMedical.getDenumire());
            JButton stergeButton = new JButton("Sterge");
            stergeButton.addActionListener(e -> {
                serviciuMedicals.remove(serviciuMedical);
                serviciiPanel.remove(serviciuMedicalPanel);
                serviciiPanel.updateUI();
            });
            serviciuMedicalPanel.add(denumireLabel);
            serviciuMedicalPanel.add(stergeButton);
            serviciiPanel.add(serviciuMedicalPanel);
        }
        JScrollPane serviciiScrollPanel = new JScrollPane(serviciiPanel);
        mainPanel.add(serviciiScrollPanel);
        JPanel simptomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel simptomeLabel = new JLabel("Simptome:");
        JTextField simptomeTextField = new JTextField();
        simptomeTextField.setPreferredSize(new Dimension(150, 30));
        simptomePanel.add(simptomeLabel);
        simptomePanel.add(simptomeTextField);
        mainPanel.add(simptomePanel);

        JPanel investigatiiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel investigatiiLabel = new JLabel("Investigatii:"+raportMedical.getInvestigatii());
        investigatiiPanel.add(investigatiiLabel);
        mainPanel.add(investigatiiPanel);

        JPanel diagnosticPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel diagnosticLabel = new JLabel("Diagnostic:");
        JTextField diagnosticTextField = new JTextField();
        diagnosticTextField.setPreferredSize(new Dimension(150, 30));
        diagnosticPanel.add(diagnosticLabel);
        diagnosticPanel.add(diagnosticTextField);
        mainPanel.add(diagnosticPanel);

        JPanel recomandariPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel recomandariLabel = new JLabel("Recomandari:");
        JTextField recomandariTextField = new JTextField();
        recomandariTextField.setPreferredSize(new Dimension(150, 30));
        recomandariPanel.add(recomandariLabel);
        recomandariPanel.add(recomandariTextField);
        mainPanel.add(recomandariPanel);

        JPanel rezultatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel rezultatLabel = new JLabel("Rezultat:");
        JFormattedTextField rezultatTextField = new JFormattedTextField("##");
        rezultatTextField.setPreferredSize(new Dimension(150, 30));
        rezultatPanel.add(rezultatLabel);
        rezultatPanel.add(rezultatTextField);
        mainPanel.add(rezultatPanel);

        JPanel finishPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        finishPanel.add(okButton);
        finishPanel.add(cancelButton);

        mainPanel.add(finishPanel);

        adaugaServiciuButton.addActionListener(e -> {
            ServiciiListFrame.setServicii(serviciuMedicals);
            ServiciiListFrame.ShowServiciiListFrame(asistentMedical.getIdUnitate(),medic.getCNP(),mainPanel,serviciiPanel,adaugaServiciuButton,null);
        });

        selectMedicRecomandareButton.addActionListener(e -> {
            ArrayList<Medic> medics=Medic.GetMedics();
            MediciListFrame.ShowMediciListFrame(medics, selectMedicRecomandareButton);
        });
        okButton.addActionListener(e -> {
            float pret=0;
            int durata=0;
            for (ServiciuMedical s: ServiciiListFrame.getServicii()) {
                pret += s.getPret();
                durata += s.getDurata();
            }
            if (RaportMedical.SetRaport(raportMedical.getIdRaportMedical(),MediciListFrame.getMedic()!=null?MediciListFrame.getMedic().getCNP():null, simptomeTextField.getText(), diagnosticTextField.getText(), recomandariTextField.getText(), Integer.parseInt(rezultatTextField.getText()), medic.getCodParafa())) {
                ServiciuMedical.RemoveServiciuProgramare(raportMedical.getIdRaportMedical());
                ServiciuMedical.RemoveServiciuRaport(raportMedical.getIdRaportMedical());
                ServiciuMedical.AddServiciuProgramare(raportMedical.getIdRaportMedical(), ServiciiListFrame.getServicii());
                ServiciuMedical.AddServiciuRaport(raportMedical.getIdRaportMedical(), ServiciiListFrame.getServicii());
                Programare.SetDurataPret(raportMedical.getIdRaportMedical(),durata,pret);
                JOptionPane.showMessageDialog(raportFrame, "Investigatii salvate!", "Succes", JOptionPane.INFORMATION_MESSAGE);
            } else
                JOptionPane.showMessageDialog(raportFrame, "A aparut o eroare", "Error", JOptionPane.ERROR_MESSAGE);
        });
        cancelButton.addActionListener(e -> {
            raportFrame.dispose();
        });

        raportFrame.setContentPane(mainPanel);
        raportFrame.setVisible(true);
    }
    public static void Update(JPanel mainFrame,JPanel serviciiPanel,JButton adaugaServiciuButton)
    {
        ArrayList<ServiciuMedical> serviciuMedicals=ServiciiListFrame.getServicii();
        serviciiPanel.removeAll();
        serviciiPanel.add(adaugaServiciuButton);
        for (ServiciuMedical serviciuMedical : serviciuMedicals) {
            JPanel serviciuMedicalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel denumireLabel = new JLabel(serviciuMedical.getDenumire());
            JButton stergeButton = new JButton("Sterge");
            stergeButton.addActionListener(e -> {
                serviciuMedicals.remove(serviciuMedical);
                serviciiPanel.remove(serviciuMedicalPanel);
                serviciiPanel.updateUI();
            });
            serviciuMedicalPanel.add(denumireLabel);
            serviciuMedicalPanel.add(stergeButton);
            serviciiPanel.add(serviciuMedicalPanel);
        }
        serviciiPanel.updateUI();
        mainFrame.updateUI();
    }
    public static void ShowRaportIstoricFrame(RaportMedical raportMedical) {
        JFrame raportFrame = new JFrame("Raport Medical");
        raportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        raportFrame.setSize(600, 600); // Adjust initial size
        raportFrame.setLocation(600, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Medic medic=Medic.GetMedic(raportMedical.getCNPMedic());
        JPanel medicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel numeMedicLabel = new JLabel("Medic:"+medic.getNume()+" "+medic.getPrenume());
        medicPanel.add(numeMedicLabel);
        mainPanel.add(medicPanel);

        JPanel medicRecomandarePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Medic medicRecomandare=Medic.GetMedic(raportMedical.getCNPMedicRecomandare());
        JLabel recomandareLabel = new JLabel("Medic recomandare:"+medicRecomandare!=null?(medicRecomandare.getNume()+" "+medicRecomandare.getPrenume()):"");
        medicRecomandarePanel.add(recomandareLabel);
        mainPanel.add(medicRecomandarePanel);

        AsistentMedical asistentMedical=AsistentMedical.GetAsistentMedical(raportMedical.getCNPAsistent());
        JPanel asistentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel asistentNumeLabel = new JLabel("Asistent:"+asistentMedical.getNume()+" "+asistentMedical.getPrenume());
        asistentPanel.add(asistentNumeLabel);
        mainPanel.add(asistentPanel);

        ArrayList<ServiciuMedical> serviciuMedicals=ServiciuMedical.GetServiciuRaport(raportMedical.getIdRaportMedical());
        JPanel serviciiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (ServiciuMedical serviciuMedical : serviciuMedicals) {
            JPanel serviciuMedicalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel denumireLabel = new JLabel(serviciuMedical.getDenumire());
            serviciuMedicalPanel.add(denumireLabel);
            serviciiPanel.add(serviciuMedicalPanel);
        }
        JScrollPane serviciiScrollPanel = new JScrollPane(serviciiPanel);
        mainPanel.add(serviciiScrollPanel);

        JPanel simptomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel simptomeLabel = new JLabel("Simptome:"+raportMedical.getSimptome());
        simptomePanel.add(simptomeLabel);
        mainPanel.add(simptomePanel);

        JPanel investigatiiPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel investigatiiLabel = new JLabel("Investigatii:"+raportMedical.getInvestigatii());
        investigatiiPanel.add(investigatiiLabel);
        mainPanel.add(investigatiiPanel);

        JPanel diagnosticPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel diagnosticLabel = new JLabel("Diagnostic:"+raportMedical.getDiagnostic());
        diagnosticPanel.add(diagnosticLabel);
        mainPanel.add(diagnosticPanel);

        JPanel recomandariPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel recomandariLabel = new JLabel("Recomandari:"+raportMedical.getRecomandari());
        recomandariPanel.add(recomandariLabel);
        mainPanel.add(recomandariPanel);

        JPanel rezultatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel rezultatLabel = new JLabel("Rezultat:"+raportMedical.getRezultat());
        rezultatPanel.add(rezultatLabel);
        mainPanel.add(rezultatPanel);

        JPanel finishPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        finishPanel.add(okButton);

        mainPanel.add(finishPanel);

        okButton.addActionListener(e -> {
            raportFrame.dispose();
        });

        raportFrame.setContentPane(mainPanel);
        raportFrame.setVisible(true);
    }
}
