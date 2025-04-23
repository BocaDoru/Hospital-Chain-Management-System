import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnitateMedicala {
    private int idUnitateMedicala;
    private String denumire;
    private String adresa;

    public UnitateMedicala(int idUnitateMedicala, String denumire, String adresa) {
        this.idUnitateMedicala = idUnitateMedicala;
        this.denumire = denumire;
        this.adresa = adresa;
    }

    public UnitateMedicala() {
    }

    public int getIdUnitateMedicala() {
        return idUnitateMedicala;
    }

    public void setIdUnitateMedicala(int idUnitateMedicala) {
        this.idUnitateMedicala = idUnitateMedicala;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "('" + idUnitateMedicala +
                "','" + denumire +
                "','" + adresa +
                "')";
    }

    public static ArrayList<UnitateMedicala> GetUnitati()
    {
        ArrayList<UnitateMedicala> unitati = new ArrayList<>();
        UnitateMedicala unitate = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * from unitatemedicala");
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                unitate=new UnitateMedicala();
                unitate.setIdUnitateMedicala(rs.getInt("id"));
                unitate.setDenumire(rs.getString("denumire"));
                unitate.setAdresa(rs.getString("adresa"));
                unitati.add(unitate);
                // System.out.println(medic.AllInfo());
                count++;
            }
            System.out.println(count + " unitati gasite!");
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
        return unitati;
    }
}
