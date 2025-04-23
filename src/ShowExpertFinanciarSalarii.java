import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class ShowExpertFinanciarSalarii extends JFrame implements ActionListener {
    private  ArrayList<Medical> medicals = Medical.GetMedicals();
    public ShowExpertFinanciarSalarii() {
        super("Lista Angajati");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400); // Adjust initial size
        setLocation(600, 300);
        //show();
        addComponente1();
    }
    private void addComponente1(){


        JPanel mainPanel = new JPanel(new BorderLayout()); // Use BorderLayout for side-by-side panels
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

// Create left panel for "Asistent Medical"
        JPanel asistentPanel = new JPanel();
        asistentPanel.setLayout(new BoxLayout(asistentPanel, BoxLayout.Y_AXIS));
        asistentPanel.setBorder(BorderFactory.createTitledBorder("Asistent Medical"));

// Create right panel for "Medic"
        JPanel medicPanel = new JPanel();
        medicPanel.setLayout(new BoxLayout(medicPanel, BoxLayout.Y_AXIS));
        medicPanel.setBorder(BorderFactory.createTitledBorder("Medic"));

// Get all medical users and separate by functie
        ArrayList<Medical> medicalUsers = Medical.GetMedicals(); // Assumes a method to fetch all Medical users
        for (Medical user : medicalUsers) {
            JButton userButton = new JButton(user.getNume() + " " + user.getPrenume());
            userButton.addActionListener(e -> {
                // Implement what happens when a user is clicked
                if (user.getFunctie().equalsIgnoreCase("Asistent Medical")) {
                    showSalarAsistent(user);
                } else if (user.getFunctie().equalsIgnoreCase("Medic")) {
                    ArrayList<Medic> medici = new ArrayList<>();
                    medici=Medic.GetMedics();
                    for(Medic i:medici){
                        System.out.println(i.getCNP());
                        if(i.getCNP().equals(user.getCNP()))
                        {
                            System.out.println(i.getNume()+" "+i.getPrenume());
                            showSalarMedic(i);
                        }
                    }
                }
            });

            if (user.getFunctie().equalsIgnoreCase("Asistent Medical")) {
                asistentPanel.add(userButton);
            } else if (user.getFunctie().equalsIgnoreCase("Medic")) {
                medicPanel.add(userButton);
            }
        }

// Wrap both panels in scroll panes
        JScrollPane asistentScrollPane = new JScrollPane(asistentPanel);
        JScrollPane medicScrollPane = new JScrollPane(medicPanel);

// Create a container panel for side-by-side scroll panes
        JPanel scrollContainer = new JPanel();
        scrollContainer.setLayout(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, 10px gap
        scrollContainer.add(asistentScrollPane);
        scrollContainer.add(medicScrollPane);

// Add everything to the main panel
        mainPanel.add(scrollContainer, BorderLayout.CENTER);
        setContentPane(mainPanel);
        //  setVisible(true);
    }
    private void addComponente(){


        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza angajatul:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel pacientiPanel = new JPanel();
        pacientiPanel.setLayout(new BoxLayout(pacientiPanel, BoxLayout.Y_AXIS));


        for (Medical medic : medicals) {
            JButton pacientButton = new JButton(medic.getNume()+" "+medic.getPrenume());
            pacientButton.addActionListener(e -> {});
            pacientiPanel.add(pacientButton);
            pacientButton.addActionListener(this);
        }

        JScrollPane pacientiScrollPane = new JScrollPane(pacientiPanel);
        mainPanel.add(pacientiScrollPane);
        setContentPane(mainPanel);
        //setVisible(true);
    }
    public static ArrayList<Integer> countWeekdaysInMonth(int year, int month) {
        // Create an ArrayList to store the counts of weekdays
        ArrayList<Integer> weekdayCounts = new ArrayList<>();

        // Initialize counts for each weekday (Monday to Friday)
        for (int i = 0; i < 5; i++) {
            weekdayCounts.add(0);
        }

        // Get the total number of days in the given month
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();

        // Iterate through each day of the month
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            // Check if the day is a weekday (Monday to Friday)
            if (dayOfWeek.getValue() >= DayOfWeek.MONDAY.getValue() &&
                    dayOfWeek.getValue() <= DayOfWeek.FRIDAY.getValue()) {
                // Increment the count for the corresponding weekday
                int index = dayOfWeek.getValue() - DayOfWeek.MONDAY.getValue();
                weekdayCounts.set(index, weekdayCounts.get(index) + 1);
            }
        }

        return weekdayCounts;
    }

    private int nr_ore(ArrayList<OrarGeneric> OrarGenerics,ArrayList<Integer> weekdayCounts){
        int nrOre=0;
        for (OrarGeneric orarGeneric : OrarGenerics) {
            // System.out.println(orarGeneric.getZi());
            if (orarGeneric.getZi().equals("Luni")) {
                Time t1 = orarGeneric.getEndTime();
                Time t2 = orarGeneric.getStartTime();
                Duration duration = Duration.between(t2.toLocalTime(), t1.toLocalTime());

                // Get the total hours and add to the count
                long hours = duration.toHours();
                // System.out.println(hours);
                nrOre += weekdayCounts.get(0) * (int) hours;
            }

            if (orarGeneric.getZi().equals("Marti")) {
                Time t1 = orarGeneric.getEndTime();
                Time t2 = orarGeneric.getStartTime();
                Duration duration = Duration.between(t2.toLocalTime(), t1.toLocalTime());

                // Get the total hours and add to the count
                long hours = duration.toHours();
                // System.out.println(hours);
                nrOre += weekdayCounts.get(1) * (int) hours;
            }

            if (orarGeneric.getZi().equals("Miercuri")) {
                Time t1 = orarGeneric.getEndTime();
                Time t2 = orarGeneric.getStartTime();
                Duration duration = Duration.between(t2.toLocalTime(), t1.toLocalTime());

                // Get the total hours and add to the count
                long hours = duration.toHours();
                //System.out.println(hours);
                nrOre += weekdayCounts.get(2) * (int) hours;
            }

            if (orarGeneric.getZi().equals("Joi")) {
                Time t1 = orarGeneric.getEndTime();
                Time t2 = orarGeneric.getStartTime();
                Duration duration = Duration.between(t2.toLocalTime(), t1.toLocalTime());

                // Get the total hours and add to the count
                long hours = duration.toHours();
                //System.out.println(hours);
                nrOre += weekdayCounts.get(3) * (int) hours;
            }

            if (orarGeneric.getZi().equals("Vineri")) {
                Time t1 = orarGeneric.getEndTime();
                Time t2 = orarGeneric.getStartTime();
                Duration duration = Duration.between(t2.toLocalTime(), t1.toLocalTime());

                // Get the total hours and add to the count
                long hours = duration.toHours();
                //System.out.println(hours);
                nrOre += weekdayCounts.get(4) * (int) hours;
            }

        }
        return nrOre;
    }
    public void showSalarAsistent(Medical medic){

        JFrame pacientiFrame = new JFrame("Salariu "+medic.getNume()+" "+medic.getPrenume());
        //pacientiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pacientiFrame.setSize(400, 400); // Adjust initial size
        pacientiFrame.setLocation(600, 300);
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ArrayList<OrarGeneric> OrarGenerics = new ArrayList<>();


        OrarGenerics=OrarGeneric.GetOrareUtilizatori(medic.getCNP());


        JLabel selectLabel = new JLabel("Salariu "+medic.getNume()+" "+medic.getPrenume());
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.add(selectPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges
        ArrayList<String> months = new ArrayList<String>();

        // Add all months to the Vector
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


        JPanel monthsPanel = new JPanel();
        monthsPanel.setLayout(new BoxLayout(monthsPanel, BoxLayout.Y_AXIS));
        for (String month : months) {
            JButton pacientButton = new JButton(month);
            ArrayList<OrarGeneric> finalOrarGenerics = OrarGenerics;
            pacientButton.addActionListener(e -> {
                int luna= months.indexOf(e.getActionCommand())+1;
                ArrayList<Integer> weekdayCounts = countWeekdaysInMonth(2024, luna);
                int nrOre=nr_ore(finalOrarGenerics,weekdayCounts);
                float salar=nrOre*medic.getSalariu();
                selectLabel.setText(e.getActionCommand()+": Nr_Ore:"+" "+nrOre+" "+"Salar:"+salar);
            });
            monthsPanel.add(pacientButton);
        }
        JScrollPane pacientiScrollPane = new JScrollPane(monthsPanel);
        mainPanel.add(pacientiScrollPane);
        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);


        pacientiFrame.setContentPane(mainPanel);
        pacientiFrame.setVisible(true);
    }


    public void showSalarMedic(Medic medic){

        JFrame pacientiFrame = new JFrame("Salariu "+medic.getNume()+" "+medic.getPrenume());
        //pacientiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pacientiFrame.setSize(400, 400); // Adjust initial size
        pacientiFrame.setLocation(600, 300);
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ArrayList<OrarGeneric> OrarGenerics = new ArrayList<>();


        OrarGenerics=OrarGeneric.GetOrareUtilizatori(medic.getCNP());


        JLabel selectLabel = new JLabel("Salariu "+medic.getNume()+" "+medic.getPrenume());
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.add(selectPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges
        ArrayList<String> months = new ArrayList<String>();

        // Add all months to the Vector
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



        JPanel monthsPanel = new JPanel();
        monthsPanel.setLayout(new BoxLayout(monthsPanel, BoxLayout.Y_AXIS));
        for (String month : months) {
            JButton pacientButton = new JButton(month);
            ArrayList<OrarGeneric> finalOrarGenerics = OrarGenerics;

            //float finalParte_medic = parte_medic;
            pacientButton.addActionListener(e -> {
                ArrayList<Programare> programari =Programare.GetProgramari(medic.getCNP());
                float parte_medic=0;

                int luna= months.indexOf(e.getActionCommand())+1;

                for(Programare prog:programari){
                    int luna1=prog.getZi().getMonth();
                    System.out.println(prog.getPret());
                    if (luna==luna1)
                        parte_medic+=prog.getPret()*(medic.getProcentServicii()/100.0);
                }


                ArrayList<Integer> weekdayCounts = countWeekdaysInMonth(2024, luna);
                int nrOre=nr_ore(finalOrarGenerics,weekdayCounts);
                float salar=nrOre*medic.getSalariu();
                selectLabel.setText(e.getActionCommand()+": Nr_Ore:"+" "+nrOre+" "+"Salar:"+salar+ "+ "+ parte_medic);
            });
            monthsPanel.add(pacientButton);
        }
        JScrollPane pacientiScrollPane = new JScrollPane(monthsPanel);
        mainPanel.add(pacientiScrollPane);
        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);


        pacientiFrame.setContentPane(mainPanel);
        pacientiFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String [] numprem= e.getActionCommand().split(" ");

        Medical medic=Medical.findMedical(numprem[0],numprem[1],medicals);//din medical medic sau asistent
        System.out.println(medic.getCNP());
        if (medic.getFunctie().equals("Asistent Medical"))
        {
            showSalarAsistent(medic);
        }
        if (medic.getFunctie().equals("Medic"))
        {
            ArrayList<Medic> medici = new ArrayList<>();
            medici=Medic.GetMedics();
            for(Medic i:medici){
                System.out.println(i.getCNP());
                if(i.getCNP().equals(medic.getCNP()))
                {
                    System.out.println(i.getNume()+" "+i.getPrenume());
                    showSalarMedic(i);
                }
            }

        }
    }
}