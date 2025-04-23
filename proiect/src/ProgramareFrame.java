import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ProgramareFrame {
    private static ArrayList<Time> times=new ArrayList<>();
    private static Time ora;
    private static Date zi;
    private static int durata;
    private static float pret;
    private static String bon;
    public static void ShowProgramareFrame(Receptioner receptioner) {
        JFrame programareFrame = new JFrame("Programare");
        programareFrame.setSize(600, 600); // Adjust initial size
        programareFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel pacientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel medicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel asistentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel serviciiPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel serviciiPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel durataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel bonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel pretPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel finishPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel pacientLabel = new JLabel("Pacient:");
        JButton selectPacientButton = new JButton("Selecteza pacientul");
        JButton createPacientButton = new JButton("Creaza un nou pacient");
        pacientPanel.add(pacientLabel);
        pacientPanel.add(selectPacientButton);
        pacientPanel.add(createPacientButton);

        JLabel dateLabel = new JLabel("Data:");
        JFormattedTextField dateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        dateField.setPreferredSize(new Dimension(150, 30));
        datePanel.add(dateLabel);
        datePanel.add(dateField);

        JLabel timeLabel = new JLabel("Ora:");
        JButton selectTimeButton = new JButton("Selecteza ora");
        timePanel.add(timeLabel);
        timePanel.add(selectTimeButton);

        JLabel medicLabel = new JLabel("Medic:");
        JButton selectMedicButton = new JButton("Selecteza medicul");
        medicPanel.add(medicLabel);
        medicPanel.add(selectMedicButton);

        JLabel asistentLabel = new JLabel("Asistent:");
        JButton selectAsistentButton = new JButton("Selecteza asistentul medical");
        asistentPanel.add(asistentLabel);
        asistentPanel.add(selectAsistentButton);

        JButton selectServiciiButton = new JButton("Selecteza serviciile");
        JLabel serviciiSelectateLabel = new JLabel("Servicii selectate:");
        serviciiPanel1.add(selectServiciiButton);
        serviciiPanel2.add(serviciiSelectateLabel);

        JLabel durataLabel = new JLabel("Durata:0min");
        durataPanel.add(durataLabel);

        JLabel bonLabel = new JLabel("Bon:");
        bonPanel.add(bonLabel);

        JLabel pretLabel = new JLabel("Pret:");
        pretPanel.add(pretLabel);

        JButton finishButton = new JButton("Creaza programarea");
        finishPanel.add(finishButton);

        // Main panel with vertical alignment
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        mainPanel.add(pacientPanel);
        mainPanel.add(medicPanel);
        mainPanel.add(asistentPanel);
        mainPanel.add(serviciiPanel1);
        mainPanel.add(serviciiPanel2);
        mainPanel.add(durataPanel);
        mainPanel.add(datePanel);
        mainPanel.add(timePanel);
        mainPanel.add(bonPanel);
        mainPanel.add(pretPanel);
        mainPanel.add(finishPanel);

        programareFrame.setContentPane(mainPanel);
        programareFrame.setVisible(true);

        selectPacientButton.addActionListener(e -> {
            ArrayList<Pacient> pacients=Pacient.GetPacients();
            PacientiListFrame.ShowPacientiListFrame(selectPacientButton,pacients);
        });

        createPacientButton.addActionListener(e -> {
            CreerePacientFrame.ShowCreerePacientFrame();
        });

        selectMedicButton.addActionListener(e -> {
            ArrayList<Medic> medics =Medic.GetMedicsUnitate(receptioner.getIdUnitateMedicala());
            MediciListFrame.ShowMediciListFrame(medics, selectMedicButton);
        });

        dateField.addActionListener(e -> {
            zi = Date.valueOf(dateField.getText());
            times = Programare.GetTimes(receptioner.getIdUnitateMedicala(), MediciListFrame.getMedic().getCNP(), AsistentListFrame.getAsistentMedical().getCNP(), zi, durata);
            if (times.size() == 0)
                JOptionPane.showMessageDialog(programareFrame, "Nu se pot face programari la aceasta data", "Error", JOptionPane.ERROR_MESSAGE);
            else
                TimeListFrame.ShowTimeListFrame(times, selectTimeButton);
        });

        selectTimeButton.addActionListener(e -> {
            if (times != null && times.size() != 0)
                TimeListFrame.ShowTimeListFrame(times, selectTimeButton);
            else
                JOptionPane.showMessageDialog(programareFrame, "Nu se pot face programari la aceasta data", "Error", JOptionPane.ERROR_MESSAGE);
        });

        selectAsistentButton.addActionListener(e -> {
            AsistentListFrame.ShowAsistentListFrame(receptioner.getIdUnitateMedicala(), selectAsistentButton);
        });

        selectServiciiButton.addActionListener(e -> {
            ServiciiListFrame.ShowServiciiListFrame(receptioner.getIdUnitateMedicala(), MediciListFrame.getMedic().getCNP(), durataLabel, bonLabel, pretLabel, serviciiSelectateLabel);
        });

        finishButton.addActionListener(e -> {
            Programare programare = Programare.AddProgramare(zi, TimeListFrame.getTime(), MediciListFrame.getMedic().getCNP(), receptioner.getCNP(), AsistentListFrame.getAsistentMedical().getCNP(), PacientiListFrame.getPacient().getIdPacient(), durata, bon, pret);
            if (programare != null) {
                if (ServiciuMedical.AddServiciuProgramare(programare.getIdProgramare(), ServiciiListFrame.getServicii())) {
                    RaportMedical raportMedical=RaportMedical.AddRaportMedical(zi,MediciListFrame.getMedic().getCNP(),AsistentListFrame.getAsistentMedical().getCNP(),PacientiListFrame.getPacient().getIdPacient());
                    if (raportMedical != null){
                        if(ServiciuMedical.AddServiciuRaport(raportMedical.getIdRaportMedical(), ServiciiListFrame.getServicii()))
                        {
                            JOptionPane.showMessageDialog(programareFrame, "Programare realizata cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
                        }else
                            JOptionPane.showMessageDialog(programareFrame, "Programare esuata!", "Error", JOptionPane.ERROR_MESSAGE);

                    }else
                        JOptionPane.showMessageDialog(programareFrame, "Programare esuata!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(programareFrame, "Programare esuata!", "Error", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(programareFrame, "Programare esuata!", "Error", JOptionPane.ERROR_MESSAGE);
        });
    }
    public static void UpdateServicii(JLabel durataLabel,JLabel bonLabel,JLabel pretLabel,JLabel serviciiLabel)
    {
        pret=0;
        durata=0;
        bon="";
        String servicii="";
        for (ServiciuMedical s:ServiciiListFrame.getServicii())
        {
            durata+=s.getDurata();
            pret+=s.getPret();
            bon+="\n"+s.getDenumire()+" "+s.getPret()+"Lei";
            servicii+="\n"+s.getDenumire()+" "+s.getDurata()+"min";
        }
        durataLabel.setText("Durata:"+durata+"min");
        bonLabel.setText("Bon:"+bon);
        pretLabel.setText("Pret:"+pret);
        serviciiLabel.setText("Servicii selectate:"+servicii);
    }
}
