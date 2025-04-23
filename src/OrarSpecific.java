import java.sql.*;

public class OrarSpecific {
    private int id;
    private String CNP;
    private Date zi;
    private Time startTime;
    private Time endTime;

    public OrarSpecific(Date zi, Time startTime, Time endTime) {
        this.zi = zi;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OrarSpecific(int id, String CNP, java.sql.Date zi, Time startTime, Time endTime) {
        this.id = id;
        this.CNP = CNP;
        this.zi = zi;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OrarSpecific() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Date getZi() {
        return zi;
    }

    public void setZi(Date zi) {
        this.zi = zi;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "('" + zi +
                "','" + startTime +
                "','" + endTime +
                "')";
    }

    public static OrarSpecific GetOrarSpecific(String CNP, Date date)
    {
        OrarSpecific orarSpecific = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT zi,start_time,end_time FROM OrarSpecific WHERE CNP=? AND zi=?");
            preparedStatement.setString(1, CNP);
            preparedStatement.setDate(2, date);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                orarSpecific = new OrarSpecific();
                orarSpecific.setZi(rs.getDate("zi"));
                orarSpecific.setStartTime(rs.getTime("start_time"));
                orarSpecific.setEndTime(rs.getTime("end_time"));
                System.out.println(orarSpecific.toString());
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
        return orarSpecific;
    }
}
