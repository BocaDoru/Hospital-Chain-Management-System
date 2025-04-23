import java.sql.*;
import java.util.ArrayList;

public class RaportMedical {
    private int idRaportMedical;
    private int idPacient;
    private String CNPMedic;
    private String CNPMedicRecomandare;
    private String CNPAsistent;
    private Date zi;
    private String simptome;
    private String investigatii;
    private String diagnostic;
    private String recomandari;
    private int rezultat;
    private String codParafa;

    public RaportMedical(int idRaportMedical, int idPacient, String CNPMedic, String CNPAsistent, String CNPMedicRecomandare, Date zi, String simptome, String investigatii, String recomandari, String diagnostic, int rezultat, String codParafa) {
        this.idRaportMedical = idRaportMedical;
        this.idPacient = idPacient;
        this.CNPMedic = CNPMedic;
        this.CNPAsistent = CNPAsistent;
        this.CNPMedicRecomandare = CNPMedicRecomandare;
        this.zi = zi;
        this.simptome = simptome;
        this.investigatii = investigatii;
        this.recomandari = recomandari;
        this.diagnostic = diagnostic;
        this.rezultat = rezultat;
        this.codParafa = codParafa;
    }

    public RaportMedical() {
    }

    public int getIdRaportMedical() {
        return idRaportMedical;
    }

    public void setIdRaportMedical(int idRaportMedical) {
        this.idRaportMedical = idRaportMedical;
    }

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public String getCNPMedicRecomandare() {
        return CNPMedicRecomandare;
    }

    public void setCNPMedicRecomandare(String CNPMedicRecomandare) {
        this.CNPMedicRecomandare = CNPMedicRecomandare;
    }

    public String getCNPMedic() {
        return CNPMedic;
    }

    public void setCNPMedic(String CNPMedic) {
        this.CNPMedic = CNPMedic;
    }

    public String getCNPAsistent() {
        return CNPAsistent;
    }

    public void setCNPAsistent(String CNPAsistent) {
        this.CNPAsistent = CNPAsistent;
    }

    public Date getZi() {
        return zi;
    }

    public void setZi(Date zi) {
        this.zi = zi;
    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }

    public String getInvestigatii() {
        return investigatii;
    }

    public void setInvestigatii(String investigatii) {
        this.investigatii = investigatii;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getRecomandari() {
        return recomandari;
    }

    public void setRecomandari(String recomandari) {
        this.recomandari = recomandari;
    }

    public int getRezultat() {
        return rezultat;
    }

    public void setRezultat(int rezultat) {
        this.rezultat = rezultat;
    }

    public String getCodParafa() {
        return codParafa;
    }

    public void setCodParafa(String codParafa) {
        this.codParafa = codParafa;
    }

    @Override
    public String toString() {
        return "('" + idRaportMedical +
                "','" + idPacient +
                "','" + CNPMedic +
                "','" + CNPMedicRecomandare +
                "','" + CNPAsistent +
                "','" + zi +
                "','" + simptome +
                "','" + investigatii +
                "','" + diagnostic +
                "','" + recomandari +
                "','" + rezultat +
                "','" + codParafa +
                "')";
    }

    public static RaportMedical AddRaportMedical(Date zi, String CNPMedic, String CNPAsistent, int idPacient) {
        RaportMedical raportMedical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("INSERT INTO RaportMedical(zi, CNP_medic, CNP_asistent, id_pacient) VALUES (?, ?, ?, ?)");
            preparedStatement.setDate(1, zi);
            preparedStatement.setString(2, CNPMedic);
            preparedStatement.setString(3, CNPAsistent);
            preparedStatement.setInt(4, idPacient);
            preparedStatement.executeUpdate();
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
                preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM RaportMedical WHERE zi=? AND CNP_medic=? AND CNP_asistent=? AND id_pacient=?");
                preparedStatement.setDate(1, zi);
                preparedStatement.setString(2, CNPMedic);
                preparedStatement.setString(3, CNPAsistent);
                preparedStatement.setInt(4, idPacient);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    raportMedical = new RaportMedical();
                    raportMedical.setIdRaportMedical(rs.getInt("id"));
                    raportMedical.setZi(rs.getDate("zi"));
                    raportMedical.setCNPMedic(rs.getString("CNP_medic"));
                    raportMedical.setCNPAsistent(rs.getString("CNP_asistent"));
                    raportMedical.setIdPacient(rs.getInt("id_pacient"));
                    System.out.println(raportMedical.toString());
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
            return raportMedical;
        }
    }

    public static ArrayList<RaportMedical> GetRaportMedicalAsistent(String CNPAsistent, int idPacient) {
        ArrayList<RaportMedical> raportMedicals = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM RaportMedical WHERE RaportMedical.CNP_asistent=? AND RaportMedical.id_pacient=? AND RaportMedical.investigatii IS NULL");
            preparedStatement.setString(1, CNPAsistent);
            preparedStatement.setInt(2, idPacient);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RaportMedical raportMedical = new RaportMedical();
                raportMedical.setIdRaportMedical(rs.getInt("id"));
                raportMedical.setIdPacient(rs.getInt("id_pacient"));
                raportMedical.setCNPMedic(rs.getString("CNP_medic"));
                raportMedical.setCNPAsistent(rs.getString("CNP_medic_recomandare"));
                raportMedical.setCNPAsistent(rs.getString("CNP_asistent"));
                raportMedical.setZi(rs.getDate("zi"));
                raportMedical.setSimptome(rs.getString("simptome"));
                raportMedical.setInvestigatii(rs.getString("investigatii"));
                raportMedical.setDiagnostic(rs.getString("diagnostic"));
                raportMedical.setRecomandari(rs.getString("recomandari"));
                raportMedical.setRezultat(rs.getInt("rezultat"));
                raportMedical.setCodParafa(rs.getString("cod_parafa"));
                System.out.println(raportMedical.toString());
                raportMedicals.add(raportMedical);
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
        return raportMedicals;
    }

    public static ArrayList<RaportMedical> GetRaportMedicalMedic(String CNPMedic,int idPacient) {
        ArrayList<RaportMedical> raportMedicals = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM RaportMedical WHERE RaportMedical.CNP_medic=? AND RaportMedical.id_pacient=? AND RaportMedical.cod_parafa IS NULL");
            preparedStatement.setString(1, CNPMedic);
            preparedStatement.setInt(2, idPacient);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RaportMedical raportMedical = new RaportMedical();
                raportMedical.setIdRaportMedical(rs.getInt("id"));
                raportMedical.setIdPacient(rs.getInt("id_pacient"));
                raportMedical.setCNPMedic(rs.getString("CNP_medic"));
                raportMedical.setCNPAsistent(rs.getString("CNP_medic_recomandare"));
                raportMedical.setCNPAsistent(rs.getString("CNP_asistent"));
                raportMedical.setZi(rs.getDate("zi"));
                raportMedical.setSimptome(rs.getString("simptome"));
                raportMedical.setInvestigatii(rs.getString("investigatii"));
                raportMedical.setDiagnostic(rs.getString("diagnostic"));
                raportMedical.setRecomandari(rs.getString("recomandari"));
                raportMedical.setRezultat(rs.getInt("rezultat"));
                raportMedical.setCodParafa(rs.getString("cod_parafa"));
                System.out.println(raportMedical.toString());
                raportMedicals.add(raportMedical);
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
        return raportMedicals;
    }


    public static ArrayList<RaportMedical> GetRaportMedicalPacient(int idPacient) {
        ArrayList<RaportMedical> raportMedicals = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT * FROM RaportMedical WHERE RaportMedical.id_pacient=? AND RaportMedical.cod_parafa IS NOT NULL");
            preparedStatement.setInt(1, idPacient);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RaportMedical raportMedical = new RaportMedical();
                raportMedical.setIdRaportMedical(rs.getInt("id"));
                raportMedical.setIdPacient(rs.getInt("id_pacient"));
                raportMedical.setCNPMedic(rs.getString("CNP_medic"));
                raportMedical.setCNPMedicRecomandare(rs.getString("CNP_medic_recomandare"));
                raportMedical.setCNPAsistent(rs.getString("CNP_asistent"));
                raportMedical.setZi(rs.getDate("zi"));
                raportMedical.setSimptome(rs.getString("simptome"));
                raportMedical.setInvestigatii(rs.getString("investigatii"));
                raportMedical.setDiagnostic(rs.getString("diagnostic"));
                raportMedical.setRecomandari(rs.getString("recomandari"));
                raportMedical.setRezultat(rs.getInt("rezultat"));
                raportMedical.setCodParafa(rs.getString("cod_parafa"));
                System.out.println(raportMedical.toString());
                raportMedicals.add(raportMedical);
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
        return raportMedicals;
    }

    public static boolean SetInvestigatii(int idRaport,String investigatii) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("UPDATE RaportMedical SET investigatii=? WHERE id=?");
            preparedStatement.setString(1, investigatii);
            preparedStatement.setInt(2, idRaport);
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

    public static boolean SetRaport(int idRaport,String CNPMedicRecomandare,String simptome,String diagnostic,String recomandari,int rezultat,String cod_parafa) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("UPDATE RaportMedical SET CNP_Medic_Recomandare=?,simptome=?,diagnostic=?,recomandari=?,rezultat=?,cod_parafa=? WHERE id=?");
            preparedStatement.setString(1, CNPMedicRecomandare);
            preparedStatement.setString(2, simptome);
            preparedStatement.setString(3, diagnostic);
            preparedStatement.setString(4, recomandari);
            preparedStatement.setInt(5, rezultat);
            preparedStatement.setString(6, cod_parafa);
            preparedStatement.setInt(7, idRaport);
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
}
