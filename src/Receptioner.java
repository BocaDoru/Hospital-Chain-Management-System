import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Receptioner extends Utilizator {
    private int idUnitateMedicala;

    public Receptioner(String CNP, String nume, String prenume, String adresa, String telefon, String email, String cont, String contract, Date angajare, String functie, String parola, int idUnitateMedicala) {
        super(CNP, nume, prenume, adresa, telefon, email, cont, contract, angajare, functie, parola);
        this.idUnitateMedicala = idUnitateMedicala;
    }

    public Receptioner(int idUnitateMedicala) {
        super();
        this.idUnitateMedicala = idUnitateMedicala;
    }

    public Receptioner() {
        super();
    }

    public int getIdUnitateMedicala() {
        return idUnitateMedicala;
    }

    public void setIdUnitateMedicala(int idUnitateMedicala) {
        this.idUnitateMedicala = idUnitateMedicala;
    }

    @Override
    public String toString() {
        return "('" + idUnitateMedicala +
                "')";
    }

    public String AllInfo() {
        return "Receptioner:" +
                "\n" + getCNP() +
                "\n" + getNume() +
                "\n" + getPrenume() +
                "\n" + getAdresa() +
                "\n" + getTelefon() +
                "\n" + getEmail() +
                "\n" + getCont() +
                "\n" + getContract() +
                "\n" + getAngajare() +
                "\n" + getFunctie() +
                "\n" + getParola() +
                "\n" + idUnitateMedicala +
                "\n";
    }

    public static ArrayList<Receptioner> GetReceptioners() {
        ArrayList<Receptioner> receptioners = new ArrayList<>();
        Receptioner receptioner = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,id_unitate_medicala FROM Utilizator JOIN Receptioner ON Utilizator.CNP=Receptioner.CNP");
            rs = preparedStatement.executeQuery();
            int count = 0;
            while (rs.next()) {
                receptioner = new Receptioner();
                receptioner.setCNP(rs.getString("CNP"));
                receptioner.setNume(rs.getString("nume"));
                receptioner.setPrenume(rs.getString("prenume"));
                receptioner.setAdresa(rs.getString("adresa"));
                receptioner.setTelefon(rs.getString("numar_telefon"));
                receptioner.setEmail(rs.getString("email"));
                receptioner.setCont(rs.getString("cont_IBAN"));
                receptioner.setContract(rs.getString("numar_contract"));
                receptioner.setAngajare(rs.getDate("data_angajarii"));
                receptioner.setFunctie(rs.getString("functie"));
                receptioner.setParola(rs.getString("parola"));
                receptioner.setIdUnitateMedicala(rs.getInt("id_unitate_medicala"));
                receptioners.add(receptioner);
                System.out.println(receptioner.AllInfo());
                count++;
            }
            System.out.println(count + " Personale medicale gasite!");
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
        return receptioners;
    }

    public static Receptioner GetReceptioner(Utilizator utilizator) {
        Receptioner receptioner = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT id_unitate_medicala FROM Receptioner WHERE CNP=?");
            preparedStatement.setString(1, utilizator.getCNP());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                receptioner = new Receptioner();
                receptioner.setCNP(utilizator.getCNP());
                receptioner.setNume(utilizator.getNume());
                receptioner.setPrenume(utilizator.getPrenume());
                receptioner.setAdresa(utilizator.getAdresa());
                receptioner.setTelefon(utilizator.getTelefon());
                receptioner.setEmail(utilizator.getEmail());
                receptioner.setCont(utilizator.getCont());
                receptioner.setContract(utilizator.getContract());
                receptioner.setAngajare(utilizator.getAngajare());
                receptioner.setFunctie(utilizator.getFunctie());
                receptioner.setParola(utilizator.getParola());
                receptioner.setIdUnitateMedicala(rs.getInt("id_unitate_medicala"));
                System.out.println(receptioner.AllInfo());
            }
            else
            {
                System.out.println("No user found with the given email and password.");
            }
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
        return receptioner;
    }

    public static boolean InsertReceptioner(String cnp, int unitate)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;

        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("CALL insert_receptioner(?, ?)");
            preparedStatement.setString(1, cnp);
            preparedStatement.setInt(2, unitate);

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
        return false;
    }
}
