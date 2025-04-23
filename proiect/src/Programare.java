import java.sql.*;
import java.util.ArrayList;

public class Programare {
    private int idProgramare;
    private Date zi;
    private Time ora ;
    private String CNPMedic;
    private String CNPReceptioner;
    private String CNPAsistent;
    private int idPacient;
    private int durata;
    private String bon;
    private float pret;

    public Programare(int idProgramare, Date zi, Time ora, String CNPMedic, String CNPReceptioner, String CNPAsistent, int idPacient, int durata, String bon, float pret) {
        this.idProgramare = idProgramare;
        this.zi = zi;
        this.ora = ora;
        this.CNPMedic = CNPMedic;
        this.CNPReceptioner = CNPReceptioner;
        this.CNPAsistent = CNPAsistent;
        this.idPacient = idPacient;
        this.durata = durata;
        this.bon = bon;
        this.pret = pret;
    }

    public Programare() {
    }

    public int getIdProgramare() {
        return idProgramare;
    }

    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public Date getZi() {
        return zi;
    }

    public void setZi(Date zi) {
        this.zi = zi;
    }

    public Time getOra() {
        return ora;
    }

    public void setOra(Time ora) {
        this.ora = ora;
    }

    public String getCNPMedic() {
        return CNPMedic;
    }

    public void setCNPMedic(String CNPMedic) {
        this.CNPMedic = CNPMedic;
    }

    public String getCNPReceptioner() {
        return CNPReceptioner;
    }

    public void setCNPReceptioner(String CNPReceptioner) {
        this.CNPReceptioner = CNPReceptioner;
    }

    public String getCNPAsistent() {
        return CNPAsistent;
    }

    public void setCNPAsistent(String CNPAsistent) {
        this.CNPAsistent = CNPAsistent;
    }

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getBon() {
        return bon;
    }

    public void setBon(String bon) {
        this.bon = bon;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "('" + idProgramare +
                "','" + zi +
                "','" + ora +
                "','" + CNPMedic +
                "','" + CNPReceptioner +
                "','" + CNPAsistent +
                "','" + idPacient +
                "','" + durata +
                "','" + bon +
                "','" + pret +
                "')";
    }
    public static boolean SetDurataPret(int idProgramare,int durata,float pret) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("UPDATE Programare SET durata=?,pret=? WHERE id=?");
            preparedStatement.setInt(1, durata);
            preparedStatement.setFloat(2, pret);
            return preparedStatement.executeUpdate()>0;
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
    public static Programare AddProgramare(Date zi,Time ora,String CNPMedic,String CNPReceptioner,String CNPAsistent,int idPacient,int durata,String bon,float pret)
    {
        Programare programare = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement=null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("INSERT INTO Programare(zi, ora, CNP_medic, CNP_receptioner, CNP_asistent, id_pacient, durata, bon, pret) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setDate(1, zi);
            preparedStatement.setTime(2, ora);
            preparedStatement.setString(3, CNPMedic);
            preparedStatement.setString(4, CNPReceptioner);
            preparedStatement.setString(5, CNPAsistent);
            preparedStatement.setInt(6, idPacient);
            preparedStatement.setInt(7, durata);
            preparedStatement.setString(8, bon);
            preparedStatement.setFloat(9, pret);

            int rowsAffected = preparedStatement.executeUpdate();
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
            try {
                preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM Programare WHERE zi=? AND ora=? AND CNP_medic=? AND CNP_receptioner=? AND CNP_asistent=? AND id_pacient=? AND durata=? AND bon=? AND pret=?");
                preparedStatement.setDate(1, zi);
                preparedStatement.setTime(2, ora);
                preparedStatement.setString(3, CNPMedic);
                preparedStatement.setString(4, CNPReceptioner);
                preparedStatement.setString(5, CNPAsistent);
                preparedStatement.setInt(6, idPacient);
                preparedStatement.setInt(7, durata);
                preparedStatement.setString(8, bon);
                preparedStatement.setFloat(9, pret);
                rs = preparedStatement.executeQuery();
                if (rs.next())
                {
                    programare = new Programare();
                    programare.setIdProgramare(rs.getInt("id"));
                    programare.setZi(rs.getDate("zi"));
                    programare.setOra(rs.getTime("ora"));
                    programare.setCNPMedic(rs.getString("CNP_medic"));
                    programare.setCNPReceptioner(rs.getString("CNP_receptioner"));
                    programare.setCNPAsistent(rs.getString("CNP_asistent"));
                    programare.setIdPacient(rs.getInt("id_pacient"));
                    programare.setDurata(rs.getInt("durata"));
                    programare.setBon(rs.getString("bon"));
                    programare.setPret(rs.getFloat("pret"));
                    System.out.println(programare.toString());
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
            return programare;
        }
    }
    public static ArrayList<Programare> GetProgramaresMedic(String CNPMedic, Date zi) {
        ArrayList<Programare> programares = new ArrayList<>();
        Programare programare = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM Programare WHERE CNP_medic=? AND zi=? ORDER BY ora ASC,durata ASC");
            preparedStatement.setString(1, CNPMedic);
            preparedStatement.setDate(2, zi);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                programare = new Programare();
                programare.setIdProgramare(rs.getInt("id"));
                programare.setZi(rs.getDate("zi"));
                programare.setOra(rs.getTime("ora"));
                programare.setCNPMedic(rs.getString("CNP_medic"));
                programare.setCNPReceptioner(rs.getString("CNP_receptioner"));
                programare.setCNPAsistent(rs.getString("CNP_asistent"));
                programare.setIdPacient(rs.getInt("id_pacient"));
                programare.setDurata(rs.getInt("durata"));
                programare.setBon(rs.getString("bon"));
                programare.setPret(rs.getFloat("pret"));
                programares.add(programare);
                System.out.println(preparedStatement.toString());
                count++;
            }
            System.out.println(count + " Programari gasite!");
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
        return programares;
    }

    public static ArrayList<Programare> GetProgramaresAsistent(String CNPAsistent, Date zi) {
        ArrayList<Programare> programares = new ArrayList<>();
        Programare programare = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM Programare WHERE CNP_asistent=? AND zi=? ORDER BY ora ASC,durata ASC");
            preparedStatement.setString(1, CNPAsistent);
            preparedStatement.setDate(2, zi);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                programare = new Programare();
                programare.setIdProgramare(rs.getInt("id"));
                programare.setZi(rs.getDate("zi"));
                programare.setOra(rs.getTime("ora"));
                programare.setCNPMedic(rs.getString("CNP_medic"));
                programare.setCNPReceptioner(rs.getString("CNP_receptioner"));
                programare.setCNPAsistent(rs.getString("CNP_asistent"));
                programare.setIdPacient(rs.getInt("id_pacient"));
                programare.setDurata(rs.getInt("durata"));
                programare.setBon(rs.getString("bon"));
                programare.setPret(rs.getFloat("pret"));
                programares.add(programare);
                System.out.println(preparedStatement.toString());
                count++;
            }
            System.out.println(count + " Programari gasite!");
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
        return programares;
    }
    public static ArrayList<Time> GetValidTimes(String CNPMedic,String CNPAsistent, Date zi,Time startTime,Time endTime, int durata) {
        ArrayList<Time> times1 = new ArrayList<>();
        ArrayList<Time> times2 = new ArrayList<>();
        ArrayList<Programare> programaresMedic = GetProgramaresMedic(CNPMedic, zi);
        ArrayList<Programare> programaresAsistent = GetProgramaresAsistent(CNPAsistent, zi);
        int duratams = durata * 60000;
        Time endTimeDuration = new Time(endTime.getTime() - duratams);
        int i = 0;
        for (Time time = startTime; time.before(endTimeDuration) || time.equals(endTimeDuration); time = new Time(time.getTime() + 1800000)) {
            if (i < programaresMedic.size()) {
                if (new Time(time.getTime() + duratams).before(programaresMedic.get(i).getOra()) || new Time(time.getTime() + duratams).equals(programaresMedic.get(i).getOra())) {
                    times1.add(time);
                } else {
                    time = new Time(programaresMedic.get(i).getOra().getTime() + programaresMedic.get(i).getDurata() * 60000 - 1800000);
                    i++;
                }
            } else {
                times1.add(time);
            }
        }
        i = 0;
        for (Time time = startTime; time.before(endTimeDuration) || time.equals(endTimeDuration); time = new Time(time.getTime() + 1800000)) {
            if (i < programaresAsistent.size()) {
                if (new Time(time.getTime() + duratams).before(programaresAsistent.get(i).getOra()) || new Time(time.getTime() + duratams).equals(programaresAsistent.get(i).getOra())) {
                    times2.add(time);
                } else {
                    time = new Time(programaresAsistent.get(i).getOra().getTime() + programaresAsistent.get(i).getDurata() * 60000 - 1800000);
                    i++;
                }
            } else {
                times2.add(time);
            }
        }
        ArrayList<Time> times = new ArrayList<>();
        if (times1.size() > times2.size()) {
            for (Time t : times2) {
                if (times1.contains(t))
                    times.add(t);
            }
        } else {
            for (Time t : times1) {
                if (times2.contains(t))
                    times.add(t);
            }
        }
        return times;
    }
    public static ArrayList<Time> GetTimes(int idUnitate, String CNPMedic,String CNPAsistent, Date zi, int durata) {
        ArrayList<Time> times = new ArrayList<>();
        Concediu concediuMedic = Concediu.GetConcediu(CNPMedic, zi);
        Concediu concediuAsistent = Concediu.GetConcediu(CNPAsistent, zi);
        if(concediuMedic != null || concediuAsistent != null) {
            return null;
        }
        OrarGeneric orarGenericUnitate=OrarGeneric.GetOrarGenericUnitate(idUnitate,zi);
        if(orarGenericUnitate == null) {
            return null;
        }

        Time minStart=orarGenericUnitate.getStartTime();
        Time maxEnd=orarGenericUnitate.getEndTime();

        OrarSpecific orarSpecificMedic=OrarSpecific.GetOrarSpecific(CNPMedic, zi);
        OrarSpecific orarSpecificAsistent=OrarSpecific.GetOrarSpecific(CNPAsistent, zi);
        if(orarSpecificMedic != null&&orarSpecificAsistent!=null) {
            if(minStart.before(orarSpecificMedic.getStartTime()))
                minStart=orarSpecificMedic.getStartTime();
            if(minStart.before(orarSpecificAsistent.getStartTime()))
                minStart=orarSpecificAsistent.getStartTime();
            if(maxEnd.after(orarSpecificMedic.getEndTime()))
                maxEnd=orarSpecificMedic.getEndTime();
            if(maxEnd.after(orarSpecificAsistent.getEndTime()))
                maxEnd=orarSpecificAsistent.getEndTime();
            times=GetValidTimes(CNPMedic,CNPAsistent,zi,minStart,maxEnd,durata);
            return times;
        }

        OrarGeneric orarGenericMedic =OrarGeneric.GetOrarGenericUtilizator(CNPMedic,zi);
        OrarGeneric orarGenericAsistent=OrarGeneric.GetOrarGenericUtilizator(CNPAsistent,zi);
        if(orarGenericMedic != null &&orarGenericAsistent!=null) {
            if(minStart.before(orarGenericMedic.getStartTime()))
                minStart=orarGenericMedic.getStartTime();
            if(minStart.before(orarGenericAsistent.getStartTime()))
                minStart=orarGenericAsistent.getStartTime();
            if(maxEnd.after(orarGenericMedic.getEndTime()))
                maxEnd=orarGenericMedic.getEndTime();
            if(maxEnd.after(orarGenericAsistent.getEndTime()))
                maxEnd=orarGenericAsistent.getEndTime();
            times=GetValidTimes(CNPMedic,CNPAsistent,zi,minStart,maxEnd,durata);
            return times;
        }
        return null;
    }

    public static ArrayList<Programare> GetProgramari(String CNP){

        ArrayList<Programare> programari = new ArrayList<>();
        Programare programare = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM Programare where CNP_medic=?");
            preparedStatement.setString(1, CNP);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                programare = new Programare();
                programare.setIdProgramare(rs.getInt("id"));
                programare.setZi(rs.getDate("zi"));
                programare.setOra(rs.getTime("ora"));
                programare.setCNPMedic(rs.getString("CNP_medic"));
                programare.setCNPReceptioner(rs.getString("CNP_receptioner"));
                programare.setCNPAsistent(rs.getString("CNP_asistent"));
                programare.setIdPacient(rs.getInt("id_pacient"));
                programare.setDurata(rs.getInt("durata"));
                programare.setBon(rs.getString("bon"));
                programare.setPret(rs.getFloat("pret"));
                programari.add(programare);
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


        return programari;
    }
}