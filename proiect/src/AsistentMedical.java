import java.sql.*;
import java.util.ArrayList;

public class AsistentMedical extends Medical {
    private int idUnitate;
    private String tip;
    private String grad;

    public AsistentMedical(String prenume, String CNP, String nume, String adresa, String telefon, String email, String cont, String contract, Date angajare, String functie, String parola, float salariu, int numarOreContractuale, int idUnitate, String tip, String grad) {
        super(prenume, CNP, nume, adresa, telefon, email, cont, contract, angajare, functie, parola, salariu, numarOreContractuale);
        this.idUnitate = idUnitate;
        this.tip = tip;
        this.grad = grad;
    }

    public AsistentMedical(float salariu, int numarOreContractuale, int idUnitate, String tip, String grad) {
        super(salariu, numarOreContractuale);
        this.idUnitate = idUnitate;
        this.tip = tip;
        this.grad = grad;
    }

    public AsistentMedical(int idUnitate, String tip, String grad) {
        super();
        this.idUnitate = idUnitate;
        this.tip = tip;
        this.grad = grad;
    }

    public AsistentMedical() {
        super();
    }

    public int getIdUnitate() {
        return idUnitate;
    }

    public void setIdUnitate(int idUnitate) {
        this.idUnitate = idUnitate;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return "('" + idUnitate +
                "','" + tip +
                "','" + grad +
                "')";
    }

    public String AllInfo() {
        return "Medical" +
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
                "\n" + getSalariu() +
                "\n" + getNumarOreContractuale() +
                "\n" + idUnitate +
                "\n" + tip +
                "\n" + grad +
                "\n";
    }


    public static AsistentMedical GetAsistentMedical(Utilizator utilizator) {
        AsistentMedical asistentMedical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT salariu,numar_ore_contractuale,id_unitate,tip,grad FROM Medical JOIN AsistentMedical ON Medical.CNP=AsistentMedical.CNP WHERE AsistentMedical.CNP=?");
            preparedStatement.setString(1, utilizator.getCNP());
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                asistentMedical = new AsistentMedical();
                asistentMedical.setCNP(utilizator.getCNP());
                asistentMedical.setNume(utilizator.getNume());
                asistentMedical.setPrenume(utilizator.getPrenume());
                asistentMedical.setAdresa(utilizator.getAdresa());
                asistentMedical.setTelefon(utilizator.getTelefon());
                asistentMedical.setEmail(utilizator.getEmail());
                asistentMedical.setCont(utilizator.getCont());
                asistentMedical.setContract(utilizator.getContract());
                asistentMedical.setAngajare(utilizator.getAngajare());
                asistentMedical.setFunctie(utilizator.getFunctie());
                asistentMedical.setParola(utilizator.getParola());
                asistentMedical.setSalariu(rs.getFloat("salariu"));
                asistentMedical.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                asistentMedical.setIdUnitate(rs.getInt("id_unitate"));
                asistentMedical.setTip(rs.getString("tip"));
                asistentMedical.setGrad(rs.getString("grad"));
                System.out.println(asistentMedical.AllInfo());
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
        return asistentMedical;
    }

    public static ArrayList<AsistentMedical> GetAsistentMedicals(int idUnitate)
    {
        ArrayList<AsistentMedical> asistentMedicals = new ArrayList<>();
        AsistentMedical asistentMedical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,salariu,numar_ore_contractuale,id_unitate,tip,grad FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP JOIN AsistentMedical ON Medical.CNP=AsistentMedical.CNP WHERE id_unitate=?");
            preparedStatement.setInt(1, idUnitate);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                asistentMedical = new AsistentMedical();
                asistentMedical.setCNP(rs.getString("CNP"));
                asistentMedical.setNume(rs.getString("nume"));
                asistentMedical.setPrenume(rs.getString("prenume"));
                asistentMedical.setAdresa(rs.getString("adresa"));
                asistentMedical.setTelefon(rs.getString("numar_telefon"));
                asistentMedical.setEmail(rs.getString("email"));
                asistentMedical.setCont(rs.getString("cont_IBAN"));
                asistentMedical.setContract(rs.getString("numar_contract"));
                asistentMedical.setAngajare(rs.getDate("data_angajarii"));
                asistentMedical.setFunctie(rs.getString("functie"));
                asistentMedical.setParola(rs.getString("parola"));
                asistentMedical.setSalariu(rs.getFloat("salariu"));
                asistentMedical.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                asistentMedical.setIdUnitate(rs.getInt("id_unitate"));
                asistentMedical.setTip(rs.getString("tip"));
                asistentMedical.setGrad(rs.getString("grad"));
                asistentMedicals.add(asistentMedical);
                System.out.println(asistentMedical.AllInfo());
                count++;
            }
            System.out.println(count + " Asistenti medicali gasiti!");
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
        return asistentMedicals;
    }

    public static AsistentMedical GetAsistentMedical(String CNP)
    {
        AsistentMedical asistentMedical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,salariu,numar_ore_contractuale,id_unitate,tip,grad FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP JOIN AsistentMedical ON Medical.CNP=AsistentMedical.CNP WHERE AsistentMedical.CNP=?");
            preparedStatement.setString(1, CNP);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                asistentMedical = new AsistentMedical();
                asistentMedical.setCNP(rs.getString("CNP"));
                asistentMedical.setNume(rs.getString("nume"));
                asistentMedical.setPrenume(rs.getString("prenume"));
                asistentMedical.setAdresa(rs.getString("adresa"));
                asistentMedical.setTelefon(rs.getString("numar_telefon"));
                asistentMedical.setEmail(rs.getString("email"));
                asistentMedical.setCont(rs.getString("cont_IBAN"));
                asistentMedical.setContract(rs.getString("numar_contract"));
                asistentMedical.setAngajare(rs.getDate("data_angajarii"));
                asistentMedical.setFunctie(rs.getString("functie"));
                asistentMedical.setParola(rs.getString("parola"));
                asistentMedical.setSalariu(rs.getFloat("salariu"));
                asistentMedical.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                asistentMedical.setIdUnitate(rs.getInt("id_unitate"));
                asistentMedical.setTip(rs.getString("tip"));
                asistentMedical.setGrad(rs.getString("grad"));
                System.out.println(asistentMedical.AllInfo());
                return asistentMedical;
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
        return null;
    }
    public static ArrayList<AsistentMedical> GetAsistentMedicals(int idUnitate, Date zi, Time ora)
    {
        ArrayList<AsistentMedical> asistentMedicals = new ArrayList<>();
        AsistentMedical asistentMedical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,salariu,numar_ore_contractuale,id_unitate,tip,grad FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP JOIN AsistentMedical ON Medical.CNP=AsistentMedical.CNP WHERE id_unitate=?");
            preparedStatement.setInt(1, idUnitate);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                asistentMedical = new AsistentMedical();
                asistentMedical.setCNP(rs.getString("CNP"));
                asistentMedical.setNume(rs.getString("nume"));
                asistentMedical.setPrenume(rs.getString("prenume"));
                asistentMedical.setAdresa(rs.getString("adresa"));
                asistentMedical.setTelefon(rs.getString("numar_telefon"));
                asistentMedical.setEmail(rs.getString("email"));
                asistentMedical.setCont(rs.getString("cont_IBAN"));
                asistentMedical.setContract(rs.getString("numar_contract"));
                asistentMedical.setAngajare(rs.getDate("data_angajarii"));
                asistentMedical.setFunctie(rs.getString("functie"));
                asistentMedical.setParola(rs.getString("parola"));
                asistentMedical.setSalariu(rs.getFloat("salariu"));
                asistentMedical.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                asistentMedical.setIdUnitate(rs.getInt("id_unitate"));
                asistentMedical.setTip(rs.getString("tip"));
                asistentMedical.setGrad(rs.getString("grad"));
                asistentMedicals.add(asistentMedical);
                System.out.println(asistentMedical.AllInfo());
                count++;
            }
            System.out.println(count + " Asistenti medicali gasiti!");
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
        return asistentMedicals;
    }

    public static boolean InsertAsistent(String cnp, int idUnitate, String tip,String grad)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;

        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("CALL insert_asistent(?, ?, ?, ?)");
            preparedStatement.setString(1, cnp);
            preparedStatement.setInt(2, idUnitate);
            preparedStatement.setString(3, tip);
            preparedStatement.setString(4, grad);

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