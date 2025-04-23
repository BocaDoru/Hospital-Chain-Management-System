import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowBonus{
    public ShowBonus(){
        chooseMedic("Profit per Medic");
    }
    private void chooseMedic(String selectedReport) {

        JFrame mediciFrame = new JFrame("Alege Medicul");

        mediciFrame.setSize(300, 300); // Adjust initial size
        mediciFrame.setLocation(600, 300);
        mediciFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ArrayList<Medic> medici = Medic.GetMedics();

        // Main container with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Label at the top-left corner
        JLabel selectLabel = new JLabel("Selecteaza angajatul:");
        mainPanel.add(selectLabel, BorderLayout.NORTH);

        // Panel for buttons with vertical BoxLayout
        JPanel pacientiPanel = new JPanel();
        pacientiPanel.setLayout(new BoxLayout(pacientiPanel, BoxLayout.Y_AXIS));
        ArrayList<String>cnpMedici=new ArrayList<>();
        // Add buttons for each medic
        JButton[] butoane=new JButton[medici.size()];
        for (int i=0;i<butoane.length;i++) {
            butoane[i] = new JButton(medici.get(i).getNume() + " " + medici.get(i).getPrenume());
            //cnpMedici.add(medici.get(i).getCNP());
            int finalI = i;
            butoane[i].addActionListener(e -> {
                // Action for when a medic is selected
                //System.out.println("Medic selected: " + medic.getNume() + " " + medic.getPrenume());
                monthSelect(selectedReport,medici.get(finalI).getCNP(),0,null);
                mediciFrame.dispose(); // Close the window after selection if needed
            });
            pacientiPanel.add(butoane[i]);
        }

        // Scroll pane for the button panel
        JScrollPane pacientiScrollPane = new JScrollPane(pacientiPanel);
        mainPanel.add(pacientiScrollPane, BorderLayout.CENTER);

        mediciFrame.setContentPane(mainPanel);
        mediciFrame.setVisible(true);
    }
    public void monthSelect(String selectedReport,String CNP,int id,String specializare) {
        // Create a new frame for month selection
        JFrame frame = new JFrame("Selecteaza Luna");
        frame.setSize(300, 300);
        frame.setLocation(600, 300);
        frame.setResizable(false);

        // Set layout
        frame.setLayout(new BorderLayout());

        // Panel for month selection
        JPanel monthsPanel = new JPanel();
        monthsPanel.setLayout(new BoxLayout(monthsPanel, BoxLayout.Y_AXIS));

        ArrayList<String> months = new ArrayList<>();
        months.add("Ianuarie");
        months.add("Februarie");
        months.add("Martie");
        months.add("Aprilie");
        months.add("Mai");
        months.add("Iunie");
        months.add("Iulie");
        months.add("August");
        months.add("Septembrie");
        months.add("Octombrie");
        months.add("Noiembrie");
        months.add("Decembrie");

        // Add checkboxes for months
        ArrayList<JCheckBox> monthCheckboxes = new ArrayList<>();
        for (String month : months) {
            JCheckBox checkBox = new JCheckBox(month);
            monthCheckboxes.add(checkBox);
            monthsPanel.add(checkBox);
        }

        JScrollPane scrollPane = new JScrollPane(monthsPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button to generate the report based on selected months and reports
        JButton generateReportButton = new JButton("Genereaza Raport");
        generateReportButton.addActionListener(e -> {
            ArrayList<String> selectedMonths = new ArrayList<>();
            ArrayList<Integer> luni = new ArrayList<>();
            Integer i=0;
            for (JCheckBox checkBox : monthCheckboxes) {
                i++;
                if (checkBox.isSelected()) {
                    luni.add(i);
                    selectedMonths.add(checkBox.getText());
                }
            }
            generateReports(selectedReport,CNP,luni,selectedMonths,frame);
            // Pass the selected reports and months to generate the actual report

        });

        frame.add(generateReportButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    public void generateReports(String selectedReport,String CNP,ArrayList<Integer> luni,ArrayList<String> selectedMonths,JFrame frame) {
        ProceduriSql pr=new ProceduriSql();
        int index=0;
        for(Integer month : luni) {
            String luna="2024-";
            if(month<10)
                luna=luna+"0"+month+"-01";
            else
                luna=luna+month+"-01";
            if(selectedReport.equals("Profit per Medic")){
                double a= pr.callSalariMedic(CNP,luna);
                JOptionPane.showMessageDialog(frame, "Salarul medicului pe luna "+selectedMonths.get(index) +" este:" + a);
            }

            index++;
        }
    }
}
