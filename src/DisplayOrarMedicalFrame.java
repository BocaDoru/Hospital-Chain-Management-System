import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayOrarMedicalFrame extends JFrame {

    private Utilizator utilizator;

    // Constructor to initialize the utilizator and create the UI
    public DisplayOrarMedicalFrame(Utilizator utilizator) {
        this.utilizator = utilizator;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Informații Utilizator: " + utilizator.getNume());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the layout to GridLayout to vertically split the window into 3 parts
        setLayout(new GridLayout(3, 1)); // 3 rows, 1 column -> equal vertical space

        // Create concedii table
        JTable concediiTable = createConcediiTable();
        JPanel concediiPanel = new JPanel(new BorderLayout());
        concediiPanel.setBorder(BorderFactory.createTitledBorder("Concedii Utilizator"));
        concediiPanel.add(new JScrollPane(concediiTable), BorderLayout.CENTER);

        // Create generic orar table
        JTable orarGenericTable = createGenericOrarTable();
        JPanel genericOrarPanel = new JPanel(new BorderLayout());
        genericOrarPanel.setBorder(BorderFactory.createTitledBorder("Orar Generic"));
        genericOrarPanel.add(new JScrollPane(orarGenericTable), BorderLayout.CENTER);

        // Create specific orar table (conditionally)
        JPanel specificOrarPanel = new JPanel(new BorderLayout());
        JTable orarSpecificTable = null;

        if ("Medic".equals(utilizator.getFunctie()) || "Asistent Medical".equals(utilizator.getFunctie())) {
            orarSpecificTable = createSpecificOrarTable();
            specificOrarPanel.setBorder(BorderFactory.createTitledBorder("Orar Specific"));
            specificOrarPanel.add(new JScrollPane(orarSpecificTable), BorderLayout.CENTER);
            add(specificOrarPanel); // Add the specific orar panel to the frame
        }

        // Add concedii and generic orar panels to the frame
        add(concediiPanel); // Add concedii (1/3 of the window)
        add(genericOrarPanel); // Add generic orar (1/3 of the window)

        // Add specific orar panel only if it exists
        if (orarSpecificTable != null) {
            add(specificOrarPanel); // Add specific orar (1/3 of the window)
        } else {
            // If no Specific Orar, add an empty placeholder for symmetry
            JPanel placeholderPanel = new JPanel();
            placeholderPanel.setBorder(BorderFactory.createTitledBorder("Fără Orar Specific"));
            add(placeholderPanel);
        }

        // Set frame size and center it
        setSize(900, 700); // Adjust size (width x height)
        setLocationRelativeTo(null);
    }

    // Define the concedii table
    private JTable createConcediiTable() {
        String[] columnNames = {"ID", "CNP", "Data Început", "Data Sfârșit"};
        Object[][] data = fetchConcediiData();

        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        table.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        table.setRowHeight(25); // Adjust row height
        return table;
    }

    private Object[][] fetchConcediiData() {
        List<Object[]> tableData = new ArrayList<>();
        String sql = "SELECT id, CNP_angajat, data_inceput, data_sfarsit FROM concediu WHERE CNP_angajat = ?";

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, utilizator.getCNP());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cnp = rs.getString("CNP_angajat");
                Date dataInceput = rs.getDate("data_inceput");
                Date dataSfarsit = rs.getDate("data_sfarsit");
                tableData.add(new Object[]{id, cnp, dataInceput, dataSfarsit});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableData.toArray(new Object[0][]);
    }

    private JTable createGenericOrarTable() {
        String[] columnNames = {"ID", "CNP", "Zi", "Start Time", "End Time"};
        Object[][] data = fetchGenericOrarData();

        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25); // Adjust row height
        return table;
    }

    private Object[][] fetchGenericOrarData() {
        List<Object[]> tableData = new ArrayList<>();
        String sql = "SELECT id, CNP, zi, start_time, end_time FROM orargeneric WHERE CNP = ?";

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, utilizator.getCNP());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cnp = rs.getString("CNP");
                String zi = rs.getString("zi");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");
                tableData.add(new Object[]{id, cnp, zi, startTime, endTime});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableData.toArray(new Object[0][]);
    }

    private JTable createSpecificOrarTable() {
        String[] columnNames = {"ID", "CNP", "Zi", "Start Time", "End Time"};
        Object[][] data = fetchSpecificOrarData();

        JTable table = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(25); // Adjust row height
        return table;
    }

    private Object[][] fetchSpecificOrarData() {
        List<Object[]> tableData = new ArrayList<>();
        String sql = "SELECT id, CNP, zi, start_time, end_time FROM orarspecific WHERE CNP = ?";

        try {
            Connection conn = Conexiune.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, utilizator.getCNP());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cnp = rs.getString("CNP");
                Date zi = rs.getDate("zi");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");
                tableData.add(new Object[]{id, cnp, zi, startTime, endTime});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableData.toArray(new Object[0][]);
    }
}