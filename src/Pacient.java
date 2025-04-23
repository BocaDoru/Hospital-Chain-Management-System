import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class Pacient {
    private int idPacient;
    private String nume;
    private String prenume;
    private Date dataNasterii;
    private String adresa;
    private String telefon;
    private String email;

    public Pacient(int idPacient, String nume, String prenume, Date dataNasterii, String adresa, String telefon, String email) {
        this.idPacient = idPacient;
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
    }

    public Pacient() {
    }

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "('" + idPacient +
                "','" + nume +
                "','" + prenume +
                "','" + dataNasterii +
                "','" + adresa +
                "','" + telefon +
                "','" + email +
                "')";
    }

    public String AllInfo()
    {
        return "Pacient:" +
                "\n" + idPacient +
                "\n" + nume +
                "\n" + prenume +
                "\n" + dataNasterii +
                "\n" + adresa +
                "\n" + telefon +
                "\n" + email +
                "\n";
    }

    public static boolean CreazaPacient(String nume, String prenume, Date dataNasterii, String adresa, String telefon, String email)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;
        if(!VerificarePacient(email)) {
            try {
                preparedStatement = Conexiune.getConnection().prepareStatement("INSERT INTO Pacient(nume, prenume, data_nasterii, adresa, numar_telefon, email) VALUES (?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, nume);
                preparedStatement.setString(2, prenume);
                preparedStatement.setDate(3, dataNasterii);
                preparedStatement.setString(4, adresa);
                preparedStatement.setString(5, telefon);
                preparedStatement.setString(6, email);

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            } catch (SQLException sqlex) {
                System.err.println("An SQL Exception occured. Details are provided below:");
                sqlex.printStackTrace(System.err);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                    }
                    rs = null;
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                    }
                    preparedStatement = null;
                }
            }
        }
        return false;
    }

    public static boolean VerificarePacient(String email)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("select * from Pacient where email=?");
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
                preparedStatement = null;
            }
        }
        return false;
    }
    public static ArrayList<Pacient> GetPacients()
    {
        ArrayList<Pacient> pacients = new ArrayList<>();
        Pacient pacient = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM Pacient");
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                pacient = new Pacient();
                pacient.setIdPacient(rs.getInt("id"));
                pacient.setNume(rs.getString("nume"));
                pacient.setPrenume(rs.getString("prenume"));
                pacient.setDataNasterii(rs.getDate("data_nasterii"));
                pacient.setAdresa(rs.getString("adresa"));
                pacient.setTelefon(rs.getString("numar_telefon"));
                pacient.setEmail(rs.getString("email"));

                pacients.add(pacient);
                System.out.println(pacient.AllInfo());
                count++;
            }
            System.out.println(count + " Utilizatori gasiti!");
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
                preparedStatement = null;
            }
        }
        return pacients;
    }

    public static ArrayList<Pacient> GetPacientsAsistent(String CNPAsistent)
    {
        ArrayList<Pacient> pacients = new ArrayList<>();
        Pacient pacient = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT DISTINCT Pacient.id,nume,prenume,data_nasterii,adresa,numar_telefon,email FROM Pacient JOIN Programare ON Pacient.id=Programare.id_pacient JOIN RaportMedical ON Programare.id=RaportMedical.id WHERE Programare.CNP_asistent=? AND RaportMedical.investigatii IS NULL");
            preparedStatement.setString(1, CNPAsistent);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                pacient = new Pacient();
                pacient.setIdPacient(rs.getInt("id"));
                pacient.setNume(rs.getString("nume"));
                pacient.setPrenume(rs.getString("prenume"));
                pacient.setDataNasterii(rs.getDate("data_nasterii"));
                pacient.setAdresa(rs.getString("adresa"));
                pacient.setTelefon(rs.getString("numar_telefon"));
                pacient.setEmail(rs.getString("email"));

                if(!pacients.contains(pacient)) {
                    pacients.add(pacient);
                    System.out.println(pacient.AllInfo());
                    count++;
                }
            }
            System.out.println(count + " Utilizatori gasiti!");
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
                preparedStatement = null;
            }
        }
        return pacients;
    }

    public static ArrayList<Pacient> GetPacientsMedic(String CNPMedic)
    {
        ArrayList<Pacient> pacients = new ArrayList<>();
        Pacient pacient = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT DISTINCT Pacient.id,nume,prenume,data_nasterii,adresa,numar_telefon,email FROM Pacient JOIN Programare ON Pacient.id=Programare.id_pacient JOIN RaportMedical ON Programare.id=RaportMedical.id WHERE Programare.CNP_medic=? AND RaportMedical.cod_parafa IS NULL");
            preparedStatement.setString(1, CNPMedic);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                pacient = new Pacient();
                pacient.setIdPacient(rs.getInt("id"));
                pacient.setNume(rs.getString("nume"));
                pacient.setPrenume(rs.getString("prenume"));
                pacient.setDataNasterii(rs.getDate("data_nasterii"));
                pacient.setAdresa(rs.getString("adresa"));
                pacient.setTelefon(rs.getString("numar_telefon"));
                pacient.setEmail(rs.getString("email"));

                if(!pacients.contains(pacient)) {
                    pacients.add(pacient);
                    System.out.println(pacient.AllInfo());
                    count++;
                }
            }
            System.out.println(count + " Utilizatori gasiti!");
        } catch (SQLException sqlex) {
            System.err.println("An SQL Exception occured. Details are provided below:");
            sqlex.printStackTrace(System.err);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
                preparedStatement = null;
            }
        }
        return pacients;
    }
}
