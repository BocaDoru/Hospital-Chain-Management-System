import java.sql.*;
import java.util.ArrayList;

public class ServiciuMedical {
    private int idServiciu;
    private String denumire;
    private int necesitaCompetenta;
    private float pret;
    private int durata;

    public ServiciuMedical(int idServiciu, String denumire, int necesitaCompetenta, float pret, int durata) {
        this.idServiciu = idServiciu;
        this.denumire = denumire;
        this.necesitaCompetenta = necesitaCompetenta;
        this.pret = pret;
        this.durata = durata;
    }

    public ServiciuMedical() {
    }

    public int getIdServiciu() {
        return idServiciu;
    }

    public void setIdServiciu(int idServiciu) {
        this.idServiciu = idServiciu;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNecesitaCompetenta() {
        return necesitaCompetenta;
    }

    public void setNecesitaCompetenta(int necesitaCompetenta) {
        this.necesitaCompetenta = necesitaCompetenta;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "('" + idServiciu +
                "','" + denumire +
                "','" + necesitaCompetenta +
                "','" + pret +
                "','" + durata +
                "')";
    }
    public static boolean AddServiciuProgramare(int idProgramare, ArrayList<ServiciuMedical> listaServiciu) {
        int rowsAffected=0;
        for (ServiciuMedical serviciuMedical : listaServiciu) {
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            ResultSetMetaData rsmd = null;
            try {
                preparedStatement = Conexiune.getConnection().prepareStatement("INSERT INTO Serviciu_Programare VALUES (?, ?)");
                preparedStatement.setInt(1, idProgramare);
                preparedStatement.setInt(2, serviciuMedical.getIdServiciu());
                rowsAffected+= preparedStatement.executeUpdate();
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
        }
        return rowsAffected==listaServiciu.size();
    }

    public static boolean AddServiciuRaport(int idRaport,ArrayList<ServiciuMedical> listaServiciu) {
        int rowsAffected=0;
        for (ServiciuMedical serviciuMedical : listaServiciu) {
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            ResultSetMetaData rsmd = null;
            try {
                preparedStatement = Conexiune.getConnection().prepareStatement("INSERT INTO Serviciu_Raport VALUES (?, ?)");
                preparedStatement.setInt(1, idRaport);
                preparedStatement.setInt(2, serviciuMedical.getIdServiciu());
                rowsAffected+= preparedStatement.executeUpdate();
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
        }
        return rowsAffected==listaServiciu.size();
    }

    public static void RemoveServiciuProgramare(int idProgramare) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("DELETE FROM Serviciu_Programare WHERE id_programare=?");
            preparedStatement.setInt(1, idProgramare);
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
        }
    }

    public static void RemoveServiciuRaport(int idRaport) {
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ResultSetMetaData rsmd = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("DELETE FROM Serviciu_Raport WHERE id_raport=?");
            preparedStatement.setInt(1, idRaport);
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
        }
    }

    public static ArrayList<ServiciuMedical> GetServiciuRaport(int idRaport) {
        ArrayList<ServiciuMedical> listaServicii = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement preparedStatement = null;
            ResultSetMetaData rsmd = null;
            try {
                preparedStatement = Conexiune.getConnection().prepareStatement("SELECT ServiciuMedical.id,denumire,necesita_competenta,pret,durata FROM ServiciuMedical JOIN Serviciu_Raport ON ServiciuMedical.id=Serviciu_Raport.id_serviciu WHERE Serviciu_Raport.id_raport=?");
                preparedStatement.setInt(1, idRaport);
                rs =preparedStatement.executeQuery();
                while (rs.next()) {
                    ServiciuMedical serviciuMedical = new ServiciuMedical();
                    serviciuMedical.setIdServiciu(rs.getInt("id"));
                    serviciuMedical.setDenumire(rs.getString("denumire"));
                    serviciuMedical.setNecesitaCompetenta(rs.getInt("necesita_competenta"));
                    serviciuMedical.setPret(rs.getFloat("pret"));
                    serviciuMedical.setDurata(rs.getInt("durata"));
                    listaServicii.add(serviciuMedical);
                    System.out.println(serviciuMedical);
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
        return listaServicii;
    }

    public static ArrayList<ServiciuMedical> GetServiciiMedicUnitate(int idUnitate,String CNPMedic)
    {
        ArrayList<ServiciuMedical> serviciuMedicals = new ArrayList<>();
        ServiciuMedical serviciuMedical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT ServiciuMedical.id,ServiciuMedical.denumire,necesita_competenta,pret,durata FROM Competenta JOIN Medic_Competenta ON Competenta.id=Medic_Competenta.id_competenta JOIN Medic ON Medic_Competenta.id_medic=Medic.CNP JOIN UnitateMedicala_Medic ON Medic.CNP=UnitateMedicala_Medic.CNP_medic JOIN UnitateMedicala ON UnitateMedicala_Medic.id_unitate=UnitateMedicala.id JOIN CabinetMedical ON UnitateMedicala.id=CabinetMedical.id_unitate_medicala JOIN CabinetMedical_ServiciuMedical ON CabinetMedical.id=CabinetMedical_ServiciuMedical.id_cabinet_medical JOIN ServiciuMedical ON CabinetMedical_ServiciuMedical.id_serviciu_medical=ServiciuMedical.id WHERE Medic.CNP=? AND UnitateMedicala.id=? AND ServiciuMedical.necesita_competenta=1 AND ServiciuMedical.denumire=Competenta.denumire");
            preparedStatement.setString(1, CNPMedic);
            preparedStatement.setInt(2, idUnitate);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                serviciuMedical = new ServiciuMedical();
                serviciuMedical.setIdServiciu(rs.getInt("id"));
                serviciuMedical.setDenumire(rs.getString("denumire"));
                serviciuMedical.setNecesitaCompetenta(rs.getInt("necesita_competenta"));
                serviciuMedical.setPret(rs.getFloat("pret"));
                serviciuMedical.setDurata(rs.getInt("durata"));
                serviciuMedicals.add(serviciuMedical);
                System.out.println(serviciuMedical.toString());
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
        try {
            preparedStatement = Conexiune.getConnection().prepareStatement("SELECT ServiciuMedical.id,ServiciuMedical.denumire,necesita_competenta,pret,durata FROM UnitateMedicala JOIN CabinetMedical ON UnitateMedicala.id=CabinetMedical.id_unitate_medicala JOIN CabinetMedical_ServiciuMedical ON CabinetMedical.id=CabinetMedical_ServiciuMedical.id_cabinet_medical JOIN ServiciuMedical ON CabinetMedical_ServiciuMedical.id_serviciu_medical=ServiciuMedical.id WHERE UnitateMedicala.id=? AND ServiciuMedical.necesita_competenta=0");
            preparedStatement.setInt(1, idUnitate);
            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                serviciuMedical = new ServiciuMedical();
                serviciuMedical.setIdServiciu(rs.getInt("id"));
                serviciuMedical.setDenumire(rs.getString("denumire"));
                serviciuMedical.setNecesitaCompetenta(rs.getInt("necesita_competenta"));
                serviciuMedical.setPret(rs.getFloat("pret"));
                serviciuMedical.setDurata(rs.getInt("durata"));
                serviciuMedicals.add(serviciuMedical);
                System.out.println(serviciuMedical.toString());
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
        return serviciuMedicals;
    }
}
