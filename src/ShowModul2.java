import javax.swing.*;
import java.awt.*;

public class ShowModul2 extends JFrame {
    public ShowModul2() {
        super("Modul 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200); // Set initial size of the window
        setLocation(600, 300); // Center the window on the screen
        setResizable(false);

        // Set layout manager
        setLayout(new GridLayout(3, 1, 10, 10)); // 2 rows, 1 column, 10px gap

        // Create buttons
        JButton salariiButton = new JButton("Operatii cu salarii");
        JButton medicButton = new JButton("Salariu medic bonus");
        JButton profitButton = new JButton("Operatii cu profit");

        // Add action listeners (you can implement the logic inside these listeners)
        salariiButton.addActionListener(e -> {
            // Your logic for "Operatii cu salarii" goes here
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    new ShowExpertFinanciarSalarii().setVisible(true);
                }
            });
        });
        medicButton.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    new ShowBonus();
                }
            });
        });

        profitButton.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    new ShowExpertFinanciarProfit().setVisible(true);
                }
            });
        });

        // Add buttons to the frame
        add(salariiButton);
        add(profitButton);
        add(medicButton);

        // Make the frame visible
        setVisible(true);
    }
}