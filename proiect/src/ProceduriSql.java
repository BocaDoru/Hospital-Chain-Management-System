import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;

public class ProceduriSql {



    public int orepezi(String zi,int hours,ArrayList<Integer> weekdayCounts)
    {
        int nrOre=0;
        if (zi.equals("Luni")) {


            // Get the total hours and add to the count

            // System.out.println(hours);
            nrOre += weekdayCounts.get(0) * (int) hours;
        }

        if (zi.equals("Marti")) {


            // Get the total hours and add to the count

            // System.out.println(hours);
            nrOre += weekdayCounts.get(1) * (int) hours;
        }

        if (zi.equals("Miercuri")) {


            // Get the total hours and add to the count

            //System.out.println(hours);
            nrOre += weekdayCounts.get(2) * (int) hours;
        }

        if (zi.equals("Joi")) {



            // Get the total hours and add to the count

            //System.out.println(hours);
            nrOre += weekdayCounts.get(3) * (int) hours;
        }

        if (zi.equals("Vineri")) {

            //System.out.println(hours);
            nrOre += weekdayCounts.get(4) * (int) hours;
        }
        return nrOre;
    }
    public double calcTotal(ArrayList<Integer> countDaysOfWeek,String luna){
        double total = 0;
        double profit =0;
        ArrayList<Medical> angajati=Medical.GetMedicals();



        for(Medical angajat:angajati){
            ArrayList<Medical> medicals = new ArrayList<>();
            Medical medical = null;
            ResultSet rs = null;
            int nrOre=0;
            PreparedStatement preparedStatement = null;
            if(angajat.getFunctie().equals("Medic")){
                profit+=callCalculProfitMedic(angajat.getCNP(),luna);
            }
            try {
                String sql = "{CALL oreLucrate(?)}";
                preparedStatement = Conexiune.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, angajat.getCNP());


                rs = preparedStatement.executeQuery();
                int count=0;
                while (rs.next())
                {

                    String zi = rs.getString(1);
                    int ore = rs.getInt(2);
                    nrOre=orepezi(zi,ore,countDaysOfWeek);
                    total+=nrOre*angajat.getSalariu();
                    // System.out.println("Profit per Medic (" + luna + "): " + profitMedic);
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

        }


        System.out.println(profit+"-"+ total);
        return profit-total;
    }

    public ArrayList<String> getSpecializari(){

        ArrayList<String> specializari = new ArrayList<>();

        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT DISTINCT specialitate FROM Medic;";
            preparedStatement = Conexiune.getConnection().prepareStatement(sql);


            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                String specializare = rs.getString(1);
                specializari.add(specializare);

                // System.out.println("Profit per Medic (" + luna + "): " + profitMedic);
            }return specializari;

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
    // Method to execute the "CalculProfitUnitate" procedure with two parameters: idUnitate and luna
    public double callCalculProfitUnitate(int idUnitate, String luna) {
        ArrayList<Medical> medicals = new ArrayList<>();
        Medical medical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "{CALL CalculProfitUnitate1(?, ?)}";
            preparedStatement = Conexiune.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idUnitate);
            preparedStatement.setString(2, luna);

            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                double profitUnitate = rs.getDouble("ProfitTotal");
                return profitUnitate;
                // System.out.println("Profit per Medic (" + luna + "): " + profitMedic);
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
        return 0;
    }
    public double callSalariMedic(String CNP, String luna) {
        ArrayList<Medical> medicals = new ArrayList<>();
        Medical medical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "{CALL CalculSalarMedici(?, ?)}";
            preparedStatement = Conexiune.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, CNP);
            preparedStatement.setString(2, luna);

            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                double profitUnitate = rs.getDouble(1);

                System.out.println(profitUnitate);return profitUnitate;
                // System.out.println("Profit per Medic (" + luna + "): " + profitMedic);
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
        return 0;
    }
    public double callDeleteUser(String CNP ) {
        ArrayList<Medical> medicals = new ArrayList<>();
        Medical medical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "{CALL delete_utilizator_cascade(?)}";
            preparedStatement = Conexiune.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, CNP);


            rs = preparedStatement.executeQuery();
            int count=0;


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
        return 0;
    }
    public double callEditUser(String CNP ,String nume, String prenume,String adresa,String numar_telefon,String email,String cont_IBAN,String numar_contract,String data_angajarii,String functie) {
        ArrayList<Medical> medicals = new ArrayList<>();
        Medical medical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "{CALL Updateutilizator(?,?,?,?,?,?,?,?,?,?)}";
            preparedStatement = Conexiune.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, CNP);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.setString(4, adresa);
            preparedStatement.setString(5, numar_telefon);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, cont_IBAN);
            preparedStatement.setString(8, numar_contract);
            preparedStatement.setString(9, data_angajarii);
            preparedStatement.setString(10, functie);


            rs = preparedStatement.executeQuery();
            int count=0;


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
        return 0;
    }
    // Method to execute the "CalculProfitMedic" procedure with two parameters: cnpMedic and luna
    public double callCalculProfitMedic(String cnpMedic, String luna) {
        ArrayList<Medical> medicals = new ArrayList<>();
        Medical medical = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "{CALL CalculProfitMedic1(?, ?)}";
            preparedStatement = Conexiune.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, cnpMedic);
            preparedStatement.setString(2, luna);

            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                double profitMedic = rs.getDouble("ProfitMedic");
                return profitMedic;
                // System.out.println("Profit per Medic (" + luna + "): " + profitMedic);
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
        return 0;
    }

    // Method to execute the "CalculProfitPeSpecialitate" procedure with two parameters: specialitateMedic and luna
    public double callCalculProfitPeSpecialitate(String specialitateMedic, String luna) {

        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            String sql = "{CALL CalculProfitPeSpecialitate(?, ?)}";
            preparedStatement = Conexiune.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, specialitateMedic);
            preparedStatement.setString(2, luna);

            rs = preparedStatement.executeQuery();
            int count=0;
            while (rs.next())
            {
                double profitSpecialitate = rs.getDouble("ProfitTotal");
                return profitSpecialitate;
                // System.out.println("Profit per Medic (" + luna + "): " + profitMedic);
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
        return 0;
    }
}
