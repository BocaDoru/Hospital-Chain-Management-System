import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Medical extends Utilizator{
    private float salariu;
    private int numarOreContractuale;

    public Medical(String CNP, String nume,String prenume, String adresa, String telefon, String email, String cont, String contract, Date angajare, String functie, String parola, float salariu, int numarOreContractuale) {
        super(CNP, nume, prenume, adresa, telefon, email, cont, contract, angajare, functie, parola);
        this.salariu = salariu;
        this.numarOreContractuale = numarOreContractuale;
    }
    public Medical(float salariu, int numarOreContractuale) {
        super();
        this.salariu = salariu;
        this.numarOreContractuale = numarOreContractuale;
    }
    public Medical() {
        super();
    }


    public float getSalariu() {
        return salariu;
    }

    public void setSalariu(float salariu) {
        this.salariu = salariu;
    }

    public int getNumarOreContractuale() {
        return numarOreContractuale;
    }

    public void setNumarOreContractuale(int numarOreContractuale) {
        this.numarOreContractuale = numarOreContractuale;
    }

    @Override
    public String toString() {
        return "('" + salariu +
                "','" + numarOreContractuale +
                "')";
    }

    public String AllInfo()
    {
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
                "\n" + salariu +
                "\n" + numarOreContractuale +
                "\n";
    }

    public static ArrayList<Medical> GetMedicals()
    {
        ArrayList<Medical> medicals = new ArrayList<>();
        Medical medical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,salariu,numar_ore_contractuale FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP");
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                medical = new Medical();
                medical.setCNP(rs.getString("CNP"));
                medical.setNume(rs.getString("nume"));
                medical.setPrenume(rs.getString("prenume"));
                medical.setAdresa(rs.getString("adresa"));
                medical.setTelefon(rs.getString("numar_telefon"));
                medical.setEmail(rs.getString("email"));
                medical.setCont(rs.getString("cont_IBAN"));
                medical.setContract(rs.getString("numar_contract"));
                medical.setAngajare(rs.getDate("data_angajarii"));
                medical.setFunctie(rs.getString("functie"));
                medical.setParola(rs.getString("parola"));
                medical.setSalariu(rs.getFloat("salariu"));
                medical.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                medicals.add(medical);
                System.out.println(medical.AllInfo());
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
        return medicals;
    }
    public static boolean InsertMedical(String cnp, float salariu, int numarOreContractuale)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;

        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("CALL insert_medical(?, ?, ?)");
            preparedStatement.setString(1, cnp);
            preparedStatement.setFloat(2, salariu);
            preparedStatement.setInt(3, numarOreContractuale);

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

    public static Medical findMedical(String Nume,String Prenume, ArrayList<Medical> medicals){
        Medical med=null;
        for (Medical medic : medicals) {
            if(medic.getNume().equals(Nume)&& medic.getPrenume().equals(Prenume)){

                System.out.println("l am gasit ");
                return medic;
            }
        }
        System.out.println("nu l am gasit ");
        return null;
    }

}
