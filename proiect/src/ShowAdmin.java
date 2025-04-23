import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowAdmin extends JFrame {
    private JTable userTable;
    private DefaultTableModel tableModel;

    public ShowAdmin(String functie) {
        super("Admin Panel");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table model and table
        String[] columnNames = {"CNP", "nume", "prenume", "adresa", "numar_telefon", "email", "cont_IBAN", "numar_contract", "data_angajarii", "functie"};
        tableModel = new DefaultTableModel(columnNames, 0);
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow single row selection
        JScrollPane scrollPane = new JScrollPane(userTable);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10)); // 1 row, 3 columns

        JButton addButton = new JButton("Add User");
        JButton editButton = new JButton("Edit User");
        JButton deleteButton = new JButton("Delete User");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add components to main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        setContentPane(mainPanel);

        // Populate the table with dummy data for demonstration
        populateTable(functie);

        // Add button action listeners
        addButton.addActionListener(e -> addUser());
        editButton.addActionListener(e -> editUser());
        deleteButton.addActionListener(e -> deleteUser());
    }

    private void populateTable(String functie) {
        // Sample data
        ArrayList<Utilizator> utilizatori=Utilizator.GetUtilizators();
        for (Utilizator utilizator : utilizatori) {
            // Extract data from each Utilizator and add it as a row
            Object []row = new Object[10];
            if(functie.equals("Admin")) {
                if (!utilizator.getFunctie().equals("Super Admin") && !utilizator.getFunctie().equals("Admin")) {
                    Object[] rowdata = {
                            utilizator.getCNP(),
                            utilizator.getNume(),
                            utilizator.getPrenume(),
                            utilizator.getAdresa(),
                            utilizator.getTelefon(),
                            utilizator.getEmail(),
                            utilizator.getCont(),
                            utilizator.getContract(),
                            utilizator.getAngajare(),
                            utilizator.getFunctie(),

                    };
                    row = rowdata;
                    tableModel.addRow(row);
                }
            }
            else if(functie.equals("Super Admin"))
                if(!utilizator.getFunctie().equals("Super Admin")){
                    Object []rowdata = {
                            utilizator.getCNP(),
                            utilizator.getNume(),
                            utilizator.getPrenume(),
                            utilizator.getAdresa(),
                            utilizator.getTelefon(),
                            utilizator.getEmail(),
                            utilizator.getCont(),
                            utilizator.getContract(),
                            utilizator.getAngajare(),
                            utilizator.getFunctie(),

                    };
                    row=rowdata;
                    tableModel.addRow(row);
                }

        }


    }
    private static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("Buttons Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1,5,5)); // 7 rows, 1 column

        // Create the buttons manually with the desired labels
        JButton button1 = new JButton("Asistent Medical");
        button1.addActionListener(e->{
            InserariFrame.ShowSignupAsistentFrame();
        });
        JButton button2 = new JButton("Medic");
        button2.addActionListener(e->{
            InserariFrame.ShowSignupMedicFrame();
        });
        JButton button3 = new JButton("Medic Competenta");
        button3.addActionListener(e->{
            InserariFrame.ShowSignupMedicCompetentaFrame();
        });
        JButton button4 = new JButton("Medic Unitate");
        button4.addActionListener(e->{
            InserariFrame.ShowSignupMedicUnitateFrame();
        });
        JButton button5 = new JButton("Utilizator");
        button5.addActionListener(e->{
            InserariFrame.ShowSignupUtilizatorFrame();
        });
        JButton button6 = new JButton("Medical");
        button6.addActionListener(e->{
            InserariFrame.ShowSignupMedicalFrame();
        });
        JButton button7 = new JButton("Receptioner");
        button7.addActionListener(e->{
            InserariFrame.ShowSignupReceptionerFrame();
        });

        // Add the buttons to the panel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
    private void addUser() {
        createAndShowGUI();
    }

    private void editUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to edit.");
            return;
        }
        String CNP = userTable.getValueAt(selectedRow, 0).toString();
        SignupFrame.ShowSignupFrame(CNP);
        // Get selected user data


        // Logic for editing user (open a dialog with pre-filled data)

    }

    private void deleteUser() {

        int selectedRow = userTable.getSelectedRow();
        String cnp = (String) userTable.getValueAt(selectedRow, 0);
        ProceduriSql pr=new ProceduriSql();
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this user?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Remove user from table
            tableModel.removeRow(selectedRow);
            pr.callDeleteUser(cnp);
        }
    }
}