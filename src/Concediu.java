import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Concediu {
    private int id;
    private String CNP;
    private Date dataInceput;
    private Date dataSfarsit;

    public Concediu(int id, String CNP, Date startDate, Date endDate) {
        this.id = id;
        this.CNP = CNP;
        this.dataInceput = startDate;
        this.dataSfarsit = endDate;
    }

    public Concediu() {
    }

    public int getId() {
        return id;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataSfarsit() {
        return dataSfarsit;
    }

    public void setDataSfarsit(Date dataSfarsit) {
        this.dataSfarsit = dataSfarsit;
    }

    @Override
    public String toString() {
        return "Concediu ID: " + id + ", Perioada: " + dataInceput + " - " + dataSfarsit;
    }

    public static Concediu GetConcediu(String CNP, Date date)
    {
        Concediu concediu = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT data_inceput,data_sfarsit FROM Concediu WHERE CNP_angajat=? AND data_inceput<=? AND data_sfarsit>=?");
            preparedStatement.setString(1, CNP);
            preparedStatement.setDate(2, date);
            preparedStatement.setDate(3, date);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                concediu = new Concediu();
                concediu.setDataInceput(rs.getDate("data_inceput"));
                concediu.setDataSfarsit(rs.getDate("data_sfarsit"));
                System.out.println(concediu.toString());
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
        return concediu;
    }
}
