import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServiciiListFrame extends JFrame {
    private static ArrayList<ServiciuMedical> servicii=new ArrayList<>();
    public static ArrayList<ServiciuMedical> getServicii() {
        return servicii;
    }
    public static void setServicii(ArrayList<ServiciuMedical> servicii) {
        ServiciiListFrame.servicii = servicii;
    }

    public static void ShowServiciiListFrame(int idUnitate, String CNPMedic, JComponent component1, JComponent component2, JComponent component3, JComponent component4) {
        JFrame serviciiFrame = new JFrame("Ore");
        serviciiFrame.setSize(300, 300); // Adjust initial size
        serviciiFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel finishPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton finishButton = new JButton("OK");
        finishPanel.add(finishButton);

        JLabel selectLabel = new JLabel("Selecteaza serviciile:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel serviciiPanel = new JPanel();
        serviciiPanel.setLayout(new BoxLayout(serviciiPanel, BoxLayout.Y_AXIS));
        ArrayList<ServiciuMedical> serviciiMedicale = ServiciuMedical.GetServiciiMedicUnitate(idUnitate,CNPMedic);
        for (ServiciuMedical s : serviciiMedicale) {
            JCheckBox checkBox = new JCheckBox(s.getDenumire());
            for(ServiciuMedical s2 :servicii)
            {
                if(s2.getIdServiciu()==s.getIdServiciu())
                    checkBox.setSelected(true);
            }
            checkBox.addActionListener(e ->
            {
                if(checkBox.isSelected())
                    servicii.add(s);
                else
                    servicii.removeIf(x->x.getIdServiciu()==s.getIdServiciu());
            });
            serviciiPanel.add(checkBox);
        }
        finishButton.addActionListener(e -> {
            if(component1 instanceof JLabel&&component2 instanceof JLabel&&component3 instanceof JLabel&&component4 instanceof JLabel)
                ProgramareFrame.UpdateServicii((JLabel)component1,(JLabel)component2,(JLabel)component3,(JLabel)component4);
            else if(component1 instanceof JPanel&&component2 instanceof JPanel&&component3 instanceof JButton&&component4==null)
                RaportMedicalFrame.Update((JPanel)component1,(JPanel)component2,(JButton)component3);
            serviciiFrame.dispose();
        });

        JScrollPane serviciiScrollPane = new JScrollPane(serviciiPanel);
        mainPanel.add(serviciiScrollPane);
        mainPanel.add(finishPanel);
        serviciiFrame.setContentPane(mainPanel);
        serviciiFrame.setVisible(true);
    }
}
