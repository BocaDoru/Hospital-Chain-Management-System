import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class AngajatFrame extends JFrame {

    private Utilizator angajat;

    public AngajatFrame(Utilizator angajat) {
        this.angajat = angajat;
        setTitle("Detalii Angajat: " + angajat.getCNP() + " " + angajat.getNume() + " " + angajat.getPrenume());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buildUI();
        setVisible(true); // Asigură vizibilitatea ferestrei
    }

    private void buildUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Creează și adaugă secțiunea pentru concedii
        mainPanel.add(createConcediuPanel(angajat.getCNP()));

        // Creează și adaugă secțiunea pentru orarul generic
        mainPanel.add(createOrarGenericPanel());

        // Adaugă orarul specific doar pentru Medic și Asistent Medical
        if (angajat.getFunctie().equals("Medic") || angajat.getFunctie().equals("Asistent Medical")) {
            mainPanel.add(createOrarSpecificPanel());
        }

        this.add(mainPanel);
    }

    private void showOrarDialog() {
        JDialog dialog = new JDialog(this, "Gestionare Orar", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new BorderLayout());

        // Panel principal pentru orare
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adăugăm tab-ul pentru orarul generic
        JPanel genericPanel = createOrarGenericPanel();
        tabbedPane.addTab("Orar Generic", genericPanel);

        // Adăugăm tab-ul pentru orarul specific, doar dacă utilizatorul e Medic/Asistent Medical
        if (angajat.getFunctie().equals("Medic") || angajat.getFunctie().equals("Asistent Medical")) {
            JPanel specificPanel = createOrarSpecificPanel();
            tabbedPane.addTab("Orar Specific", specificPanel);
        }

        dialog.add(tabbedPane, BorderLayout.CENTER);

        JButton btnClose = new JButton("Închide");
        btnClose.addActionListener(e -> dialog.dispose());
        dialog.add(btnClose, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JPanel createOrarGenericPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Lista cu orare generice
        ArrayList<OrarGeneric> orarGenericList = getOrarGenericForAngajat(angajat.getCNP());
        DefaultListModel<OrarGeneric> listModel = new DefaultListModel<>();
        orarGenericList.forEach(listModel::addElement);

        JList<OrarGeneric> list = new JList<>(listModel);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        // Butoane pentru adăugare și modificare
        JPanel buttonPanel = new JPanel();


        JButton btnAdd = new JButton("Adaugă");
        btnAdd.addActionListener(e -> {
            OrarGeneric newOrar = showAddOrarGenericDialog();
            if (newOrar != null) {
                boolean success = addOrarGeneric(newOrar.getCNP(), newOrar.getZi(), newOrar.getStartTime(), newOrar.getEndTime());
                if (success) {
                    listModel.addElement(newOrar);
                    JOptionPane.showMessageDialog(this, "Orarul generic a fost adăugat cu succes!");
                } else {
                    JOptionPane.showMessageDialog(this, "Eroare la adăugarea orarului generic.");
                }
            }
        });

        JButton btnEdit = new JButton("Modifică");
        btnEdit.addActionListener(e -> {
            OrarGeneric selectedOrar = list.getSelectedValue();
            if (selectedOrar != null) {
                OrarGeneric updatedOrar = showEditOrarGenericDialog(selectedOrar);
                if (updatedOrar != null) {
                    boolean success = updateOrarGeneric(selectedOrar.getId(), updatedOrar.getZi(), updatedOrar.getStartTime(), updatedOrar.getEndTime());
                    if (success) {
                        int index = list.getSelectedIndex();
                        listModel.set(index, updatedOrar);
                        JOptionPane.showMessageDialog(this, "Orarul generic a fost modificat cu succes!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Eroare la modificarea orarului generic.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selectați un orar pentru a-l modifica.");
            }
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createOrarSpecificPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Lista cu orare specifice
        ArrayList<OrarSpecific> orarSpecificList = getOrarSpecificForAngajat(angajat.getCNP());
        DefaultListModel<OrarSpecific> listModel = new DefaultListModel<>();
        orarSpecificList.forEach(listModel::addElement);

        JList<OrarSpecific> list = new JList<>(listModel);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        // Butoane pentru adăugare și modificare
        JPanel buttonPanel = new JPanel();

        JButton btnAdd = new JButton("Adaugă");
        btnAdd.addActionListener(e -> {
            OrarSpecific newOrar = showAddOrarSpecificDialog();
            if (newOrar != null) {
                boolean success = addOrarSpecific(newOrar.getCNP(), newOrar.getZi(), newOrar.getStartTime(), newOrar.getEndTime());
                if (success) {
                    listModel.addElement(newOrar);
                    JOptionPane.showMessageDialog(this, "Orarul specific a fost adăugat cu succes!");
                } else {
                    JOptionPane.showMessageDialog(this, "Eroare la adăugarea orarului specific.");
                }
            }
        });

        JButton btnEdit = new JButton("Modifică");
        btnEdit.addActionListener(e -> {
            OrarSpecific selectedOrar = list.getSelectedValue();
            if (selectedOrar != null) {
                OrarSpecific updatedOrar = showEditOrarSpecificDialog(selectedOrar);
                if (updatedOrar != null) {
                    boolean success = updateOrarSpecific(selectedOrar.getId(), updatedOrar.getZi(), updatedOrar.getStartTime(), updatedOrar.getEndTime());
                    if (success) {
                        int index = list.getSelectedIndex();
                        listModel.set(index, updatedOrar);
                        JOptionPane.showMessageDialog(this, "Orarul specific a fost modificat cu succes!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Eroare la modificarea orarului specific.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selectați un orar pentru a-l modifica.");
            }
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private OrarGeneric showAddOrarGenericDialog() {
        JTextField txtZi = new JTextField();
        JTextField txtStartTime = new JTextField();
        JTextField txtEndTime = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Zi:"));
        panel.add(txtZi);
        panel.add(new JLabel("Start Time:"));
        panel.add(txtStartTime);
        panel.add(new JLabel("End Time:"));
        panel.add(txtEndTime);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adaugă Orar Generic", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String zi = txtZi.getText();
                Time startTime = Time.valueOf(txtStartTime.getText());
                Time endTime = Time.valueOf(txtEndTime.getText());

                return new OrarGeneric(0, angajat.getCNP(), zi, startTime, endTime);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Datele introduse sunt invalide. Vă rugăm să verificați formatul orei (HH:mm:ss) și ziua.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private OrarSpecific showAddOrarSpecificDialog() {
        JTextField txtZi = new JTextField();
        JTextField txtStartTime = new JTextField();
        JTextField txtEndTime = new JTextField();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Zi (yyyy-MM-dd):"));
        panel.add(txtZi);
        panel.add(new JLabel("Start Time:"));
        panel.add(txtStartTime);
        panel.add(new JLabel("End Time:"));
        panel.add(txtEndTime);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adaugă Orar Specific", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Date zi = Date.valueOf(txtZi.getText());
                Time startTime = Time.valueOf(txtStartTime.getText());
                Time endTime = Time.valueOf(txtEndTime.getText());

                return new OrarSpecific(0, angajat.getCNP(), zi, startTime, endTime);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Datele introduse sunt invalide. Vă rugăm să verificați formatul datei (yyyy-MM-dd) și orei (HH:mm:ss).", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private OrarGeneric showEditOrarGenericDialog(OrarGeneric orar) {
        JTextField txtZi = new JTextField(orar.getZi());
        JTextField txtStartTime = new JTextField(orar.getStartTime().toString());
        JTextField txtEndTime = new JTextField(orar.getEndTime().toString());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Zi:"));
        panel.add(txtZi);
        panel.add(new JLabel("Start Time:"));
        panel.add(txtStartTime);
        panel.add(new JLabel("End Time:"));
        panel.add(txtEndTime);

        int result = JOptionPane.showConfirmDialog(this, panel, "Modifică Orar Generic", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String zi = txtZi.getText();
                Time startTime = Time.valueOf(txtStartTime.getText());
                Time endTime = Time.valueOf(txtEndTime.getText());

                return new OrarGeneric(orar.getId(), orar.getCNP(), zi, startTime, endTime);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Datele introduse sunt invalide. Vă rugăm să verificați formatul orei (HH:mm:ss) și ziua.", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private OrarSpecific showEditOrarSpecificDialog(OrarSpecific orar) {
        JTextField txtZi = new JTextField(orar.getZi().toString());
        JTextField txtStartTime = new JTextField(orar.getStartTime().toString());
        JTextField txtEndTime = new JTextField(orar.getEndTime().toString());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Zi (yyyy-MM-dd):"));
        panel.add(txtZi);
        panel.add(new JLabel("Start Time:"));
        panel.add(txtStartTime);
        panel.add(new JLabel("End Time:"));
        panel.add(txtEndTime);

        int result = JOptionPane.showConfirmDialog(this, panel, "Modifică Orar Specific", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                Date zi = Date.valueOf(txtZi.getText());
                Time startTime = Time.valueOf(txtStartTime.getText());
                Time endTime = Time.valueOf(txtEndTime.getText());

                return new OrarSpecific(orar.getId(), orar.getCNP(), zi, startTime, endTime);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Datele introduse sunt invalide. Vă rugăm să verificați formatul datei (yyyy-MM-dd) și orei (HH:mm:ss).", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private ArrayList<OrarGeneric> getOrarGenericForAngajat(String cnp) {
        ArrayList<OrarGeneric> orare = new ArrayList<>();
        String sql = "SELECT id, zi, start_time, end_time FROM orargeneric WHERE CNP = ?";

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cnp);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String zi = rs.getString("zi");
                    Time startTime = rs.getTime("start_time");
                    Time endTime = rs.getTime("end_time");
                    orare.add(new OrarGeneric(id, cnp, zi, startTime, endTime));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orare;
    }

    public boolean addOrarGeneric(String cnp, String zi, Time startTime, Time endTime) {
        String sql = "INSERT INTO orargeneric (CNP, zi, start_time, end_time) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cnp);
            stmt.setString(2, zi);
            stmt.setTime(3, startTime);
            stmt.setTime(4, endTime);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateOrarGeneric(int id, String zi, Time startTime, Time endTime) {
        // SQL query to update the orargeneric table
        String sql = "UPDATE orargeneric SET zi = ?, start_time = ?, end_time = ? WHERE id = ?";
        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Debugging the data that's being passed
            System.out.println("Parameters being passed to updateOrarGeneric:");
            System.out.println("ID: " + id); // Primary key, ensure it's correct
            System.out.println("Zi: " + zi); // Work day (e.g., "Monday")
            System.out.println("StartTime: " + startTime); // Format hh:mm:ss
            System.out.println("EndTime: " + endTime); // Format hh:mm:ss

            // Set the parameters for the query
            stmt.setString(1, zi.trim()); // Trimming whitespace from the day
            stmt.setTime(2, startTime); // Start time for 'zi'
            stmt.setTime(3, endTime); // End time for 'zi'
            stmt.setInt(4, id); // ID of the record to update

            // Execute the update query and get the affected rows count
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated in orargeneric: " + rowsUpdated);

            // Return true if at least one row was updated
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            // Print SQL exception details if an error occurs
            System.err.println("SQL Exception occurred during updateOrarGeneric:");
            ex.printStackTrace();
            return false;
        }
    }

    private ArrayList<OrarSpecific> getOrarSpecificForAngajat(String cnp) {
        ArrayList<OrarSpecific> orare = new ArrayList<>();
        String sql = "SELECT id, zi, start_time, end_time FROM orarspecific WHERE CNP = ?";

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cnp);
            ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Date zi = rs.getDate("zi");
                    Time startTime = rs.getTime("start_time");
                    Time endTime = rs.getTime("end_time");
                    orare.add(new OrarSpecific(id, cnp, zi, startTime, endTime));
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return orare;
    }

    public boolean addOrarSpecific(String cnp, Date zi, Time startTime, Time endTime) {
        String sql = "INSERT INTO orarspecific (CNP, zi, start_time, end_time) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = Conexiune.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cnp);
            stmt.setDate(2, zi);
            stmt.setTime(3, startTime);
            stmt.setTime(4, endTime);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateOrarSpecific(int id, Date zi, Time startTime, Time endTime) {
        // SQL query to update the orarspecific table
        String sql = "UPDATE orarspecific SET zi = ?, start_time = ?, end_time = ? WHERE id = ?";
        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Debugging the data that's being passed
            System.out.println("Parameters being passed to updateOrarSpecific:");
            System.out.println("ID: " + id); // Primary key, ensure this matches
            System.out.println("Zi: " + zi); // Specific date (e.g., "2023-12-01")
            System.out.println("StartTime: " + startTime); // Format hh:mm:ss
            System.out.println("EndTime: " + endTime); // Format hh:mm:ss

            // Set the parameters for the query
            stmt.setDate(1, zi); // Set the specific date
            stmt.setTime(2, startTime); // Start time for specific date
            stmt.setTime(3, endTime); // End time for specific date
            stmt.setInt(4, id); // ID of the record to update

            // Execute the update query and get the affected rows count
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated in orarspecific: " + rowsUpdated);

            // Return true if at least one row was updated
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            // Print SQL exception details if an error occurs
            System.err.println("SQL Exception occurred during updateOrarSpecific:");
            ex.printStackTrace();
            return false;
        }
    }



    private JPanel createConcediuPanel(String userCnp) {
        JPanel concediuPanel = new JPanel(new BorderLayout());

        // Step 1: Create JTable with a custom model
        String[] columnNames = {"ID", "CNP", "Data Început", "Data Sfârșit"};
        Object[][] initialData = {}; // Start with an empty table
        JTable concediiTable = new JTable(initialData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        concediiTable.getTableHeader().setReorderingAllowed(false);

        // Step 2: Populate the JTable with data filtered by user CNP
        populateConcediuTable(concediiTable, userCnp);



        // Step 3: Add JTable to JScrollPane
        JScrollPane scrollPane = new JScrollPane(concediiTable);
        concediuPanel.add(scrollPane, BorderLayout.CENTER);

        // Step 4: Add action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdd = new JButton("Adaugă Concediu");
        JButton btnEdit = new JButton("Editează Concediu");
        buttonPanel.add(btnAdd);

        buttonPanel.add(btnEdit);
        concediuPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Step 5: Add event listeners
        btnAdd.addActionListener(e -> showAddConcediuDialog(concediiTable, userCnp));
        btnEdit.addActionListener(e -> {
            int selectedRow = concediiTable.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) concediiTable.getValueAt(selectedRow, 0); // Extract ID
                String cnp = (String) concediiTable.getValueAt(selectedRow, 1);
                Date dataInceput = (Date) concediiTable.getValueAt(selectedRow, 2);
                Date dataSfarsit = (Date) concediiTable.getValueAt(selectedRow, 3);

                // Create Concediu object for editing
                Concediu concediu = new Concediu(id, cnp, dataInceput, dataSfarsit);
                showEditConcediuDialog(concediu, concediiTable);
            } else {
                JOptionPane.showMessageDialog(concediuPanel, "Selectați un concediu pentru a edita!", "Eroare", JOptionPane.WARNING_MESSAGE);
            }
        });

        return concediuPanel;
    }

    private void populateConcediuTable(JTable concediiTable, String cnp) {
        // Step 1: Define the SQL query with a WHERE clause
        String sql = "SELECT id, CNP_angajat, data_inceput, data_sfarsit FROM concediu WHERE CNP_angajat = ?";

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Step 2: Set the cnp parameter in the query
            stmt.setString(1, cnp);
            ResultSet rs = stmt.executeQuery();
                // Step 3: Fetch all matching rows
                ArrayList<Object[]> tableData = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String cnpValue = rs.getString("CNP_angajat");
                    Date dataInceput = rs.getDate("data_inceput");
                    Date dataSfarsit = rs.getDate("data_sfarsit");

                    // Add the row to the tableData
                    tableData.add(new Object[]{id, cnpValue, dataInceput, dataSfarsit});
                }

                // Step 4: Convert List<Object[]> to Object[][]
                Object[][] dataArray = tableData.toArray(new Object[0][]);

                // Step 5: Update JTable model with filtered data
                String[] columnNames = {"ID", "CNP", "Data Început", "Data Sfârșit"};
                concediiTable.setModel(new javax.swing.table.AbstractTableModel() {
                    @Override
                    public int getRowCount() {
                        return dataArray.length;
                    }

                    @Override
                    public int getColumnCount() {
                        return columnNames.length;
                    }

                    @Override
                    public Object getValueAt(int rowIndex, int columnIndex) {
                        return dataArray[rowIndex][columnIndex];
                    }

                    @Override
                    public String getColumnName(int column) {
                        return columnNames[column];
                    }
                });

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Eroare la încărcarea datelor: " + ex.getMessage(),
                    "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void showAddConcediuDialog(JTable concediiTable, String userCnp) {
        // Create input fields (only for data_inceput and data_sfarsit)
        JTextField dataInceputField = new JTextField(20);
        JTextField dataSfarsitField = new JTextField(20);

        // Input panel
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Data Început (yyyy-mm-dd):"));
        panel.add(dataInceputField);
        panel.add(new JLabel("Data Sfârșit (yyyy-mm-dd):"));
        panel.add(dataSfarsitField);

        // Show dialog
        int result = JOptionPane.showConfirmDialog(
                this, panel, "Adăugare Concediu",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String dataInceputStr = dataInceputField.getText();
            String dataSfarsitStr = dataSfarsitField.getText();

            // Validate input
            if (dataInceputStr.isEmpty() || dataSfarsitStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Toate câmpurile trebuie completate!",
                        "Eroare", JOptionPane.WARNING_MESSAGE);
                return;
            }
                // Convert input to date
                java.sql.Date dataInceput = java.sql.Date.valueOf(dataInceputStr);
                java.sql.Date dataSfarsit = java.sql.Date.valueOf(dataSfarsitStr);

                // Insert new row into the database
                String sql = "INSERT INTO concediu (CNP_angajat, data_inceput, data_sfarsit) VALUES (?, ?, ?)";
                try {
                    Connection conn = Conexiune.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    stmt.setString(1, userCnp); // Use current user's CNP
                    stmt.setDate(2, dataInceput);
                    stmt.setDate(3, dataSfarsit);

                    int rowsInserted = stmt.executeUpdate();

                    if (rowsInserted > 0) {
                        // Reload data in the table
                        populateConcediuTable(concediiTable, userCnp);
                        JOptionPane.showMessageDialog(this, "Concediu adăugat cu succes!",
                                "Succes", JOptionPane.INFORMATION_MESSAGE);
                    }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Eroare la adăugarea concediului în baza de date: " + ex.getMessage(),
                        "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showEditConcediuDialog(Concediu concediu, JTable concediiTable) {
        // Step 1: Create input fields pre-filled with the existing data
        JTextField dataInceputField = new JTextField(concediu.getDataInceput().toString(), 20);
        JTextField dataSfarsitField = new JTextField(concediu.getDataSfarsit().toString(), 20);

        // Input panel
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Data Început (yyyy-mm-dd):"));
        panel.add(dataInceputField);
        panel.add(new JLabel("Data Sfârșit (yyyy-mm-dd):"));
        panel.add(dataSfarsitField);

        // Step 2: Show dialog box
        int result = JOptionPane.showConfirmDialog(
                this, panel, "Editare Concediu",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            // Step 3: Get and validate inputs
            String dataInceputStr = dataInceputField.getText();
            String dataSfarsitStr = dataSfarsitField.getText();

            if (dataInceputStr.isEmpty() || dataSfarsitStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Toate câmpurile trebuie completate!", "Eroare", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Convert input to SQL Dates
                java.sql.Date dataInceput = java.sql.Date.valueOf(dataInceputStr);
                java.sql.Date dataSfarsit = java.sql.Date.valueOf(dataSfarsitStr);

                // Step 4: Update the database
                String sql = "UPDATE concediu SET data_inceput = ?, data_sfarsit = ? WHERE id = ? AND CNP_angajat = ?";
                try {
                    Connection conn = Conexiune.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    stmt.setDate(1, dataInceput);
                    stmt.setDate(2, dataSfarsit);
                    stmt.setInt(3, concediu.getId()); // Use the ID to identify the row
                    stmt.setString(4, concediu.getCNP()); // Ensure the row belongs to the correct user

                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        // Step 5: Refresh the table data
                        JOptionPane.showMessageDialog(this, "Concediu actualizat cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
                        populateConcediuTable(concediiTable, concediu.getCNP());
                    } else {
                        JOptionPane.showMessageDialog(this, "Eroare: Actualizarea nu a avut loc!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "Formatul datelor este invalid! Folosiți yyyy-mm-dd.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Eroare la actualizarea bazei de date: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean addConcediu(String cnp, Date startDate, Date endDate) {
        String sql = "INSERT INTO concediu (CNP_angajat, data_inceput, data_sfarsit) VALUES (?, ?, ?)";
        try{
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Set the query parameters
            stmt.setString(1, cnp); // Employee CNP
            stmt.setDate(2, startDate); // Start of vacation
            stmt.setDate(3, endDate); // End of vacation

            // Execute the query
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Rows inserted in concedii: " + rowsInserted);

            // Return true if at least one row was inserted
            return rowsInserted > 0;
        } catch (SQLException ex) {
            // Log SQL exception
            System.err.println("SQL Exception in addConcediu:");
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateConcediu(int id, String cnp, Date startDate, Date endDate) {
        String sql = "UPDATE concediu SET CNP_ANGAJAT = ?, data_inceput = ?, data_sfarsit = ? WHERE id = ?";
        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Set the query parameters
            stmt.setString(1, cnp); // Employee CNP
            stmt.setDate(2, startDate); // Start date
            stmt.setDate(3, endDate); // End date
            stmt.setInt(4, id); // The ID of the specific vacation record to update

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated in concedii: " + rowsUpdated);

            // Return true if at least one row was updated
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            // Log SQL exception if an error occurs
            System.err.println("SQL Exception in updateConcediu:");
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Concediu> getConcediiForAngajat(String cnp) {
        String sql = "SELECT id, data_inceput, data_sfarsit FROM concediu WHERE CNP_angajat = ?";
        ArrayList<Concediu> concedii = new ArrayList<>();

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Set the employee CNP in the query
            stmt.setString(1, cnp);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Extract data from the result set
                    int id = rs.getInt("id");
                    Date startDate = rs.getDate("start_date");
                    Date endDate = rs.getDate("end_date");

                    // Create a Concediu object and add it to the list
                    Concediu concediu = new Concediu(id, cnp, startDate, endDate);
                    concedii.add(concediu);
                }
            }
        } catch (SQLException ex) {
            // Log SQL exception if an error occurs
            System.err.println("SQL Exception in getConcediiForAngajat:");
            ex.printStackTrace();
        }

        return concedii;
    }

}