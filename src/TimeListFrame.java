import javax.swing.*;
import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;

public class TimeListFrame extends JFrame {
    private static Time time;

    public static Time getTime() {
        return time;
    }

    public static void ShowTimeListFrame(ArrayList<Time> times,JButton selectTimeButton) {
        JFrame timesFrame = new JFrame("Ore");
        timesFrame.setSize(300, 300); // Adjust initial size
        timesFrame.setLocation(600, 300);

        // Panels for user input and buttons
        JPanel selectPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel selectLabel = new JLabel("Selecteaza ora:");
        selectPanel.add(selectLabel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around the edges

        // Add glue for vertical centering
        mainPanel.add(Box.createVerticalGlue()); // Flexible space above
        mainPanel.add(selectPanel);

        JPanel mediciPanel = new JPanel();
        mediciPanel.setLayout(new BoxLayout(mediciPanel, BoxLayout.Y_AXIS));

        for (Time t : times) {
            JButton timeButton = new JButton(t.toString().substring(0,5));
            timeButton.addActionListener(e -> {
                time = t;
                selectTimeButton.setText(t.toString().substring(0,5));
                timesFrame.dispose();
            });
            mediciPanel.add(timeButton);
        }

        JScrollPane mediciScrollPane = new JScrollPane(mediciPanel);
        mainPanel.add(mediciScrollPane);
        timesFrame.setContentPane(mainPanel);
        timesFrame.setVisible(true);
    }
}
