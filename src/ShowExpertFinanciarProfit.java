import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowExpertFinanciarProfit extends JFrame {
    public ShowExpertFinanciarProfit() {
        super("Selecteaza Raport");
        setSize(300, 300);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(600, 300);
        setResizable(false);

        setLayout(new BorderLayout());

        // Panel for report types
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(new GridLayout(4, 1)); // Adjust layout for radio buttons

        ButtonGroup reportGroup = new ButtonGroup(); // Group to ensure only one can be selected

        JRadioButton medicProfitRadioButton = new JRadioButton("Profit per Medic");
        JRadioButton unitateProfitRadioButton = new JRadioButton("Profit per Locatie");
        JRadioButton specialitateProfitRadioButton = new JRadioButton("Profit per Specialitate");
        JRadioButton totalProfitRadioButton = new JRadioButton("Profit Total");

        // Add radio buttons to the group so only one can be selected at a time
        reportGroup.add(medicProfitRadioButton);
        reportGroup.add(unitateProfitRadioButton);
        reportGroup.add(specialitateProfitRadioButton);
        reportGroup.add(totalProfitRadioButton);

        // Add radio buttons to the panel
        reportPanel.add(medicProfitRadioButton);
        reportPanel.add(unitateProfitRadioButton);
        reportPanel.add(specialitateProfitRadioButton);
        reportPanel.add(totalProfitRadioButton);

        add(reportPanel, BorderLayout.CENTER);

        // Button to generate report based on the selected option
        JButton generateButton = new JButton("Genereaza Raport");
        generateButton.addActionListener(e -> {
            // Get the selected report type
            String selectedReport = null;
            int ok=0;
            if (medicProfitRadioButton.isSelected()) {
                selectedReport = "Profit per Medic";
                chooseMedic(selectedReport);
                ok=1;
                selectedReport=null;
            }
            else if (unitateProfitRadioButton.isSelected()) {
                selectedReport = "Profit per Locatie";
                chooseUnitate(selectedReport);
                ok=1;
                selectedReport=null;
            }
            else if (specialitateProfitRadioButton.isSelected()) {
                selectedReport = "Profit per Specialitate";
                chooseSpecializare(selectedReport);
                ok=1;
                selectedReport=null;
            }
            else if (totalProfitRadioButton.isSelected()) {
                selectedReport = "Profit Total";
                ok=1;
            }

            if ( ok==1 && selectedReport != null) {
                //ArrayList<String> selectedReports = new ArrayList<>();
                //selectedReports.add(selectedReport);
                monthSelect(selectedReport,null,0,null); // Call method to select months and generate the report
            } else if(ok==0 && selectedReport == null) {
                JOptionPane.showMessageDialog(this, "Te rog selecteaza un raport.");
            }
        });

        add(generateButton, BorderLayout.SOUTH);

        setVisible(true);
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

    private void chooseSpecializare(String selectedReport) {
        JFrame specializareFrame = new JFrame("Alege Specializarea");

        specializareFrame.setSize(300, 300); // Adjust initial size
        specializareFrame.setLocation(600, 300);
        specializareFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ProceduriSql pr=new ProceduriSql();
        ArrayList<String> specializari = pr.getSpecializari();

        // Main container with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Label at the top-left corner
        JLabel selectLabel = new JLabel("Selecteaza unitatea:");
        mainPanel.add(selectLabel, BorderLayout.NORTH);

        // Panel for buttons with vertical BoxLayout
        JPanel specializariPanel = new JPanel();
        specializariPanel.setLayout(new BoxLayout(specializariPanel, BoxLayout.Y_AXIS));
        ArrayList<String>cnpMedici=new ArrayList<>();
        // Add buttons for each medic
        JButton[] butoane=new JButton[specializari.size()];
        for (int i=0;i<butoane.length;i++) {
            butoane[i] = new JButton(specializari.get(i));
            //cnpMedici.add(medici.get(i).getCNP());
            int finalI = i;
            butoane[i].addActionListener(e -> {
                // Action for when a medic is selected
                //System.out.println("Medic selected: " + medic.getNume() + " " + medic.getPrenume());
                monthSelect(selectedReport,null,0,e.getActionCommand());
                specializareFrame.dispose(); // Close the window after selection if needed
            });
            specializariPanel.add(butoane[i]);
        }

        // Scroll pane for the button panel
        JScrollPane unitatiScrollPane = new JScrollPane(specializariPanel);
        mainPanel.add(unitatiScrollPane, BorderLayout.CENTER);

        specializareFrame.setContentPane(mainPanel);
        specializareFrame.setVisible(true);
    }
    private void chooseUnitate(String selectedReport) {
        JFrame unitateFrame = new JFrame("Alege Unitatea");

        unitateFrame.setSize(300, 300); // Adjust initial size
        unitateFrame.setLocation(600, 300);
        unitateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ArrayList<UnitateMedicala> unitati = UnitateMedicala.GetUnitati();

        // Main container with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Label at the top-left corner
        JLabel selectLabel = new JLabel("Selecteaza unitatea:");
        mainPanel.add(selectLabel, BorderLayout.NORTH);

        // Panel for buttons with vertical BoxLayout
        JPanel unitatiPanel = new JPanel();
        unitatiPanel.setLayout(new BoxLayout(unitatiPanel, BoxLayout.Y_AXIS));
        ArrayList<String>cnpMedici=new ArrayList<>();
        // Add buttons for each medic
        JButton[] butoane=new JButton[unitati.size()];
        for (int i=0;i<butoane.length;i++) {
            butoane[i] = new JButton(unitati.get(i).getDenumire());
            //cnpMedici.add(medici.get(i).getCNP());
            int finalI = i;
            butoane[i].addActionListener(e -> {
                // Action for when a medic is selected
                //System.out.println("Medic selected: " + medic.getNume() + " " + medic.getPrenume());
                monthSelect(selectedReport,null,unitati.get(finalI).getIdUnitateMedicala(),null);
                unitateFrame.dispose(); // Close the window after selection if needed
            });
            unitatiPanel.add(butoane[i]);
        }

        // Scroll pane for the button panel
        JScrollPane unitatiScrollPane = new JScrollPane(unitatiPanel);
        mainPanel.add(unitatiScrollPane, BorderLayout.CENTER);

        unitateFrame.setContentPane(mainPanel);
        unitateFrame.setVisible(true);
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

            // Pass the selected reports and months to generate the actual report
            generateReport(selectedReport, selectedMonths,luni, CNP,0,specializare,frame);
        });

        frame.add(generateReportButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void generateReport(String selectedReport, ArrayList<String> selectedMonths,ArrayList<Integer> luni,String CNP,int idUnitate,String Specializare,JFrame fr) {
        //System.out.print(luni);
        ProceduriSql pr=new ProceduriSql();
        int index=0;
        for(Integer month : luni) {
            String luna="2024-";
            if(month<10)
                luna=luna+"0"+month+"-01";
            else
                luna=luna+month+"-01";
            if(selectedReport.equals("Profit per Medic")){
                double a= pr.callCalculProfitMedic(CNP,luna);
                JOptionPane.showMessageDialog(fr, "Profitul pe luna "+selectedMonths.get(index) +" este:" + a);
            }
            if(selectedReport.equals("Profit per Locatie")){
                double a=pr.callCalculProfitUnitate(idUnitate,luna);
                JOptionPane.showMessageDialog(fr, "Profitul pe luna "+selectedMonths.get(index) +" este:" + a);
            }
            if(selectedReport.equals("Profit per Specialitate")){
                double a=pr.callCalculProfitPeSpecialitate(Specializare,luna);
                JOptionPane.showMessageDialog(fr, "Profitul pe luna "+selectedMonths.get(index) +" este:" + a+ Specializare);
            }
            if(selectedReport.equals("Profit Total")){
                ArrayList<Integer> countDaysOfWeek=ShowExpertFinanciarSalarii.countWeekdaysInMonth(2024,month);
                double a =pr.calcTotal(countDaysOfWeek,luna);
                JOptionPane.showMessageDialog(fr, "Profitul pe luna "+selectedMonths.get(index) +" este:" + a+ Specializare);
            }
            index++;
        }
    }
}
