import java.sql.*;
import java.util.ArrayList;

public class OrarGeneric {
    private int id;
    private String CNP;
    private String zi;
    private Time startTime;
    private Time endTime;

    public OrarGeneric(String zi, Time startTime, Time endTime) {
        this.zi = zi;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OrarGeneric(int id, String CNP, String zi, Time startTime, Time endTime) {
        this.id = id;
        this.CNP = CNP;
        this.zi = zi;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public OrarGeneric() {
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
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

    public static int StringToDay(String zi) {
        switch (zi) {
            case "Duminica":
                return 1;
            case "Luni":
                return 2;
            case "Marti":
                return 3;
            case "Miercuri":
                return 4;
            case "Joi":
                return 5;
            case "Vineri":
                return 6;
            case "Sambata":
                return 7;
            default:
                return 0;
        }
    }

    public static String DateToString(Date zi) {
        int day=zi.toLocalDate().getDayOfWeek().getValue();
        switch (day) {
            case 1:
                return "Luni";
            case 2:
                return "Marti";
            case 3:
                return "Miercuri";
            case 4:
                return "Joi";
            case 5:
                return "Vineri";
            case 6:
                return "Sambata";
            case 7:
                return "Duminica";
            default:
                return null;
        }
    }

    public static OrarGeneric GetOrarGenericUnitate(int idUnitate, Date date)
    {
        OrarGeneric orarGeneric = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT zi,start_time,end_time FROM OrarGenericUnitate WHERE id_unitate=? AND zi=?");
            preparedStatement.setInt(1, idUnitate);
            preparedStatement.setString(2, DateToString(date));
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                orarGeneric = new OrarGeneric();
                orarGeneric.setZi(rs.getString("zi"));
                orarGeneric.setStartTime(rs.getTime("start_time"));
                orarGeneric.setEndTime(rs.getTime("end_time"));
                System.out.println(orarGeneric.toString());
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
        return orarGeneric;
    }
    public static OrarGeneric GetOrarGenericUtilizator(String CNP, Date date)
    {
        OrarGeneric orarGeneric = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT zi,start_time,end_time FROM OrarGeneric WHERE CNP=? AND zi=?");
            preparedStatement.setString(1, CNP);
            preparedStatement.setString(2, DateToString(date));
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                orarGeneric = new OrarGeneric();
                orarGeneric.setZi(rs.getString("zi"));
                orarGeneric.setStartTime(rs.getTime("start_time"));
                orarGeneric.setEndTime(rs.getTime("end_time"));
                System.out.println(orarGeneric.toString());
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
        return orarGeneric;
    }

    public static ArrayList<OrarGeneric> GetOrareUtilizatori(String CNP){

        ArrayList<OrarGeneric> OrarGenerics = new ArrayList<>();
        OrarGeneric orar = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT zi,start_time,end_time FROM OrarGeneric where CNP=?");
            preparedStatement.setString(1, CNP);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                orar = new OrarGeneric();
                orar.setZi(rs.getString("zi"));
                // System.out.println(orar.getZi());
                orar.setStartTime(rs.getTime("start_time"));
                orar.setEndTime(rs.getTime("end_time"));
                OrarGenerics.add(orar);
                count++;
            }
            System.out.println(count + " zile in orar gasite");
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


        return OrarGenerics;
    }
}
