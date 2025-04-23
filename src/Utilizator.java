import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.sql.PreparedStatement;

public class Utilizator {
    private String CNP;
    private String nume;
    private String prenume;
    private String adresa;
    private String telefon;
    private String email;
    private String cont;
    private String contract;
    private Date angajare;
    private String functie;
    private String parola;

    public Utilizator(String CNP, String nume, String prenume, String adresa, String telefon, String email, String cont, String contract, Date angajare, String functie, String parola) {
        this.CNP = CNP;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.cont = cont;
        this.contract = contract;
        this.angajare = angajare;
        this.functie = functie;
        this.parola = parola;
    }
    public Utilizator() {
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Date getAngajare() {
        return angajare;
    }

    public void setAngajare(Date angajare) {
        this.angajare = angajare;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "('" + CNP +
                "','" + nume +
                "','" + prenume +
                "','" + adresa +
                "','" + telefon +
                "','" + email +
                "','" + cont +
                "','" + contract +
                "','" + angajare +
                "','" + functie +
                "','" + parola +
                "')";
    }
    public String AllInfo()
    {
        return  "Utilizator:" +
                "\n" + CNP +
                "\n" + nume +
                "\n" + prenume +
                "\n" + adresa +
                "\n" + telefon +
                "\n" + email +
                "\n" + cont +
                "\n" + contract +
                "\n" + angajare +
                "\n" + functie +
                "\n" + parola +
                "\n";
    }

    public static ArrayList<Utilizator> GetUtilizators()
    {
        ArrayList<Utilizator> utilizators = new ArrayList<>();
        Utilizator utilizator = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola FROM Utilizator");
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                utilizator = new Utilizator();
                utilizator.setCNP(rs.getString("CNP"));
                utilizator.setNume(rs.getString("nume"));
                utilizator.setPrenume(rs.getString("prenume"));
                utilizator.setAdresa(rs.getString("adresa"));
                utilizator.setTelefon(rs.getString("numar_telefon"));
                utilizator.setEmail(rs.getString("email"));
                utilizator.setCont(rs.getString("cont_IBAN"));
                utilizator.setContract(rs.getString("numar_contract"));
                utilizator.setAngajare(rs.getDate("data_angajarii"));
                utilizator.setFunctie(rs.getString("functie"));
                utilizator.setParola(rs.getString("parola"));

                utilizators.add(utilizator);
                System.out.println(utilizator.AllInfo());
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
        return utilizators;
    }

    public static Utilizator Login(String email, String parola)
    {
        Utilizator utilizator = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("select CNP,nume,prenume,adresa,numar_telefon,cont_IBAN,numar_contract,data_angajarii,functie from Utilizator where email=? and parola=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, parola);
            rs = preparedStatement.executeQuery();

            if (rs.next())
            {
                String CNP = rs.getString("CNP");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                String adresa = rs.getString("adresa");
                String telefon = rs.getString("numar_telefon");
                String cont = rs.getString("cont_IBAN");
                String contract = rs.getString("numar_contract");
                Date angajare = rs.getDate("data_angajarii");
                String functie = rs.getString("functie");

                utilizator = new Utilizator(CNP, nume, prenume, adresa, telefon, email, cont, contract, angajare, functie, parola);
                System.out.println("Functie: " + functie);
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
        return utilizator;
    }

    public static boolean InsertUtilizator(String cnp, String nume, String prenume, String adresa, String telefon, String email, String iban, String contract, String data, String functie, String parola)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;

        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("CALL insert_utilizator(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, cnp);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.setString(4, adresa);
            preparedStatement.setString(5, telefon);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, iban);
            preparedStatement.setString(8, contract);
            preparedStatement.setString(9, data);
            preparedStatement.setString(10, functie);
            preparedStatement.setString(11, parola);

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
    public static boolean VerificareUtilizator(String email)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("select * from Utilizator where email=?");
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();
            return rs.next() ;
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
