import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

public class Medic extends Medical{
    private String specialitate;
    private String grad;
    private String codParafa;
    private int procentServicii;
    private String titluStintific;
    private String postDidactic;

    public Medic(String prenume, String CNP, String nume, String adresa, String telefon, String email, String cont, String contract, Date angajare, String functie, String parola, float salariu, int numarOreContractuale, String specialitate, String grad, String codParafa, int procentServicii, String titluStintific, String postDidactic) {
        super(prenume, CNP, nume, adresa, telefon, email, cont, contract, angajare, functie, parola, salariu, numarOreContractuale);
        this.specialitate = specialitate;
        this.grad = grad;
        this.codParafa = codParafa;
        this.procentServicii = procentServicii;
        this.titluStintific = titluStintific;
        this.postDidactic = postDidactic;
    }

    public Medic(float salariu, int numarOreContractuale, String specialitate, String grad, String codParafa, int procentServicii, String titluStintific, String postDidactic) {
        super(salariu, numarOreContractuale);
        this.specialitate = specialitate;
        this.grad = grad;
        this.codParafa = codParafa;
        this.procentServicii = procentServicii;
        this.titluStintific = titluStintific;
        this.postDidactic = postDidactic;
    }

    public Medic(String specialitate, String grad, String codParafa, int procentServicii, String titluStintific, String postDidactic) {
        super();
        this.specialitate = specialitate;
        this.grad = grad;
        this.codParafa = codParafa;
        this.procentServicii = procentServicii;
        this.titluStintific = titluStintific;
        this.postDidactic = postDidactic;
    }

    public Medic() {
        super();
    }

    public String getSpecialitate() {
        return specialitate;
    }

    public void setSpecialitate(String specialitate) {
        this.specialitate = specialitate;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getCodParafa() {
        return codParafa;
    }

    public void setCodParafa(String codParafa) {
        this.codParafa = codParafa;
    }

    public int getProcentServicii() {
        return procentServicii;
    }

    public void setProcentServicii(int procentServicii) {
        this.procentServicii = procentServicii;
    }

    public String getTitluStintific() {
        return titluStintific;
    }

    public void setTitluStintific(String titluStintific) {
        this.titluStintific = titluStintific;
    }

    public String getPostDidactic() {
        return postDidactic;
    }

    public void setPostDidactic(String postDidactic) {
        this.postDidactic = postDidactic;
    }

    @Override
    public String toString() {
        return "('" + specialitate +
                "','" + grad +
                "','" + codParafa +
                "','" + procentServicii +
                "','" + titluStintific +
                "','" + postDidactic +
                "')";
    }
    public String AllInfo()
    {
        return "Medic" +
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
                "\n" + specialitate +
                "\n" + grad +
                "\n" + codParafa +
                "\n" + procentServicii +
                "\n" + titluStintific +
                "\n" + postDidactic +
                "\n";
    }
    public static ArrayList<Medic> GetMedics()
    {
        ArrayList<Medic> medici = new ArrayList<>();
        Medic medic = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,salariu,numar_ore_contractuale,specialitate,grad,cod_parafa,procent_servicii,titlu_stintific,post_didactic FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP JOIN Medic ON Medical.CNP=Medic.CNP");
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                medic = new Medic();
                medic.setCNP(rs.getString("CNP"));
                medic.setNume(rs.getString("nume"));
                medic.setPrenume(rs.getString("prenume"));
                medic.setAdresa(rs.getString("adresa"));
                medic.setTelefon(rs.getString("numar_telefon"));
                medic.setEmail(rs.getString("email"));
                medic.setCont(rs.getString("cont_IBAN"));
                medic.setContract(rs.getString("numar_contract"));
                medic.setAngajare(rs.getDate("data_angajarii"));
                medic.setFunctie(rs.getString("functie"));
                medic.setParola(rs.getString("parola"));
                medic.setSalariu(rs.getFloat("salariu"));
                medic.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                medic.setSpecialitate(rs.getString("specialitate"));
                medic.setGrad(rs.getString("grad"));
                medic.setCodParafa(rs.getString("cod_parafa"));
                medic.setProcentServicii(rs.getInt("procent_servicii"));
                medic.setTitluStintific(rs.getString("titlu_stintific"));
                medic.setPostDidactic(rs.getString("post_didactic"));
                medici.add(medic);
                System.out.println(medic.AllInfo());
                count++;
            }
            System.out.println(count + " Medici gasiti!");
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
        return medici;
    }


    public static Medic GetMedic(String CNP)
    {
        Medic medic = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,salariu,numar_ore_contractuale,specialitate,grad,cod_parafa,procent_servicii,titlu_stintific,post_didactic FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP JOIN Medic ON Medical.CNP=Medic.CNP WHERE Medic.CNP=?");
            preparedStatement.setString(1, CNP);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                medic=new Medic();
                medic.setCNP(rs.getString("CNP"));
                medic.setNume(rs.getString("nume"));
                medic.setPrenume(rs.getString("prenume"));
                medic.setAdresa(rs.getString("adresa"));
                medic.setTelefon(rs.getString("numar_telefon"));
                medic.setEmail(rs.getString("email"));
                medic.setCont(rs.getString("cont_IBAN"));
                medic.setContract(rs.getString("numar_contract"));
                medic.setAngajare(rs.getDate("data_angajarii"));
                medic.setFunctie(rs.getString("functie"));
                medic.setParola(rs.getString("parola"));
                medic.setSalariu(rs.getFloat("salariu"));
                medic.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                medic.setSpecialitate(rs.getString("specialitate"));
                medic.setGrad(rs.getString("grad"));
                medic.setCodParafa(rs.getString("cod_parafa"));
                medic.setProcentServicii(rs.getInt("procent_servicii"));
                medic.setTitluStintific(rs.getString("titlu_stintific"));
                medic.setPostDidactic(rs.getString("post_didactic"));
                System.out.println(medic.AllInfo());
                return medic;
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

    public static ArrayList<Medic> GetMedicsUnitate(int id_unitate)
    {
        ArrayList<Medic> medici = new ArrayList<>();
        Medic medic = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT Utilizator.CNP,nume,prenume,adresa,numar_telefon,email,cont_IBAN,numar_contract,data_angajarii,functie,parola,salariu,numar_ore_contractuale,specialitate,grad,cod_parafa,procent_servicii,titlu_stintific,post_didactic FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP JOIN Medic ON Medical.CNP=Medic.CNP JOIN UnitateMedicala_Medic ON Medic.CNP=UnitateMedicala_Medic.CNP_medic WHERE id_unitate=?");
            preparedStatement.setInt(1, id_unitate);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                medic = new Medic();
                medic.setCNP(rs.getString("CNP"));
                medic.setNume(rs.getString("nume"));
                medic.setPrenume(rs.getString("prenume"));
                medic.setAdresa(rs.getString("adresa"));
                medic.setTelefon(rs.getString("numar_telefon"));
                medic.setEmail(rs.getString("email"));
                medic.setCont(rs.getString("cont_IBAN"));
                medic.setContract(rs.getString("numar_contract"));
                medic.setAngajare(rs.getDate("data_angajarii"));
                medic.setFunctie(rs.getString("functie"));
                medic.setParola(rs.getString("parola"));
                medic.setSalariu(rs.getFloat("salariu"));
                medic.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                medic.setSpecialitate(rs.getString("specialitate"));
                medic.setGrad(rs.getString("grad"));
                medic.setCodParafa(rs.getString("cod_parafa"));
                medic.setProcentServicii(rs.getInt("procent_servicii"));
                medic.setTitluStintific(rs.getString("titlu_stintific"));
                medic.setPostDidactic(rs.getString("post_didactic"));
                medici.add(medic);
                System.out.println(medic.AllInfo());
                count++;
            }
            System.out.println(count + " Medici gasiti!");
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
        return medici;
    }
    public static Medic GetMedic(Utilizator utilizator)
    {
        Medic medic = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT salariu,numar_ore_contractuale,specialitate,grad,cod_parafa,procent_servicii,titlu_stintific,post_didactic FROM Utilizator JOIN Medical ON Utilizator.CNP=Medical.CNP JOIN Medic ON Medical.CNP=Medic.CNP JOIN UnitateMedicala_Medic ON Medic.CNP=UnitateMedicala_Medic.CNP_medic WHERE Medic.CNP=?");
            preparedStatement.setString(1, utilizator.getCNP());
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                medic = new Medic();
                medic.setCNP(utilizator.getCNP());
                medic.setNume(utilizator.getNume());
                medic.setPrenume(utilizator.getPrenume());
                medic.setAdresa(utilizator.getAdresa());
                medic.setTelefon(utilizator.getTelefon());
                medic.setEmail(utilizator.getEmail());
                medic.setCont(utilizator.getCont());
                medic.setContract(utilizator.getContract());
                medic.setAngajare(utilizator.getAngajare());
                medic.setFunctie(utilizator.getFunctie());
                medic.setParola(utilizator.getParola());
                medic.setSalariu(rs.getFloat("salariu"));
                medic.setNumarOreContractuale(rs.getInt("numar_ore_contractuale"));
                medic.setSpecialitate(rs.getString("specialitate"));
                medic.setGrad(rs.getString("grad"));
                medic.setCodParafa(rs.getString("cod_parafa"));
                medic.setProcentServicii(rs.getInt("procent_servicii"));
                medic.setTitluStintific(rs.getString("titlu_stintific"));
                medic.setPostDidactic(rs.getString("post_didactic"));
                System.out.println(medic.AllInfo());
                count++;
            }
            System.out.println(count + " Medici gasiti!");
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
        return medic;
    }

    public static boolean InsertMedic(String cnp, String speciali, String grad,String cod_parafa,int procent_servicii,String titlu_stintific,String post_didactic)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;

        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("CALL insert_medic(?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, cnp);
            preparedStatement.setString(2, speciali);
            preparedStatement.setString(3, grad);
            preparedStatement.setString(4, cod_parafa);
            preparedStatement.setInt(3, procent_servicii);
            preparedStatement.setString(3, titlu_stintific);
            preparedStatement.setString(3, post_didactic);

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

    public static boolean InsertMedicCompetenta(String cnp, int competenta)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;

        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("CALL insert_medic_competenta(?, ?)");
            preparedStatement.setString(1, cnp);
            preparedStatement.setInt(2, competenta);

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

    public static boolean InsertMedicUnitate(String cnp, int unitate)
    {
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;

        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("CALL insert_medic_unitate(?, ?)");
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
